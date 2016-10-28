package Acopio;

import Campania.Campania;
import Campania.GestorCampania;
import Conexion.Coneccion;
import Datos.*;
import Lote.Lote;
import Repository.LaboreoLoteCampaniaRepository;
import Repository.LoteRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 24/10/2016.
 */
public class PantallaEgresoAcopio extends JFrame {
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
    public JComboBox cboMomentos;
    public JComboBox cbxSemillas;
    public JTextField txtFechaIni;
    public JTextField txtCantidad;
    public JComboBox cbxEstado;
    public JComboBox cbxMedida;

    private String tipoOperacion;
    private int cnaId;
    private GestorCampania gestor = new GestorCampania();
    LaboreoLoteCampaniaRepository laboreoLoteCampaniaRepository = new LaboreoLoteCampaniaRepository();
    LoteRepository loteRepository = new LoteRepository();

    public PantallaEgresoAcopio(String operacion, int camId, String denominacion) {
//    public PantallaEgresoAcopio(String operacion, int camId, String denominacion, java.sql.Date fechaInicio, java.sql.Date fechaFin, java.sql.Date fechaFinReal) {

        //INICIO
        setContentPane(panel1);
        pack();
        setBounds(200,300,900,500);
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Egreso de Semillas");
        } else {
            this.setTitle("Modificar Avance Campania");
        }
//        inicializaTabla();
//        cargarLotes(camId);

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


//            txtCampania.setText(denominacion);
//            txtFechaIni.setText(fechaInicio.toString());
//            txtFechaFin.setText(fechaFin.toString());
//            modelIni.setValue(fechaInicio);
//            modelIni.setValue((java.sql.Date) dateInicio);
//            modelFin.setValue((java.sql.Date) dateFin);
//            modelFin.setValue(fechaFin);
            cnaId = camId;
        }

//        lstLotes.addListSelectionListener(e -> buscarLaboreoPorLote(camId));

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


//        lstLotes.addListSelectionListener(e -> lblLotes.setText(String.valueOf(lstLotes.getSelectedValuesList().size())));

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
//            modelIni.setValue(fechaInicio);
//            modelIni.setValue((java.sql.Date) dateInicio);
//            modelFin.setValue((java.sql.Date) dateFin);
//            modelFin.setValue(fechaFin);
            cnaId = camId;
        }

        //Registrar Ingreso Acopio
        btnFinalizar.addActionListener(e -> {
            if (validaCarga().equals("S")) {
                try {

                    Campania campania = new Campania();

                    campania.setDenominacion(txtCampania.getText());
                    campania.setLotes(lstLotes.getSelectedValuesList());

                    Session session = Coneccion.getSession();
                    Transaction tx = session.beginTransaction();

                    IngresoAcopioEntity ingresoAcopioEntity = new IngresoAcopioEntity();
                    ingresoAcopioEntity.setTipoGrano((TipoGranoEntity)cbxSemillas.getSelectedItem());
                    ingresoAcopioEntity.setTipoLaboreo((TipoLaboreoEntity) cboMomentos.getSelectedItem());
                    ingresoAcopioEntity.setLote((LoteEntity) lstLotes.getSelectedValue());
                    ingresoAcopioEntity.setIngresoCantidadTotal(Integer.parseInt(txtCantidad.getText()));
                    ingresoAcopioEntity.setEstadoSemilla((String) cbxEstado.getSelectedItem());

                    session.save(ingresoAcopioEntity);
                    try {
                        tx.commit();
                    }catch(Exception ex){
                        tx.rollback();
                    }
                    session.close();

//                    Date selectedDateIni = (Date) datePickerIni.getModel().getValue();
//                    Date selectedDateFin = (Date) datePickerFin.getModel().getValue();
//                    Date selectedDateFinReal = (Date) datePickerFin.getModel().getValue();
//                    campania.setFechaInicio((java.sql.Date) selectedDateIni);
//                    campania.setFechaFinEstimada((java.sql.Date) selectedDateFin);
//                    campania.setFechaFinReal((java.sql.Date) selectedDateFinReal);

//                    gestor.registrarCampania(campania, tipoOperacion, cnaId);
//                    gestor.registrarIngresoAcopio(campania, tipoOperacion, cnaId);

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "Ocurri? un error al registrar el avance de campania: " + e1.toString());
                } finally {
                    JOptionPane.showMessageDialog(null, "El Ingreso de Acopio de la Campania: " + txtCampania.getText() + " fue guardado con exito.");
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos para continuar.");
            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> {
            dispose();
        });

//        //NUEVO LOTE
//        nuevoLoteBtn.addActionListener(e -> {
//            CargaLote cargaLote = new CargaLote("Carga", "", 0, 0);
//            cargaLote.setVisible(true);
//            getDefaultCloseOperation();
//        });
//
//        lstLotes.addMouseMotionListener(new MouseMotionAdapter() {
//
//        });
//
//        //ACTUALIZAR LOTES
//        actualizarLotesBtn.addActionListener(e -> {
//            cargarLotes(0);
//        });

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
        LaboreoEntity laboreo ;
        Lote lote = (Lote)lstLotes.getSelectedValue();
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


    //METODO VALIDAR CARGA
    private String validaCarga() {
        if (txtCampania.getText().replaceAll(" ", "").length() == 0) return "N";
        return "S";
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
