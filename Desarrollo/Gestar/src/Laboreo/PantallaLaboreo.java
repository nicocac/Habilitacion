package Laboreo;

import Datos.*;
import Insumo.Insumo;
import Maquinaria.Maquinaria;
import Campania.Campania;
import Lote.Lote;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import TipoInsumo.TipoInsumo;


/**
 * Created by OWNER on 8/15/2016.
 */
public class PantallaLaboreo extends JFrame {
    private JTextField txtFecha;
    private JPanel panel1;
    private JTextField txtDescripcion;
    private JTable tblDetalles;
    private JList lstInsumos;
    private JList lstMaquinarias;
    private JComboBox cboCampania;
    private JButton btnAgregarItem;
    private JButton btnAgregarMaquinaria;
    private JButton btnCancelar;
    private JButton btnFinalizar;
    private JButton btnLimpiar;
    private JButton btnEliminar;
    private JList lstLotes;
    private JLabel lblLotes;
    private JComboBox cboMomentos;
    private JTextField txtSeleccion;
    private DefaultTableModel modelDetalle = new DefaultTableModel();
    private GestorLaboreo gestor = new GestorLaboreo();

    public PantallaLaboreo() {


        //INICIO
        setContentPane(panel1);
        pack();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Registrar Laboreo");
        inicializaTabla();
        cargarItems();
        cargarMaquinas();
        cargarCampanias();
        cargarMomentos();
        cboCampania.addActionListener(e -> cargarLotes((Campania) cboCampania.getSelectedItem()));
        lstLotes.addListSelectionListener(e -> lblLotes.setText(String.valueOf(lstLotes.getSelectedValuesList().size())));
        btnAgregarItem.addActionListener(e -> {
            if (!existeLote()) {
                showMessage("Debe seleccionar al menos un lote para continuar.");
                return;
            }
            java.util.List listaSeleccion;
            listaSeleccion = lstInsumos.getSelectedValuesList();
            Iterator iter = listaSeleccion.iterator();
            int fila = tblDetalles.getRowCount() - 1;
            if (tblDetalles.getValueAt(fila, 1) != null) {
                if (!tblDetalles.getValueAt(fila, 1).equals("")) {
                    fila++;
                }
            }
            while (iter.hasNext()) {
                Insumo ins = (Insumo) iter.next();
                if (permiteSeleccion("Insumo", ins.getNombre())) {
                    if (fila == 0) {
                        tblDetalles.setValueAt("Insumo", fila, 0);
                        tblDetalles.setValueAt(ins.getNombre(), fila, 1);
                        tblDetalles.setValueAt(ins.getUnidadMedida(), fila, 2);
                        tblDetalles.setValueAt("0", fila, 3);
                        fila++;
                    } else {
                        modelDetalle.addRow(new Object[]{"Insumo"
                                , ins.getNombre()
                                , ins.getUnidadMedida()
                                , "0"});
                        fila++;
                    }
                }
            }
        });
        btnAgregarMaquinaria.addActionListener(e -> {
            if (!existeLote()) {
                showMessage("Debe seleccionar al menos un lote para continuar.");
                return;
            }
            java.util.List listaSeleccion;
            listaSeleccion = lstMaquinarias.getSelectedValuesList();
            Iterator iter = listaSeleccion.iterator();
            int fila = tblDetalles.getRowCount() - 1;
            if (tblDetalles.getValueAt(fila, 1) != null) {
                if (!tblDetalles.getValueAt(fila, 1).equals("")) {
                    fila++;
                }

            }
            while (iter.hasNext()) {
                Maquinaria maq = (Maquinaria) iter.next();
                if (permiteSeleccion("Maquinaria", maq.getNombre())) {
                    if (fila == 0) {
                        tblDetalles.setValueAt("Maquinaria", fila, 0);
                        tblDetalles.setValueAt(maq.getNombre() + ", " + maq.getDescripcion() + ", " + maq.getModeloAnio(), fila, 1);
                        tblDetalles.setValueAt("-", fila, 2);
                        tblDetalles.setValueAt("-", fila, 3);
                        fila++;
                    } else {
                        modelDetalle.addRow(new Object[]{"Maquinaria"
                                , maq.getNombre() //+ ", " + maq.getDescripcion() + ", " + maq.getModeloAnio()
                                , "-"
                                , "-"});
                        fila++;
                    }
                }
            }
        });
        btnEliminar.addActionListener(e -> {
            if (!isCellSelected(tblDetalles)) {
                showMessage("Debe seleccionar un item para continuar.");
                return;
            }
            int fila = tblDetalles.getSelectedRow();
            if (fila == 0) {
                tblDetalles.setValueAt("", 0, 0);
                tblDetalles.setValueAt("", 0, 1);
                tblDetalles.setValueAt("", 0, 2);
                tblDetalles.setValueAt("", 0, 3);
            } else {
                DefaultTableModel modelo = (DefaultTableModel) tblDetalles.getModel();
                modelo.removeRow(tblDetalles.getSelectedRow());
            }
        });

        //LIMPIAR
        btnLimpiar.addActionListener(e -> limpiarPantalla());

        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());

        //GUARDAR
        btnFinalizar.addActionListener(e -> {
            GestorLaboreo gest = new GestorLaboreo();
            Campania camp = (Campania) cboCampania.getSelectedItem();
            ArrayList lotes = (ArrayList) lstLotes.getSelectedValuesList();
            ArrayList<DetalleLaboreo> detalles = new ArrayList<DetalleLaboreo>();
            for (int i = 0; i < tblDetalles.getModel().getRowCount(); i++) {
                DetalleLaboreo det = new DetalleLaboreo();
                if (tblDetalles.getValueAt(i, 0).equals("Insumo")) {
                    Insumo ins = new Insumo((String) tblDetalles.getValueAt(i, 1), null, null, null);
                    det.setInsumo(ins);
                    det.setCantidad(Integer.parseInt((String) tblDetalles.getValueAt(i, 3)));
                } else {
                    Maquinaria maq = new Maquinaria();
                    maq.setDescripcion((String) tblDetalles.getValueAt(i, 1));
                    det.setMaquinaria(maq);
                }
                detalles.add(det);
            }
            SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
            java.util.Date date = new java.util.Date();
            java.util.Calendar cal = Calendar.getInstance();
            try {
                date = formatter.parse(txtFecha.getText());
            } catch (Exception exe) {
                showMessage(exe.getMessage());
            }
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date fecha = new Date(cal.getTime().getTime());
            try {
                gest.registrarLaboreo(camp, lotes, detalles, (MomentoLaboreo) cboMomentos.getSelectedItem(), fecha, null, txtDescripcion.getText());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Ocurri? un error al cargar el laboreo: " + e1.toString());
            } finally {
                JOptionPane.showMessageDialog(null, "La operacion fue realizada con exito.");
                dispose();
            }
        });
    }

    private boolean isCellSelected(JTable tabla) {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            return false;
        }
        return true;
    }

    private void inicializaTabla() {
        String[] columnNamesCompra = {"Tipo", "Descripcion", "Unidad de Medida", "Cantidad"};
        Object[][] data = new Object[1][5];
        setModelDetalles(columnNamesCompra, data);

    }

    private boolean permiteSeleccion(String tipo, String descripcion) {
        for (int i = 0; i <= tblDetalles.getRowCount() - 1; i++) {
            String str;

            try {
                str = new String((String) tblDetalles.getValueAt(i, 1));
                if (tipo.equals("Maquinaria") && tipo.equals(tblDetalles.getValueAt(i, 0)))
                    str = str.substring(0, descripcion.length());
            } catch (NullPointerException np) {
                str = "aaaaaaaaaaaaa";
            }

            if (tipo.equals(tblDetalles.getValueAt(i, 0)) && descripcion.equals(str)) {
                return false;
            }
        }
        return true;
    }

    private void setModelDetalles(String[] columnames, Object[][] data) {
        TableColumn col;
        modelDetalle.setDataVector(data, columnames);
        tblDetalles.setModel(modelDetalle);
        //tblDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblDetalles.getColumnModel().getColumn(0).setPreferredWidth(180);
        tblDetalles.getColumnModel().getColumn(1).setPreferredWidth(350);
        tblDetalles.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(3).setPreferredWidth(60);
        col = tblDetalles.getColumnModel().getColumn(3);
        col.setCellEditor(new MyTableCellEditor());
        tblDetalles.setCellSelectionEnabled(true);
        //  tblDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    private void cargarItems() {
        DefaultListModel modelo = new DefaultListModel();
        java.util.List listaItems;
        listaItems = gestor.getInsumos();

        Iterator iter = listaItems.iterator();
        while (iter.hasNext()) {
            modelo.addElement(iter.next());
        }
        lstInsumos.setModel(modelo);
    }

    private void cargarMaquinas() {
        DefaultListModel modelo = new DefaultListModel();
        java.util.List listaItems;
        listaItems = gestor.getMaquinaria();

        Iterator iter = listaItems.iterator();
        while (iter.hasNext()) {
            modelo.addElement(iter.next());
        }
        lstMaquinarias.setModel(modelo);
    }

    private boolean existeLote() {
        if (lstLotes.getSelectedValuesList().size() == 0) {
            return false;
        }
        return true;
    }

    private void limpiarPantalla() {
        inicializaTabla();
    }

    private void cargarCampanias() {
        CampaniaEntity camp;
        Campania campania = new Campania();
        java.util.List listaItems;
        listaItems = gestor.getCampanias();

        Iterator iter = listaItems.iterator();
        while (iter.hasNext()) {
            cboCampania.addItem(iter.next());
        }

        cboCampania.setSelectedItem(null);

    }

    private void cargarMomentos() {
        java.util.List listaItems;
        listaItems = gestor.getMomentos();

        Iterator iter = listaItems.iterator();
        while (iter.hasNext()) {
            cboMomentos.addItem(iter.next());
        }
//        cboMomentos.setSelectedIndex(0);
    }

    private void showMessage(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private void cargarLotes(Campania camp) {
        java.util.List lista;
        lista = gestor.getLotesCampania(camp);
        DefaultListModel modelo = new DefaultListModel();

        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            modelo.addElement(iter.next());
        }
        lstLotes.setModel(modelo);
    }

}

class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    private JComponent component = new JTextField();

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                 int rowIndex, int vColIndex) {

        ((JTextField) component).setText(String.valueOf(value));

        return component;
    }

    public Object getCellEditorValue() {
        return ((JTextField) component).getText();
    }
}


