package Maquinaria;

import Conexion.Coneccion;
import Datos.MaquinariaEntity;
import Datos.TipoEstadoMaquinariaEntity;
import Datos.TipoMaquinariaEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.sql.Date;
import java.util.Vector;


public class CargaMaquinaria extends JFrame {
    private JPanel panel1;
    private JPanel alta;
    private JComboBox cbxEstado;
    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JTextField txtAnio;
    private JComboBox cbxMarca;
    private JComboBox cbxModelo;
    private JComboBox cbxTipoMaquinaria;

    private String tipoOperacion;
    private int maqId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());

    public CargaMaquinaria(String operacion, String nombre, String descripcion, TipoEstadoMaquinariaEntity tipoEstadoMaquinaria,
                           String marca, String modelo, TipoMaquinariaEntity tipoMaquinaria, String anioFabricacion, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Maquinaria");
        } else {
            this.setTitle("Modificar Maquinaria");
        }
        cargaComboBoxTipoMaquinaria();
        cargaComboBoxTipoEstado();


        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save()) {
                JOptionPane.showMessageDialog(null, "Se guarda la alta de maquinaria con exito.");
                dispose();
            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> dispose());


        if (nombre.length() > 1 && descripcion.length() > 1 && id != 0) {
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            cbxEstado.setSelectedItem(tipoEstadoMaquinaria);
            cbxMarca.setSelectedItem(marca);
            cbxModelo.setSelectedItem(modelo);
            cbxTipoMaquinaria.setSelectedItem(tipoMaquinaria);
            txtAnio.setText(anioFabricacion);
            maqId = id;
        }

    }


    //METODO GUARDAR
    private Boolean save() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        MaquinariaEntity maquinaria = new MaquinariaEntity();
        if (validaCarga().equals("S")) {
            try {

                maquinaria.setMaqNombre(txtNombre.getText());
                maquinaria.setMaqDescripcion(txtDescripcion.getText());
                maquinaria.setMaqFechaAlta(fechaActual);
                maquinaria.setMaqUsuarioAlta("admin");
                maquinaria.setMaqMarca(cbxMarca.getSelectedItem().toString());
                maquinaria.setMaqModelo(cbxModelo.getSelectedItem().toString());
                maquinaria.setMaqAnioFabricacion(txtAnio.getText());

                String tipoMaquinaria = cbxTipoMaquinaria.getSelectedItem().toString();
                TipoMaquinariaEntity tipoMaquinariaEntity = (TipoMaquinariaEntity) session.createQuery("select x from TipoMaquinariaEntity x where x.tmaNombre = :pNombre").setParameter("pNombre", tipoMaquinaria).uniqueResult();
                maquinaria.setTipoMaquinariaByMaqTmaqId(tipoMaquinariaEntity);

                String tipoEstadoMaquinaria = cbxEstado.getSelectedItem().toString();
                TipoEstadoMaquinariaEntity tipoEstadoMaquinariaEntity = (TipoEstadoMaquinariaEntity) session.createQuery("select x from TipoEstadoMaquinariaEntity x where x.teMaNombre = :pNombre").setParameter("pNombre", tipoEstadoMaquinaria).uniqueResult();
                maquinaria.setTipoEstadoMaquinariaByMaqTestadoId(tipoEstadoMaquinariaEntity);


                Transaction tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(maquinaria);
                } else {
                    maquinaria.setMaqId(maqId);
                    session.update(maquinaria);
                }

                tx.commit();
                guardado = tx.wasCommitted();
//                session.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurri? un error al cargar la maquinaria: " + e.toString());
            } finally {
                session.close();
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


    //METODO CARGA COMBO TIPO MAQUINARIA
    private void cargaComboBoxTipoMaquinaria() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM TipoMaquinariaEntity p");
        java.util.List<TipoMaquinariaEntity> listaTipoMaquinaria = query.list();

        Vector<String> miVectorTipoMaquinaria = new Vector<>();
        for (TipoMaquinariaEntity tipoMaquinaria : listaTipoMaquinaria) {
            //System.out.println(tipoMaquinaria.getTeMaNombre());
            miVectorTipoMaquinaria.add(tipoMaquinaria.getTmaNombre());
            cbxTipoMaquinaria.addItem(tipoMaquinaria);
        }

    }

    //METODO CARGA COMBO TIPO ESTADO MAQUINARIA
    private void cargaComboBoxTipoEstado() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM TipoEstadoMaquinariaEntity p");
        java.util.List<TipoEstadoMaquinariaEntity> listaTipoEstadoMaquinaria = query.list();

        Vector<String> miVectorEstado = new Vector<>();
        for (TipoEstadoMaquinariaEntity tipoEstado : listaTipoEstadoMaquinaria) {
            //System.out.println(tipoEstado.getTeMaNombre());
            miVectorEstado.add(tipoEstado.getTeMaNombre());
            cbxEstado.addItem(tipoEstado);
        }

    }

}
