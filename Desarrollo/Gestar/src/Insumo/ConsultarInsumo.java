package Insumo;

import Conexion.Coneccion;
import Datos.InsumoEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

/**
 * Created by jagm on 5/31/2016.
 */
public class ConsultarInsumo extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JList jlInsumos;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnGuardar;
    private JTextField txtDescripcion;
    private JTextField txtNombre;
    private JButton btnCancelar;
    private JComboBox cbxTipoInsumo;
    private JTextField txtUnidadMedida;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public ConsultarInsumo() {


        //INICIO
        cargaComboBoxTipo();
        setContentPane(panel1);
        pack();
        this.setTitle("Consultar Alta de Insumo");
        try {
            Image imgSave = ImageIO.read(getClass().getResource("rsz_guardar.png"));
            Image imgCancel = ImageIO.read(getClass().getResource("rsz_cancelar.png"));
            Image imgFind = ImageIO.read(getClass().getResource("rsz_buscar.png"));
            Image imgEdit = ImageIO.read(getClass().getResource("rsz_editar.png"));
            Image imgClean = ImageIO.read(getClass().getResource("rsz_clean.png"));
            btnBuscar.setIcon(new ImageIcon(imgFind));
            btnCancelar.setIcon(new ImageIcon(imgCancel));
            btnEditar.setIcon(new ImageIcon(imgEdit));
            btnGuardar.setIcon(new ImageIcon(imgSave));
            btnLimpiar.setIcon(new ImageIcon(imgClean));
            deshabilitarEdicion();


        } catch (IOException ex) {
        }


        //BUSCAR
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session session = Coneccion.getSession();
                buscarInsumos();
            }
        });


        //EDITAR
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsumoEntity insumoEntity = (InsumoEntity) jlInsumos.getSelectedValue();
                if (insumoEntity == null) {
                    showError("Debe seleccionar un Insumo para modificar");
                    return;
                }
                habilitarEdicion();
                txtNombre.setText(insumoEntity.getInsNombre());
                txtDescripcion.setText(insumoEntity.getInsDescripcion());
                txtUnidadMedida.setText(insumoEntity.getInsUnidadMedida());
                cbxTipoInsumo.setSelectedItem(insumoEntity.getTipoInsumoByInsTinId());
            }
        });



        //GUARDAR
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session session = Coneccion.getSession();
//                InsumoEntity insumoEntity = (InsumoEntity) session.createQuery("select x from InsumoEntity x where x.insNombre = :pNombre").setParameter("pNombre", txtNombre.getText()).uniqueResult();
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                String unidadMedida = txtUnidadMedida.getText();
                TipoInsumoEntity tipoInsumoEntity = (TipoInsumoEntity) cbxTipoInsumo.getSelectedItem();

//

                if (modify(session, nombre, descripcion, unidadMedida, tipoInsumoEntity)) {
                    JOptionPane.showMessageDialog(null, "Se modifico correctamente el insumo");
                    buscarInsumos();
                    deshabilitarEdicion();

                }
            }
        });



        //LIMPIAR
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deshabilitarEdicion();
                DefaultListModel listModel = new DefaultListModel();
                jlInsumos.setModel(listModel);
                txtBuscar.setText("");
            }
        });



        //CANCELAR
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    //METODOS

    private void showError(String error) {
        JOptionPane.showMessageDialog(this, error);
    }


    private void deshabilitarEdicion() {
        txtNombre.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtUnidadMedida.setEnabled(false);
        cbxTipoInsumo.setEnabled(false);
        btnGuardar.setEnabled(false);
        txtNombre.setText("");
        txtDescripcion.setText("");
    }


    private void habilitarEdicion() {
        txtNombre.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtUnidadMedida.setEnabled(true);
        cbxTipoInsumo.setEnabled(true);
        btnGuardar.setEnabled(true);
    }


    private void cargaComboBoxTipo() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM TipoInsumoEntity p");
        java.util.List<TipoInsumoEntity> listaTipoInsumo = query.list();

        Vector<String> mivector = new Vector<>();
        for (TipoInsumoEntity tipoInsumo : listaTipoInsumo) {
            System.out.println(tipoInsumo.getTinNombre());
            mivector.add(tipoInsumo.getTinNombre());
            cbxTipoInsumo.addItem(tipoInsumo);
        }

    }


    //METODO MODIFICAR
    public Boolean modify(Session session, String nombre, String descripcion, String unidadMedida, TipoInsumoEntity tipoInsumoEntity) {
        Boolean guardado = false;
        try {
//            InsumoEntity insumoEntity = (InsumoEntity) session.createQuery("select x from InsumoEntity x where x.insNombre = :pNombre").setParameter("pNombre", nombre).uniqueResult();
            InsumoEntity insumoEntity = (InsumoEntity) jlInsumos.getSelectedValue();
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            txtUnidadMedida.setText(unidadMedida);
            insumoEntity.setInsNombre(txtNombre.getText());
            insumoEntity.setInsDescripcion(txtDescripcion.getText());
            insumoEntity.setInsUnidadMedida(txtUnidadMedida.getText());
            insumoEntity.setTipoInsumoByInsTinId(tipoInsumoEntity);
            insumoEntity.setInsFechaAlta(fechaActual);
            insumoEntity.setInsUsuarioAlta("admin");
            Transaction tx = session.beginTransaction();
            session.update(insumoEntity);
            tx.commit();
            guardado = tx.wasCommitted();
//            session.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al guardar el insumo.");
        } finally {
            session.close();
        }

        return guardado;
    }



    //METODO BUSCAR INSUMOS
    public void buscarInsumos() {
        Session session = Coneccion.getSession();
        try {
            InsumoEntity entity;
            Query query = session.createQuery("select t from InsumoEntity t where ucase(insNombre) like ucase(:pNombre)");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            DefaultListModel listModel = new DefaultListModel();
            while (iter.hasNext()) {
                entity = (InsumoEntity) iter.next();
                //JOptionPane.showMessageDialog(panel1, entity.toString());
                listModel.addElement(entity);
                jlInsumos.setModel(listModel);
            }
        } finally {
            session.close();
        }

    }

}
