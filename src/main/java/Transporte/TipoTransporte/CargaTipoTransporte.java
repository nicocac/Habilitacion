package Transporte.TipoTransporte;

import Conexion.Conexion;
import Datos.TipoTransporteEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.sql.Date;

/**
 * Created by jagm on 14/02/2017.
 */
public class CargaTipoTransporte extends JFrame {

    private JTextArea txtDescripcion;
    private JTextField txtNombre;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JPanel panel1;
    private String tipoOperacion;
    private int tinId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());

    public CargaTipoTransporte() {
    }

    public CargaTipoTransporte(String operacion, String nombre, String descripcion, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Tipo de Transporte");
        } else {
            this.setTitle("Modificar Tipo de Transporte");
        }


        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save()) {
                JOptionPane.showMessageDialog(null, "Se guardo correctamente el tipo Transporte: " + txtNombre.getText());
                dispose();
            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> dispose());


        if (nombre.length() > 1 && descripcion.length() > 1 && id != 0) {
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            tinId = id;
        }
    }


    //METODO GUARDAR
    private Boolean save() {
        Session session = Conexion.getSessionFactory().getCurrentSession();

        Boolean guardado = false;
        if (validaCarga().equals("S")) {
            try {
                TipoTransporteEntity tipo = new TipoTransporteEntity();
                tipo.setTinNombre(txtNombre.getText());
                tipo.setTinDescripcion(txtDescripcion.getText());
                tipo.setTinFechaAlta(fechaActual);
                tipo.setTinUsuarioAlta("admin");
                Transaction tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(tipo);
                } else {
                    tipo.setTinId(tinId);
                    session.update(tipo);
                }
                tx.commit();
                guardado = tx.wasCommitted();
//                guardado = true;
//            //session.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error al guardar el tipo de Transporte: " + e.toString());
            } finally {
                //session.close();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos para continuar.");
        }

        return guardado;
    }


    //METODO VALIDAR DATOS
    private String validaCarga() {
        if (txtNombre.getText().replaceAll(" ", "").length() == 0) return "N";

        if (txtDescripcion.getText().replaceAll(" ", "").length() == 0) return "N";

        return "S";

    }


}
