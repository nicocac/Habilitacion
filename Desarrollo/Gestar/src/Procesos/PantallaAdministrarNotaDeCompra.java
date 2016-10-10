package Procesos;

import Conexion.Coneccion;
import Datos.CompraEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Iterator;

/**
 * Created by jagm on 09/10/2016.
 */
public class PantallaAdministrarNotaDeCompra extends JFrame {
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
    private CompraEntity compra;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public PantallaAdministrarNotaDeCompra() {


        //INICIO
        setContentPane(panel1);
        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Administrar Notas de Compra");
        inicializaTabla();


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Coneccion.getSession();
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
                    showMessage("Se dio de baja exitosamente la compra.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja la compra.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            PantallaAdministrarCompra pantallaAdministrarCompra = new PantallaAdministrarCompra();
            pantallaAdministrarCompra.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());

    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Cantidad Items", "Fecha Alta", "Fecha Compra", "Monto Total"};
        Object[][] data = new Object[1][5];
        setModel(columnNames, data, tblCompras);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblCompras.setModel(model);
        tblCompras.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblCompras.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblCompras.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblCompras.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblCompras.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblCompras.getColumnModel().getColumn(4).setPreferredWidth(200);
    }

    private void showMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }


    //METODO DAR BAJA
    public int darBaja() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        try {
            compra = new CompraEntity();
            int fila = tblCompras.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            compra.setCpaId((int) tblCompras.getModel().getValueAt(fila, 0));
            compra.setCpaCantidadItems((int) tblCompras.getModel().getValueAt(fila, 1));
            compra.setCpaFechaAlta((Date) tblCompras.getModel().getValueAt(fila, 2));
            compra.setCpaFechaCompra((Date) tblCompras.getModel().getValueAt(fila, 3));
            compra.setCpaMontoTotal((BigDecimal) tblCompras.getModel().getValueAt(fila, 3));
            compra.setCpaFechaUltMod(fechaActual);
//            compra.setCpaUsuarioAlta("adminBajaINSUMO");
            compra.setCpaUsuarioBaja("adminBajaINSUMO");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja de la compra: " + tblCompras.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(compra);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja la compra: " + e.toString());
            return 2;
        } finally {
            session.close();
        }

        return 0;
    }


    //METODO BUSCAR Compras
    public void buscarNotasCompra() {
        Session session = Coneccion.getSession();
        int i = 0;
        try {
            compra = new CompraEntity();
            Query query = session.createQuery("select t from CompraEntity t where ucase(cpaId) like ucase(:pNombre) and cpaFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Cantidad Items", "Fecha Alta", "Fecha Compra", "Monto Total"};
            Object[][] data = new Object[list.size()][5];

            while (iter.hasNext()) {
                compra = (CompraEntity) iter.next();
                data[i][0] = compra.getCpaId();
                data[i][1] = compra.getCpaCantidadItems();
                data[i][2] = compra.getCpaFechaAlta();
                data[i][3] = compra.getCpaFechaCompra();
                data[i][4] = compra.getCpaMontoTotal();
                i++;
            }
            setModel(columnNames, data, tblCompras);
        } finally {
            session.close();
        }

    }

}

