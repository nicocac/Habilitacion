package Procesos;

import Conexion.Conexion;
import Datos.InsumoEntity;
import Insumo.Insumo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * Created by jagm on 6/11/2016.
 */
public class PantallaSolicitudInsumos extends JFrame {
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
    private JLabel jlTotal;
    private JButton btnFinalizar;
    private JLabel jlCantidadItems;
    public JTextField txtSolicitud;
    public JTextField txtEstado;
    public JTextField txtRemito;
    private InsumoEntity insumo;
    private Transaction tx;
    private DefaultTableModel modelInsumo;
    private DefaultTableModel modelCompra;
    private String[] columnNamesInsumo = {"Cod", "Nombre", "Descripcion", "Unidad de Medida", "Tipo Insumo", "Stock"};
    private String[] columnNamesCompra = {"Cod", "Nombre", "Observaciones", "Unidad de Medida", "Cantidad"};
    private float total = 0;
    private java.util.Date fecha = new java.util.Date();
    private boolean existeValorCero = false;
    private String scantidad = "0";
    private String sprecio = "0";
    private float cantidad = 0;
    private float precio = 0;
    private int cantItems = 0;

    public PantallaSolicitudInsumos(String insumos) {


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
        pack();
//        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Solicitud de Insumos");
        inicializaTabla(0);


        buscarInsumos(insumos);


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession()
            buscarInsumos(insumos);
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> limpiarPantalla());


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());


        //AGREGAR
        btnNuevo.addActionListener(e -> {
            if (!isCellSelected(tblInsumos)) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            int fila = tblCompra.getRowCount() - 1;
            if (tblCompra.getValueAt(fila, 0) != null) {
                if (!tblCompra.getValueAt(fila, 1).equals("")) {
                    fila++;
                }
            }

            scantidad = (String) JOptionPane.showInputDialog(null, "Ingrese la cantidad del insumo", "Ingreso Cantidad", JOptionPane.PLAIN_MESSAGE, null, null, null);
            scantidad = nvl(scantidad, "0").replace(",", ".");
//            sprecio = (String) JOptionPane.showInputDialog(null,"Ingrese el precio unitario del insumo","Ingreso Precio",JOptionPane.PLAIN_MESSAGE, null, null, null);
//            sprecio = nvl(sprecio,"0").replace(",",".");
            cantidad = 0;
            precio = 0;
            try {
                cantidad = Float.valueOf(nvl(scantidad, "0"));
//                precio = Float.valueOf(nvl(sprecio, "0"));
            } catch (NumberFormatException ex) {
                showMessage("Debe ingresar valores numericos");
                scantidad = "0";
                sprecio = "0";
            } catch (NullPointerException ex) {
                scantidad = "0";
            }

//            total += cantidad * precio;
            total += cantidad;

            if (fila == 0) {
                tblCompra.setValueAt(tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 0), fila, 0);
                tblCompra.setValueAt(tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 1), fila, 1);
                tblCompra.setValueAt("", fila, 2);
                tblCompra.setValueAt(tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 3), fila, 3);
                tblCompra.setValueAt(scantidad, fila, 4);

            } else {

                modelCompra.addRow(new Object[]{tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 0)
                        , tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 1)
                        , ""
                        , tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 3)
                        , scantidad});
            }

//            actualizaLabelTotal(total);
            actualizaCantItems("add");
        });


        //QUITAR
        btnEliminar.addActionListener(e -> {
            if (!isCellSelected(tblCompra)) {
                showMessage("Debe seleccionar un item para continuar.");
                return;
            }
            int fila = tblCompra.getSelectedRow();
            if (fila == 0) {
                tblCompra.setValueAt("", 0, 0);
                tblCompra.setValueAt("", 0, 1);
                tblCompra.setValueAt("", 0, 2);
                tblCompra.setValueAt("", 0, 3);
                tblCompra.setValueAt("0", 0, 4);
            } else {
                DefaultTableModel modelo = (DefaultTableModel) tblCompra.getModel();
                modelo.removeRow(tblCompra.getSelectedRow());
            }

            actualizaCantItems("remove");
            actualizaTotal();

        });


        //GUARDAR
        btnFinalizar.addActionListener(e -> {
            if (actualizaTotal()) {
                showMessage("No puede haber ni cantidades con valor 0.");
                return;
            }

            if (txtSolicitud.getText().replaceAll(" ", "").length() == 0) {
                showMessage("Debe ingresar el nro de solicitud antes de continuar.");
                return;
            }

            Insumo ins = new Insumo("(Sin datos)", null, null, null);
            DetalleCompra detalle;
            Compra compra = new Compra();
            ArrayList<DetalleCompra> detalles = new ArrayList<>();
            Date utilDate = new Date();
            compra.setFechaOperacion(new java.sql.Date(utilDate.getTime()));
//            compra.setMontoTotal(BigDecimal.valueOf(Double.valueOf((jlTotal.getText()))));
            compra.setCantidadItems(Integer.valueOf(jlCantidadItems.getText()));
            compra.setNroSolicitud(Integer.valueOf(txtSolicitud.getText()));
            try {
                for (int i = 0; i < tblCompra.getRowCount(); i++) {
                    ins = new Insumo((String) tblCompra.getValueAt(i, 1), null, null, null);
                    detalle = new DetalleCompra();
                    detalle.setCantidad(BigDecimal.valueOf(Double.valueOf((String) tblCompra.getValueAt(i, 4))));
//                    detalle.setPrecio(BigDecimal.valueOf(Double.valueOf((String)tblCompra.getValueAt(i,5))));
                    detalle.setInsumo(ins);
                    detalle.setObservaciones((String) tblCompra.getValueAt(i, 2));
                    detalles.add(detalle);
                }
            } catch (NumberFormatException ex) {
                showMessage("Ocurrio un error por el ingreso de datos del insumo: " + ins.getNombre() + ".\n Recuerde que solo debe ingresar datos numericos");
            }
// catch (Exception ex) {
//                showMessage("Ocurrio un error calcular el total de la compra: " + e.toString());
//            }
            compra.setDetalles(detalles);
            boolean save = new GestorCompras().registrarCompra(compra);

            if (save) {
                showMessage("La Solicitud de insumos nro: " + txtSolicitud.getText() + " fue registrada exitosamente.");
                limpiarPantalla();
            }
        });
    }

    private String nvl(String scantidad, String s) {
        if (scantidad == null) {
            return s;
        }
        return scantidad;
    }

    private void limpiarPantalla() {
        inicializaTabla(0);
        txtSolicitud.setText("");
        txtEstado.setText("");
        txtRemito.setText("");
        txtBuscar.setText("");
        jlCantidadItems.setText("0");
//        jlTotal.setText("0");
        cantItems = 0;

    }


    //METODOS
    private void inicializaTabla(int tipo) {
        if (tipo == 1 || tipo == 0) {
            //String[] columnNames = {"Cod", "Nombre", "Descripcion", "Unidad de Medida", "Tipo Insumo", "Stock"};
            Object[][] data = new Object[1][6];
            setModelInsumo(columnNamesInsumo, data);
        }
        if (tipo == 2 || tipo == 0) {
            //String[] columnNamesCompra = {"Cod", "Nombre", "Unidad de Medida", "Cantidad", "Precio"};
            Object[][] data = new Object[1][5];
            setModelCompra(columnNamesCompra, data);
        }
    }

    private void setModelInsumo(String[] columnames, Object[][] data) {
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
        tblInsumos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //tblInsumos.setCe
    }

    private void setModelCompra(String[] columnames, Object[][] data) {
        TableColumn col;
        modelCompra = new DefaultTableModel();
        modelCompra.setDataVector(data, columnames);
        tblCompra.setModel(modelCompra);
        tblCompra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblCompra.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblCompra.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblCompra.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblCompra.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblCompra.getColumnModel().getColumn(4).setPreferredWidth(60);
        col = tblCompra.getColumnModel().getColumn(4);
        col.setCellEditor(new MyTableCellEditor());
        col.setCellEditor(new MyTableCellEditor());
        col = tblCompra.getColumnModel().getColumn(2);
        col.setCellEditor(new MyTableCellEditor());
        tblCompra.setCellSelectionEnabled(true);
        tblCompra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblCompra.getModel().addTableModelListener(e -> {
            if (tblCompra.getSelectedColumn() == 4 || tblCompra.getSelectedColumn() == 5) {
                actualizaTotal();
            }
        });

    }

    private void showMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    private void actualizaLabelTotal(float total) {
        jlTotal.setText(String.valueOf(total));
    }

    private void actualizaCantItems(String tipo) {
        if (!tipo.equals("add")) {
            cantItems--;
        } else {
            cantItems++;
        }
        jlCantidadItems.setText(String.valueOf(cantItems));
    }

    private boolean actualizaTotal() {
        String ins = "";
        existeValorCero = false;
        try {
            total = 0;
            for (int i = 0; i < tblCompra.getRowCount(); i++) {
                ins = (String) tblCompra.getValueAt(i, 1);
                scantidad = nvl((String) tblCompra.getValueAt(i, 4), "0");
                scantidad = scantidad.replace(",", ".");
//                sprecio = nvl((String) tblCompra.getValueAt(i, 5), "0");
//                sprecio = sprecio.replace(",", ".");
                cantidad = Float.valueOf(scantidad);
//                precio = Float.valueOf(sprecio);
//                if (cantidad == 0 || precio == 0) {
                if (cantidad == 0) {
                    existeValorCero = true;
                }
//                total += cantidad * precio;
                total += cantidad;
            }
        } catch (NumberFormatException e) {
            showMessage("Ocurrio un error por el ingreso de datos del insumo: " + ins + ".\n Recuerde que solo debe ingresar datos numericos");
        }
//        catch (Exception e) {
//            showMessage("Ocurrio un error calcular el total de la compra: " + e.toString());
//        }

//        actualizaLabelTotal(total);

        return existeValorCero;

    }

    //METODO BUSCAR INSUMOS
    private void buscarInsumos(String insumos) {
        Session session = Conexion.getSessionFactory().openSession();
        int i = 0;
        try {
//            java.util.List<String> listaInsumos = new ArrayList<>();
            String[] objectoInsumos = null;
            if(insumos!=null){
                objectoInsumos = insumos.split(", ");
//                for(String obj : objectoInsumos){
//                    listaInsumos.add(obj);
//                }
            }

            InsumoEntity insumo = new InsumoEntity();
            Query query = session.createQuery("select t from InsumoEntity t where ucase(insNombre) like ucase(:pNombre) and insFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Descripcion", "Unidad de Medida", "Tipo Insumo", "Stock"};
            Object[][] data = new Object[list.size()][6];

            while (iter.hasNext()) {
                insumo = (InsumoEntity) iter.next();
                if(objectoInsumos != null) {
                    for (String obj : objectoInsumos) {
                        if (insumo.getInsNombre().equals(obj)) {
                            data[i][0] = insumo.getInsId();
                            data[i][1] = insumo.getInsNombre();
                            data[i][2] = insumo.getInsDescripcion();
                            data[i][3] = insumo.getInsUnidadMedida();
                            data[i][4] = insumo.getTipoInsumoByInsTinId();
                            data[i][5] = insumo.getInsStock();
                            i++;
                        }
                    }
                } else {
                    data[i][0] = insumo.getInsId();
                    data[i][1] = insumo.getInsNombre();
                    data[i][2] = insumo.getInsDescripcion();
                    data[i][3] = insumo.getInsUnidadMedida();
                    data[i][4] = insumo.getTipoInsumoByInsTinId();
                    data[i][5] = insumo.getInsStock();
                    i++;
                }
            }
            setModelInsumo(columnNames, data);
        } finally {
            session.close();
        }

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {

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

    private boolean isCellSelected(JTable tabla) {
        int fila = tabla.getSelectedRow();
        return fila != -1;
    }


}
