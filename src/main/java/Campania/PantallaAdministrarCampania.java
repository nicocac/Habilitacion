package Campania;

import Conexion.Conexion;
import Datos.*;
import Repository.CampaniaRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Created by jagm on 05/09/2016.
 */
public class PantallaAdministrarCampania extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnNuevo;
    private JButton btnEditar;
    private JTable tblCampania;
    private JButton btnCancelar;
    public JButton btnRegistrarAvance;

    private JTable table1;
    private CampaniaEntity campania;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());
    CampaniaRepository campaniaRepository = new CampaniaRepository();

    public PantallaAdministrarCampania() {


        //INICIO
        JPanel container = new JPanel();
//        container.setPreferredSize(new Dimension(1920, 1900));
//        panel1.setPreferredSize(new Dimension(1900, 1800));
        container.add(panel1);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);
//        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Campania");
        inicializaTabla();
        buscarCampanias();

        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession();
            buscarCampanias();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
            inicializaTabla();
            txtBuscar.setText("");
        });


        //EDITAR
        btnEditar.addActionListener(e -> {
            int fila = tblCampania.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            int camId = (int) tblCampania.getModel().getValueAt(fila, 0);
            String denominacion = (String) tblCampania.getModel().getValueAt(fila, 1);
            Date fechaInicio = (Date) tblCampania.getModel().getValueAt(fila, 2);
            Date fechaFinEstimada = (Date) tblCampania.getModel().getValueAt(fila, 3);
            Date fechaFinReal = (Date) tblCampania.getModel().getValueAt(fila, 4);

            CargaCampania carga = new CargaCampania("", camId, denominacion, fechaInicio, fechaFinEstimada, fechaFinReal);
            carga.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();


        });


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente la campania.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja la campania.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            CargaCampania cargaCampania = new CargaCampania("Carga",0, "", null, null, null);

            cargaCampania.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());

        //REGISTRAR AVANCE
        btnRegistrarAvance.addActionListener(e -> {

            int fila = tblCampania.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }

            java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTimeInMillis());


            int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea Finalizar la Campaa ?", "Advertencia", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {

                int camId = (int) tblCampania.getModel().getValueAt(fila, 0);

                Session session = Conexion.getSessionFactory().openSession();
                Transaction tx = session.beginTransaction();

                CampaniaEntity campaniaEntity = campaniaRepository.getCampaniaById(camId);
                campaniaEntity.setCnaFechaUltMod(fecha);
                campaniaEntity.setCnaUsuarioUltMod("Admin que Finaliza la orden");
                campaniaEntity.setEstado("CANCELADA");

                session.update(campaniaEntity);

                try {
                    tx.commit();
                    JOptionPane.showMessageDialog(null, "La Campa�a fue cancelada en forma definitiva con exito.");
                    session.flush();
//                        session.close();
                    dispose();


                } catch (Exception ex) {
                    tx.rollback();
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al finalizar la Campaa : " + ex.toString());
                } finally {
                    session.close();
                }
            } else {
                return;
            }
        });

    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Nombre", "Estado", "Fecha Inicio", "Fecha Fin Real", "Cantidad de lotes"};
        Object[][] data = new Object[1][3];
        setModel(columnNames, data, tblCampania);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblCampania.setModel(model);
        tblCampania.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblCampania.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblCampania.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblCampania.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblCampania.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblCampania.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblCampania.getColumnModel().getColumn(5).setPreferredWidth(100);
    }

    private void showMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }


//    //METODO DAR BAJA
    public int darBaja() {
        Session session = Conexion.getSessionFactory().openSession();
        Boolean guardado = false;
        try {
            campania = new CampaniaEntity();
            int fila = tblCampania.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            campania.setCnaId((int) tblCampania.getModel().getValueAt(fila, 0));
            campania.setCnaDenominacion((String) tblCampania.getModel().getValueAt(fila, 1));
            campania.setCnaFechaInicio((Date) tblCampania.getModel().getValueAt(fila, 2));
            campania.setCnaFechaFinEstimada((Date) tblCampania.getModel().getValueAt(fila, 3));
            campania.setCnaFechaFinReal((Date) tblCampania.getModel().getValueAt(fila, 4));
//            campania.set((String) tblCampania.getModel().getValueAt(fila, 5));

            campania.setCnaFechaAlta(fechaActual);
            campania.setCnaUsuarioBaja("adminBajaMAQUINARIA");
            campania.setCnaFechaUltMod(fechaActual);
            campania.setCnaUsuarioUltMod("adminBajaMAQUINARIA");
            campania.setCnaFechaBaja(fechaActual);
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja de la campania: " + tblCampania.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(campania);
                tx.commit();
//                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja la campania: " + e.toString());
            return 2;
        } finally {
            session.close();
        }
        return 0;
    }



    //METODO BUSCAR Campanias
    public void buscarCampanias() {
        Session session = Conexion.getSessionFactory().openSession();
        int i = 0;
        try {
            campania = new CampaniaEntity();
            Query query = session.createQuery("select t from CampaniaEntity t where ucase(cnaDenominacion) like ucase(:pNombre) and cnaFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Estado", "Fecha Inicio", "Fecha Fin Real", "Cantidad de lotes"};
            Object[][] data = new Object[list.size()][6];
            while (iter.hasNext()) {
                campania = (CampaniaEntity) iter.next();
                data[i][0] = campania.getCnaId();
                data[i][1] = campania.getCnaDenominacion();
                data[i][2] = campania.getEstado();
                data[i][3] = campania.getCnaFechaInicio();
                data[i][4] = campania.getCnaFechaFinReal();
                data[i][5] = campania.getLoteCampaniasByCnaId().size();
                i++;
            }
            setModel(columnNames, data, tblCampania);
        } finally {
            session.close();
        }

    }
//}

}
