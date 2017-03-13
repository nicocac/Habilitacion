package Acopio;

import Acopio.Acopio.CargaAcopio;
import Acopio.TipoAcopio.CargaTipoAcopio;
import Conexion.Coneccion;
import Datos.AcopioEntity;
import Datos.TipoAcopioEntity;
import Laboreo.GestorLaboreo;
import Repository.AcopioRepository;
import TipoInsumo.CargaTipoInsumo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Iterator;

/**
 * Created by jagm on 17/02/2017.
 */
public class AdministrarAcopio extends JFrame {
    private JTextField txtBuscar;
    private JPanel panel1;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JTable tblTipos;
    private JButton btnEliminar;
    private JButton btnNuevo;
    private AcopioEntity tipo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    AcopioRepository acopioRepository = new AcopioRepository();
    GestorLaboreo gest = new GestorLaboreo();

    public AdministrarAcopio() {


        //INICIO
        JPanel container = new JPanel();
//        container.setPreferredSize(new Dimension(1920, 1900));
//        panel1.setPreferredSize(new Dimension(1900, 1800));
        container.add(panel1);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);

//        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Acopios");
        inicializaTabla();
        buscarTiposAcopio();

        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Coneccion.getSession();
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
                    showMessage("Se dio de baja exitosamente el  Acopio.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el  Acopio.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            CargaAcopio cargaTipoInsumo = new CargaAcopio("Carga", "", 0, 0);
            cargaTipoInsumo.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });
    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Nombre", "Codigo", "Tipo Acopio", "Semilla", "Cantidad Total"};
        Object[][] data = new Object[1][6];
        setModel(columnNames, data, tblTipos);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblTipos.setModel(model);
        tblTipos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblTipos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblTipos.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblTipos.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblTipos.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblTipos.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblTipos.getColumnModel().getColumn(5).setPreferredWidth(100);
    }

    private void showMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    //METODO DAR BAJA
    public int darBaja() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        try {
            tipo = new AcopioEntity();
            int fila = tblTipos.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            if (Integer.parseInt(tblTipos.getModel().getValueAt(fila, 5).toString()) > 0) {
                showMessage("El Acopio debe estar vacio para poder darse de baja.");
                return 3;
            }
            AcopioEntity acopioEntity = acopioRepository.getAcopioById((Long) tblTipos.getModel().getValueAt(fila, 0));
            acopioEntity.setAcopioFechaUltMod(fechaActual);
            acopioEntity.setAcopioFechaBaja(fechaActual);
            acopioEntity.setAcopioUsuarioBaja("adminBAJA");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del Acopio: " + tblTipos.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(acopioEntity);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja el Acopio: " + e.toString());
            return 2;
        } finally {
            session.close();
        }

        return 0;
    }


    //METODO BUSCAR TIPOS
    public void buscarTiposAcopio() {
        Session session = Coneccion.getSession();
        int i = 0;
        try {
            java.util.List<Object[]> list;
            Object[] acopio;
//            tipo = new AcopioEntity();

//            Query query = session.createQuery("select t from AcopioEntity t where ucase(nombre) like ucase(:pNombre) " +
//                    "and acopioFechaBaja is null");
//            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");

//            java.util.List list = query.list();
            list = gest.getStockByAcopio();

            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Nro", "Tipo Acopio", "Semilla", "Cantidad Total"};
            Object[][] data = new Object[list.size()][6];

            while (iter.hasNext()) {
                acopio = (Object[]) iter.next();
                data[i][0] = acopio[0];
                data[i][1] = acopio[1];
                data[i][2] = acopio[2];
                data[i][3] = acopio[3];
                data[i][4] = acopio[4];
                data[i][5] = acopio[5];
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
