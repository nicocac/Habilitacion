package Campo;

import Conexion.Conexion;
import Datos.CampoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import Date.DateLabelFormatter;

import javax.swing.*;
import java.sql.Date;
import java.util.Properties;


public class CargaCampo  extends JFrame {
    private JPanel panel1;
    private JTextField txtNombre;
    private JTextField txtMetros;
    private JButton cancelarButton;
    private JButton guardarButton;
    public JTextField txtUbicacion;
    public JComboBox comboBox1;
    public JButton BtnFechaIni;
    public JTextField txtCoordenadasX;
    public JComboBox cbxCliente;
    public JTextField txtObservaciones;
    public JButton BtnFechaFin;


    private String tipoOperacion;
    private int cmpoId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());

    public CargaCampo(String operacion, String nombre, int metros, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        setBounds(200,300,450,500);
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Campo");
        } else {
            this.setTitle("Modificar Campo");
        }

        //Fecha
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

        BtnFechaIni.add(datePickerIni);



        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save()) {
                JOptionPane.showMessageDialog(null, "Se guardo el alta del Campo: "+txtNombre.getText()+" con exito.");
                dispose();
            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> dispose());


        if (nombre.length() > 1  && id != 0) {
            txtNombre.setText(nombre);
            txtMetros.setText(String.valueOf(metros));

            cmpoId = id;
        }

    }


    //METODO GUARDAR
    private Boolean save() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        Boolean guardado = false;
        CampoEntity campo = new CampoEntity();
        if (validaCarga().equals("S")) {
            try {

                campo.setCpoNombre(txtNombre.getText());
                campo.setCpoUbicacion(txtUbicacion.getText());
                campo.setCpoObservaciones(txtObservaciones.getText());
                campo.setCpoPropietario(cbxCliente.getSelectedItem().toString());
                campo.setCpoCantMetros(Integer.parseInt(txtMetros.getText()));
                campo.setCpoCoordenadas(txtCoordenadasX.getText());
                campo.setCpoFechaAlta(fechaActual);
                campo.setCpoFechaUltMod(fechaActual);
//                campo.setCpoFechaBaja(fechaActual);
                campo.setCpoUsuarioAlta("admin");

//                Transaction tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(campo);
                } else {
                    campo.setCpoId(cmpoId);
                    session.update(campo);
                }
                tx.commit();
                guardado = tx.wasCommitted();
//                guardado = true;
//                //session.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar el Campo: " + e.toString());
            } finally {
                //session.close();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos para continuar.");
        }

        return guardado;
    }


    //METODO VALIDAR CARGA
    private String validaCarga() {
        if (txtNombre.getText().replaceAll(" ", "").length() == 0) return "N";

        if (txtMetros.getText().replaceAll(" ", "").length() == 0) return "N";

        return "S";
    }


}

