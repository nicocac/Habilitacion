package Laboreo;

import Conexion.Conexion;
import Datos.InsumoEntity;
import Datos.LaboreoEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Iterator;

/**
 * Created by jagm on 05/09/2016.
 */
public class PantallaAdministrarLaboreos  extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnNuevo;
    private JTable tblLaboreos;
    private JButton btnCancelar;
    private JTable table1;
    private InsumoEntity insumo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public PantallaAdministrarLaboreos() {


        //INICIO
        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Laboreos");
        inicializaTabla();
        buscarLaboreos();


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession()
            buscarLaboreos();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
            inicializaTabla();
            txtBuscar.setText("");
        });


        //EDITAR
        btnEditar.addActionListener(e -> {
            int fila = tblLaboreos.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            Long laboreoId = (Long) tblLaboreos.getModel().getValueAt(fila, 0);
//            String nombre = (String) tblLaboreos.getModel().getValueAt(fila, 1);
//            String descripcion = (String) tblLaboreos.getModel().getValueAt(fila, 2);
//            String unidadMedida = (String) tblLaboreos.getModel().getValueAt(fila, 3);
//            TipoInsumoEntity tipoInsumo = (TipoInsumoEntity) tblLaboreos.getModel().getValueAt(fila, 4);
//            String stock = String.valueOf(tblLaboreos.getModel().getValueAt(fila, 5));

            PantallaLaboreoCargado carga = new PantallaLaboreoCargado("Modificacion", laboreoId);
            carga.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente el laboreo.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el laboreo.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            PantallaLaboreoCargado pantallaLaboreoCargado = new PantallaLaboreoCargado("Carga", 0L);
            pantallaLaboreoCargado.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());

    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod","Nombre", "Tipo Laboreo", "Descripcion", "Fecha Alta",};
        Object[][] data = new Object[1][5];
        setModel(columnNames, data, tblLaboreos);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblLaboreos.setModel(model);
        tblLaboreos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblLaboreos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblLaboreos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblLaboreos.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblLaboreos.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblLaboreos.getColumnModel().getColumn(4).setPreferredWidth(150);
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
            insumo = new InsumoEntity();
            int fila = tblLaboreos.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            insumo.setInsId((int) tblLaboreos.getModel().getValueAt(fila, 0));
            insumo.setInsNombre((String) tblLaboreos.getModel().getValueAt(fila, 1));
            insumo.setInsDescripcion((String) tblLaboreos.getModel().getValueAt(fila, 2));
            insumo.setInsUnidadMedida((String) tblLaboreos.getModel().getValueAt(fila, 3));
            insumo.setTipoInsumoByInsTinId((TipoInsumoEntity) tblLaboreos.getModel().getValueAt(fila, 4));
            insumo.setInsFechaAlta(fechaActual);
            insumo.setInsUsuarioAlta("adminBajaLaboreo");
            insumo.setInsFechaUltMod(fechaActual);
            insumo.setInsUsuarioUtlMod("adminBajaLaboreo");
            insumo.setInsFechaBaja(fechaActual);
            insumo.setInsUsuarioBaja("adminBajaLaboreo");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del laboreo: " + tblLaboreos.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(insumo);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja el insumo: " + e.toString());
            return 2;
        } finally {
            //session.close();
        }

        return 0;
    }


    //METODO BUSCAR Laboreos
    public void buscarLaboreos() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        int i = 0;
        try {
            LaboreoEntity laboreoEntity = new LaboreoEntity();
            Query query = session.createQuery("select t from LaboreoEntity t where ucase(lboDescripcion) like ucase(:pNombre) and lboFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod","Nombre", "Tipo Laboreo", "Descripcion", "Fecha Alta",};
            Object[][] data = new Object[list.size()][5];

            while (iter.hasNext()) {
                laboreoEntity = (LaboreoEntity) iter.next();
                data[i][0] = laboreoEntity.getLboId();
                data[i][1] = laboreoEntity.getLboNombre();
                data[i][2] = laboreoEntity.getTipoLaboreoEntity().getTpoNombre();
                data[i][3] = laboreoEntity.getLboDescripcion();
                data[i][4] = laboreoEntity.getLboFechaAlta();

                i++;
            }
            setModel(columnNames, data, tblLaboreos);
        } finally {
            tx.rollback();
        }

    }

}
