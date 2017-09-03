package Transporte;

import Conexion.Conexion;
import Datos.TipoTransporteEntity;
import Datos.TransporteEntity;
import Repository.TipoTransporteRepository;
import Transporte.TipoTransporte.CargaTipoTransporte;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by jagm on 14/02/2017.
 */
public class CargaTransporte extends JFrame {
    private JPanel panel1;
    private JTextArea txtDescripcion;
    private JTextField txtNombre;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JComboBox cbxTipoTransporte;
    private JButton nuevoTipoTransporteButton;

    private String tipoOperacion;
    private int insId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());

    TipoTransporteRepository tipoTransporteRepository = new TipoTransporteRepository();


    public CargaTransporte(String operacion, String nombre, String descripcion, TipoTransporteEntity tipoTransporte, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Transporte");
        } else {
            this.setTitle("Modificar Transporte");
        }
        cargaComboBoxTipo();


        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save()) {
                if (tipoOperacion.equals("Carga")) {
                    JOptionPane.showMessageDialog(null, "El Transporte: " + txtNombre.getText() + "fue dado de alta con exito.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "El Transporte: " + txtNombre.getText() + "fue modificado con exito.");
                    dispose();
                }

            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> dispose());


        if (nombre.length() > 1 && descripcion.length() > 1 && id != 0) {
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            cbxTipoTransporte.setSelectedItem(tipoTransporte);
            insId = id;
        }

        nuevoTipoTransporteButton.addActionListener(e -> {
            CargaTipoTransporte cargaTipoTransporte = new CargaTipoTransporte("Carga", "", "", 0);
            cargaTipoTransporte.setVisible(true);
            getDefaultCloseOperation();
        });


        cbxTipoTransporte.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxTipo();
                cargaComboBoxTipo();

            }
        });
    }


    //METODO GUARDAR
    private Boolean save() {
        Session session = null;
        Transaction tx = null;

        Boolean guardado = false;
        TransporteEntity transporte = new TransporteEntity();
        if (validaCarga().equals("S")) {
            try {

                transporte.setTransporteNombre(txtNombre.getText());
                transporte.setTransporteDescripcion(txtDescripcion.getText());
                transporte.setTransporteFechaAlta(fechaActual);
                transporte.setTransporteUsuarioAlta("admin");

                String tipoTransporte = cbxTipoTransporte.getSelectedItem().toString();
                TipoTransporteEntity tipoTransporteEntity = tipoTransporteRepository.getTipoTransporteByNombre(tipoTransporte);
                transporte.setTipoTransporteEntity(tipoTransporteEntity);

                 session = Conexion.getSessionFactory().getCurrentSession();
                 tx = session.beginTransaction();

//                Transaction tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(transporte);
                } else {
                    transporte.setTransporteId(insId);
                    session.update(transporte);
                }

                tx.commit();
                guardado = tx.wasCommitted();
//                guardado = true;
//                //session.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar el Transporte: " + e.toString());
            } finally {
                //session.close();
                //guardaStock(insumo);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos para continuar.");
        }

        return guardado;
    }


    //METODO VALIDAR CARGA
    private String validaCarga() {
        if (txtNombre.getText().replaceAll(" ", "").length() == 0) return "N";

        if (txtDescripcion.getText().replaceAll(" ", "").length() == 0) return "N";

        return "S";
    }





    //METODO CARGA COMBO
    private void cargaComboBoxTipo() {

        List<TipoTransporteEntity> listaTipoInsumo = tipoTransporteRepository.getAllTipoTransportes();

        Vector<String> mivector = new Vector<>();
        for (TipoTransporteEntity tipoInsumo : listaTipoInsumo) {
            mivector.add(tipoInsumo.getTinNombre());
            cbxTipoTransporte.addItem(tipoInsumo);
        }

    }

    private void borrarComboBoxTipo() {
        cbxTipoTransporte.removeAllItems();
    }

}
