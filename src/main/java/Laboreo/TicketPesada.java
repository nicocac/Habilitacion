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
public class TicketPesada extends JFrame {
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
    private GestorLaboreo gestor = new GestorLaboreo();
    private String tipoOperacion;


    public TicketPesada(String operacion, OrdenTrabajoEntity orden) {

        //INICIO
        JPanel container = new JPanel();
        container.add(panelIni);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);
        setBounds(200, 300, 1500, 800);
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Registrar Orden Trabajo Realizada");
        } else {
            this.setTitle("Modificar Avance Campania");
        }

        txtNroOrden.setText(orden.getNroOrden().toString());
        txtCampania.setText(orden.getPlanificacion().getCampania().getCnaDenominacion());
        txtLote.setText(orden.getLote().getLteDenominacion());
        txtFechaIni.setText(orden.getPlanificacion().getCampania().getCnaFechaInicio().toString());
        txtFechaFin.setText(orden.getPlanificacion().getCampania().getCnaFechaFinReal().toString());
        txtLaboreo.setText(orden.getLaboreo().getLboNombre());
        txtSemilla.setText(orden.getGrano().getTgrNombre());
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



        //LIMPIAR
        btnLimpiar.addActionListener(e -> dispose());

        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());


        //GUARDAR
        btnFinalizar.addActionListener(e -> {

            java.sql.Date fecha = (java.sql.Date) datePickerIni.getModel().getValue();
            if (fecha == null) {
                showMessage("Debe completar la fecha antes de continuar");
                return;
            }

            if (txtPeso.getText().equals("")) {
                showMessage("Debe completar el peso antes de continuar");
                return;
            }

            if (txtObservaciones.getText().equals("")) {
                showMessage("Debe completar observaciones antes de continuar");
                return;
            }

//            java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
            Session session = null;
            Transaction tx = null;
            try {

                session = Conexion.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();

                TicketPesadaEntity ticket = new TicketPesadaEntity();

                ticket.setLaboreo(orden.getLaboreo());
                ticket.setOrdenTrabajoEntity(orden);
                ticket.setMedida(cbxMedida.getSelectedItem().toString());
                ticket.setPeso(txtPeso.getText());
                ticket.setObservaciones(txtObservaciones.getText());
                ticket.setUsuarioAlta("Admin");
                ticket.setFechaAlta(fecha);

                session.save(ticket);

                tx.commit();
                JOptionPane.showMessageDialog(null, "El Ticket de pesada fue cargado con exito.");
                dispose();
            } catch (Exception ex) {
                tx.rollback();
                JOptionPane.showMessageDialog(null, "Ocurrio un error al cargar el Ticket : " + ex.toString());

            }
            //session.close();
        });



    }



    private void showMessage(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
