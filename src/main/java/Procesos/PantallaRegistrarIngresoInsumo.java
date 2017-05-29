package Procesos;

import Conexion.Conexion;
import Datos.InsumoEntity;
import Insumo.Insumo;
import Repository.InsumoRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ContainerAdapter;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by jagm on 16/10/2016.
 */
public class PantallaRegistrarIngresoInsumo extends JFrame{
    public JPanel panel1;
    public JButton btnEliminar;
    public JButton btnLimpiar;
    public JButton btnNuevo;
    public JTable tblInsumos;
    public JButton btnCancelar;
    public JButton btnFinalizar;
    public JTable tblCompra;
    public JLabel jlCantidadItems;
    public JTextField txtRemito;
    public JList listSolicitudes;
    public JPanel panelIni;

    private InsumoEntity insumo;
    private Transaction tx;
    private DefaultTableModel modelInsumo;
    private DefaultTableModel modelCompra;
    private String[] columnNamesInsumo = {"Cod", "Nombre", "Descripcion", "Unidad de Medida", "Tipo Insumo", "Stock"};
    private String[] columnNamesCompra = {"Cod", "Nombre", "Observaciones", "Unidad de Medida", "Cantidad", "Precio"};
    private float total = 0;
    private java.util.Date fecha = new java.util.Date();
    private boolean existeValorCero = false;
    private String scantidad = "0";
    private String sprecio = "0";
    private float cantidad = 0;
    private float precio = 0;
    private int cantItems = 0;
    GestorCompras gestorCompras = new GestorCompras();

    InsumoRepository insumoRepository = new InsumoRepository();


    public PantallaRegistrarIngresoInsumo() {

        listSolicitudes.addListSelectionListener(e -> buscarInsumosPorSolicitud());


        //INICIO
        JPanel container = new JPanel();
//        container.setPreferredSize(new Dimension(1920, 1900));
//        panel1.setPreferredSize(new Dimension(1900, 1800));
        container.add(panelIni);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);
//        setContentPane(panel1);
        pack();
//        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Ingreso de Insumos");
        inicializaTabla(0);
//        buscarInsumos();
        cargarSolicitudes();

//        //BUSCAR
//        btnBuscar.addActionListener(e -> {
////            Session session = Conexion.getSessionFactory().openSession()
//            buscarInsumos();
//        });


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
                precio = Float.valueOf(nvl(sprecio, "0"));
            } catch (NumberFormatException ex) {
                showMessage("Debe ingresar valores numericos");
                scantidad = "0";
                sprecio = "0";
            } catch (NullPointerException ex) {
                scantidad = "0";
                sprecio = "0";
            }

            total += cantidad * precio;

            if (fila == 0) {
                tblCompra.setValueAt(tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 0), fila, 0);
                tblCompra.setValueAt(tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 1), fila, 1);
                tblCompra.setValueAt("", fila, 2);
                tblCompra.setValueAt(tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 3), fila, 3);
                tblCompra.setValueAt(scantidad, fila, 4);
                tblCompra.setValueAt(sprecio, fila, 5);

            } else {

                modelCompra.addRow(new Object[]{tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 0)
                        , tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 1)
                        , ""
                        , tblInsumos.getValueAt(tblInsumos.getSelectedRow(), 3)
                        , scantidad
                        , sprecio});
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
                tblCompra.setValueAt("0", 0, 5);
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
                showMessage("No puede haber ni cantidades ni precios con valor 0.");
                return;
            }

            if (txtRemito.getText().replaceAll(" ", "").length() == 0) {
                showMessage("Debe ingresar el nro de Remito antes de continuar.");
                return;
            }


            Insumo ins = new Insumo("(Sin datos)", null, null, null);
            DetalleIngresoInsumo detalle;
            IngresoInsumo ingreso = new IngresoInsumo();
            ArrayList<DetalleIngresoInsumo> detalles = new ArrayList<>();
            Date utilDate = new Date();
            ingreso.setFechaOperacion(new java.sql.Date(utilDate.getTime()));
//            compra.setMontoTotal(BigDecimal.valueOf(Double.valueOf((jlTotal.getText()))));
            ingreso.setCantidadItems(Integer.valueOf(jlCantidadItems.getText()));
            ingreso.setIngresoNroRemito(Integer.parseInt(txtRemito.getText()));

            for(Object nro: listSolicitudes.getSelectedValuesList()){
                Integer nro1 = (Integer)nro;
                ingreso.getListaNroSolicitudes().add(nro1.longValue());
            }


            try {
                for (int i = 0; i < tblCompra.getRowCount(); i++) {

                    ins = new Insumo((String) tblCompra.getValueAt(i, 1), null, null, null);
                    detalle = new DetalleIngresoInsumo();
                    detalle.setCantidadIngresada((Long.valueOf((String) tblCompra.getValueAt(i, 4))));
//                    detalle.setPrecio(BigDecimal.valueOf(Double.valueOf((String)tblCompra.getValueAt(i,5))));
                    detalle.setInsumo(ins);
                    detalle.setObservaciones((String) tblCompra.getValueAt(i, 2));
                    detalles.add(detalle);
                }
            } catch (NumberFormatException ex) {
                showMessage("Ocurrio un error por el ingreso de datos del insumo: " + ins.getNombre() + ".\n Recuerde que solo debe ingresar datos numericos");

            }
            ingreso.setListaDetalleIngresoEntity(detalles);
            boolean save = new GestorCompras().registrarIngresoInsumos(ingreso);

            if (save) {
                showMessage("El ingreso de insumos nro: " + txtRemito.getText() + " fue registrado exitosamente.");
                limpiarPantalla();
            }
        });






        listSolicitudes.addMouseListener(new MouseAdapter() {
        });
        listSolicitudes.addComponentListener(new ComponentAdapter() {
        });
        listSolicitudes.addContainerListener(new ContainerAdapter() {
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
        txtRemito.setText("");
//        txtBuscar.setText("");
        jlCantidadItems.setText("0");
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
        tblCompra.getColumnModel().getColumn(5).setPreferredWidth(60);
        col = tblCompra.getColumnModel().getColumn(5);
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

//    private void actualizaLabelTotal(float total) {
//        jlTotal.setText(String.valueOf(total));
//    }

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
                sprecio = nvl((String) tblCompra.getValueAt(i, 5), "0");
                sprecio = sprecio.replace(",", ".");
                cantidad = Float.valueOf(scantidad);
                precio = Float.valueOf(sprecio);
//                if (cantidad == 0 || precio == 0) {
                if (cantidad == 0) {
                    existeValorCero = true;
                }
                total += cantidad * precio;
            }
        } catch (NumberFormatException e) {
            showMessage("Ocurrio un error por el ingreso de datos del insumo: " + ins + ".\n Recuerde que solo debe ingresar datos numericos");
        } catch (Exception e) {
            showMessage("Ocurrio un error calcular el total de la compra: " + e.toString());
        }

//        actualizaLabelTotal(total);

        return existeValorCero;

    }

    //METODO BUSCAR INSUMOS
    private void buscarInsumos() {
        Session session = Conexion.getSessionFactory().openSession();
        int i = 0;
        try {
            insumo = new InsumoEntity();
            Query query = session.createQuery("select t from InsumoEntity t where ucase(insNombre) like ucase(:pNombre) and insFechaBaja is null");
            query.setParameter("pNombre", "%" + "" + "%");
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
                data[i][5] = insumo.getInsStock();
                i++;
            }
            setModelInsumo(columnNames, data);
        } finally {
            session.close();
        }
    }

    //BUSCAR SOLICITUDES EN CURSO

    private void cargarSolicitudes() {
        java.util.List lista;
        lista = gestorCompras.getSolicitudesEnCurso();
        DefaultListModel modelo = new DefaultListModel();

        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            modelo.addElement(iter.next());
        }
        listSolicitudes.setModel(modelo);
    }




    //METODO BUSCAR INSUMOS DE SOLICUTUDES
    private void buscarInsumosPorSolicitud() {
        Session session = Conexion.getSessionFactory().openSession();
        Integer nroSolicitud = (Integer)listSolicitudes.getSelectedValue();
        int i = 0;
        try {
            InsumoEntity insumo = new InsumoEntity();
            Query query = session.createQuery("select t.insumo from DetalleSolicitudInsumoEntity t " +
                                                "where t.solicitudInsumo.siNroSolicitud = :pNombre and t.dsiFechaBaja is null");
//            query.setParameter("pNombre", "%" + listSolicitudes.getSelectedValue() + "%");
            query.setParameter("pNombre", nroSolicitud);
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
                data[i][5] = insumo.getInsStock();
                i++;
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

