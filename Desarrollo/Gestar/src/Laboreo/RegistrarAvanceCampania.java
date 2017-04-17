package Laboreo;

import Acopio.Acopio.CargaAcopio;
import Campania.Campania;
import Campania.GestorCampania;
import Conexion.Coneccion;
import Date.DateLabelFormatter;
import Datos.*;
import Granos.TipoGrano;
import Insumo.Insumo;
import Lote.Lote;
import Maquinaria.Maquinaria;
import Repository.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by jagm on 23/10/2016.
 */
public class RegistrarAvanceCampania extends JFrame {
    private JButton btnFinalizar;
    private JList lstLotes;
    private JLabel lblLotes;
    private JTextField txtCampania;
    private JButton cancelarButton;
    public JTextField txtFechaFin;
    public JButton BtnFechaFin;
    public JButton BtnFechaIni;
    public JButton nuevoLoteBtn;
    public JButton actualizarLotesBtn;
    public JComboBox cboMomentos;
    public JComboBox cbxSemillas;
    public JTextField txtFechaIni;
    public JTextField txtCantidad;
    public JComboBox cbxEstado;
    public JTable tblDetalles;
    public JButton btnCargarPesada;
    public JTextField txtSemilla;
    public JTextField txtNroOrden;
    public JTextField txtLote;
    public JTextField txtRRHH;
    public JTextField txtLaboreo;
    public JTextField txtTiempo;
    public JComboBox cbxAcopio;
    public JButton nvoBtnAcopio;
    public JTextField txtTipoAcopio;
    public JButton btnActualizar;
    public JButton btnActualizarPeso;
    public JButton btnFinalizarOrden;
    public JPanel panel1;
    public JComboBox cbxMedida;
    public JTextField txtObservaciones;
    public JPanel panelIni;
    public JTextField txtNroAcopio;
    public JTextField txtNombreAcopio;
    public JTextField txtCantidadAcopioSoportdada;
    public JTextField txtCantidadActualAcopio;
    private DefaultTableModel modelDetalle = new DefaultTableModel();
    private GestorLaboreo gestorLab = new GestorLaboreo();
    private DefaultTableModel modelInsumoMaquinaria;


    private String tipoOperacion;
    private int cnaId;
    private GestorCampania gestor = new GestorCampania();
    LaboreoLoteCampaniaRepository laboreoLoteCampaniaRepository = new LaboreoLoteCampaniaRepository();
    LoteRepository loteRepository = new LoteRepository();
    TipoGranoRepository tipoGranoRepository = new TipoGranoRepository();
    InsumoRepository insumoRepository = new InsumoRepository();
    LaboreoRepository laboreoRepository = new LaboreoRepository();
    PlanificacionRepository planificacionRepository = new PlanificacionRepository();
    OrdenRepository ordenRepository = new OrdenRepository();
    TipoAcopioRepository tipoAcopioRepository = new TipoAcopioRepository();
    AcopioRepository acopioRepository = new AcopioRepository();
    TicketPesadaRepository ticketPesadaRepository = new TicketPesadaRepository();

    public RegistrarAvanceCampania(String operacion, OrdenTrabajoEntity orden) {

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
        txtRRHH.setText(orden.getRecursoHumano());
        txtFechaIni.setText(orden.getPlanificacion().getCampania().getCnaFechaInicio().toString());
        txtFechaFin.setText(orden.getPlanificacion().getCampania().getCnaFechaFinReal().toString());
        txtTiempo.setText(orden.getTiempo());
        txtLaboreo.setText(orden.getLaboreo().getLboNombre());
        txtSemilla.setText(orden.getGrano().getTgrNombre());

        buscarInsumosMaquinariasPorLaboreo(orden.getLaboreo(), orden.getPlanificacion().getPlanificacionId());

//        if (denominacion.length() > 1 && camId != 0) {

//            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//            Date dateInicio = new java.sql.Date(2016,10,20);
//            Date dateFin = new java.sql.Date(2016,10,20);
//
//            try {
//
//                 dateInicio = formatter.parse(fechaInicio);
//                 dateFin = formatter.parse(fechaFin);
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }


//            txtCampania.setText(denominacion);
//            txtFechaIni.setText(fechaInicio.toString());
//            txtFechaFin.setText(fechaFin.toString());
//            modelIni.setValue(fechaInicio);
//            modelIni.setValue((java.sql.Date) dateInicio);
//            modelFin.setValue((java.sql.Date) dateFin);
//            modelFin.setValue(fechaFin);
//            cnaId = camId;
//        }


//        //Fecha
//        SqlDateModel modelIni = new SqlDateModel();
//        SqlDateModel modelFin = new SqlDateModel();
//        modelIni.setDate(2016, 04, 20);
//        modelFin.setDate(2016, 04, 20);
//        // Need this...
//        Properties p = new Properties();
//        p.put("text.today", "Today");
//        p.put("text.month", "Month");
//        p.put("text.year", "Year");
//        JDatePanelImpl datePanelIni = new JDatePanelImpl(modelIni, p);
//        JDatePanelImpl datePanelFin = new JDatePanelImpl(modelFin, p);
//        //the formatter,  there it is...
//        JDatePickerImpl datePickerIni = new JDatePickerImpl(datePanelIni, new DateLabelFormatter());
//        JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanelFin, new DateLabelFormatter());
//
//        BtnFechaIni.add(datePickerIni);
//        BtnFechaFin.add(datePickerFin);
        //


        //MODIFICAR
//        if (denominacion.length() > 1 && camId != 0) {

//            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//            Date dateInicio = new java.sql.Date(2016,10,20);
//            Date dateFin = new java.sql.Date(2016,10,20);
//
//            try {
//
//                 dateInicio = formatter.parse(fechaInicio);
//                 dateFin = formatter.parse(fechaFin);
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }


//            txtCampania.setText(denominacion);
//            modelIni.setValue(fechaInicio);
//            modelIni.setValue((java.sql.Date) dateInicio);
//            modelFin.setValue((java.sql.Date) dateFin);
//            modelFin.setValue(fechaFin);
//            cnaId = camId;
//        }

        //Registrar Ingreso Acopio
        btnFinalizar.addActionListener(e -> {
            if (validaCarga().equals("S")) {
                try {


                    if(txtCantidad.getText().equals("")){
                        showMessage("Debe completar Cantidad antes de continuar");
                        return;
                    }
                    if(txtTiempo.getText().equals("")){
                        showMessage("Debe completar tiempo antes de continuar");
                        return;
                    }
                    if(txtObservaciones.getText().equals("")){
                        showMessage("Debe completar las observaciones antes de continuar");
                        return;
                    }
//                    Date selectedDateIni = (Date) datePickerIni.getModel().getValue();
//                    Date selectedDateFin = (Date) datePickerFin.getModel().getValue();
//                    Date selectedDateFinReal = (Date) datePickerFin.getModel().getValue();
//                    campania.setFechaInicio((java.sql.Date) selectedDateIni);
//                    campania.setFechaFinEstimada((java.sql.Date) selectedDateFin);
//                    campania.setFechaFinReal((java.sql.Date) selectedDateFinReal);

//                    gestor.registrarCampania(campania, tipoOperacion, cnaId);
//                    gestor.registrarIngresoAcopio(campania, tipoOperacion, cnaId);

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

                    try {
                        AcopioEntity acopioEntity = acopioRepository.getAcopioByCodigo(Integer.parseInt(cbxAcopio.getSelectedItem().toString()));

                        java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());

                        java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTimeInMillis());


                        gest.registrarAvanceOrdenIngreso(fecha, detalles, orden, txtRRHH.getText(), txtTiempo.getText(), txtCantidad.getText(),
                                (String) cbxEstado.getSelectedItem(), acopioEntity);

                        JOptionPane.showMessageDialog(null, "El avance de la Orden de trabajo fue cargada con exito.");
                        dispose();

                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar el avance de la orden : " + e1.toString());
                    }
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar los campos requeridos antes de continuar");
                    return;
                }
            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> {
            dispose();
        });


        btnCargarPesada.addActionListener(e -> {
            TicketPesada cargaTicketPesada = new TicketPesada("Carga", orden);
            cargaTicketPesada.setVisible(true);
            getDefaultCloseOperation();
        });


        //NUEVO ACOPIO
        nvoBtnAcopio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargaAcopio cargaAcopio = new CargaAcopio("Carga", "", 0, 0);
                cargaAcopio.setVisible(true);
                getDefaultCloseOperation();
            }
        });

//        cbxAcopio.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                super.focusGained(e);
//                borrarComboBoxAcopio();
//                cargaComboBoxAcopio();
//            }
//        });


        cbxAcopio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cargaTipoAcopio
//                AcopioEntity acopioEntity = acopioRepository.getAcopioByCodigo(Integer.parseInt(cbxAcopio.getSelectedItem().toString()));
//                if (acopioEntity != null){
//                    txtTipoAcopio.setText(acopioEntity.getTipoAcopioEntity().getTipoAcopioNombre());
//                } else {
//                    txtTipoAcopio.setText("No encontrado");
//                }
            }
        });


        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarComboBoxAcopio();
                cargaComboBoxAcopio();
            }
        });


        cbxAcopio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (cbxAcopio.getSelectedItem() == null) {
                    return;
                }
                AcopioEntity acopioEntity = acopioRepository.getAcopioByCodigo(Integer.parseInt(cbxAcopio.getSelectedItem().toString()));
                if (acopioEntity != null) {
                    txtTipoAcopio.setText(acopioEntity.getTipoAcopioEntity().getTipoAcopioNombre());
                    txtNroAcopio.setText(acopioEntity.getCodigo().toString());
                    txtNombreAcopio.setText(acopioEntity.getNombre());
                    txtCantidadAcopioSoportdada.setText(acopioEntity.getCantidadSoportada().toString());
                    txtCantidadActualAcopio.setText(acopioEntity.getCantidadGrano().toString());

                } else {
                    txtTipoAcopio.setText("No encontrado");
                }
            }
        });


        //FINALIZAR ALL ORDEN
        btnFinalizarOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

                if(fecha == null){
                    showMessage("Debe completar la fecha antes de continuar");
                    return;
                }

                if(txtCantidad.getText().equals("")){
                    showMessage("Debe completar Cantidad antes de continuar");
                    return;
                }
                if(txtTiempo.getText().equals("")){
                    showMessage("Debe completar tiempo antes de continuar");
                    return;
                }
                if(txtObservaciones.getText().equals("")){
                    showMessage("Debe completar las observaciones antes de continuar");
                    return;
                }

                Session session = Coneccion.getSession();
                Transaction tx = session.beginTransaction();

                orden.setObservaciones("Modifica de una orden");
                orden.setRecursoHumano(txtRRHH.getText());
                orden.setFechaAlta(fecha);
                orden.setTiempo(txtTiempo.getText());
                orden.setEstaRegistrada(true);

                session.update(orden);

                try {
                    tx.commit();
                    JOptionPane.showMessageDialog(null, "El Ticket de pesada fue cargado con exito.");
                    dispose();
                } catch (Exception ex) {
                    tx.rollback();
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al cargar el avance de la orden : " + ex.toString());

                }
                session.close();

            }
        });
        btnActualizarPeso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                find ticket by orden
                TicketPesadaEntity ticket = ticketPesadaRepository.getTicketByOrdenId(orden.getId());
                txtCantidad.setText(ticket.getPeso());
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

//    private void inicializaTabla() {
//        String[] columnNamesCompra = {"Tipo", "Descripcion", "Unidad de Medida", "Cantidad"};
//        Object[][] data = new Object[1][5];
//
//    }


    private boolean existeLote() {
        if (lstLotes.getSelectedValuesList().size() == 0) {
            return false;
        }
        return true;
    }

//    private void limpiarPantalla() {
//        inicializaTabla();
//    }


    private void cargarLotes(int camId) {
        java.util.List lista;
        DefaultListModel modelo = new DefaultListModel();

        if (tipoOperacion.equals("Carga")) {
            lista = gestor.getLotes();
        } else {
            lista = gestor.getLotesByCampania(camId);
        }
        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            modelo.addElement(iter.next());
        }
        lstLotes.setModel(modelo);


    }


    //METODO BUSCAR LABOREO DE LOTE
    private void buscarLaboreoPorLote(int camId) {
        Session session = Coneccion.getSession();
        LaboreoEntity laboreo;
        Lote lote = (Lote) lstLotes.getSelectedValue();
        LoteEntity loteEntity = loteRepository.getLoteByDenominacion(lote.getDenominacion());
        Integer nroLote = loteEntity.getLteId();
        int i = 0;
        try {
            List<LaboreoEntity> listaLaboreoEntity;
            listaLaboreoEntity = laboreoLoteCampaniaRepository.getAllLaboreoByLoteAndCampania(nroLote, camId);

//            for(LaboreoEntity lab:listaLaboreoEntity){
//                lab.getTipoGrano()
//            }

            Iterator iter = listaLaboreoEntity.iterator();
            while (iter.hasNext()) {
                laboreo = (LaboreoEntity) iter.next();
                cboMomentos.addItem(laboreo.getTipoLaboreoEntity());
                cbxSemillas.addItem(laboreo.getTipoGrano());
                i++;
            }
        } finally {
            session.close();
        }
    }


    //METODO BUSCAR INSUMOS DE SOLICUTUDES
    private void buscarInsumosMaquinariasPorLaboreo(LaboreoEntity laboreoEntity, Integer planificacionId) {

//        LaboreoEntity laboreoEntity = laboreo.getLaboreoEntity();
//        LoteEntity loteEntity = laboreo.getLoteEntity();
//        TipoGranoEntity tipoGranoEntity = laboreo.getSemilla();

//        txtLote.setText(loteEntity.getLteDenominacion());
//        txtSemilla.setText(tipoGranoEntity.getTgrNombre());

        //Carga insumos
        java.util.List<Object[]> listaIns;
        //getInsumos por laboreo
        listaIns = gestorLab.getInsumosByLaboreoAndPlanificacion(laboreoEntity.getLboId(), planificacionId);

        //Carga maquinarias
        java.util.List<Object[]> listaMaq;
        //getMaquinaria por laboreo
        listaMaq = gestorLab.getMaquinariasByLaboreoAndPlanificacion(laboreoEntity.getLboId(), planificacionId);


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


        String[] columnNames = {"Clasificacion", "Nombre", "Tipo", "Cantidad",};
        DefaultTableModel model = new DefaultTableModel();
        model.setDataVector(data, columnNames);
        tblDetalles.setModel(model);

//            return;

    }


    //---CARGA DE COMBO BOXS

    //METODO CARGA COMBO  Acopio
    private void cargaComboBoxAcopio() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM AcopioEntity p");
        java.util.List<AcopioEntity> listaTipoMaquinaria = query.list();

        Vector<String> miVectorTipoMaquinaria = new Vector<>();
        for (AcopioEntity tipoMaquinaria : listaTipoMaquinaria) {
            //System.out.println(tipoMaquinaria.getTeMaNombre());
            miVectorTipoMaquinaria.add(tipoMaquinaria.getNombre());
            cbxAcopio.addItem(tipoMaquinaria);
        }

    }

    private void borrarComboBoxAcopio() {
        cbxAcopio.removeAllItems();
    }


    private void showMessage(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }


    //METODO VALIDAR CARGA
    private String validaCarga() {
        if (txtCampania.getText().replaceAll(" ", "").length() == 0 && txtCantidad.getText() == null) return "N";
        return "S";
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
