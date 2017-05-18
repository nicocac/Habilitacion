package Campania;

import Date.DateLabelFormatter;
import Datos.LoteEntity;
import Lote.CargaLote;
import Lote.Lote;
import Repository.LoteRepository;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

//import java.sql.Date;

/**
 * Created by jagm on 05/09/2016.
 */
public class CargaCampania extends JFrame {
    private JPanel panel1;
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

    private String tipoOperacion;
    private int cnaId;
    private GestorCampania gestor = new GestorCampania();
    LoteRepository loteRepository = new LoteRepository();
    public CargaCampania(String operacion, int camId, String denominacion, java.sql.Date fechaInicio, java.sql.Date fechaFin, java.sql.Date fechaFinReal) {

        //INICIO
        setContentPane(panel1);
        pack();
        setBounds(200,300,900,500);
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Campania");
        } else {
            this.setTitle("Modificar Campania");
        }
//        inicializaTabla();
        cargarLotes(camId);

//        //Fecha
        SqlDateModel modelIni = new SqlDateModel();
        SqlDateModel modelFin = new SqlDateModel();
        modelIni.setDate(2016, 04, 20);
        modelFin.setDate(2016, 04, 20);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanelIni = new JDatePanelImpl(modelIni, p);
        JDatePanelImpl datePanelFin = new JDatePanelImpl(modelFin, p);
        //the formatter,  there it is...
        JDatePickerImpl datePickerIni = new JDatePickerImpl(datePanelIni, new DateLabelFormatter());
        JDatePickerImpl datePickerFin = new JDatePickerImpl(datePanelFin, new DateLabelFormatter());

        BtnFechaIni.add(datePickerIni);
        BtnFechaFin.add(datePickerFin);
        //


        lstLotes.addListSelectionListener(e -> {
                    lblLotes.setText(String.valueOf(lstLotes.getSelectedValuesList().size()));
                    List<Lote> lotes = lstLotes.getSelectedValuesList();
                    for (Lote lote : lotes) {
                        LoteEntity loteEntity = loteRepository.getLoteByDenominacion(lote.getDenominacion());
                        if (loteEntity.getEstado().equals("OCUPADO")){
                            JOptionPane.showMessageDialog(this, "El lote: " + loteEntity.getLteDenominacion() + " seleccionado" +
                                    " no se puede incluir en la campaña ya que el mismo esta siendo utilizado para otra campaña." +
                                    " Por favor seleccione otro.");
                            lstLotes.removeSelectionInterval(lstLotes.getLeadSelectionIndex(), lstLotes.getLeadSelectionIndex());
                        }

                    }
                }
        );

        //MODIFICAR
        if (denominacion.length() > 1 && camId != 0) {

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


            txtCampania.setText(denominacion);
            modelIni.setValue(fechaInicio);
//            modelIni.setValue((java.sql.Date) dateInicio);
//            modelFin.setValue((java.sql.Date) dateFin);
            modelFin.setValue(fechaFin);
            cnaId = camId;
        }

        btnFinalizar.addActionListener(e -> {
            if (validaCarga().equals("S")) {
                try {

                    Campania campania = new Campania();

                    campania.setDenominacion(txtCampania.getText());
                    campania.setLotes(lstLotes.getSelectedValuesList());
                    Date selectedDateIni = (Date) datePickerIni.getModel().getValue();
                    Date selectedDateFin = (Date) datePickerFin.getModel().getValue();
                    Date selectedDateFinReal = (Date) datePickerFin.getModel().getValue();
                    campania.setFechaInicio((java.sql.Date) selectedDateIni);
                    campania.setFechaFinEstimada((java.sql.Date) selectedDateFin);
                    campania.setFechaFinReal((java.sql.Date) selectedDateFinReal);

                    gestor.registrarCampania(campania, tipoOperacion, cnaId);

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar Campania: " + e1.toString());
                } finally {
                    JOptionPane.showMessageDialog(null, "La Campania: " + txtCampania.getText() + " fue guardada con exito.");
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos para continuar.");
            }
        });


        cancelarButton.addActionListener(e -> {
            dispose();
        });

        //NUEVO LOTE
        nuevoLoteBtn.addActionListener(e -> {
            CargaLote cargaLote = new CargaLote("Carga", "", 0, 0);
            cargaLote.setVisible(true);
            getDefaultCloseOperation();
        });

        lstLotes.addMouseMotionListener(new MouseMotionAdapter() {

        });

        //ACTUALIZAR LOTES
        actualizarLotesBtn.addActionListener(e -> {
            cargarLotes(0);
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


    //METODO VALIDAR CARGA
    private String validaCarga() {
        if (txtCampania.getText().replaceAll(" ", "").length() == 0) return "N";

//        if (txtFechaFin.getText().replaceAll(" ", "").length() == 0) return "N";

        return "S";
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
