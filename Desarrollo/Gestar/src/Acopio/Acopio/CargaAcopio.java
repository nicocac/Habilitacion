package Acopio.Acopio;

import Conexion.Coneccion;
import Datos.AcopioEntity;
import Datos.TipoAcopioEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.sql.Date;

/**
 * Created by jagm on 28/02/2017.
 */
public class CargaAcopio extends JFrame {

    private JTextArea txtDescripcion;
    private JTextField txtNombre;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JPanel panel1;
    public JPanel alta;
    public JTextField cantidadPeso;
    public JComboBox cbxEstado;
    public JComboBox cbxGrano;
    public JButton nvoBtnTipoAcopio;
    public JComboBox cbxTipoAcopio;
    public JTextArea txtCodigo;
    public JLabel cantidadSoportada;
    public JTextField cantidadActual;
    public JTextField textNombre;
    private String tipoOperacion;
    private int tinId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());

    public CargaAcopio() {
    }

    public CargaAcopio(String operacion, String nombre, Integer codigo, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Acopio");
        } else {
            this.setTitle("Modificar Acopio");
        }


        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save()) {
                JOptionPane.showMessageDialog(null, "Se guardo correctamente el Acopio: " + txtCodigo.getText());
                dispose();
            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> dispose());


        if (nombre.length() > 1 && codigo > 1 && id != 0) {
            txtNombre.setText(nombre);
            txtCodigo.setText(codigo.toString());
            tinId = id;
        }
    }


    //METODO GUARDAR
    private Boolean save() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        if (validaCarga().equals("S")) {
            try {
                AcopioEntity tipo = new AcopioEntity();
                tipo.setNombre(txtNombre.getText());
                tipo.setCodigo(Integer.parseInt(txtCodigo.getText()));
                tipo.setTipoAcopioEntity((TipoAcopioEntity) cbxTipoAcopio.getSelectedItem());
                tipo.setCantidadSoportada(Integer.parseInt(cantidadSoportada.getText()));
                tipo.setCantidadGrano(Integer.parseInt(cantidadActual.getText()));
                tipo.setAcopioFechaAlta(fechaActual);
                tipo.setAcopioUsuarioAlta("admin");
                Transaction tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(tipo);
                } else {
                    tipo.setAcopioId(tinId);
                    session.update(tipo);
                }
                tx.commit();
                guardado = tx.wasCommitted();
//            session.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error al guardar el Acopio: " + e.toString());
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