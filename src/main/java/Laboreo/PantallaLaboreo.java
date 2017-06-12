package Laboreo;

import Campania.Campania;
import Campania.CargaCampania;
import Conexion.Conexion;
import Date.DateLabelFormatter;
import Datos.*;
import Granos.CargaTipoGrano;
import Insumo.CargaInsumo;
import Insumo.Insumo;
import Maquinaria.CargaMaquinaria;
import Maquinaria.Maquinaria;
import Procesos.PantallaSolicitudInsumos;
import Repository.CampaniaRepository;
import Repository.InsumoRepository;
import Repository.LaboreoRepository;
import Repository.LoteRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


/**
 * Created by OWNER on 8/15/2016.
 */
public class PantallaLaboreo extends JFrame {
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
    public JButton btnAgregarLaboreo;
    public JTable tableLaboreos;
    public JTextField txtFechaIni;
    public JTextField txtFechaFin;
    public JButton btnQuitarInsumosOMaq;
    public JButton btnQuitarLaboreos;
    public JButton actualizarLaboreo;
    public JButton btnGenerarPedidoInsumo;
    public JButton btnAbajo;
    public JButton btnArriba;
    private DefaultTableModel modelDetalle = new DefaultTableModel();
    private DefaultTableModel modelLaboreos = new DefaultTableModel();
    private GestorLaboreo gestor = new GestorLaboreo();
    private String tipoOperacion;

    InsumoRepository insumoRepository = new InsumoRepository();
    LaboreoRepository laboreoRepository = new LaboreoRepository();
    LoteRepository loteRepository = new LoteRepository();
    CampaniaRepository campaniaRepository = new CampaniaRepository();

    DetalleLote detalleLote = null;
    ArrayList<DetalleLaboreos> listaDetalleDeLaboreos = new ArrayList<>();

    Boolean sinStock = false;
    Boolean stockFaltante = false;
    String insumosSinStock = "";
    String insumosFaltantes = "";

    public PantallaLaboreo() {


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
//        this.set
//        tipoOperacion = operacion;
//        if (tipoOperacion.equals("Carga")) {
//            this.setTitle("Cargar Tipo de Insumo");
//        } else {
//            this.setTitle("Modificar Tipo de Insumo");
//        }

//        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Planificar Campaña");
        inicializaTabla();
        inicializaTablaLaboreos();
        cargarItems();
        cargarMaquinas();
        cargarCampanias();
        cargarCbxSemillas();
//        cargarMomentos();
        cargaComboBoxLaboreo();


        Planificacion planificacion = new Planificacion();
        ArrayList<DetalleLote> listaDetalleDeLotes = new ArrayList<>();
//        ArrayList<DetalleLaboreos> listaDetalleDeLaboreos = new ArrayList<>();

        cboCampania.addActionListener(e ->
                        cargarLotes((Campania) cboCampania.getSelectedItem())

        );

//        cboCampania.addActionListener(e -> {
//                    cargarLotes((Campania) cboCampania.getSelectedItem());
//                    CampaniaEntity campaniaEntity = (CampaniaEntity) cboCampania.getSelectedItem();
////                    txtFechaIni.setText(campaniaEntity.getCnaFechaInicio().toString());
////                    txtFechaFin.setText(campaniaEntity.getCnaFechaFinEstimada().toString());
//                }
//        );

        lstLotes.addListSelectionListener(e -> lblLotes.setText(String.valueOf(lstLotes.getSelectedValuesList().size())));

        //BUTTON FECHA
        net.sourceforge.jdatepicker.impl.SqlDateModel modelIni = new net.sourceforge.jdatepicker.impl.SqlDateModel();
        modelIni.setDate(2017, 12, 28);

        net.sourceforge.jdatepicker.impl.JDatePanelImpl datePanelIni =
                new net.sourceforge.jdatepicker.impl.JDatePanelImpl(modelIni);
        //the formatter,  there it is...
        net.sourceforge.jdatepicker.impl.JDatePickerImpl datePickerIni =
                new net.sourceforge.jdatepicker.impl.JDatePickerImpl(datePanelIni, new DateLabelFormatter());

        buttonFecha.add(datePickerIni);
        //

        //AGREGAR INSUMO
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
//                if (permiteSeleccion("Insumo", ins.getNombre())) {
                if (fila == 0) {
                    tblDetalles.setValueAt("Insumo", fila, 0);
                    tblDetalles.setValueAt(ins.getNombre(), fila, 1);

//                    Integer tipoId = 1;
//                    //FIND ALL INSUMOS BY TIPO
//                    List<InsumoEntity> listaInsumos = insumoRepository.getAllInsumosByTipo(tipoId);
                    tblDetalles.setValueAt(ins.getUnidadMedida(), fila, 2);
////                            String[] DATA = { "Dato 1", "Dato 2", "Dato 3", "Dato 4" };
//                    String[] DATA = {};
//                    int i = 0;
//                    for (InsumoEntity nombreInsumo : listaInsumos) {
//                        DATA[i] = nombreInsumo.getInsNombre();
//                        i++;
//                    }
//                    JComboBox comboBox = new JComboBox(DATA);
//                    DefaultCellEditor defaultCellEditor = new DefaultCellEditor(comboBox);
//                    tblDetalles.getColumnModel().getColumn(2).setCellEditor(defaultCellEditor);
                    tblDetalles.setValueAt("1", fila, 3);
                    tblDetalles.setValueAt(ins.getStock(), fila, 4);
                    tblDetalles.setValueAt(ins.getStock(), fila, 5);
                    fila++;
                } else {
                    modelDetalle.addRow(new Object[]{"Insumo"
                            , ins.getNombre()
                            , ins.getUnidadMedida()
                            , "1"
                            , ins.getStockDisponible()
                            , ins.getStock()});
                    fila++;
                }
//                }
            }
        });


        //AGREGAR MAQUINARIA
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
//                if (permiteSeleccion("Maquinaria", maq.getNombre())) {
                if (fila == 0) {
                    tblDetalles.setValueAt("Maquinaria", fila, 0);
                    tblDetalles.setValueAt(maq.getNombre(), fila, 1);
                    tblDetalles.setValueAt(maq.getMarca(), fila, 2);
                    tblDetalles.setValueAt("1", fila, 3);
                    fila++;
                } else {
                    modelDetalle.addRow(new Object[]{"Maquinaria"
                            , maq.getNombre() //+ ", " + maq.getDescripcion() + ", " + maq.getModeloAnio()
                            , maq.getMarca()
                            , "1"});
                    fila++;
                }
//                }
            }
        });


        //AGREGAR LABOREO
        btnAgregarLaboreo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//
                if (cboMomentos.getSelectedItem() == null) {
                    showMessage("Debe seleccionar al menos un laboreo para continuar.");
                    return;
                }

                if (!existeLote()) {
                    showMessage("Debe seleccionar al menos un lote para continuar.");
                    return;
                }

                LaboreoEntity laboreo = (LaboreoEntity) cboMomentos.getSelectedItem();
                LoteEntity loteEntity = null;
                ArrayList lotes = (ArrayList) lstLotes.getSelectedValuesList();
                Iterator iter = lotes.iterator();
                while (iter.hasNext()) {
                    Lote.Lote lote = (Lote.Lote) iter.next();
                    loteEntity = loteRepository.getLoteByDenominacion(lote.getDenominacion());
                }
                //   LoteEntity lote = (LoteEntity) lstLotes.getSelectedValuesList().get(0);
                TipoGranoEntity grano = (TipoGranoEntity) cbxSemillas.getSelectedItem();

                if (detalleLote == null) {
                    detalleLote = new DetalleLote();
                    listaDetalleDeLaboreos = new ArrayList<>();
                    detalleLote.setLoteEntity(loteEntity);
//                    detalleLote.setLaboreoEntity(laboreo);
                    listaDetalleDeLotes.add(detalleLote);
                } else {
                    Boolean existe = false;
                    if (!detalleLote.getLoteEntity().equals(loteEntity)) {
                        //verificar si ya existe
                        for (DetalleLote lote : listaDetalleDeLotes) {
                            if (lote.getLoteEntity().equals(loteEntity)) {
                                detalleLote = lote;
                                detalleLote.setListaDetalleLaboreos(lote.getListaDetalleLaboreos());
                                for (DetalleLaboreos laboreos : detalleLote.getListaDetalleLaboreos()) {
                                    if (laboreos.getLaboreoEntity().equals(laboreo)) {
                                        JOptionPane.showMessageDialog(null, "El Laboreo ya esta cargado para ese lote, seleccione otro laboreo");
                                        return;
                                    }
                                }
                                existe = true;
                                break;

                            }
                        }
                        if (!existe) {
                            detalleLote = new DetalleLote();
                            listaDetalleDeLaboreos = new ArrayList<>();
                            detalleLote.setLoteEntity(loteEntity);
//                    detalleLote.setLaboreoEntity(laboreo);
                            listaDetalleDeLotes.add(detalleLote);
                        }
                    }
                    for (DetalleLaboreos laboreos : detalleLote.getListaDetalleLaboreos()) {
                        if (laboreos.getLaboreoEntity().equals(laboreo)) {
                            JOptionPane.showMessageDialog(null, "El Laboreo ya esta cargado para ese lote, seleccione otro laboreo");
                            return;
                        }
                    }
                }


                DetalleLaboreos detalleLaboreos = new DetalleLaboreos();
                detalleLaboreos.setLaboreoEntity(laboreo);
                detalleLaboreos.setTipoGranoEntity(grano);

                //Cargar detalles de insumos y maquinarias
//                        ArrayList<DetalleLaboreo> detalles = new ArrayList<DetalleLaboreo>();
                ArrayList<DetalleLaboreo> listaDetallesInsMaq = new ArrayList<>();


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

                        Integer stockIns = (insumoEntity.getInsStockDisponible() == null) ? 0 : insumoEntity.getInsStockDisponible().intValue();
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
                        Maquinaria maq = new Maquinaria((String) tblDetalles.getValueAt(i, 1), null, null, null, null, null, null);
                        det.setMaquinaria(maq);
                        det.setCantidadMaquinaria(Integer.parseInt((String) tblDetalles.getValueAt(i, 3)));

                    }
                    listaDetallesInsMaq.add(det);
                }

                listaDetalleDeLaboreos.add(detalleLaboreos);
                detalleLote.setListaDetalleLaboreos(listaDetalleDeLaboreos);
                detalleLaboreos.setListaDetalleLaboreo(listaDetallesInsMaq);
//                ArrayList lotes = (ArrayList) lstLotes.getSelectedValuesList();


                int fila = tableLaboreos.getRowCount() - 1;
                if (tableLaboreos.getRowCount() != 0) {
                    fila = tableLaboreos.getRowCount() - 1;
                    if (tableLaboreos.getValueAt(fila, 1) != null) {
                        if (!tableLaboreos.getValueAt(fila, 1).equals("")) {
                            fila++;
                        }
                    }
                }
//                Laboreo laboreo = (Laboreo) iter.next();
                if (fila == 0) {
                    tableLaboreos.setValueAt(fila+1, fila, 0);
                    tableLaboreos.setValueAt(laboreo.getLboNombre(), fila, 1);
                    tableLaboreos.setValueAt(laboreo.getLboDescripcion(), fila, 2);
                    tableLaboreos.setValueAt(loteEntity.getLteDenominacion(), fila, 3);
                    tableLaboreos.setValueAt(grano.getTgrNombre(), fila, 4);
                    fila++;
                } else {
                    modelLaboreos.addRow(new Object[]{fila+1
                            , laboreo.getLboNombre()
                            , laboreo.getLboDescripcion()
                            , loteEntity.getLteDenominacion()
                            , grano.getTgrNombre()});
                    fila++;
                }


            }
        });


        //GUARDAR
        btnFinalizar.addActionListener(e -> {

                    if (cboCampania.getSelectedItem() == null) {
                        showMessage("Debe seleccionar campaña antes de continuar");
                        return;
                    }
                    if (lstLotes.getSelectedValuesList().size() == 0) {
                        showMessage("Debe seleccionar lote antes de continuar");
                        return;
                    }
                    if (cboMomentos.getSelectedItem() == null) {
                        showMessage("Debe seleccionar laboreo antes de continuar");
                        return;
                    }
                    if (cbxSemillas.getSelectedItem() == null) {
                        showMessage("Debe seleccionar semilla antes de continuar");
                        return;
                    }
                    if (txtDescripcion.getText().equals("")) {
                        showMessage("Debe completar la descripcion antes de continuar");
                        return;
                    }

                    ////////
                    if (sinStock) {
//                        JOptionPane.showOptionDialog(null, "Se encuentran insumos sin stock. Desea Solicitar el Pedido del mismo", "Cuidado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,null);
                        int respuesta = JOptionPane.showConfirmDialog(null, "Los siguientes insumos: " + insumosSinStock + " se encuentran sin stock. No puede completar la planificacion de la misma. Desea Solicitar el Pedido de los mismos?", "Cuidado", JOptionPane.YES_NO_OPTION);
                        if (respuesta == 0) {
//                        dispose();
                            PantallaSolicitudInsumos pantallaAdministrarSolicitudInsumos = new PantallaSolicitudInsumos(insumosSinStock);
                            pantallaAdministrarSolicitudInsumos.setVisible(true);
                            getDefaultCloseOperation();
                            return;
                        } else {
                            return;
                        }
                    }

                    if (stockFaltante) {
                        int respuesta = JOptionPane.showConfirmDialog(null, "Los siguientes insumos: " + insumosFaltantes + " no alcanzan la cantidad solicitada en stock.No puede completar la planificacion de la misma Desea Solicitar el Pedido de los mismos?", "Cuidado", JOptionPane.YES_NO_OPTION);
                        if (respuesta == 0) {
                            PantallaSolicitudInsumos pantallaAdministrarSolicitudInsumos = new PantallaSolicitudInsumos(insumosFaltantes);
                            pantallaAdministrarSolicitudInsumos.setVisible(true);
                            getDefaultCloseOperation();
                            return;
                        } else {
                            return;
                        }
                    }

                    /////////

                    GestorLaboreo gest = new GestorLaboreo();

                    Campania camp = (Campania) cboCampania.getSelectedItem();
                    CampaniaEntity campania = campaniaRepository.getCampaniaByNombre(camp.getDenominacion());
                    ArrayList lotes = (ArrayList) lstLotes.getSelectedValuesList();

//            ArrayList<DetalleLaboreo> detalles = new ArrayList<DetalleLaboreo>();


                    planificacion.setCampania(campania);
                    planificacion.setListaDetallesLote(listaDetalleDeLotes);


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
                        gest.registrarLaboreo(planificacion, fecha, txtDescripcion.getText());

                        JOptionPane.showMessageDialog(null, "La Planificacion: " + planificacion.getCampania().getCnaDenominacion() + " fue realizada con exito.");
                        dispose();

                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar la Planificacion de Campaña: " + e1.toString());

                    } finally {
                        //  dispose();
                    }
                }
        );


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
                tblDetalles.setValueAt("", 0, 4);
            } else {
                DefaultTableModel modelo = (DefaultTableModel) tblDetalles.getModel();
                modelo.removeRow(tblDetalles.getSelectedRow());
            }
        });

        //LIMPIAR
        btnLimpiar.addActionListener(e -> limpiarPantalla());

        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());


        //NUEVA CAMPANIA
        nuevaCampaniaBtn.addActionListener(e -> {
            CargaCampania cargaCampania = new CargaCampania("Carga", 0, "", null, null, null);
            cargaCampania.setVisible(true);
            getDefaultCloseOperation();
        });

//        cboCampania.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                super.focusGained(e);
//                borrarComboBoxCampania();
//                cargaComboBoxCampania();
//            }
//        });

        cboMomentos.addMouseListener(new MouseAdapter() {
        });


        cboCampania.addMouseMotionListener(new MouseMotionAdapter() {

        });
        cboCampania.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                borrarComboBoxCampania();
//                cargarCampanias();
                agregarCampanias();
            }
        });


        //NUEVA ACTIVIDAD
        nuevoTipoLaboreoBtn.addActionListener(e -> {
            PantallaLaboreoCargado cargaTipoLaboreo = new PantallaLaboreoCargado("Carga", 0L);
            cargaTipoLaboreo.setVisible(true);
            getDefaultCloseOperation();
        });

//        cboMomentos.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                super.focusGained(e);
//                borrarComboBoxTipoLaboreo();
//                cargaComboBoxLaboreo();
//            }
//        });

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




        cbxSemillas.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxTipoGrano();
                cargaComboBoxTipoGrano();
            }
        });


        cboMomentos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                LaboreoEntity laboreoEntity = (LaboreoEntity) cboMomentos.getSelectedItem();
//                txtDescripcion.setText(laboreoEntity.getLboNombre());
            }
        });



        //Metodo Tipo Laboreo
        cboMomentos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                LaboreoEntity laboreoEntity = (LaboreoEntity) cboMomentos.getSelectedItem();
                if (laboreoEntity != null) {
                    cbxSemillas.setSelectedItem(laboreoEntity.getTipoGrano());
                }
                //Carga insumos
                List<Object[]> listaIns;
                //getInsumos por laboreo
                listaIns = gestor.getInsumosByLaboreo(laboreoEntity.getLboId());

                //Carga maquinarias
                List<Object[]> listaMaq;
                //getMaquinaria por laboreo
                listaMaq = gestor.getMaquinariasByLaboreo(laboreoEntity.getLboId());


                Object[][] data = new Object[listaIns.size() + listaMaq.size()][6];
                int i = 0;
                if (listaIns.size() != 0) {
                    for (Object[] row : listaIns) {
                        InsumoEntity insumoEntity = (InsumoEntity) row[0];
                        Integer cantidad = (Integer) row[1];

                        data[i][0] = "Insumo";
                        data[i][1] = insumoEntity.getInsNombre();
                        data[i][2] = insumoEntity.getTipoInsumoByInsTinId().getTinNombre();
                        data[i][3] = String.valueOf(cantidad);
                        data[i][4] = String.valueOf(insumoEntity.getInsStockDisponible());
                        data[i][5] = String.valueOf(insumoEntity.getInsStock());
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

                String[] columnNames = {"Secuencia", "Nombre", "Tipo", "Cantidad", "Stock Disponible", "Stock Real"};
//                DefaultTableModel model = new DefaultTableModel();
//                model.setDataVector(data, columnNames);
//                tblDetalles.setModel(model);
                modelDetalle = new DefaultTableModel();
                modelDetalle.setDataVector(data, columnNames);
                tblDetalles.setModel(modelDetalle);
//            return;
            }
        });


        //ELIMINAR INSUMO DE LA TABLA
        btnQuitarInsumosOMaq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    tblDetalles.setValueAt("", 0, 4);
                    tblDetalles.setValueAt("", 0, 5);
                } else {
                    DefaultTableModel modelo = (DefaultTableModel) tblDetalles.getModel();
                    modelo.removeRow(tblDetalles.getSelectedRow());
                }
            }
        });

        //ELIMINAR LABOREO DE LA TABLA
        btnQuitarLaboreos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isCellSelected(tableLaboreos)) {
                    showMessage("Debe seleccionar un item para continuar.");
                    return;
                }
                int fila = tableLaboreos.getSelectedRow();
                if (fila == 0) {
                    tableLaboreos.setValueAt("", 0, 0);
                    tableLaboreos.setValueAt("", 0, 1);
                    tableLaboreos.setValueAt("", 0, 2);
                    tableLaboreos.setValueAt("", 0, 3);
                } else {
                    DefaultTableModel modelo = (DefaultTableModel) tableLaboreos.getModel();
                    modelo.removeRow(tableLaboreos.getSelectedRow());
                }
            }
        });


        actualizarLaboreo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarComboBoxTipoLaboreo();
                cargaComboBoxLaboreo();
            }
        });


        //GENERAR PEDIDO INSUMO
        btnGenerarPedidoInsumo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaSolicitudInsumos pantallaSolicitudInsumos = new PantallaSolicitudInsumos(null);
                pantallaSolicitudInsumos.setVisible(true);
                getDefaultCloseOperation();
            }
        });


        btnArriba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelLaboreos.moveRow(tableLaboreos.getSelectedRow(),tableLaboreos.getSelectedRow(), tableLaboreos.getSelectedRow() -1);
                tableLaboreos.setRowSelectionInterval(tableLaboreos.getSelectedRow()-1, tableLaboreos.getSelectedRow()-1);

            }
        });
        btnAbajo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelLaboreos.moveRow(tableLaboreos.getSelectedRow(),tableLaboreos.getSelectedRow(), tableLaboreos.getSelectedRow() +1);
                tableLaboreos.setRowSelectionInterval(tableLaboreos.getSelectedRow()+1, tableLaboreos.getSelectedRow()+1);
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
        String[] columnNamesCompra = {"Tipo", "Descripcion", "Unidad de Medida", "Cantidad", "Stock Disponible", "Stock Real"};
        Object[][] data = new Object[1][6];
        setModelDetalles(columnNamesCompra, data);

    }


//    @Override
//    public void componentMoved(ComponentEvent e) {
//        super.componentMoved(e);
//    }

    private void inicializaTablaLaboreos() {
        String[] columnNamesLaboreo = {"Secuencia", "Nombre", "Descripcion", "Lote", "Semilla"};
        Object[][] data = new Object[1][5];
        setModelLaboreos(columnNamesLaboreo, data);


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
        tblDetalles.getColumnModel().getColumn(0).setPreferredWidth(180);
        tblDetalles.getColumnModel().getColumn(1).setPreferredWidth(350);
        tblDetalles.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblDetalles.getColumnModel().getColumn(4).setPreferredWidth(60);
        tblDetalles.getColumnModel().getColumn(5).setPreferredWidth(60);
        col = tblDetalles.getColumnModel().getColumn(5);
        col.setCellEditor(new MyTableCellEditor());
        tblDetalles.setCellSelectionEnabled(true);
    }

    private void setModelLaboreos(String[] columnames, Object[][] data) {
        TableColumn col;

        modelLaboreos.setDataVector(data, columnames);
        tableLaboreos.setModel(modelLaboreos);
        tableLaboreos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableLaboreos.getColumnModel().getColumn(0).setPreferredWidth(180);
        tableLaboreos.getColumnModel().getColumn(1).setPreferredWidth(350);
        tableLaboreos.getColumnModel().getColumn(2).setPreferredWidth(150);
        tableLaboreos.getColumnModel().getColumn(3).setPreferredWidth(100);
        tableLaboreos.getColumnModel().getColumn(4).setPreferredWidth(100);
        col = tableLaboreos.getColumnModel().getColumn(4);
        col.setCellEditor(new MyTableCellEditor());
        tableLaboreos.setCellSelectionEnabled(true);
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

    private void agregarCampanias() {
        java.util.List listaItems;
        listaItems = gestor.getCampanias();

//        java.util.List<Campania> listaItemsCBX =(List<Campania>) cboCampania.getModel();

        Iterator iter = listaItems.iterator();

        ComboBoxModel model = cboCampania.getModel();
        int size = model.getSize();

        while (iter.hasNext()) {
            boolean flag = false;
            Campania campania = (Campania) iter.next();
            for (int i = 0; i < size; i++) {
                Campania element = (Campania) model.getElementAt(i);

                if (campania.getDenominacion().equals(element.getDenominacion())) {
                    flag = true;
                }
            }

            if (!flag) {
                cboCampania.addItem(campania);
            }
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

    private void cargarCbxSemillas() {
        java.util.List listaItems;
        listaItems = gestor.getSemillas();

        Iterator iter = listaItems.iterator();
        while (iter.hasNext()) {
            cbxSemillas.addItem(iter.next());
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

        txtFechaIni.setText((camp == null) ? "" : camp.getFechaInicio().toString());
        txtFechaFin.setText((camp == null) ? "" : camp.getFechaFinReal().toString());
    }


    private void borrarComboBoxCampania() {
        cboCampania.removeAllItems();
    }

    private void borrarComboBoxTipoLaboreo() {
        if(cboMomentos != null) {
            cboMomentos.removeAllItems();
        }
    }

    private void borrarComboBoxTipoGrano() {
        cbxSemillas.removeAllItems();
    }


    //METODO CARGA COMBO CAMPANIA
    private void cargaComboBoxCampania() {
        Session session = Conexion.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT p FROM CampaniaEntity p");
        java.util.List<CampaniaEntity> listaCampaniaEntity = query.list();

//        Vector<String> miVectorcampania = new Vector<>();

//        Iterator iter = listaItems.iterator();
//        while (iter.hasNext()) {
//            cboCampania.addItem(iter.next());
//        }

        cboCampania.setSelectedItem(null);

        for (CampaniaEntity campania : listaCampaniaEntity) {
            //System.out.println(tipoEstado.getTeMaNombre());
//            miVectorcampania.add(campania.getCnaDenominacion());
            cboCampania.addItem(campania);
            cboCampania.setSelectedItem(null);
        }

    }


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


    //METODO CARGA COMBO LABOREO
    private void cargaComboBoxLaboreo() {
        Session session = Conexion.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT p FROM LaboreoEntity p");
        java.util.List<LaboreoEntity> listaLaboreoEntity = query.list();

        Vector<String> miVectorLaboreo = new Vector<>();
        for (LaboreoEntity laboreo : listaLaboreoEntity) {
            miVectorLaboreo.add(laboreo.getLboNombre());
            cboMomentos.addItem(laboreo);
        }
        session.close();
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
//        session.close();

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


