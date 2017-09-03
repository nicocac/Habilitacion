package Laboreo;

import Conexion.Conexion;
import Datos.*;
import Granos.CargaTipoGrano;
import Insumo.CargaInsumo;
import Insumo.Insumo;
import Laboreo.TipoLaboreo.CargaTipoLaboreo;
import Maquinaria.CargaMaquinaria;
import Maquinaria.Maquinaria;
import Repository.InsumoRepository;
import Repository.LaboreoRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jagm on 28/10/2016.
 */
public class PantallaLaboreoCargado extends JFrame {
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
    public JButton buttonFecha;
    public JButton nuevaCampaniaBtn;
    public JButton nuevoTipoLaboreoBtn;
    public JButton nuevoInsumoBtn;
    public JButton nuevaMaquinariaBtn;
    public JButton actualizarInsumosBtn;
    public JButton actualizarMaqBtn;
    public JComboBox cbxSemillas;
    public JButton nuevaSemillaBtn;
    public JTextField txtMetrica;
    public JComboBox cbxMedida;
    public JTextField txtNombre;
    private DefaultTableModel modelDetalle = new DefaultTableModel();
    private GestorLaboreo gestor = new GestorLaboreo();
    private String tipoOperacion;

    InsumoRepository insumoRepository = new InsumoRepository();
    LaboreoRepository laboreoRepository = new LaboreoRepository();

    public PantallaLaboreoCargado(String operacion, Long laboreoId) {


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
        pack();
        inicializaTabla();
        cargarItems();
        cargarMaquinas();
        cargaComboBoxTipoLaboreo();
        cargaComboBoxTipoGrano();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Registrar Laboreo");

        } else {
            this.setTitle("Modificar Laboreo");

            LaboreoEntity laboreoEntity = laboreoRepository.getLaboreoById(laboreoId);
            txtNombre.setText(laboreoEntity.getLboNombre());
            txtMetrica.setText(laboreoEntity.getMetrica());
            cboMomentos.setSelectedItem(laboreoEntity.getTipoLaboreoEntity());
            cbxSemillas.setSelectedItem(laboreoEntity.getTipoGrano());

            //Carga insumos
            List<Object[]> listaIns;
            //getInsumos por laboreo
            listaIns = gestor.getInsumosByLaboreo(laboreoId);

            //Carga maquinarias
            List<Object[]> listaMaq;
            //getMaquinaria por laboreo
            listaMaq = gestor.getMaquinariasByLaboreo(laboreoId);


            Object[][] data = new Object[listaIns.size() + listaMaq.size()][4];
            int i = 0;
            if (listaIns.size() != 0) {
                for (Object[] row : listaIns) {
                    InsumoEntity insumoEntity = (InsumoEntity) row[0];
                    Integer cantidad = (Integer) row[1];

                    data[i][0] = "Insumo";
                    data[i][1] = insumoEntity.getInsNombre();
                    data[i][2] = insumoEntity.getTipoInsumoByInsTinId().getTinNombre();
                    data[i][3] = String.valueOf(cantidad);
                    i++;
                }
            }

            if (listaMaq.size() != 0) {
                for (Object[] row : listaMaq) {
                    MaquinariaEntity maquinariaEntity = (MaquinariaEntity) row[0];
                    Integer cantidad = (Integer) row[1];

                    data[i][0] = "Maquinaria";
                    data[i][1] = maquinariaEntity.getMaqNombre();
                    data[i][2] = maquinariaEntity.getTipoMaquinariaByMaqTmaqId().getTmaNombre();
                    data[i][3] = String.valueOf(cantidad);
                    i++;
                }
            }

//                }
//            }

            String[] columnNames = {"Clasificacion", "Nombre", "Tipo", "Cantidad",};
            DefaultTableModel model = new DefaultTableModel();
            model.setDataVector(data, columnNames);
            tblDetalles.setModel(model);

//            return;
        }


        //AGREGAR INSUMO
        btnAgregarItem.addActionListener(e -> {
//            if (!existeLote()) {
//                showMessage("Debe seleccionar al menos un lote para continuar.");
//                return;
//            }
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

        //BUTTON FECHA
//        net.sourceforge.jdatepicker.impl.SqlDateModel modelIni = new net.sourceforge.jdatepicker.impl.SqlDateModel();
//        modelIni.setDate(2017, 05, 28);
//        // Need this...
//        Properties p = new Properties();
//        p.put("text.today", "Today");
//        p.put("text.month", "Month");
//        p.put("text.year", "Year");
//        net.sourceforge.jdatepicker.impl.JDatePanelImpl datePanelIni =
//                new net.sourceforge.jdatepicker.impl.JDatePanelImpl(modelIni);
//        //the formatter,  there it is...
//        net.sourceforge.jdatepicker.impl.JDatePickerImpl datePickerIni =
//                new net.sourceforge.jdatepicker.impl.JDatePickerImpl(datePanelIni, new Date.DateLabelFormatter());
//

//        buttonFecha.add(datePickerIni);
        //

        //AGREGAR MAQUINARIA
        btnAgregarMaquinaria.addActionListener(e -> {
//            if (!existeLote()) {
//                showMessage("Debe seleccionar al menos un lote para continuar.");
//                return;
//            }
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
                        tblDetalles.setValueAt("0", fila, 3);
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

        //ELIMINAR ITEM
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


        cboMomentos.addMouseListener(new MouseAdapter() {
        });


        //NUEVA ACTIVIDAD
        nuevoTipoLaboreoBtn.addActionListener(e -> {
            CargaTipoLaboreo cargaTipoLaboreo = new CargaTipoLaboreo("Carga", "", "", 0);
            cargaTipoLaboreo.setVisible(true);
            getDefaultCloseOperation();
        });

        cboMomentos.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxTipoLaboreo();
                cargaComboBoxTipoLaboreo();
            }
        });

        //NUEVO INSUMO
        nuevoInsumoBtn.addActionListener(e -> {
            CargaInsumo cargaInsumo = new CargaInsumo("Carga", "", "", "", null, "", 0);
            cargaInsumo.setVisible(true);
            getDefaultCloseOperation();
        });

        actualizarInsumosBtn.addActionListener(e -> {
            cargarItems();
        });

        //NUEVA MAQUINARIA
        nuevaMaquinariaBtn.addActionListener(e -> {
            CargaMaquinaria cargaMaquinaria = new CargaMaquinaria("Carga", "", "", null, "", "", null, "", 0);
            cargaMaquinaria.setVisible(true);
            getDefaultCloseOperation();
        });
        actualizarMaqBtn.addActionListener(e -> {
            cargarMaquinas();
        });


        //NUEVA SEMILLA
        nuevaSemillaBtn.addActionListener(e -> {
            CargaTipoGrano cargaTipoGrano = new CargaTipoGrano("Carga", "", "", 0);
            cargaTipoGrano.setVisible(true);
            getDefaultCloseOperation();
        });

        cbxSemillas.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxTipoGrano();
                cargaComboBoxTipoGrano();
            }
        });


        //GUARDAR
        btnFinalizar.addActionListener(e -> {
            GestorLaboreo gest = new GestorLaboreo();
            ArrayList<DetalleLaboreo> detalles = new ArrayList<DetalleLaboreo>();
            Boolean sinStock = false;
            String insumosSinStock = "";

            for (int i = 0; i < tblDetalles.getModel().getRowCount(); i++) {
                DetalleLaboreo det = new DetalleLaboreo();
                if (tblDetalles.getValueAt(i, 0).equals("Insumo")) {
                    InsumoEntity insumoEntity = insumoRepository.getInsumoByNombre((String) tblDetalles.getValueAt(i, 1));

                    if (insumoEntity.getInsStock() == null) {
                        sinStock = true;
                        if (insumosSinStock.equals("")) {
                            insumosSinStock = insumoEntity.getInsNombre();
                        } else {
                            insumosSinStock = insumosSinStock + ", " + insumoEntity.getInsNombre();
                        }
                    }


                    Insumo ins = new Insumo((String) tblDetalles.getValueAt(i, 1), null, null, null, null, null);
                    det.setInsumo(ins);
                    det.setCantidadIsumo(Integer.parseInt((String) tblDetalles.getValueAt(i, 3)));
                } else {
                    Maquinaria maq = new Maquinaria();
                    maq.setNombre((String) tblDetalles.getValueAt(i, 1));
                    det.setMaquinaria(maq);
                    det.setCantidadMaquinaria(Integer.parseInt((String) tblDetalles.getValueAt(i, 3)));

                }
                detalles.add(det);
            }

//            if (sinStock){
////                        JOptionPane.showOptionDialog(null, "Se encuentran insumos sin stock. Desea Solicitar el Pedido del mismo", "Cuidado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);
//                int respuesta =JOptionPane.showConfirmDialog(null, "Los siguientes insumos: "+insumosSinStock +" se encuentran sin stock. Desea Solicitar el Pedido de los mismo?", "Cuidado", JOptionPane.YES_NO_OPTION);
//                if(respuesta == 0){
//                    dispose();
//                    PantallaAdministrarSolicitudInsumos pantallaAdministrarSolicitudInsumos = new PantallaAdministrarSolicitudInsumos();
//                    pantallaAdministrarSolicitudInsumos.setVisible(true);
//                    getDefaultCloseOperation();
//                    return;
//                }
////                          else {
////                            return;
////                        }
//            }

            SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
            java.util.Date date = new java.util.Date();
            java.util.Calendar cal = Calendar.getInstance();
            try {
//                date = formatter.parse(txtFecha.getText());
            } catch (Exception exe) {
                showMessage(exe.getMessage());
            }
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
//            Date fecha = new Date(cal.getTime().getTime());

//            Date fecha = (Date) datePickerIni.getModel().getValue();
            try {
                gest.registrarLaboreoPrecargado(detalles, (TipoLaboreoEntity) cboMomentos.getSelectedItem(),
                        txtDescripcion.getText(), (TipoGranoEntity) cbxSemillas.getSelectedItem(), txtMetrica.getText(),
                        (String) cbxMedida.getSelectedItem(), txtNombre.getText());


            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar el laboreo: " + e1.toString());
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
        String[] columnNamesCompra = {"Tipo", "Nombre", "Unidad de Medida", "Cantidad Necesaria"};
        Object[][] data = new Object[1][5];
        setModelDetalles(columnNamesCompra, data);

    }

    private boolean permiteSeleccion(String tipo, String descripcion) {
        for (int i = 0; i <= tblDetalles.getRowCount() - 1; i++) {
            String str;

            try {
                str = new String((String) tblDetalles.getValueAt(i, 1));
//                if (tipo.equals("Maquinaria") && tipo.equals(tblDetalles.getValueAt(i, 0)))
//                    str = str.substring(0, descripcion.length());
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


    private void limpiarPantalla() {
        inicializaTabla();
    }


    private void showMessage(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }


    private void borrarComboBoxTipoLaboreo() {
        cboMomentos.removeAllItems();
    }

    private void borrarComboBoxTipoGrano() {
        cbxSemillas.removeAllItems();
    }


    //METODO CARGA COMBO TIPO LABOREO
    private void cargaComboBoxTipoLaboreo() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("SELECT p FROM TipoLaboreoEntity p");
        java.util.List<TipoLaboreoEntity> listaTipoLaboreoEntity = query.list();

        Vector<String> miVectorTipoLaboreo = new Vector<>();
        for (TipoLaboreoEntity tipoLaboreo : listaTipoLaboreoEntity) {
            miVectorTipoLaboreo.add(tipoLaboreo.getTpoNombre());
            cboMomentos.addItem(tipoLaboreo);

//            cboCampania.setSelectedItem(null);
        }
        tx.rollback();
    }


    //METODO CARGA COMBO TIPO GRANO
    private void cargaComboBoxTipoGrano() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("SELECT p FROM TipoGranoEntity p");
        java.util.List<TipoGranoEntity> listaTipoGranoEntity = query.list();

        Vector<String> miVectorTipoLaboreo = new Vector<>();
        for (TipoGranoEntity tipoGrano : listaTipoGranoEntity) {
            miVectorTipoLaboreo.add(tipoGrano.getTgrNombre());
            cbxSemillas.addItem(tipoGrano);

//            cboCampania.setSelectedItem(null);
        }
        tx.rollback();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}




