package Granos;

import Conexion.Coneccion;
import Datos.TipoGranoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.sql.Date;

/**
 * Created by jagm on 05/09/2016.
 */
public class CargaTipoGrano extends JFrame {

    private JTextArea txtDescripcion;
    private JTextField txtNombre;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JPanel panel1;
    private String tipoOperacion;
    private int tgrId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());

    public CargaTipoGrano() {
    }

    public CargaTipoGrano(String operacion, String nombre, String descripcion, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Tipo de Grano");
        } else {
            this.setTitle("Modificar Tipo de Grano");
        }


        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save()) {
                JOptionPane.showMessageDialog(null, "Se guardo correctamente el tipo Grano");
                dispose();
            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> dispose());


        if (nombre.length() > 1 && descripcion.length() > 1 && id != 0) {
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            tgrId = id;
        }
    }


    //METODO GUARDAR
    private Boolean save() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        if (validaCarga().equals("S")) {
            try {
                TipoGranoEntity tipo = new TipoGranoEntity();
                tipo.setTgrNombre(txtNombre.getText());
                tipo.setTgrDescripcion(txtDescripcion.getText());
                tipo.setTgrFechaAlta(fechaActual);
                tipo.setTgrUsuarioAlta("admin");
                Transaction tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(tipo);
                } else {
                    tipo.setTgrId(tgrId);
                    session.update(tipo);
                }
                tx.commit();
                guardado = tx.wasCommitted();
//            session.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar el tipo de Grano: " + e.toString());
            } finally {
                session.close();
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

