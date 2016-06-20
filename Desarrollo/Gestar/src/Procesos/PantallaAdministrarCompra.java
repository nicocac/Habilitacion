package Procesos;

import Conexion.Coneccion;
import Datos.InsumoEntity;
import Datos.StockInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.sql.Date;
import java.util.Arrays;
import java.util.EventObject;
import java.util.Iterator;

/**
 * Created by jagm on 6/11/2016.
 */
public class PantallaAdministrarCompra extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnNuevo;
    private JTable tblInsumos;
    private JTable tblCompra;
    private JButton btnCancelar;
    private JLabel jlTitulo;
    private JLabel jlTotal;
    private InsumoEntity insumo;
    private Transaction tx;
    private DefaultTableModel modelInsumo;
    private DefaultTableModel modelCompra;
    private String[] columnNamesInsumo = {"Cod", "Nombre", "Descripcion", "Unidad de Medida", "Tipo Insumo", "Stock"};
    private String[] columnNamesCompra = {"Cod", "Nombre", "Unidad de Medida", "Cantidad", "Precio"};
    private float total = 0;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public PantallaAdministrarCompra() {


        //INICIO
        setContentPane(panel1);
        pack();
        this.setTitle("Registrar Compra Insumos");
        inicializaTabla(0);


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Coneccion.getSession();
            buscarInsumos();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
            inicializaTabla(0);
            txtBuscar.setText("");
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());

        //AGREGAR
        btnNuevo.addActionListener(e -> {
            if (!isCellSelected(tblInsumos)){
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            int fila = tblCompra.getRowCount()-1;
            if (tblCompra.getValueAt(fila,0) != null){
                fila ++;
            }

            String scantidad = JOptionPane.showInputDialog("Ingrese la cantidad del insumo");
            String sprecio = JOptionPane.showInputDialog("Ingrese el precio del insumo");
            float cantidad;
            float precio;
            try{
                cantidad = Float.valueOf(nvl(scantidad,"0"));
                precio = Float.valueOf(nvl(sprecio,"0"));
            }catch (NumberFormatException ex){
                showMessage("Debe ingresar valores numericos");
                cantidad = 0;
                precio = 0;
            }

            total += cantidad*precio;

            if (fila == 0 ) {
                tblCompra.setValueAt(tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 0), fila, 0);
                tblCompra.setValueAt(tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 1), fila, 1);
                tblCompra.setValueAt(tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 3), fila, 2);
                tblCompra.setValueAt(cantidad, fila, 3);
                tblCompra.setValueAt(precio, fila, 4);

                showMessage(String.valueOf(fila));
            }else{

                modelCompra.addRow(new Object[]{tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 0)
                        ,tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 1)
                        ,tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 3)
                        ,cantidad
                        ,precio});
            }

            jlTotal.setText(String.valueOf(total));

        });

        //QUITAR
        btnEliminar.addActionListener(e -> {
            if (!isCellSelected(tblCompra)){
                showMessage("Debe seleccionar una fila para continuar.");
            }
        });
    }

    private String nvl(String scantidad, String s) {
        if (scantidad.equals(null)){
            return s;
        }
        return scantidad;
    }


    //METODOS
    private void inicializaTabla(int tipo) {
        if (tipo == 1 || tipo == 0) {
            //String[] columnNames = {"Cod", "Nombre", "Descripcion", "Unidad de Medida", "Tipo Insumo", "Stock"};
            Object[][] data = new Object[1][6];
            setModelInsumo(columnNamesInsumo, data, tblInsumos);
        }
        if (tipo == 2 || tipo == 0){
            //String[] columnNamesCompra = {"Cod", "Nombre", "Unidad de Medida", "Cantidad", "Precio"};
            Object[][] data = new Object[1][5];
            setModelCompra(columnNamesCompra, data, tblCompra);
        }
    }

    private void setModelInsumo(String[] columnames, Object[][] data, JTable tabla) {
        modelInsumo = new DefaultTableModel();
        modelInsumo.setDataVector(data, columnames);
        tblInsumos.setModel(modelInsumo);
        tblInsumos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblInsumos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblInsumos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblInsumos.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblInsumos.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblInsumos.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblInsumos.getColumnModel().getColumn(5).setPreferredWidth(60);
    }

    private void setModelCompra(String[] columnames, Object[][] data, JTable tabla) {
        TableColumn col;
        modelCompra = new DefaultTableModel();
        modelCompra.setDataVector(data, columnames);
        tblCompra.setModel(modelCompra);
        tblCompra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblCompra.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblCompra.getColumnModel().getColumn(1).setPreferredWidth(380);
        tblCompra.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblCompra.getColumnModel().getColumn(3).setPreferredWidth(60);
        col = tblCompra.getColumnModel().getColumn(3);
        col.setCellEditor(new MyTableCellEditor());
        tblCompra.getColumnModel().getColumn(4).setPreferredWidth(60);
        col = tblCompra.getColumnModel().getColumn(4);
        col.setCellEditor(new MyTableCellEditor());

    }

    private void showMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }


    //METODO BUSCAR INSUMOS
    public void buscarInsumos() {
        Session session = Coneccion.getSession();
        int i = 0;
        try {
            insumo = new InsumoEntity();
            Query query = session.createQuery("select t from InsumoEntity t where ucase(insNombre) like ucase(:pNombre) and insFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Descripcion", "Unidad de Medida", "Tipo Insumo", "Stock"};
            Object[][] data = new Object[list.size()][6];

            while (iter.hasNext()) {
                insumo = (InsumoEntity) iter.next();
                data[i][0] = insumo.getInsId();
                data[i][1] = insumo.getInsNombre();
                data[i][2] = insumo.getInsDescripcion();
                data[i][3] = insumo.getInsUnidadMedida();
                data[i][4] = insumo.getTipoInsumoByInsTinId();
                //TODO verificar como manejamos el stock
                StockInsumoEntity stockInsumoEntity = (StockInsumoEntity) session.createQuery("select x from StockInsumoEntity x where x.insumoBySinInsId = :pNombre").setParameter("pNombre", insumo).uniqueResult();
                if (stockInsumoEntity != null) {
                    data[i][5] = stockInsumoEntity.getSinTotal();
                } else {
                    data[i][5] = 0;
                }
                i++;
            }
            setModelInsumo(columnNames, data, tblInsumos);
        } finally {
            session.close();
        }

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {

        JComponent component = new JTextField();

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                     int rowIndex, int vColIndex) {

            ((JTextField) component).setText(String.valueOf(value));

            return component;
        }

        public Object getCellEditorValue() {
            return ((JTextField) component).getText();
        }
    }

    private boolean isCellSelected(JTable tabla){
        int fila = tabla.getSelectedRow();
        if (fila == -1){
            return false;
        }
        return true;
    }



}
