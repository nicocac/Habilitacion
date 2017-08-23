package Procesos;

import Conexion.Conexion;
import Datos.SolicitudInsumoEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Iterator;

/**
 * Created by jagm on 09/10/2016.
 */
public class PantallaAdministrarSolicitudInsumos extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnNuevo;
    private JTable tblCompras;
    private JButton btnCancelar;
    private JTable table1;
    private SolicitudInsumoEntity compra;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public PantallaAdministrarSolicitudInsumos() {


        //INICIO
        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Administrar Solicitud de insumos");
        inicializaTabla();


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession()
            buscarNotasCompra();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
            inicializaTabla();
            txtBuscar.setText("");
        });


        //EDITAR
        btnEditar.addActionListener(e -> {
            int fila = tblCompras.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            int cpaId = (int) tblCompras.getModel().getValueAt(fila, 0);
            int cpaCantidadItems = (int) tblCompras.getModel().getValueAt(fila, 1);
            String descripcion = (String) tblCompras.getModel().getValueAt(fila, 2);
            String unidadMedida = (String) tblCompras.getModel().getValueAt(fila, 3);
            TipoInsumoEntity tipoInsumo = (TipoInsumoEntity) tblCompras.getModel().getValueAt(fila, 4);
            String stock = String.valueOf(tblCompras.getModel().getValueAt(fila, 5));

//            CargaInsumo carga = new CargaInsumo("Modificacion", nombre, descripcion, unidadMedida, tipoInsumo, stock, insId);
//            carga.setVisible(true);
//            getDefaultCloseOperation();
//            inicializaTabla();
        });


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente la solicitud de insumos.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja la solicitud de insumos.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            PantallaSolicitudInsumos pantallaSolicitudInsumos = new PantallaSolicitudInsumos(null);
            pantallaSolicitudInsumos.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());

    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Nro Solicitud", "Cantidad Items", "Fecha Solicitud", "Estado", "Nro Remito"};
        Object[][] data = new Object[1][6];
        setModel(columnNames, data, tblCompras);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblCompras.setModel(model);
        tblCompras.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblCompras.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblCompras.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblCompras.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblCompras.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblCompras.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblCompras.getColumnModel().getColumn(5).setPreferredWidth(200);
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
            compra = new SolicitudInsumoEntity();
            int fila = tblCompras.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            compra.setSiId((int) tblCompras.getModel().getValueAt(fila, 0));
            compra.setSiNroSolicitud((int) tblCompras.getModel().getValueAt(fila, 1));
            compra.setSiCantidadItems((int) tblCompras.getModel().getValueAt(fila, 2));
            compra.setSiFechaSolicitud((Date) tblCompras.getModel().getValueAt(fila, 3));
            compra.setSiEstado((String) tblCompras.getModel().getValueAt(fila, 4));
            compra.setSiNroRemito((int) tblCompras.getModel().getValueAt(fila, 5));
            compra.setSiFechaUltMod(fechaActual);
//            compra.setSiUsuarioAlta("adminBajaINSUMO");
            compra.setSiUsuarioBaja("adminBajaSolicitud");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja de la solicitud de insumos: " + tblCompras.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(compra);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja la solicitud de insumos: " + e.toString());
            return 2;
        } finally {
            //session.close();
        }

        return 0;
    }


    //METODO BUSCAR Compras
    public void buscarNotasCompra() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        int i = 0;
        try {
            compra = new SolicitudInsumoEntity();
            Query query = session.createQuery("select t from SolicitudInsumoEntity t where ucase(siId) like ucase(:pNombre) and siFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nro Solicitud", "Cantidad Items", "Fecha Solicitud", "Estado", "Nro Remito"};
            Object[][] data = new Object[list.size()][6];

            while (iter.hasNext()) {
                compra = (SolicitudInsumoEntity) iter.next();
                data[i][0] = compra.getSiId();
                data[i][1] = compra.getSiNroSolicitud();
                data[i][2] = compra.getSiCantidadItems();
                data[i][3] = compra.getSiFechaSolicitud();
                data[i][4] = compra.getSiEstado();
                data[i][5] = compra.getSiNroRemito();
                i++;
            }
            setModel(columnNames, data, tblCompras);
        } finally {
            //session.close();
        }

    }

}

