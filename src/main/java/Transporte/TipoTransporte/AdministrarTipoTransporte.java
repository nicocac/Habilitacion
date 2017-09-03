package Transporte.TipoTransporte;

import Conexion.Conexion;
import Datos.TipoInsumoEntity;
import TipoInsumo.CargaTipoInsumo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Iterator;

/**
 * Created by jagm on 14/02/2017.
 */
public class AdministrarTipoTransporte extends JFrame {
    private JTextField txtBuscar;
    private JPanel panel1;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JTable tblTipos;
    private JButton btnEliminar;
    private JButton btnNuevo;
    public JPanel panelIni;
    private TipoInsumoEntity tipo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public AdministrarTipoTransporte() {


        //INICIO
        setContentPane(panelIni);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Tipo de Transporte");
        inicializaTabla();
//        this.setPreferredSize(this.getParen/t().getPreferredSize());
        this.pack();

        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession()
            buscarTiposInsumo();
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
            CargaTipoInsumo carga = new CargaTipoInsumo("Modificacion", nombre, descripcion, tinId);
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
                    showMessage("Se dio de baja exitosamente el tipo de Transporte.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el tipo de Transporte.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            CargaTipoInsumo cargaTipoInsumo = new CargaTipoInsumo("Carga", "", "", 0);
            cargaTipoInsumo.setVisible(true);
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
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        Boolean guardado = false;
        try {
            tipo = new TipoInsumoEntity();
            int fila = tblTipos.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            tipo.setTinId((int) tblTipos.getModel().getValueAt(fila, 0));
            tipo.setTinNombre((String) tblTipos.getModel().getValueAt(fila, 1));
            tipo.setTinDescripcion((String) tblTipos.getModel().getValueAt(fila, 2));
            tipo.setTinFechaAlta(fechaActual);
            tipo.setTinUsuarioAlta("adminBAJA");
            tipo.setTinFechaUltMod(fechaActual);
            tipo.setTinUsuarioUtlMod("adminBAJA");
            tipo.setTinFechaBaja(fechaActual);
            tipo.setTinUsuarioBaja("adminBAJA");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del tipo de Transporte: " + tblTipos.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(tipo);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja el tipo de Transporte: " + e.toString());
            return 2;
        } finally {
            //session.close();
        }

        return 0;
    }


    //METODO BUSCAR TIPOS
    public void buscarTiposInsumo() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        int i = 0;
        try {
            tipo = new TipoInsumoEntity();
            Query query = session.createQuery("select t from TipoInsumoEntity t where ucase(tinNombre) like ucase(:pNombre) and tinFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Descripcion"};
            Object[][] data = new Object[list.size()][3];

            while (iter.hasNext()) {
                tipo = (TipoInsumoEntity) iter.next();
                data[i][0] = tipo.getTinId();
                data[i][1] = tipo.getTinNombre();
                data[i][2] = tipo.getTinDescripcion();
                i++;
            }
            setModel(columnNames, data, tblTipos);
        } finally {
            tx.rollback();
        }

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
