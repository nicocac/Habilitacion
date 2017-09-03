package Acopio.TipoAcopio;

import Conexion.Conexion;
import Datos.TipoAcopioEntity;
import TipoInsumo.CargaTipoInsumo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Iterator;

/**
 * Created by jagm on 16/02/2017.
 */
public class AdministrarTipoAcopio extends JFrame {
    private JTextField txtBuscar;
    private JPanel panel1;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JTable tblTipos;
    private JButton btnEliminar;
    private JButton btnNuevo;
    private TipoAcopioEntity tipo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public AdministrarTipoAcopio() {


        //INICIO
        JPanel container = new JPanel();
//        container.setPreferredSize(new Dimension(1920, 1900));
//        panel1.setPreferredSize(new Dimension(1900, 1800));
        container.add(panel1);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);

//        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Tipo de Acopio");
        inicializaTabla();


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().getCurrentSession();
            buscarTiposAcopio();
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
                    showMessage("Se dio de baja exitosamente el tipo de Acopio.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el tipo de Acopio.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            CargaTipoAcopio cargaTipoInsumo = new CargaTipoAcopio("Carga", "", "", 0);
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
            tipo = new TipoAcopioEntity();
            int fila = tblTipos.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            tipo.setTipoAcopioId((int) tblTipos.getModel().getValueAt(fila, 0));
            tipo.setTipoAcopioNombre((String) tblTipos.getModel().getValueAt(fila, 1));
            tipo.setTipoAcopioDescripcion((String) tblTipos.getModel().getValueAt(fila, 2));
            tipo.setTipoAcopioFechaAlta(fechaActual);
            tipo.setTipoAcopioUsuarioAlta("adminBAJA");
            tipo.setTipoAcopioFechaUltMod(fechaActual);
            tipo.setTipoAcopioUsuarioUtlMod("adminBAJA");
            tipo.setTipoAcopioFechaBaja(fechaActual);
            tipo.setTipoAcopioUsuarioBaja("adminBAJA");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del tipo de Acopio: " + tblTipos.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(tipo);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja el tipo de Acopio: " + e.toString());
            return 2;
        } finally {
            ////session.close();
        }

        return 0;
    }


    //METODO BUSCAR TIPOS
    public void buscarTiposAcopio() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        int i = 0;
        try {
            tipo = new TipoAcopioEntity();
            Query query = session.createQuery("select t from TipoAcopioEntity t where ucase(tipoAcopioNombre) like ucase(:pNombre) and tipoAcopioFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Descripcion"};
            Object[][] data = new Object[list.size()][3];

            while (iter.hasNext()) {
                tipo = (TipoAcopioEntity) iter.next();
                data[i][0] = tipo.getTipoAcopioId();
                data[i][1] = tipo.getTipoAcopioNombre();
                data[i][2] = tipo.getTipoAcopioDescripcion();
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

