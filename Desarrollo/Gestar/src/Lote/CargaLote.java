package Lote;

import Conexion.Coneccion;
import Datos.LoteEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.sql.Date;

/**
 * Created by jagm on 05/09/2016.
 */
public class CargaLote extends JFrame{
    private JPanel panel1;
    private JTextField txtNombre;
    private JTextArea txtMetros;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JTextField textField1;


    private String tipoOperacion;
    private int lotId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());

    public CargaLote(String operacion, String nombre, int metros, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Lote");
        } else {
            this.setTitle("Modificar Lote");
        }


        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save()) {
                JOptionPane.showMessageDialog(null, "Se guarda la alta del lote con exito.");
                dispose();
            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> dispose());


        if (nombre.length() > 1  && id != 0) {
            txtNombre.setText(nombre);
            txtMetros.setText(String.valueOf(metros));

            lotId = id;
        }

    }


    //METODO GUARDAR
    private Boolean save() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        LoteEntity lote = new LoteEntity();
        if (validaCarga().equals("S")) {
            try {

                lote.setLteDenominacion(txtNombre.getText());
                lote.setLteCantMetros(Integer.parseInt(txtMetros.getText()));
                lote.setLteFechaAlta(fechaActual);
                lote.setLteUsuarioAlta("admin");

                Transaction tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(lote);
                } else {
                    lote.setLteId(lotId);
                    session.update(lote);
                }
                tx.commit();
                guardado = tx.wasCommitted();
//                session.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurri? un error al cargar el lote: " + e.toString());
            } finally {
                session.close();
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
