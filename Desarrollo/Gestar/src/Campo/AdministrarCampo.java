package Campo;

import Conexion.Coneccion;
import Datos.CampoEntity;
import Datos.LoteEntity;
import Lote.CargaLote;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Iterator;


public class AdministrarCampo extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnNuevo;
    private JButton btnEditar;
    private JTable tblLote;
    private JButton btnCancelar;

    private JTable table1;
    private CampoEntity campo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public AdministrarCampo() {


        //INICIO
        JPanel container = new JPanel();
//        container.setPreferredSize(new Dimension(1920, 1900));
//        panel1.setPreferredSize(new Dimension(1900, 1800));
        container.add(panel1);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        jsp.setBounds(50, 30, 900, 900);
//        this.add(jsp);
        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Campo");
        inicializaTabla();


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Coneccion.getSession();
            buscarCampos();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
            inicializaTabla();
            txtBuscar.setText("");
        });


        //EDITAR
        btnEditar.addActionListener(e -> {
            int fila = tblLote.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            int lotId = (int) tblLote.getModel().getValueAt(fila, 0);
            String nombre = (String) tblLote.getModel().getValueAt(fila, 1);
            Integer metros = (Integer) tblLote.getModel().getValueAt(fila, 3);

            CargaLote carga = new CargaLote("Modificacion", nombre, metros, lotId);
            carga.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();


        });


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente el Campo.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el Campo.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            CargaLote cargaLote = new CargaLote("Carga", "", 0, 0);
            cargaLote.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());

    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Nombre", "Metros"};
        Object[][] data = new Object[1][3];
        setModel(columnNames, data, tblLote);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblLote.setModel(model);
        tblLote.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblLote.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblLote.getColumnModel().getColumn(1).setPreferredWidth(450);
        tblLote.getColumnModel().getColumn(2).setPreferredWidth(200);
    }

    private void showMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }


    //METODO DAR BAJA
    public int darBaja() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        try {
            campo = new CampoEntity();
            int fila = tblLote.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            campo.setCpoId((int) tblLote.getModel().getValueAt(fila, 0));
            campo.setCpoNombre((String) tblLote.getModel().getValueAt(fila, 1));
            campo.setCpoCantMetros((int) tblLote.getModel().getValueAt(fila, 4));

            campo.setCpoFechaAlta(fechaActual);
            campo.setCpoUsuarioBaja("adminBajaCampo");
            campo.setCpoFechaUltMod(fechaActual);
            campo.setCpoUsuarioUltMod("adminBajaLCampo");
            campo.setCpoFechaBaja(fechaActual);
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del Campo: " + tblLote.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(campo);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja el Campo: " + e.toString());
            return 2;
        } finally {
            session.close();
        }

        return 0;
    }


    //METODO BUSCAR campos
    public void buscarCampos() {
        Session session = Coneccion.getSession();
        int i = 0;
        try {
            campo = new CampoEntity();
            Query query = session.createQuery("select t from CampoEntity t where ucase(cpoNombre) like ucase(:pNombre) and cpoFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Ubicacion"};
            Object[][] data = new Object[list.size()][8];

            while (iter.hasNext()) {
                campo = (CampoEntity) iter.next();
                data[i][0] = campo.getCpoId();
                data[i][1] = campo.getCpoNombre();
                data[i][2] = campo.getCpoUbicacion();
                i++;
            }
            setModel(columnNames, data, tblLote);
        } finally {
            session.close();
        }

    }
}

