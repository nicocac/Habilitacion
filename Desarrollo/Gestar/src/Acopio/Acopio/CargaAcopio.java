package Acopio.Acopio;

import Acopio.TipoAcopio.CargaTipoAcopio;
import Conexion.Coneccion;
import Datos.AcopioEntity;
import Datos.TipoAcopioEntity;
import Datos.TipoGranoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.util.Vector;

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
        cargaComboBoxSemilla();


        //NEVO TIPO ACOPIO
        nvoBtnTipoAcopio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargaTipoAcopio cargaAcopio = new CargaTipoAcopio("Carga", "", "", 0);
                cargaAcopio.setVisible(true);
                getDefaultCloseOperation();
            }
        });
        nvoBtnTipoAcopio.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxTipoAcopio();
                cargaComboBoxTipoAcopio();
            }
        });

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
                tipo.setCantidadSoportada(Integer.parseInt(cantidadPeso.getText()));
//                tipo.setCantidadGrano(Integer.parseInt(cantidadActual.getText()));
                tipo.setTipoGrano((TipoGranoEntity)cbxGrano.getSelectedItem());
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
                return false;
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

        if (txtCodigo.getText().replaceAll(" ", "").length() == 0) return "N";

        return "S";

    }


    //---CARGA DE COMBO BOXS

    //METODO CARGA COMBO  Acopio
    private void cargaComboBoxTipoAcopio() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM TipoAcopioEntity p");
        java.util.List<TipoAcopioEntity> listaTipoMaquinaria = query.list();

        Vector<String> miVectorTipoMaquinaria = new Vector<>();
        for (TipoAcopioEntity tipoMaquinaria : listaTipoMaquinaria) {
            //System.out.println(tipoMaquinaria.getTeMaNombre());
            miVectorTipoMaquinaria.add(tipoMaquinaria.getTipoAcopioNombre());
            cbxTipoAcopio.addItem(tipoMaquinaria);
        }

    }

    private void borrarComboBoxTipoAcopio() {
        cbxTipoAcopio.removeAllItems();
    }


    private void cargaComboBoxSemilla() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM TipoGranoEntity p");
        java.util.List<TipoGranoEntity> listaTipoMaquinaria = query.list();

        Vector<String> miVectorTipoMaquinaria = new Vector<>();
        for (TipoGranoEntity tipoMaquinaria : listaTipoMaquinaria) {
            //System.out.println(tipoMaquinaria.getTeMaNombre());
            miVectorTipoMaquinaria.add(tipoMaquinaria.getTgrNombre());
            cbxTipoAcopio.addItem(tipoMaquinaria);
        }

    }


}