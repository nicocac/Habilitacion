package Transporte;

import Conexion.Conexion;
import Datos.TipoTransporteEntity;
import Datos.TransporteEntity;
import Repository.TransporteRepository;
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
public class AdministrarTransporte extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnNuevo;
    private JTable tblTransportes;
    private JButton btnCancelar;
    private JTable table1;
    private TransporteEntity transporte;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    TransporteRepository transporteRepository = new TransporteRepository();

    public AdministrarTransporte() {


        //INICIO
        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Transportes");
        inicializaTabla();


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession()
            buscarTransportes();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
            inicializaTabla();
            txtBuscar.setText("");
        });


        //EDITAR
        btnEditar.addActionListener(e -> {
            int fila = tblTransportes.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            int insId = (int) tblTransportes.getModel().getValueAt(fila, 0);
            String nombre = (String) tblTransportes.getModel().getValueAt(fila, 1);
            String descripcion = (String) tblTransportes.getModel().getValueAt(fila, 2);
            TipoTransporteEntity tipoTransporte = (TipoTransporteEntity) tblTransportes.getModel().getValueAt(fila, 3);

            CargaTransporte carga = new CargaTransporte("Modificacion", nombre, descripcion, tipoTransporte, insId);
            carga.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente el Transporte.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el Transporte.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            CargaTransporte cargaTransporte = new CargaTransporte("Carga", "", "", null, 0);
            cargaTransporte.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());

    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Nombre", "Descripcion", "Tipo Transporte"};
        Object[][] data = new Object[1][3];
        setModel(columnNames, data, tblTransportes);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblTransportes.setModel(model);
        tblTransportes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblTransportes.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblTransportes.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblTransportes.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblTransportes.getColumnModel().getColumn(3).setPreferredWidth(150);
    }

    private void showMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }


    //METODO DAR BAJA
    public int darBaja() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        Boolean guardado = false;
        try {
            transporte = new TransporteEntity();
            int fila = tblTransportes.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }

            transporte = transporteRepository.getTransporteById((Long) tblTransportes.getModel().getValueAt(fila, 0));

//            transporte.setTransporteId((int) tblTransportes.getModel().getValueAt(fila, 0));
//            transporte.setTransporteNombre((String) tblTransportes.getModel().getValueAt(fila, 1));
//            transporte.setTransporteDescripcion((String) tblTransportes.getModel().getValueAt(fila, 2));
//            transporte.setTipoTransporteEntity((TipoTransporteEntity) tblTransportes.getModel().getValueAt(fila, 3));
//            transporte.setTransporteFechaAlta(fechaActual);
//            transporte.setTransporteUsuarioAlta("adminBajaTransporte");
            transporte.setTransporteFechaUltMod(fechaActual);
            transporte.setTransporteUsuarioUltMod("adminBajaTransporte");
            transporte.setTransporteFechaBaja(fechaActual);
            transporte.setTransporteUsuarioBaja("adminBajaTransporte");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del Transporte: " + tblTransportes.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(transporte);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Error al dar de baja el Transporte: " + e.toString());
            return 2;
        } finally {
            //session.close();
        }

        return 0;
    }


    //METODO BUSCAR CLIENTES
    public void buscarTransportes() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        int i = 0;
        try {
            transporte = new TransporteEntity();
            Query query = session.createQuery("select t from TransporteEntity t where ucase(transporteNombre) like ucase(:pNombre) and transporteFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Descripcion", "Tipo Transporte"};
            Object[][] data = new Object[list.size()][4];

            while (iter.hasNext()) {
                transporte = (TransporteEntity) iter.next();
                data[i][0] = transporte.getTransporteId();
                data[i][1] = transporte.getTransporteNombre();
                data[i][2] = transporte.getTransporteDescripcion();
                if(transporte.getTipoTransporteEntity() == null){
                    data[i][3] = null;
                } else {
                    data[i][3] = transporte.getTipoTransporteEntity().getTinNombre();
                }
                i++;
            }
            setModel(columnNames, data, tblTransportes);
        } finally {
            tx.rollback();
        }

    }

}