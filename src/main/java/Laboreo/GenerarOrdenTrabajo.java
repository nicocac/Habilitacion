package Laboreo;

import Datos.*;
import Date.DateLabelFormatter;
import Insumo.Insumo;
import Maquinaria.Maquinaria;
import Procesos.PantallaRegistrarIngresoInsumo;
import Repository.InsumoRepository;
import Repository.LaboreoRepository;
import Repository.OrdenRepository;
import Repository.PlanificacionRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

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
    public JButton btnGenerarOrden;
    public JTextField txtFechaIni;
    public JTextField txtFechaFin;
    public JTextField txtNroOrden;
    public JTextField txtCamp;
    public JTextField txtTiempo;
    public JTextField txtObservaciones;
    public JTextField txtLote;
    public JTextField txtSemilla;
    public JPanel panelIni;
    public JTextField txtTiempoGastado;
    public JComboBox cbxRRHH;
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
    OrdenRepository ordenRepository = new OrdenRepository();

    PlanificacionCampaniaEntity planificacion = null;

    public GenerarOrdenTrabajo(String operacion, Integer planificacionId) {


        //INICIO
        JPanel container = new JPanel();
//        container.setPreferredSize(new Dimension(1920, 1900));
//        panel1.setPreferredSize(new Dimension(1900, 1800));
        container.add(panelIni);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);
//        setContentPane(panel1);
        pack();
        inicializaTabla();


        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Generar Ordenes de Trabajo");

            txtTiempoGastado.disable();

            planificacion = planificacionRepository.getPlanificadaById(planificacionId);
            txtCamp.setText(planificacion.getCampania().getCnaDenominacion());
            txtFechaIni.setText(planificacion.getCampania().getCnaFechaInicio().toString());
            txtFechaFin.setText(planificacion.getCampania().getCnaFechaFinReal().toString());
            //buscar size de todas las ordenes mas 1
            Integer orden = ordenRepository.getAllOrdenes().size();
            orden = orden + 1;
            txtNroOrden.setText(orden.toString());

            List<OrdenTrabajoLaboreo> listaLaboreoLote = planificacionRepository.getLaboreosByCampIdPlanificadaId(planificacionId);

            List<LaboreoEntity> listaLaboreoEntity = new ArrayList<>();

            for (OrdenTrabajoLaboreo ordenTrabajoLaboreo : listaLaboreoLote) {
                listaLaboreoEntity.add(ordenTrabajoLaboreo.getLaboreoEntity());
            }
//            cargarLaboreos(listaLaboreoEntity);
            cargarLaboreos(listaLaboreoLote);

//            lstLaboreos.addListSelectionListener(e ->
//                            buscarInsumosMaquinariasPorLaboreo((Integer) lstLaboreos.getSelectedValue(), planificacion.getPlanificacionId())
//
//
//            );

            lstLaboreos.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    lblLotes.setText(String.valueOf(lstLaboreos.getSelectedValuesList().size()));
                    buscarInsumosMaquinariasPorLaboreo((OrdenTrabajoLaboreo) lstLaboreos.getSelectedValue(), planificacion.getPlanificacionId());


                }
            });


//        this.setExtendedState(MAXIMIZED_BOTH);
            this.setTitle("Registrar Orden de trabajo");


        } else {
            this.setTitle("Modificar Ordenes de Trabajo");

            OrdenTrabajoEntity orden = ordenRepository.getOrdenById(planificacionId);
            txtCamp.setText(orden.getPlanificacion().getCampania().getCnaDenominacion());
            txtFechaIni.setText(orden.getPlanificacion().getCampania().getCnaFechaInicio().toString());
            txtFechaFin.setText(orden.getPlanificacion().getCampania().getCnaFechaFinReal().toString());
            txtNroOrden.setText(orden.getNroOrden().toString());
            txtTiempo.setText(orden.getTiempo().toString());
            txtTiempoGastado.setText((orden.getTiempoGastado() == null) ? "0" : orden.getTiempoGastado().toString());
            cbxRRHH.setSelectedItem(orden.getRecursoHumano().toString());
            txtObservaciones.setText(orden.getObservaciones().toString());
            Object[] laboreos = new Object[1];
            laboreos[0] = orden.getLaboreo().getLboNombre();
            lstLaboreos.setListData(laboreos);
//            breakEdit();
        }

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


        //GUARDAR
        btnFinalizar.addActionListener(e -> {
            if (lstLaboreos.getSelectedValuesList().size() == 0) {
                showMessage("Debe seleccionar el laboreo antes de continuar");
                return;
            }

            if (cbxRRHH.getSelectedItem().toString().equals("")) {
                showMessage("Debe completar RRHH antes de continuar");
                return;
            }
            if (txtTiempo.getText().equals("")) {
                showMessage("Debe completar tiempo antes de continuar");
                return;
            }
            if (txtObservaciones.getText().equals("")) {
                showMessage("Debe completar las observaciones antes de continuar");
                return;
            }

            GestorLaboreo gest = new GestorLaboreo();
            ArrayList<DetalleLaboreo> detalles = new ArrayList<DetalleLaboreo>();
            Boolean sinStock = false;
            Boolean stockFaltante = false;
            String insumosSinStock = "";
            String insumosFaltantes = "";

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

                    Integer stockIns = (insumoEntity.getInsStock() == null) ? 0 : insumoEntity.getInsStock().intValue();
                    Integer cantidadFaltante = stockIns - Integer.parseInt((String) tblDetalles.getValueAt(i, 3));
                    if (cantidadFaltante < 0) {
                        stockFaltante = true;
                        if (insumosFaltantes.equals("")) {
                            insumosFaltantes = insumoEntity.getInsNombre();
                        } else {
                            insumosFaltantes = insumosFaltantes + ", " + insumoEntity.getInsNombre();
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

            if (sinStock) {
                int respuesta = JOptionPane.showConfirmDialog(null, "Los siguientes insumos: " + insumosSinStock + " se encuentran sin stock. Desea Registrar el ingreso de insumos solicitados previamente?", "Cuidado", JOptionPane.YES_NO_OPTION);
                if (respuesta == 0) {
//                        dispose();
                    PantallaRegistrarIngresoInsumo pantallaAdministrarSolicitudInsumos = new PantallaRegistrarIngresoInsumo();
                    pantallaAdministrarSolicitudInsumos.setVisible(true);
                    getDefaultCloseOperation();
                    return;
                } else {
                    return;
                }
            }

            if (stockFaltante) {
                int respuesta = JOptionPane.showConfirmDialog(null, "Los siguientes insumos: " + insumosFaltantes + " a la fecha no alcanzan la cantidad solicitada en stock. Desea Registrar el ingreso de insumos solicitados previamente?", "Cuidado", JOptionPane.YES_NO_OPTION);
                if (respuesta == 0) {
                    PantallaRegistrarIngresoInsumo pantallaAdministrarSolicitudInsumos = new PantallaRegistrarIngresoInsumo();
                    pantallaAdministrarSolicitudInsumos.setVisible(true);
                    getDefaultCloseOperation();
                    return;
                } else {
                    return;
                }
            }

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

            Date fecha = (Date) datePickerIni.getModel().getValue();
            if (fecha == null) {
                showMessage("Debe completar la fecha antes de continuar");
                return;
            }
            try {

                OrdenTrabajoLaboreo laboreo = (OrdenTrabajoLaboreo) lstLaboreos.getSelectedValue();

                LaboreoEntity laboreoEntity = laboreo.getLaboreoEntity();
                LoteEntity loteEntity = laboreo.getLoteEntity();
                TipoGranoEntity tipoGranoEntity = laboreo.getSemilla();

                gest.registrarOrden(detalles, txtNroOrden.getText(), planificacion,
                        cbxRRHH.getSelectedItem().toString(), fecha, txtTiempo.getText(), laboreoEntity, loteEntity, tipoGranoEntity);


            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar la orden de trabajo : " + e1.toString());
                return;
            } finally {
                JOptionPane.showMessageDialog(null, "La Orden de trabajo fue cargada con exito.");
                dispose();
            }
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
                    PdfTemplate template = contentByte.createTemplate(700, 300);
                    Graphics2D g2 = template.createGraphics(700, 300);
                    g2.scale(0.25, 0.25);
                    panelIni.print(g2);
                    g2.dispose();
                    contentByte.addTemplate(template, 30, 500);
                    try {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "C:\\Users\\jagm\\Desktop\\test.pdf");
                    } catch (IOException io) {
                        Logger.getLogger(GenerarOrdenTrabajo.class.getName()).log(Level.SEVERE, null, io);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                } finally {
                    if (document.isOpen()) {
                        document.close();
                    }
                }
//                    dispose();
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
        String[] columnNamesCompra = {"Clasificacion", "Nombre", "Tipo", "Cantidad", "Stock"};
        Object[][] data = new Object[1][5];
        setModelDetalles(columnNamesCompra, data);

    }

    private void breakEdit() {
        return;

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
        tblDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDetalles.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(4).setPreferredWidth(150);
        col = tblDetalles.getColumnModel().getColumn(4);
        col.setCellEditor(new MyTableCellEditor());
        tblDetalles.setCellSelectionEnabled(true);
    }




    private void limpiarPantalla() {
        inicializaTabla();
    }



    private void showMessage(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }


    private void cargarLaboreos(List<OrdenTrabajoLaboreo> listaLaboreoEntity) {
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


    //METODO BUSCAR INSUMOS DE SOLICUTUDES
    private void buscarInsumosMaquinariasPorLaboreo(OrdenTrabajoLaboreo laboreo, Integer planificacionId) {

        LaboreoEntity laboreoEntity = laboreo.getLaboreoEntity();
        LoteEntity loteEntity = laboreo.getLoteEntity();
        TipoGranoEntity tipoGranoEntity = laboreo.getSemilla();

        //Carga insumos
        java.util.List<Object[]> listaIns;
        //getInsumos por laboreo
        listaIns = gestor.getInsumosByLaboreoAndPlanificacion(laboreoEntity.getLboId(), planificacionId);

        //Carga maquinarias
        java.util.List<Object[]> listaMaq;
        //getMaquinaria por laboreo
        listaMaq = gestor.getMaquinariasByLaboreoAndPlanificacion(laboreoEntity.getLboId(), planificacionId);


        Object[][] data = new Object[listaIns.size() + listaMaq.size()][5];
        int i = 0;
        if (listaIns.size() != 0) {
            for (Object[] row : listaIns) {
                InsumoEntity insumoEntity = (InsumoEntity) row[0];
                Integer cantidad = (Integer) row[1];

                data[i][0] = "Insumo";
                data[i][1] = insumoEntity.getInsNombre();
                data[i][2] = insumoEntity.getTipoInsumoByInsTinId().getTinNombre();
                data[i][3] = String.valueOf(cantidad);
                data[i][4] = String.valueOf(insumoEntity.getInsStock());
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


        String[] columnNames = {"Clasificacion", "Nombre", "Tipo", "Cantidad", "Stock"};
        DefaultTableModel model = new DefaultTableModel();
        model.setDataVector(data, columnNames);
        tblDetalles.setModel(model);
        tblDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDetalles.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(4).setPreferredWidth(150);
        TableColumn col= tblDetalles.getColumnModel().getColumn(4);
        col.setCellEditor(new MyTableCellEditor());
        tblDetalles.setCellSelectionEnabled(true);

//            return;

    }


    private void setModelInsumosMaquinarias(String[] columnames, Object[][] data) {
        modelInsumoMaquinaria = new DefaultTableModel();
        modelInsumoMaquinaria.setDataVector(data, columnames);
        tblDetalles.setModel(modelInsumoMaquinaria);
        tblDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblDetalles.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblDetalles.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblDetalles.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblDetalles.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblDetalles.getColumnModel().getColumn(4).setPreferredWidth(50);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
