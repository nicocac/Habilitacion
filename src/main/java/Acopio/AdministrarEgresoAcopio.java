package Acopio;

import Conexion.Conexion;
import Datos.EgresoAcopioEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.Iterator;

/**
 * Created by jagm on 17/02/2017.
 */
public class AdministrarEgresoAcopio extends JFrame {
    private JTextField txtBuscar;
    private JPanel panel1;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JTable tblTipos;
    private JButton btnEliminar;
    public JPanel panelIni;
    private TipoInsumoEntity tipo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public AdministrarEgresoAcopio() {


        //INICIO
        JPanel container = new JPanel();
//        container.setPreferredSize(new Dimension(1920, 1900));
//        panel1.setPreferredSize(new Dimension(1900, 1800));
        container.add(panelIni);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);
//        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
//        pack();
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        this.setTitle("Consultar Egresos Granos Realizados");
        inicializaTabla();
        buscarEgresos();

        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().getCurrentSession();
            buscarEgresos();
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

            Integer tinId = (Integer) tblTipos.getModel().getValueAt(fila, 0);
            PantallaEgresoAcopio carga = new PantallaEgresoAcopio("Modificacion", tinId);
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
                    showMessage("Se dio de baja exitosamente el tipo de insumo.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el tipo de insumo.");
                    break;
                }
            }

        });


//        //NUEVO
//        btnNuevo.addActionListener(e -> {
//            CargaTipoInsumo cargaTipoInsumo = new CargaTipoInsumo("Carga", "", "", 0);
//            cargaTipoInsumo.setVisible(true);
//            getDefaultCloseOperation();
//            inicializaTabla();
//        });
    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Cliente", "Cantidad", "Fecha"};
        Object[][] data = new Object[1][4];
        setModel(columnNames, data, tblTipos);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblTipos.setModel(model);
        tblTipos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblTipos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblTipos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblTipos.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblTipos.getColumnModel().getColumn(2).setPreferredWidth(100);
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
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja delegreso: " + tblTipos.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(tipo);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja el egreso: " + e.toString());
            return 2;
        } finally {
            ////session.close();
        }

        return 0;
    }


    //METODO BUSCAR TIPOS
    public void buscarEgresos() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        int i = 0;
        try {
            EgresoAcopioEntity egreso;
            Query query = session.createQuery("select t from EgresoAcopioEntity t where (cliente.clienteNombre like ucase(:pNombre) or cliente.clienteCuitCuil like ucase(:pNombre)) and egresoFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Cliente", "Cantidad", "Fecha"};
            Object[][] data = new Object[list.size()][4];

            while (iter.hasNext()) {
                egreso = (EgresoAcopioEntity) iter.next();
                data[i][0] = egreso.getEgresoId();
                if (egreso.getCliente() != null){
                    data[i][1] = egreso.getCliente().getClienteNombre();
                } else {
                    data[i][1] = "Sin Datos";
                }
                data[i][2] = egreso.getEgresoCantidadTotal();
                data[i][3] = egreso.getEgresoFecha();
                i++;
            }
            setModel(columnNames, data, tblTipos);
        } finally {
            tx.rollback();        }

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
