package Laboreo;

import Conexion.Conexion;
import Date.DateLabelFormatter;
import Datos.*;
import Repository.InsumoRepository;
import Repository.LaboreoRepository;
import Repository.PlanificacionRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.*;
import java.util.List;

/**
 * Created by jagm on 11/11/2016.
 */
public class TicketPesada  extends JFrame {
    private JPanel panel1;
    private JTextField txtPeso;
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
    private JComboBox cboMomentos;
    public JList lstLaboreos;
    public JButton buttonFecha;
    public JTextField txtMedida;
    public JButton btnGenerarOrden;
    public JTextField txtFechaIni;
    public JTextField txtFechaFin;
    public JTextField txtNroOrden;
    public JTextField txtCampania;
    public JTextField txtLote;
    public JTextField txtLaboreo;
    public JTextField txtSemilla;
    public JTextField txtObservaciones;
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
    public JPanel panelIni;
    public JTextField txtNombre;
    private DefaultTableModel modelDetalle = new DefaultTableModel();
    private GestorLaboreo gestor = new GestorLaboreo();
    private String tipoOperacion;
    private DefaultTableModel modelInsumoMaquinaria;

    InsumoRepository insumoRepository = new InsumoRepository();
    LaboreoRepository laboreoRepository = new LaboreoRepository();
    PlanificacionRepository planificacionRepository = new PlanificacionRepository();

    public TicketPesada(String operacion, OrdenTrabajoEntity orden) {

        //INICIO
//        setContentPane(panel1);
//        pack();
        JPanel container = new JPanel();

        container.add(panelIni);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);
//        inicializaTabla();
//        cargarItems();
//        cargarMaquinas();
//        cargaComboBoxTipoLaboreo();
//        cargaComboBoxTipoGrano();
        setBounds(200,300,1500,800);
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Registrar Orden Trabajo Realizada");
        } else {
            this.setTitle("Modificar Avance Campania");
        }
//        inicializaTabla();
//        cargarLotes(camId);

        txtNroOrden.setText(orden.getNroOrden().toString());
        txtCampania.setText(orden.getPlanificacion().getCampania().getCnaDenominacion());
        txtLote.setText(orden.getLote().getLteDenominacion());
//        txtPeso.setText(orden.get());
        txtFechaIni.setText(orden.getPlanificacion().getCampania().getCnaFechaInicio().toString());
        txtFechaFin.setText(orden.getPlanificacion().getCampania().getCnaFechaFinReal().toString());
//        txtMedida.setText(orden.getTiempo());
        txtLaboreo.setText(orden.getLaboreo().getLboNombre());
        txtSemilla.setText(orden.getGrano().getTgrNombre());
//        PlanificacionCampaniaEntity planificacion = planificacionRepository.getPlanificadaById(planificacionId);
//        List<LaboreoEntity> listaLaboreoEntity = planificacionRepository.getLaboreosByCampIdPlanificadaId(planificacionId);
//        cargarLaboreos(listaLaboreoEntity);

//        lstLaboreos.addListSelectionListener(e -> buscarInsumosMaquinariasPorLaboreo((Integer) lstLaboreos.getSelectedValue(), planificacion.getPlanificacionId()));

//        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Registrar Peso de Laboreo");


        //BUTTON FECHA
        net.sourceforge.jdatepicker.impl.SqlDateModel modelIni = new net.sourceforge.jdatepicker.impl.SqlDateModel();
        modelIni.setDate(2017, 05, 28);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        net.sourceforge.jdatepicker.impl.JDatePanelImpl datePanelIni =
                new net.sourceforge.jdatepicker.impl.JDatePanelImpl(modelIni);
        //the formatter,  there it is...
        net.sourceforge.jdatepicker.impl.JDatePickerImpl datePickerIni =
                new net.sourceforge.jdatepicker.impl.JDatePickerImpl(datePanelIni, new DateLabelFormatter());


        buttonFecha.add(datePickerIni);


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

            java.sql.Date fecha = (java.sql.Date) datePickerIni.getModel().getValue();
            if(fecha == null){
                showMessage("Debe completar la fecha antes de continuar");
                return;
            }

            if(txtPeso.getText().equals("")){
                showMessage("Debe completar el peso antes de continuar");
                return;
            }

            if(txtObservaciones.getText().equals("")){
                showMessage("Debe completar observaciones antes de continuar");
                return;
            }

//            java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

            Session session = Conexion.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            TicketPesadaEntity ticket = new TicketPesadaEntity();

            ticket.setLaboreo(orden.getLaboreo());
            ticket.setOrdenTrabajoEntity(orden);
            ticket.setMedida(cbxMedida.getSelectedItem().toString());
            ticket.setPeso(txtPeso.getText());
            ticket.setObservaciones(txtObservaciones.getText());
            ticket.setUsuarioAlta("Admin");
            ticket.setFechaAlta(fecha);

            session.save(ticket);

            try {
                tx.commit();
                JOptionPane.showMessageDialog(null, "El Ticket de pesada fue cargado con exito.");
                dispose();
            } catch (Exception ex) {
                tx.rollback();
                JOptionPane.showMessageDialog(null, "Ocurrio un error al cargar el Ticket : " + ex.toString());

            }
            session.close();
        });


//        btnActualizar.addActionListener(new ActionListener() {
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
//        Session session = Conexion.getSessionFactory().openSession()
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
        Session session = Conexion.getSessionFactory().openSession();
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
        Session session = Conexion.getSessionFactory().openSession();
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
    private void buscarInsumosMaquinariasPorLaboreo(LaboreoEntity laboreo, Integer planificacionId) {

        //Carga insumos
        java.util.List<Object[]> listaIns;
        //getInsumos por laboreo
        listaIns = gestor.getInsumosByLaboreoAndPlanificacion(laboreo.getLboId(), planificacionId);

        //Carga maquinarias
        java.util.List<Object[]> listaMaq;
        //getMaquinaria por laboreo
        listaMaq = gestor.getMaquinariasByLaboreoAndPlanificacion(laboreo.getLboId(), planificacionId);


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
