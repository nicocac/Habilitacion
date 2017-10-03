package Lote;

import Campo.CargaCampo;
import Conexion.Conexion;
import Datos.CampoEntity;
import Datos.LoteEntity;
import Repository.CampoRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import Date.DateLabelFormatter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by jagm on 05/09/2016.
 */
public class CargaLote extends JFrame {
    private JPanel panel1;
    private JTextField txtNombre;
    private JTextField txtMetros;
    private JButton cancelarButton;
    private JButton guardarButton;
    public JTextField txtUbicacion;
    public JComboBox comboBox1;
    public JButton BtnFechaIni;
    public JButton BtnFechaFin;
    public JComboBox cbxCampo;
    public JButton nuevoCampoBtnn;
    public JButton nuevoLoteBtn;


    private String tipoOperacion;
    private int lotId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());


    CampoRepository campoRepository = new CampoRepository();

    public CargaLote(String operacion, String nombre, int metros, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        setBounds(200, 300, 450, 500);
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Lote");
        } else {
            this.setTitle("Modificar Lote");
        }

        //Fecha

        net.sourceforge.jdatepicker.impl.SqlDateModel modelIni = new net.sourceforge.jdatepicker.impl.SqlDateModel();
        modelIni.setDate(2017, 05, 28);
        // Need this...
        net.sourceforge.jdatepicker.impl.JDatePanelImpl datePanelIni =
                new net.sourceforge.jdatepicker.impl.JDatePanelImpl(modelIni);
        //the formatter,  there it is...
        net.sourceforge.jdatepicker.impl.JDatePickerImpl datePickerIni =
                new net.sourceforge.jdatepicker.impl.JDatePickerImpl(datePanelIni, new DateLabelFormatter());

        BtnFechaIni.add(datePickerIni);

        net.sourceforge.jdatepicker.impl.SqlDateModel modelFin = new net.sourceforge.jdatepicker.impl.SqlDateModel();
        modelIni.setDate(2017, 05, 28);

        net.sourceforge.jdatepicker.impl.JDatePanelImpl datePanelFin =
                new net.sourceforge.jdatepicker.impl.JDatePanelImpl(modelFin);
        //the formatter,  there it is...
        net.sourceforge.jdatepicker.impl.JDatePickerImpl datePickerFin =
                new net.sourceforge.jdatepicker.impl.JDatePickerImpl(datePanelFin, new DateLabelFormatter());

        BtnFechaFin.add(datePickerFin);


        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save()) {
                JOptionPane.showMessageDialog(null, "Se guardo el alta del lote: " + txtNombre.getText() + " con exito.");
                dispose();
            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> dispose());


        if (nombre.length() > 1 && id != 0) {
            txtNombre.setText(nombre);
            txtMetros.setText(String.valueOf(metros));

            lotId = id;
        }

        nuevoCampoBtnn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargaCampo cargaCampo = new CargaCampo("Carga", "", 0, 0);
                cargaCampo.setVisible(true);
                getDefaultCloseOperation();
            }
        });
        cbxCampo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxCampo();
                cargaComboBoxCampo();
            }
        });
    }

    //METODO CARGA COMBO CAMPO
    private void cargaComboBoxCampo() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("SELECT p FROM CampoEntity p  where cpoFechaBaja is null");
        java.util.List<CampoEntity> listaTipoMaquinaria = query.list();

        Vector<String> miVectorTipoMaquinaria = new Vector<>();
        for (CampoEntity tipoMaquinaria : listaTipoMaquinaria) {
            //System.out.println(tipoMaquinaria.getTeMaNombre());
            miVectorTipoMaquinaria.add(tipoMaquinaria.getCpoNombre());
            cbxCampo.addItem(tipoMaquinaria);
        }
        tx.rollback();
    }

    private void borrarComboBoxCampo() {
        cbxCampo.removeAllItems();
    }


    //METODO GUARDAR
    private Boolean save() {
        Session session = null;
        Transaction tx = null;

        Boolean guardado = false;
        LoteEntity lote = new LoteEntity();
        if (validaCarga().equals("S")) {
            try {

                lote.setLteDenominacion(txtNombre.getText());
                lote.setLteUbicacion(txtUbicacion.getText());
                lote.setLteCantMetros(Integer.parseInt(txtMetros.getText()));
                lote.setLteFechaAlta(fechaActual);
                lote.setLteFechaDesde(fechaActual);
                lote.setLteFechaHasta(fechaActual);
                lote.setLteUsuarioAlta("admin");
                lote.setEstado("LIBRE");

                String campo = cbxCampo.getSelectedItem().toString();
                CampoEntity campoEntity = campoRepository.getCampoByNombre(campo);

                session = Conexion.getSessionFactory().getCurrentSession();
                 tx = session.beginTransaction();
                lote.setCampo(campoEntity);

//                Transaction tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(lote);
                } else {
                    lote.setLteId(lotId);
                    session.update(lote);
                }
                tx.commit();
                guardado = tx.wasCommitted();
//                //session.close();
            } catch (Exception e) {
//                tx.rollback();
                JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar el lote: " + e.toString());
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
