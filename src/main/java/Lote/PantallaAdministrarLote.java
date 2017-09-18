package Lote;

import Conexion.Conexion;
import Datos.LoteEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Iterator;

/**
 * Created by jagm on 8/22/2016.
 */
public class PantallaAdministrarLote extends JFrame {
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
    private LoteEntity lote;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public PantallaAdministrarLote() {


        //INICIO
        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Lote");
        inicializaTabla();
        buscarLotes();

        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession()
            buscarLotes();
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
            Integer metros = (Integer) tblLote.getModel().getValueAt(fila, 2);

            CargaLote carga = new CargaLote("Modificacion", nombre, metros, lotId);
            carga.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();


        });


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente el Lote.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el Lote.");
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
        String[] columnNames = {"Cod", "Nombre", "Metros", "Estado"};
        Object[][] data = new Object[1][4];
        setModel(columnNames, data, tblLote);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblLote.setModel(model);
        tblLote.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblLote.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblLote.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblLote.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblLote.getColumnModel().getColumn(2).setPreferredWidth(200);
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
            lote = new LoteEntity();
            int fila = tblLote.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            lote.setLteId((int) tblLote.getModel().getValueAt(fila, 0));
            lote.setLteDenominacion((String) tblLote.getModel().getValueAt(fila, 1));
            lote.setLteCantMetros((int) tblLote.getModel().getValueAt(fila, 4));

            lote.setLteFechaAlta(fechaActual);
            lote.setLteUsuarioBaja("adminBajaLOTE");
            lote.setLteFechaUltMod(fechaActual);
            lote.setLteUsuarioUltMod("adminBajaLLOTE");
            lote.setLteFechaBaja(fechaActual);
            lote.setLteUsuarioBaja("adminBajaLOTE");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del lote: " + tblLote.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(lote);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja el lote: " + e.toString());
            return 2;
        } finally {
            //session.close();
        }

        return 0;
    }


    //METODO BUSCAR Lotes
    public void buscarLotes() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        int i = 0;
        try {
            lote = new LoteEntity();
            Query query = session.createQuery("select t from LoteEntity t where ucase(lteDenominacion) like ucase(:pNombre) and lteFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Metros", "Estado"};
            Object[][] data = new Object[list.size()][8];

            while (iter.hasNext()) {
                lote = (LoteEntity) iter.next();
                data[i][0] = lote.getLteId();
                data[i][1] = lote.getLteDenominacion();
                data[i][2] = lote.getLteCantMetros();
                data[i][3] = lote.getEstado();
                i++;
            }
            setModel(columnNames, data, tblLote);
        } finally {
            tx.rollback();
        }

    }
}
