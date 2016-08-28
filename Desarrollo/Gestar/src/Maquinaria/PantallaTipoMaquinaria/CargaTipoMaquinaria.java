package Maquinaria.PantallaTipoMaquinaria;

import Conexion.Coneccion;
import Datos.TipoMaquinariaEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.sql.Date;


public class CargaTipoMaquinaria extends JFrame {
    private JPanel panel1;
    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JButton cancelarButton;
    private JButton guardarButton;
    private String tipoOperacion;
    private int tinId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());


    public CargaTipoMaquinaria(String operacion, String nombre, String descripcion, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Tipo Maquinaria");
        } else {
            this.setTitle("Modificar Tipo Maquinaria");
        }


        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save()) {
                JOptionPane.showMessageDialog(null, "Se guardo correctamente el tipo maquinaria");
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
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        if (validaCarga().equals("S")) {
            try {
                TipoMaquinariaEntity tipo = new TipoMaquinariaEntity();
                tipo.setTmaNombre(txtNombre.getText());
                tipo.setTmaDescripcion(txtDescripcion.getText());
                tipo.setTmaFechaAlta(fechaActual);
                tipo.setTmaUsuarioAlta("admin");
                Transaction tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(tipo);
                } else {
                    tipo.setTmaId(tinId);
                    session.update(tipo);
                }
                tx.commit();
                guardado = tx.wasCommitted();
//            session.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurri� un error al guardar el tipo maquinaria: " + e.toString());
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
