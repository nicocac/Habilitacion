package TipoMedioAlmacenamiento;

import Conexion.Conexion;
import Datos.TipoMedioEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Iterator;

/**
 * Created by jagm on 10/10/2016.
 */
public class PantallaAdministrarMedios extends JFrame {
    private JTextField txtBuscar;
    private JPanel panel1;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JTable tblTipos;
    private JButton btnEliminar;
    private JButton btnNuevo;
    private TipoMedioEntity tipo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public PantallaAdministrarMedios() {


        //INICIO
        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Medios de Almacenamiento");
        inicializaTabla();


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession()
            buscarTiposAlmacenamiento();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
            inicializaTabla();
            txtBuscar.setText("");
        });

        //EDITAR
        btnEditar.addActionListener(e -> {
            int fila = tblTipos.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            int tinId = (int) tblTipos.getModel().getValueAt(fila, 0);
            String nombre = (String) tblTipos.getModel().getValueAt(fila, 1);
            String descripcion = (String) tblTipos.getModel().getValueAt(fila, 2);
            CargaMedio carga = new CargaMedio("Modificacion", nombre, descripcion, tinId);
            carga.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente el tipo de Medio de Almacenamiento.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el Medio de Almacenamiento.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            CargaMedio cargaMedio = new CargaMedio("Carga", "", "", 0);
            cargaMedio.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });
    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Nombre", "Descripcion"};
        Object[][] data = new Object[1][3];
        setModel(columnNames, data, tblTipos);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblTipos.setModel(model);
        tblTipos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblTipos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblTipos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblTipos.getColumnModel().getColumn(2).setPreferredWidth(500);
    }

    private void showMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    //METODO DAR BAJA
    public int darBaja() {
        Session session = Conexion.getSessionFactory().openSession();
        Boolean guardado = false;
        try {
            tipo = new TipoMedioEntity();
            int fila = tblTipos.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            tipo.setTiMedioId((int) tblTipos.getModel().getValueAt(fila, 0));
            tipo.setTiMedioNombre((String) tblTipos.getModel().getValueAt(fila, 1));
            tipo.setTiMedioDescripcion((String) tblTipos.getModel().getValueAt(fila, 2));
            tipo.setTiMedioFechaAlta(fechaActual);
            tipo.setTiMedioUsuarioAlta("adminBAJA");
            tipo.setTiMedioFechaUltMod(fechaActual);
            tipo.setTiMedioUsuarioUtlMod("adminBAJA");
            tipo.setTiMedioFechaBaja(fechaActual);
            tipo.setTiMedioUsuarioBaja("adminBAJA");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del tipo de medio de almacenamiento: " + tblTipos.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(tipo);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja el tipo de medio de almacenamiento: " + e.toString());
            return 2;
        } finally {
            session.close();
        }

        return 0;
    }


    //METODO BUSCAR TIPOS
    public void buscarTiposAlmacenamiento() {
        Session session = Conexion.getSessionFactory().openSession();
        int i = 0;
        try {
            tipo = new TipoMedioEntity();
            Query query = session.createQuery("select t from TipoMedioEntity t where ucase(tiMedioNombre) like ucase(:pNombre) and tiMedioFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Descripcion"};
            Object[][] data = new Object[list.size()][3];

            while (iter.hasNext()) {
                tipo = (TipoMedioEntity) iter.next();
                data[i][0] = tipo.getTiMedioId();
                data[i][1] = tipo.getTiMedioNombre();
                data[i][2] = tipo.getTiMedioDescripcion();
                i++;
            }
            setModel(columnNames, data, tblTipos);
        } finally {
            session.close();
        }

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
