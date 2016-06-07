package Insumo;

import Conexion.Coneccion;
import Datos.InsumoEntity;
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
import java.util.Vector;

/**
 * Created by jagm on 6/6/2016.
 */
public class EliminarInsumo extends JFrame {
    private JPanel panel1;
    private JComboBox cbxInsumos;
    private JButton btnGuardar;
    private JButton btnCancelar;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public EliminarInsumo() {

        cargaComboBoxTipo();
        setContentPane(panel1);
        pack();
        this.setTitle("Dar de Baja un Insumo");
        try {
            Image imgSave = ImageIO.read(getClass().getResource("rsz_guardar.png"));
            Image imgCancel = ImageIO.read(getClass().getResource("rsz_cancelar.png"));
            btnCancelar.setIcon(new ImageIcon(imgCancel));
            btnGuardar.setIcon(new ImageIcon(imgSave));



        } catch (IOException ex) {
        }

        //CANCELAR
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });



        //GUARDAR
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session session = Coneccion.getSession();
                InsumoEntity insumoEntity = (InsumoEntity) cbxInsumos.getSelectedItem();
                if (insumoEntity == null) {
                    showError("Debe seleccionar un Insumo para dar de baja");
                    return;
                }
                if (insumoEntity.getInsFechaBaja() != null) {
                    showError("El Insumo ya fue dado de baja el: " + insumoEntity.getInsFechaBaja());
                    return;
                }

                if (darBaja(session, insumoEntity)) {
                    JOptionPane.showMessageDialog(null, "Se dio de baja correctamente el insumo");
                    dispose();
                }

            }
        });


    }



    //METODOS

    private void showError(String error) {
        JOptionPane.showMessageDialog(this, error);
    }




    private void cargaComboBoxTipo() {
        Session session = Coneccion.getSession();
        try {
        Query query = session.createQuery("SELECT p FROM InsumoEntity p");
        java.util.List<InsumoEntity> listaInsumo = query.list();

        Vector<String> mivector = new Vector<>();
        for (InsumoEntity insumo : listaInsumo) {
            System.out.println(insumo.getInsNombre());
            mivector.add(insumo.getInsNombre());
            cbxInsumos.addItem(insumo);
        }
        }finally{
            session.close();
        }
    }


    //METODO DAR BAJA
    public Boolean darBaja(Session session, InsumoEntity insumoEntity) {
        Boolean guardado = false;
        try {
            insumoEntity.setInsFechaBaja(fechaActual);
            insumoEntity.setInsUsuarioBaja("adminBAJA");
            Transaction tx = session.beginTransaction();
            session.update(insumoEntity);
            tx.commit();
            guardado = tx.wasCommitted();
//            session.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al dar de baja el insumo.");
        } finally {
            session.close();
        }

        return guardado;
    }


}
