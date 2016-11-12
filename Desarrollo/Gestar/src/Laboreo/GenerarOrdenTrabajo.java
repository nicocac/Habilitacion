package Laboreo;

import Conexion.Coneccion;
import Datos.*;
import Granos.CargaTipoGrano;
import Insumo.Insumo;
import Laboreo.TipoLaboreo.CargaTipoLaboreo;
import Maquinaria.Maquinaria;
import Repository.InsumoRepository;
import Repository.LaboreoRepository;
import Repository.PlanificacionRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by jagm on 01/11/2016.
 */
public class GenerarOrdenTrabajo extends JFrame {
    private JPanel panel1;
    private JTextField txtRRHH;
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
    public JList lstLaboreos;
    public JButton buttonFecha;
    public JButton btnAgregarLaboreo;
    public JTextField txtTiempo;
    public JButton btnGenerarOrden;
    public JTextField txtFechaIni;
    public JTextField txtFechaFin;
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
    private DefaultTableModel modelInsumoMaquinaria;

    InsumoRepository insumoRepository = new InsumoRepository();
    LaboreoRepository laboreoRepository = new LaboreoRepository();
    PlanificacionRepository planificacionRepository = new PlanificacionRepository();

    public GenerarOrdenTrabajo(String operacion, Integer planificacionId) {


        //INICIO
        setContentPane(panel1);
        pack();
//        inicializaTabla();
//        cargarItems();
//        cargarMaquinas();
//        cargaComboBoxTipoLaboreo();
//        cargaComboBoxTipoGrano();
        tipoOperacion = operacion;
//        if (tipoOperacion.equals("Carga")) {
//            this.setTitle("Generar Ordenes de Trabajo");
//        } else {
//            this.setTitle("Modificar Ordenes de Trabajo");

            PlanificacionCampaniaEntity  planificacion = planificacionRepository.getPlanificadaById(planificacionId);
            List<LaboreoEntity> listaLaboreoEntity = planificacionRepository.getLaboreosByCampIdPlanificadaId(planificacionId);
            cargarLaboreos(listaLaboreoEntity);

        lstLaboreos.addListSelectionListener(e -> buscarInsumosMaquinariasPorLaboreo((Integer) lstLaboreos.getSelectedValue(), planificacion.getPlanificacionId()));

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Registrar Orden de trabajo");


        //BUTTON FECHA
//        SqlDateModel modelIni = new SqlDateModel();
//        modelIni.setDate(2016, 04, 20);
//        // Need this...
//        Properties p = new Properties();
//        p.put("text.today", "Today");
//        p.put("text.month", "Month");
//        p.put("text.year", "Year");
//        JDatePanelImpl datePanelIni = new JDatePanelImpl(modelIni, p);
//        //the formatter,  there it is...
//        JDatePickerImpl datePickerIni = new JDatePickerImpl(datePanelIni, new DateLabelFormatter());

//        buttonFecha.add(datePickerIni);
        //


        //ELIMINAR ITEM
//        btnEliminar.addActionListener(e -> {
//            if (!isCellSelected(tblDetalles)) {
//                showMessage("Debe seleccionar un item para continuar.");
//                return;
//            }
//            int fila = tblDetalles.getSelectedRow();
//            if (fila == 0) {
//                tblDetalles.setValueAt("", 0, 0);
//                tblDetalles.setValueAt("", 0, 1);
//                tblDetalles.setValueAt("", 0, 2);
//                tblDetalles.setValueAt("", 0, 3);
//            } else {
//                DefaultTableModel modelo = (DefaultTableModel) tblDetalles.getModel();
//                modelo.removeRow(tblDetalles.getSelectedRow());
//            }
//        });

        //LIMPIAR
        btnLimpiar.addActionListener(e -> limpiarPantalla());

        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());


//        //NUEVA CAMPANIA
//        nuevaCampaniaBtn.addActionListener(e -> {
//            Campania.CargaCampania cargaCampania = new Campania.CargaCampania("Carga", 0, "", null, null, null);
//            cargaCampania.setVisible(true);
//            getDefaultCloseOperation();
//        });

//        cboCampania.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                super.focusGained(e);
//                borrarComboBoxCampania();
//                cargaComboBoxCampania();
//            }
//        });



//        cboCampania.addMouseMotionListener(new MouseMotionAdapter() {

//        });
//        cboCampania.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                borrarComboBoxCampania();
////                cargarCampanias();
//            }
//        });


        //NUEVA ACTIVIDAD
//        nuevoTipoLaboreoBtn.addActionListener(e -> {
//            CargaTipoLaboreo cargaTipoLaboreo = new CargaTipoLaboreo("Carga", "", "", 0);
//            cargaTipoLaboreo.setVisible(true);
//            getDefaultCloseOperation();
//        });
//
//        cboMomentos.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                super.focusGained(e);
//                borrarComboBoxTipoLaboreo();
//                cargaComboBoxTipoLaboreo();
//            }
//        });


        //NUEVA SEMILLA
//        nuevaSemillaBtn.addActionListener(e -> {
//            CargaTipoGrano cargaTipoGrano = new CargaTipoGrano("Carga", "", "", 0);
//            cargaTipoGrano.setVisible(true);
//            getDefaultCloseOperation();
//        });
//
//        cbxSemillas.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                super.focusGained(e);
////                borrarComboBoxTipoGrano();
//                cargaComboBoxTipoGrano();
//            }
//        });


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


                    Insumo ins = new Insumo((String) tblDetalles.getValueAt(i, 1), null, null, null);
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
                        txtRRHH.getText(), (TipoGranoEntity) cbxSemillas.getSelectedItem(), txtMetrica.getText(),
                        (String) cbxMedida.getSelectedItem(), txtNombre.getText());


            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Ocurri? un error al cargar la orden de trabajo : " + e1.toString());
            } finally {
                JOptionPane.showMessageDialog(null, "La Orden de trabajo fue cargada con exito.");
                dispose();
            }
        });


//        btnAgregarLaboreo.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<LaboreoEntity> listaLaboreoEntity = planificacionRepository.getLaboreosByCampIdPlanificadaId(planificacionId);
//                borrarLstLaboreos();
//                cargarLaboreos(listaLaboreoEntity);
//            }
//        });
        btnGenerarOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = new Document();
                try {
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\jagm\\Desktop\\test.pdf"));
                    document.open();
                    PdfContentByte contentByte = writer.getDirectContent();
                    PdfTemplate template = contentByte.createTemplate(200, 600);
                    Graphics2D g2 = template.createGraphics(200, 400);
                    g2.scale(30,50);
                    panel1.print(g2);
                    g2.dispose();
                    contentByte.addTemplate(template, 30, 300);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                finally{
                    if(document.isOpen()){
                        document.close();
                    }
                }
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

//    private boolean existeLote() {
//        if (lstLotes.getSelectedValuesList().size() == 0) {
//            return false;
//        }
//        return true;
//    }

    private void limpiarPantalla() {
        inicializaTabla();
    }

//    private void cargarCampanias() {
//        CampaniaEntity camp;
//        Campania campania = new Campania();
//        java.util.List listaItems;
//        listaItems = gestor.getCampanias();
//
//        Iterator iter = listaItems.iterator();
//        while (iter.hasNext()) {
//            cboCampania.addItem(iter.next());
//        }
//
//        cboCampania.setSelectedItem(null);
//
//    }

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


    private void cargarLaboreos(List<LaboreoEntity> listaLaboreoEntity) {
        DefaultListModel modelo = new DefaultListModel();
//        List<LaboreoEntity> listaLaboreoSinOrden = new ArrayList<>();
//        for(LaboreoEntity laboreo :listaLaboreoSinOrden){
//            if(laboreo.)
//        }
        Iterator iter = listaLaboreoEntity.iterator();
        while (iter.hasNext()) {
            modelo.addElement(iter.next());
        }
        lstLaboreos.setModel(modelo);
    }


    private void borrarComboBoxCampania() {
//        cboCampania.removeAllItems();
    }

    private void borrarComboBoxTipoLaboreo() {
        cboMomentos.removeAllItems();
    }

    private void borrarLstLaboreos() {
        lstLaboreos.removeAll();
    }


//    //METODO CARGA COMBO CAMPANIA
//    private void cargaComboBoxCampania() {
//        Session session = Coneccion.getSession();
//        Query query = session.createQuery("SELECT p FROM CampaniaEntity p");
//        java.util.List<CampaniaEntity> listaCampaniaEntity = query.list();
//
////        Vector<String> miVectorcampania = new Vector<>();
//
////        Iterator iter = listaItems.iterator();
////        while (iter.hasNext()) {
////            cboCampania.addItem(iter.next());
////        }
//
////        cboCampania.setSelectedItem(null);
//
//        for (CampaniaEntity campania : listaCampaniaEntity) {
//            //System.out.println(tipoEstado.getTeMaNombre());
////            miVectorcampania.add(campania.getCnaDenominacion());
//            cboCampania.addItem(campania);
//            cboCampania.setSelectedItem(null);
//        }
//
//    }


    //METODO CARGA COMBO TIPO LABOREO
    private void cargaComboBoxTipoLaboreo() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM TipoLaboreoEntity p");
        java.util.List<TipoLaboreoEntity> listaTipoLaboreoEntity = query.list();

        Vector<String> miVectorTipoLaboreo = new Vector<>();
        for (TipoLaboreoEntity tipoLaboreo : listaTipoLaboreoEntity) {
            miVectorTipoLaboreo.add(tipoLaboreo.getTpoNombre());
            cboMomentos.addItem(tipoLaboreo);

//            cboCampania.setSelectedItem(null);
        }

    }


    //METODO CARGA COMBO TIPO GRANO
    private void cargaComboBoxTipoGrano() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM TipoGranoEntity p");
        java.util.List<TipoGranoEntity> listaTipoGranoEntity = query.list();

        Vector<String> miVectorTipoLaboreo = new Vector<>();
        for (TipoGranoEntity tipoGrano : listaTipoGranoEntity) {
            miVectorTipoLaboreo.add(tipoGrano.getTgrNombre());
            cbxSemillas.addItem(tipoGrano);

//            cboCampania.setSelectedItem(null);
        }

    }


    //METODO BUSCAR INSUMOS DE SOLICUTUDES
    private void buscarInsumosMaquinariasPorLaboreo(Integer laboreoId, Integer planificacionId) {

            //Carga insumos
            java.util.List<Object[]> listaIns;
            //getInsumos por laboreo
            listaIns = gestor.getInsumosByLaboreoAndPlanificacion(laboreoId, planificacionId);

            //Carga maquinarias
            java.util.List<Object[]> listaMaq;
            //getMaquinaria por laboreo
            listaMaq = gestor.getMaquinariasByLaboreoAndPlanificacion(laboreoId, planificacionId);


            Object[][] data = new Object[listaIns.size() + listaMaq.size()][4];
            int i = 0;
            if (listaIns.size() != 0) {
                for (Object[] row: listaIns) {
                    InsumoEntity insumoEntity = (InsumoEntity)row[0];
                    Integer cantidad = (Integer) row[1];

                    data[i][0] = "Insumo";
                    data[i][1] = insumoEntity.getInsNombre();
                    data[i][2] = insumoEntity.getTipoInsumoByInsTinId().getTinNombre();
                    data[i][3] = String.valueOf(cantidad);
                    i++;
                }
            }

            if (listaMaq.size() != 0) {
                for (Object[] row: listaMaq) {
                    MaquinariaEntity maquinariaEntity = (MaquinariaEntity)row[0];
                    Integer cantidad = (Integer) row[1];

                    data[i][0] =  "Maquinaria";
                    data[i][1] = maquinariaEntity.getMaqNombre();
                    data[i][2] = maquinariaEntity.getTipoMaquinariaByMaqTmaqId().getTmaNombre();
                    data[i][3] = String.valueOf(cantidad);
                    i++;
                }
            }


            String[] columnNames = {"Clasificacion", "Nombre", "Tipo", "Cantidad",};
            DefaultTableModel model = new DefaultTableModel();
            model.setDataVector(data, columnNames);
            tblDetalles.setModel(model);

//            return;

    }


    private void setModelInsumosMaquinarias(String[] columnames, Object[][] data) {
        modelInsumoMaquinaria = new DefaultTableModel();
        modelInsumoMaquinaria.setDataVector(data, columnames);
        tblDetalles.setModel(modelInsumoMaquinaria);
        tblDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblDetalles.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblDetalles.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblDetalles.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblDetalles.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblDetalles.getColumnModel().getColumn(5).setPreferredWidth(60);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
