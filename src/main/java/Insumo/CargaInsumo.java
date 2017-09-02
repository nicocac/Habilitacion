package Insumo;

import Conexion.Conexion;
import Datos.InsumoEntity;
import Datos.TipoInsumoEntity;
import Repository.TipoInsumoRepository;
import TipoInsumo.CargaTipoInsumo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.util.List;
import java.util.Vector;


public class CargaInsumo extends JFrame {
    private JPanel panel1;
    private JTextArea txtDescripcion;
    private JTextField txtNombre;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JComboBox cbxTipoInsumo;
    private JPanel alta;
    private JComboBox textUnidadMedida;
    private JTextField txtStock;
    private JButton nuevoTipoInsumoButton;

    private String tipoOperacion;
    private int insId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());

    TipoInsumoRepository tipoInsumoRepository = new TipoInsumoRepository();


    public CargaInsumo(String operacion, String nombre, String descripcion, String unidadMedida, TipoInsumoEntity tipoInsumo, String stock, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Insumo");
        } else {
            this.setTitle("Modificar Insumo");
        }
        cargaComboBoxTipo();


        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save()) {
                if (tipoOperacion.equals("Carga")) {
                    JOptionPane.showMessageDialog(null, "El Insumo: " + txtNombre.getText() + "fue dado de alta con exito.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "El Insumo: " + txtNombre.getText() + "fue modificado con exito.");
                    dispose();
                }

            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> dispose());


        if (nombre.length() > 1 && descripcion.length() > 1 && id != 0) {
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            textUnidadMedida.setSelectedItem(unidadMedida); //setText(unidadMedida);
            cbxTipoInsumo.setSelectedItem(tipoInsumo);
            txtStock.setText(stock);
            insId = id;
        }

        nuevoTipoInsumoButton.addActionListener(e -> {
            CargaTipoInsumo cargaTipoInsumo = new CargaTipoInsumo("Carga", "", "", 0);
            cargaTipoInsumo.setVisible(true);
            getDefaultCloseOperation();
        });


        cbxTipoInsumo.addFocusListener(new FocusAdapter() {
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
        InsumoEntity insumo = new InsumoEntity();
        if (validaCarga().equals("S")) {
            try {

                insumo.setInsNombre(txtNombre.getText());
                insumo.setInsDescripcion(txtDescripcion.getText());
                insumo.setInsFechaAlta(fechaActual);
                insumo.setInsUsuarioAlta("admin");
                insumo.setInsUnidadMedida(textUnidadMedida.getSelectedItem().toString());

                String tipoInsumo = cbxTipoInsumo.getSelectedItem().toString();
                TipoInsumoEntity tipoInsumoEntity = tipoInsumoRepository.getTipoInsumoByNombre(tipoInsumo);
                insumo.setTipoInsumoByInsTinId(tipoInsumoEntity);

                session = Conexion.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(insumo);
                } else {
                    insumo.setInsId(insId);
                    session.update(insumo);
                }

                tx.commit();
                guardado = tx.wasCommitted();
                guardado = true;
//                //session.close();
            } catch (Exception e) {
                tx.rollback();
                JOptionPane.showMessageDialog(this, "Ocurri� un error al cargar el insumo: " + e.toString());
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

        List<TipoInsumoEntity> listaTipoInsumo = tipoInsumoRepository.getAllTipoInsumos();

        Vector<String> mivector = new Vector<>();
        for (TipoInsumoEntity tipoInsumo : listaTipoInsumo) {
            mivector.add(tipoInsumo.getTinNombre());
            cbxTipoInsumo.addItem(tipoInsumo);
        }

    }

    private void borrarComboBoxTipo() {
        cbxTipoInsumo.removeAllItems();
    }

}
