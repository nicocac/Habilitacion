package Cliente;

import Cliente.TipoCliente.CargaTipoCliente;
import Conexion.Conexion;
import Datos.ClienteEntity;
import Datos.TipoClienteEntity;
import Repository.TipoClienteRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by jagm on 10/10/2016.
 */
public class CargaCliente extends JFrame {
    private JPanel panel1;
    private JTextArea txtApellido;
    private JTextField txtNombre;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JComboBox cbxTipoCliente;
    private JTextField txtCuitCuil;
    private JButton nuevoTipoCliente;

    private String tipoOperacion;
    private int insId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());

    TipoClienteRepository tipoClienteRepository = new TipoClienteRepository();


    public CargaCliente(String operacion, String nombre, String apellido, String tipoCliente, String cuitCuil, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Cliente");
        } else {
            this.setTitle("Modificar Cliente");
        }
        cargaComboBoxTipo();


        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save()) {
                JOptionPane.showMessageDialog(null, "El Cliente : "+txtNombre.getText()+" fue guardado con exito.");
                dispose();
            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> dispose());


        if (nombre.length() > 1 && apellido.length() > 1 && id != 0) {
            txtNombre.setText(nombre);
            txtApellido.setText(apellido);
            cbxTipoCliente.setSelectedItem(tipoCliente);
            txtCuitCuil.setText(cuitCuil);
            insId = id;
        }

        nuevoTipoCliente.addActionListener(e -> {
            CargaTipoCliente cargaTipoInsumo = new CargaTipoCliente("Carga", "", "", 0);
            cargaTipoInsumo.setVisible(true);
            getDefaultCloseOperation();
        });


        cbxTipoCliente.addFocusListener(new FocusAdapter() {
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


        Boolean guardado = false;
        ClienteEntity cliente = new ClienteEntity();
        if (validaCarga().equals("S")) {
            try {

                cliente.setClienteNombre(txtNombre.getText());
                cliente.setClienteApellido(txtApellido.getText());
                cliente.setClienteFechaAlta(fechaActual);
                cliente.setClienteUsuarioAlta("admin");
                cliente.setClienteCuitCuil(txtCuitCuil.getText());
//                cliente.setTipoClienteEntity(cbxTipoCliente.getSelectedItem().toString());

                String tipoCliente = cbxTipoCliente.getSelectedItem().toString();
                TipoClienteEntity tipoClienteEntity = tipoClienteRepository.getTipoClienteByNombre(tipoCliente);
                cliente.setTipoClienteEntity(tipoClienteEntity);

                Session session = Conexion.getSessionFactory().getCurrentSession();
                Transaction tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(cliente);
                } else {
                    cliente.setClienteId(insId);
                    session.update(cliente);
                }

                tx.commit();
                guardado = tx.wasCommitted();
                guardado = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar el cliente: " + e.toString());
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

        if (txtApellido.getText().replaceAll(" ", "").length() == 0) return "N";

        return "S";
    }





    //METODO CARGA COMBO
    private void cargaComboBoxTipo() {

        List<TipoClienteEntity> listaTipoCliente = tipoClienteRepository.getAllTipoClientes();

        Vector<String> mivector = new Vector<>();
        for (TipoClienteEntity tipoInsumo : listaTipoCliente) {
            mivector.add(tipoInsumo.getTinNombre());
            cbxTipoCliente.addItem(tipoInsumo);
        }

    }

    private void borrarComboBoxTipo() {
        cbxTipoCliente.removeAllItems();
    }

}
