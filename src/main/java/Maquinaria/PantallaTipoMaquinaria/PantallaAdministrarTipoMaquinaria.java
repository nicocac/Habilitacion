package Maquinaria.PantallaTipoMaquinaria;

import Conexion.Conexion;
import Datos.TipoMaquinariaEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Iterator;


public class PantallaAdministrarTipoMaquinaria extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnNuevo;
    private JTable tblTipos;
    private JButton btnCancelar;
    private TipoMaquinariaEntity tipo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public PantallaAdministrarTipoMaquinaria() {


        //INICIO
        setContentPane(panel1);
        pack();
        this.setTitle("Consultar Tipo Maquinaria");
        inicializaTabla();


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession()
            buscarTiposMaquinaria();
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
            int tmaId = (int) tblTipos.getModel().getValueAt(fila, 0);
            String nombre = (String) tblTipos.getModel().getValueAt(fila, 1);
            String descripcion = (String) tblTipos.getModel().getValueAt(fila, 2);
            CargaTipoMaquinaria carga = new CargaTipoMaquinaria("Modificacion", nombre, descripcion, tmaId);
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
                    showMessage("Se dio de baja exitosamente el tipo de maquinaria.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el tipo de maquinaria.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            CargaTipoMaquinaria cargaTipoMaquinaria = new CargaTipoMaquinaria("Carga", "", "", 0);
            cargaTipoMaquinaria.setVisible(true);
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
            tipo = new TipoMaquinariaEntity();
            int fila = tblTipos.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            tipo.setTmaId((int) tblTipos.getModel().getValueAt(fila, 0));
            tipo.setTmaNombre((String) tblTipos.getModel().getValueAt(fila, 1));
            tipo.setTmaDescripcion((String) tblTipos.getModel().getValueAt(fila, 2));
            tipo.setTmaFechaAlta(fechaActual);
            tipo.setTmaUsuarioAlta("adminBAJA");
            tipo.setTmaFechaUltMod(fechaActual);
            tipo.setTmaUsuarioUtlMod("adminBAJA");
            tipo.setTmaFechaBaja(fechaActual);
            tipo.setTmaUsuarioBaja("adminBAJA");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del tipo de maquinaria: " + tblTipos.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(tipo);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja el tipo de maquinaria: " + e.toString());
            return 2;
        } finally {
            session.close();
        }

        return 0;
    }


    //METODO BUSCAR TIPOS
    public void buscarTiposMaquinaria() {
        Session session = Conexion.getSessionFactory().openSession();
        int i = 0;
        try {
            tipo = new TipoMaquinariaEntity();
            Query query = session.createQuery("select t from TipoMaquinariaEntity t where ucase(tmaNombre) like ucase(:pNombre) and tmaFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Descripcion"};
            Object[][] data = new Object[list.size()][3];

            while (iter.hasNext()) {
                tipo = (TipoMaquinariaEntity) iter.next();
                data[i][0] = tipo.getTmaId();
                data[i][1] = tipo.getTmaNombre();
                data[i][2] = tipo.getTmaDescripcion();
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
