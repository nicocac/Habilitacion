package Acopio;

import Campania.Campania;
import Campania.GestorCampania;
import Cliente.CargaCliente;
import Conexion.Coneccion;
import Date.DateLabelFormatter;
import Datos.*;
import Insumo.Insumo;
import Laboreo.DetalleLaboreo;
import Laboreo.GestorLaboreo;
import Lote.Lote;
import Maquinaria.Maquinaria;
import Repository.*;
import Transporte.CargaTransporte;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jagm on 24/10/2016.
 */
public class PantallaEgresoAcopio extends JFrame {
    private JPanel panel1;
    private JButton btnGuardar;
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
    public JButton nuevoClienteBtn;
    public JButton nuevoTransporteBtn;
    public JButton salirButton;
    public JButton btnImprimir;
    public JComboBox cbxAcopio;
    public JComboBox cbxSemilla;
    public JComboBox cbxCliente;
    public JComboBox cbxTransporte;
    public JTextArea txtObserv;
    public JButton btnAgregarSemilla;
    public JTable tblDetalles;
    public JButton btnQuitar;
    public JTextField cantidadTotal;
    public JSeparator Detalles;
    public JComboBox cbxMedida;
    public JTextField txtTipoAcopio;
    public JTextField txtStock;
    public JButton btnAgregarParcial;
    public JTextField cantidadSemillaParcialTotal;
    public JTextField txtEstado;
    public JButton btnRemito;
    public JLabel lblDetallesEgresos;
    private DefaultTableModel modelDetalle = new DefaultTableModel();

    private String tipoOperacion;
    private int cnaId;
    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());
    List<Object[]> listaAcopios = null;

    private GestorCampania gestor = new GestorCampania();
    GestorLaboreo gest = new GestorLaboreo();
    LaboreoLoteCampaniaRepository laboreoLoteCampaniaRepository = new LaboreoLoteCampaniaRepository();
    TipoGranoRepository tipoGranoRepository = new TipoGranoRepository();
    TipoAcopioRepository tipoAcopioRepository = new TipoAcopioRepository();
    AcopioRepository acopioRepository = new AcopioRepository();
    ClienteRepository clienteRepository = new ClienteRepository();
    TransporteRepository transporteRepository = new TransporteRepository();
    EgresoAcopioRepository egresoAcopioRepository = new EgresoAcopioRepository();

    Long cantidad = null;
    Long cantidadPesoTotal = 0L;

    public PantallaEgresoAcopio(String operacion, int egresoId) {

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
        setBounds(200, 300, 1500, 900);
//        inicializaTablaSemillas();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Egreso de Semillas");
        } else {
            this.setTitle("Modificar Egreso de Semillas");
        }

        cargaComboBoxTransporte();
        cargaComboBoxCliente();
        cargaComboBoxSemillas();


        //----------------------------------------------------------------------
        //FECHA
        SqlDateModel modelIni = new SqlDateModel();
        modelIni.setDate(2016, 04, 20);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanelIni = new JDatePanelImpl(modelIni, p);
        //the formatter,  there it is...
        JDatePickerImpl datePickerIni = new JDatePickerImpl(datePanelIni, new DateLabelFormatter());

        BtnFechaIni.add(datePickerIni);
        //----------------------------------------------------------------------


        //MODIFICAR
        if (egresoId != 0) {

            EgresoAcopioEntity egreso = egresoAcopioRepository.getEgresoById(egresoId);
            cbxCliente.setSelectedItem(egreso.getCliente());
            cbxTransporte.setSelectedItem(egreso.getTransporte());
            //DEBERIA SER SIEMPRE 1
            List<DetalleEgresoAcopioEntity> listaDetalles = egresoAcopioRepository.getAllDetallesByEgresoId(egresoId);
            DetalleEgresoAcopioEntity detalle = listaDetalles.get(0);
            TipoGranoEntity semilla = detalle.getTipoGrano();
            AcopioEntity acopio = detalle.getAcopio();

            inicializaTablaSemillas();
            int fila = tblDetalles.getRowCount() - 1;
            if (tblDetalles.getRowCount() != 0) {
                fila = tblDetalles.getRowCount() - 1;
                if (tblDetalles.getValueAt(fila, 1) != null) {
                    if (!tblDetalles.getValueAt(fila, 1).equals("")) {
                        fila++;
                    }
                }
            }

            if (fila == 0) {
                tblDetalles.setValueAt("Egreso", fila, 0);
                tblDetalles.setValueAt(semilla.getTgrNombre(), fila, 1);
                tblDetalles.setValueAt(egreso.getEgresoCantidadTotal(), fila, 2);
                tblDetalles.setValueAt(acopio.getCodigo(), fila, 3);

                fila++;
            } else {
                modelDetalle.addRow(new Object[]{"Egreso"
                        , semilla.getTgrNombre()
                        , cantidadSemillaParcialTotal.getText()
                        , acopio.getCodigo()});
                fila++;
            }

            cantidadTotal.setText(egreso.getEgresoCantidadTotal().toString());
//            BtnFechaIni.setText(egreso.getEgresoFecha().toString());
            JTextField btnFechaInicio = new JTextField(egreso.getEgresoFecha().toString());
            btnFechaInicio.setVisible(true);
            btnFechaInicio.setBounds(50,30,15,10);

//

//
            cbxSemilla.hide();
            cbxAcopio.hide();
            txtTipoAcopio.hide();
            txtStock.hide();
            txtCantidad.hide();
            txtEstado.hide();
            btnAgregarSemilla.hide();
            btnAgregarParcial.hide();
            cantidadSemillaParcialTotal.hide();
            cbxMedida.hide();
            lblDetallesEgresos.hide();
            BtnFechaIni.hide();
        }


        //Registrar Egreso Acopio
        btnGuardar.addActionListener(e -> {
            if (validaCarga().equals("S")) {
                try {

                    if(cbxTransporte.getSelectedItem() == null){
                        showMessage("Debe seleccionar Transporte antes de continuar");
                        return;
                    }

                    if(cbxSemilla.getSelectedItem() == null){
                        showMessage("Debe seleccionar Semilla antes de continuar");
                        return;
                    }
                    if(cbxAcopio.getSelectedItem() == null){
                        showMessage("Debe seleccionar Acopio antes de continuar");
                        return;
                    }
                    if(txtCantidad.getText().equals("")){
                        showMessage("Debe completar la cantidad parcial antes de continuar");
                        return;
                    }

                    if(txtObserv.getText().equals("")){
                        showMessage("Debe completar observaciones antes de continuar");
                        return;
                    }

                    if(tblDetalles.getRowCount() == 0){
                        showMessage("Debe agregar al menos una semilla para exportar antes de continuar");
                        return;
                    }

                    //-------------------------------------------------------
                    GestorLaboreo gest = new GestorLaboreo();

                    EgresoAcopioEntity egreso = new EgresoAcopioEntity();
//
                    java.util.Date selectedDateIni = (java.util.Date) datePickerIni.getModel().getValue();
                    if(selectedDateIni == null){
                        showMessage("Debe seleccionar la fecha antes de continuar");
                        return;
                    }
                    egreso.setEgresoFecha((java.sql.Date) selectedDateIni);

                    egreso.setCliente((ClienteEntity) cbxCliente.getSelectedItem());
                    egreso.setTransporte((TransporteEntity) cbxTransporte.getSelectedItem());
                    egreso.setEgresoCantidadTotal(Integer.parseInt(cantidadTotal.getText()));

                    egreso.setMotivo(txtObserv.getText());
                    egreso.setEgresoFechaAlta(fechaActual);
                    egreso.setEgresoUsuarioAlta("admin");
                    egreso.setEgresoFechaUltMod(fechaActual);

                    TipoGranoEntity tipoGrano = null;
                    AcopioEntity acopio = null;

                    ArrayList<DetalleEgresoAcopioEntity> detalles = new ArrayList<>();

                    for (int i = 0; i < tblDetalles.getModel().getRowCount(); i++) {
                        DetalleEgresoAcopioEntity det = new DetalleEgresoAcopioEntity();
                        if (tblDetalles.getValueAt(i, 0).equals("Egreso")) {
                            //{"Egreso id", "Semilla", "Cantidad", "Acopio"};
                            tipoGrano = tipoGranoRepository.getTipoGranoByNombre((String) tblDetalles.getValueAt(i, 1));
                            String cantidad = ((String) tblDetalles.getValueAt(i, 2));
                            acopio = acopioRepository.getAcopioByCodigo((Integer) tblDetalles.getValueAt(i, 3));

                            det.setDetalleEgresoCantidad(Long.parseLong(cantidad));
                            det.setDetalleEgresoFechaAlta(fechaActual);
                            det.setDetalleEgresoUsuarioAlta("admin");
                            det.setTipoGrano(tipoGrano);
                            det.setAcopio(acopio);
                        }
                        detalles.add(det);
                    }

                    try {
                        gest.registrarEgresoSemillas(detalles, acopio, egreso, tipoGrano);
                        JOptionPane.showMessageDialog(null, "La operacion fue realizada con exito.");
                        dispose();

                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar el egreso de semillas: " + e1.toString());
                        return;
                    }
                    // ------------------------------------------------------

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar el egreso de semillas: " + e1.toString());
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos para continuar.");
                return;

            }
        });


//        //IMPRIMIR
//        btnImprimir.addActionListener(e -> {
//            Document document = new Document();
//            try {
//                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\jagm\\Desktop\\test.pdf"));
//                document.open();
//                PdfContentByte contentByte = writer.getDirectContent();
//                PdfTemplate template = contentByte.createTemplate(500, 500);
//                Graphics2D g2 = template.createGraphics(500, 500);
//                panel1.print(g2);
//                g2.dispose();
//                contentByte.addTemplate(template, 30, 300);
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            } finally {
//                if (document.isOpen()) {
//                    document.close();
//                }
//            }
//            dispose();
//        });


        //NUEVO CLIENTE
        nuevoClienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargaCliente cargaCliente = new CargaCliente("Carga", "", "", null, "", 0);
                cargaCliente.setVisible(true);
                getDefaultCloseOperation();

            }
        });

        cbxCliente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxCliente();
                cargaComboBoxCliente();

            }
        });


        //NUEVO TRANSPORTE
        nuevoTransporteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargaTransporte cargaTransporte = new CargaTransporte("Carga", "", "", null, 0);
                cargaTransporte.setVisible(true);
                getDefaultCloseOperation();

            }
        });
        cbxTransporte.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxTransporte();
                cargaComboBoxTransporte();

            }
        });


        //RELLENAR CAMPOS ACOPIO
        cbxAcopio.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                Integer nroAcopio = Integer.parseInt(cbxAcopio.getSelectedItem().toString());

                for (Object[] acopio : listaAcopios) {
                    if (acopio[0].equals(nroAcopio)) {
                        //String[] columnNames = {"Cod", "Nombre", "Nro", "Tipo Acopio", "Semilla", "Estado", "Cantidad Total"};

                        txtTipoAcopio.setText((String) acopio[3]);
                        txtEstado.setText((String) acopio[5]);
                        Long stock = (Long) acopio[6];
                        txtStock.setText((Long.toString(stock)));
                    }
                }
            }
        });


        //AGREGAR SEMILLA
        btnAgregarSemilla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializaTablaSemillas();
                if (cbxSemilla.getSelectedItem() == null) {
                    showMessage("Debe seleccionar al menos una semilla para continuar.");
                    return;
                }

                if (!existeLote()) {
                    showMessage("Debe seleccionar al menos un  acopio para continuar.");
                    return;
                }


                TipoGranoEntity semilla = (TipoGranoEntity) cbxSemilla.getSelectedItem();
                AcopioEntity acopio = acopioRepository.getAcopioById(Long.parseLong(cbxAcopio.getSelectedItem().toString()));


                int fila = tblDetalles.getRowCount() - 1;
                if (tblDetalles.getRowCount() != 0) {
                    fila = tblDetalles.getRowCount() - 1;
                    if (tblDetalles.getValueAt(fila, 1) != null) {
                        if (!tblDetalles.getValueAt(fila, 1).equals("")) {
                            fila++;
                        }
                    }
                }

//                Laboreo laboreo = (Laboreo) iter.next();
                if (fila == 0) {
                    tblDetalles.setValueAt("Egreso", fila, 0);
                    tblDetalles.setValueAt(semilla.getTgrNombre(), fila, 1);
                    tblDetalles.setValueAt(cantidadSemillaParcialTotal.getText(), fila, 2);
                    tblDetalles.setValueAt(acopio.getCodigo(), fila, 3);

                    fila++;
                } else {
                    modelDetalle.addRow(new Object[]{"Egreso"
                            , semilla.getTgrNombre()
                            , cantidadSemillaParcialTotal.getText()
                            , acopio.getCodigo()});
                    fila++;
                }

                cantidadPesoTotal = cantidadPesoTotal + Long.parseLong(tblDetalles.getValueAt(tblDetalles.getRowCount() - 1, 2).toString());
                cantidadTotal.setText(cantidadPesoTotal.toString());
            }

        });


        //SALIR
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        //LIMPIAR
        cancelarButton.addActionListener(e -> inicializaTablaSemillas());


        //ELIMINAR ITEM
        btnQuitar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isCellSelected(tblDetalles)) {
                    showMessage("Debe seleccionar un item para continuar.");
                    return;
                }
                cantidadPesoTotal = cantidadPesoTotal - Long.parseLong(tblDetalles.getValueAt(tblDetalles.getSelectedRow(), 2).toString());
                cantidadTotal.setText(cantidadPesoTotal.toString());

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


            }
        });


        //CARGAR ACOPIOS SEGUN SEMILLAS
        cbxSemilla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cantidad = 0L;
                String nombreSemilla = cbxSemilla.getSelectedItem().toString();
                listaAcopios = buscarAcopios();
                for (Object[] acopio : listaAcopios) {
                    //String[] columnNames = {"Cod", "Nombre", "Nro", "Tipo Acopio", "Semilla", "Estado", "Cantidad Total"};
                    if (acopio[4].equals(nombreSemilla))
                        cbxAcopio.addItem(acopio[0]);
                }
            }
        });

        btnAgregarParcial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long cantidadParcial = Long.parseLong(txtCantidad.getText());
                Long diferencia = Long.parseLong(txtStock.getText()) - cantidadParcial;
                if (diferencia < 0) {
                    showMessage("La cantidad seleccionada supera el stock, establezca un valor menor.");
                    return;
                }
                txtStock.setText(diferencia.toString());

                cantidad = cantidad + Long.parseLong(txtCantidad.getText());
                cantidadSemillaParcialTotal.setText(cantidad.toString());
            }
        });

        //BUTTON REMITO
        btnRemito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    // Creacion del parrafo
                    Paragraph paragraph = new Paragraph();
                    com.itextpdf.text.Font fontTitulos = FontFactory.getFont(
                            FontFactory.COURIER_BOLD, 14, com.itextpdf.text.Font.UNDERLINE,
                            BaseColor.BLACK);

                    com.itextpdf.text.Font categoryFont = FontFactory.getFont(
                            FontFactory.TIMES_ROMAN, 11, com.itextpdf.text.Font.NORMAL,
                            BaseColor.BLACK);


                    paragraph.add(new Phrase("Remito Transporte Granos:", fontTitulos));
                    paragraph.add(new Phrase(Chunk.NEWLINE));
                    paragraph.add(new Phrase(Chunk.NEWLINE));

                    paragraph.add(new Phrase("Transporte: " + cbxTransporte.getSelectedItem().toString(), categoryFont));
                    paragraph.add(new Phrase(Chunk.NEWLINE));

                    paragraph.add(new Phrase("Cliente: " + cbxCliente.getSelectedItem().toString(), categoryFont));
                    paragraph.add(new Phrase(Chunk.NEWLINE));

                    Chunk chunk = new Chunk("This is the title", fontTitulos);
                    chunk.setBackground(BaseColor.GRAY);


                    Document doc = new Document();
                    PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\jagm\\Documents\\Habilitacion\\Desarrollo\\Gestar\\src\\Imagenes\\remito.pdf"));
                    doc.open();
//                    PdfPTable pdfTable = new PdfPTable(tblInsumos.getColumnCount());
//                    //adding table headers
//                    for (int i = 0; i < tblInsumos.getColumnCount(); i++) {
//                        pdfTable.addCell(tblInsumos.getColumnName(i));
//                    }
//                    //extracting data from the JTable and inserting it to PdfPTable
//                    for (int rows = 0; rows < tblInsumos.getRowCount() ; rows++) {
//                        for (int cols = 0; cols < tblInsumos.getColumnCount(); cols++) {
//                            pdfTable.addCell(tblInsumos.getModel().getValueAt(rows, cols).toString());
//
//                        }
//                    }

                    doc.add(paragraph);
//                    doc.add(pdfTable);
                    doc.close();
                    showMessage("Remito Egreso Granos");
                    System.out.println("Remito Egreso Granos");
//                    doc.open();
                    String pdfFile = "C:\\Users\\jagm\\Documents\\Habilitacion\\Desarrollo\\Gestar\\src\\Imagenes\\remito.pdf";
                    try {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFile);
                    } catch (IOException io) {
                        Logger.getLogger(PantallaEgresoAcopio.class.getName()).log(Level.SEVERE, null, io);
                    }

                } catch (DocumentException ex) {
                    Logger.getLogger(PantallaEgresoAcopio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PantallaEgresoAcopio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    private void inicializaTablaSemillas() {
        String[] columnNamesSemilla = {"Egreso id", "Semilla", "Cantidad", "Acopio"};
        Object[][] data = new Object[1][5];
        setModelDetalles(columnNamesSemilla, data);


    }

    private boolean isCellSelected(JTable tabla) {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            return false;
        }
        return true;
    }

    private void setModelDetalles(String[] columnames, Object[][] data) {
        TableColumn col;
        modelDetalle.setDataVector(data, columnames);
        tblDetalles.setModel(modelDetalle);
        //tblDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblDetalles.getColumnModel().getColumn(0).setPreferredWidth(180);
        tblDetalles.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblDetalles.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(3).setPreferredWidth(100);
        col = tblDetalles.getColumnModel().getColumn(3);
        col.setCellEditor(new MyTableCellEditor());
        tblDetalles.setCellSelectionEnabled(true);
        //  tblDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }


//    private boolean isCellSelected(JTable tabla) {
//        int fila = tabla.getSelectedRow();
//        if (fila == -1) {
//            return false;
//        }
//        return true;
//    }

//    private void inicializaTabla() {
//        String[] columnNamesCompra = {"Tipo", "Descripcion", "Unidad de Medida", "Cantidad"};
//        Object[][] data = new Object[1][5];
//
//    }


    private boolean existeLote() {
        if (cbxAcopio.getSelectedItem() == null) {
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


    //METODO CARGA COMBO CLIENTE
    private void cargaComboBoxCliente() {

        List<ClienteEntity> listaTipoCliente = clienteRepository.getAllClientes();

        Vector<String> mivector = new Vector<>();
        for (ClienteEntity tipoInsumo : listaTipoCliente) {
            mivector.add(tipoInsumo.getClienteNombre());
            cbxCliente.addItem(tipoInsumo);
        }

    }

    private void borrarComboBoxCliente() {
        cbxCliente.removeAllItems();
    }


    //METODO CARGA COMBO TRANSPORTE
    private void cargaComboBoxTransporte() {

        List<TransporteEntity> listaTipoCliente = transporteRepository.getAllTransportes();

        Vector<String> mivector = new Vector<>();
        for (TransporteEntity tipoInsumo : listaTipoCliente) {
            mivector.add(tipoInsumo.getTransporteNombre());
            cbxTransporte.addItem(tipoInsumo);
        }

    }

    private void borrarComboBoxTransporte() {
        cbxTransporte.removeAllItems();
    }


    //CARGA COMBO SEMILLAS
    private void cargaComboBoxSemillas() {

        List<TipoGranoEntity> listaSemillas = tipoGranoRepository.getAllTipoGranos();

//        Vector<String> mivector = new Vector<>();
        for (TipoGranoEntity tipoGrano : listaSemillas) {
//            mivector.add(tipoGrano.getTgrNombre());
            cbxSemilla.addItem(tipoGrano);

        }
//        cbxTransporte.addItem(mivector);


    }


    //METODO VALIDAR CARGA
    private String validaCarga() {
        if (cbxCliente.getSelectedItem() == null) return "N";
        return "S";
    }

    private void showMessage(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


    //METODO BUSCAR ACOPIOS
    public List<Object[]> buscarAcopios() {
        Session session = Coneccion.getSession();
        java.util.List<Object[]> listaAcopios = null;
        int i = 0;
        try {
            java.util.List<Object[]> list;
            listaAcopios = new ArrayList<>();
            Object[] acopio;
            list = gest.getStockByAcopio();

            //
            List<Object[]> listEgreso = gest.getStockEgresoByAcopio();

            List<Object[]> listStockFinal = new ArrayList<>();

            for (Object[] ingreso : list) {

                for (Object[] egreso : listEgreso) {
                    if (ingreso[0].equals(egreso[0])) {

                        Long cantidadFinal = (Long) ingreso[6] - (Long) egreso[1];
                        ingreso[6] = cantidadFinal;
                    }

                }
                listStockFinal.add(ingreso);
            }
            //

            Iterator iter = listStockFinal.iterator();

//            String[] columnNames = {"Cod", "Nombre", "Nro", "Tipo Acopio", "Semilla", "Estado", "Cantidad Total"};

            while (iter.hasNext()) {
                acopio = (Object[]) iter.next();
                listaAcopios.add(acopio);
                i++;
            }

        } finally {
            session.close();
            return listaAcopios;
        }

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