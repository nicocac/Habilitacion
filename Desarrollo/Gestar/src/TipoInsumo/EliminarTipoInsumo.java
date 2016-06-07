package TipoInsumo;

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
import java.util.Vector;

/**
 * Created by jagm on 6/6/2016.
 */
public class EliminarTipoInsumo extends JFrame{
    private JPanel panel1;
    private JComboBox cbxTipoInsumo;
    private JButton btnCancelar;
    private JButton btnGuardar;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public EliminarTipoInsumo() {

        cargaComboBoxTipo();
        setContentPane(panel1);
        pack();
        this.setTitle("Dar de Baja un Tipo de Insumo");
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

                TipoInsumoEntity tipoInsumoEntity = (TipoInsumoEntity) cbxTipoInsumo.getSelectedItem();
                if (tipoInsumoEntity == null) {
                    showError("Debe seleccionar un tipo Insumo para dar de baja");
                    return;
                }
                if (tipoInsumoEntity.getTinFechaBaja() != null) {
                    showError("El Tipo de Insumo ya fue dado de baja el: " + tipoInsumoEntity.getTinFechaBaja());
                    return;
                }

                if (darBaja(tipoInsumoEntity)) {
                    JOptionPane.showMessageDialog(null, "Se dio de baja correctamente el tipo de insumo");
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
        Query query = session.createQuery("SELECT p FROM TipoInsumoEntity p");
        java.util.List<TipoInsumoEntity> listaTipoInsumo = query.list();

        Vector<String> mivector = new Vector<>();
        for (TipoInsumoEntity tipoInsumo : listaTipoInsumo) {
            System.out.println(tipoInsumo.getTinNombre());
            mivector.add(tipoInsumo.getTinNombre());
            cbxTipoInsumo.addItem(tipoInsumo);
        }
        }finally{
            session.close();
        }

    }


    //METODO DAR BAJA
    public Boolean darBaja(TipoInsumoEntity tipoInsumoEntity) {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        try {
            tipoInsumoEntity.setTinFechaBaja(fechaActual);
            tipoInsumoEntity.setTinUsuarioBaja("adminBAJA");
            Transaction tx = session.beginTransaction();
            session.update(tipoInsumoEntity);
            tx.commit();
            guardado = tx.wasCommitted();
//            session.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al dar de baja el tipo de insumo.");
        } finally {
            session.close();
        }

        return guardado;
    }


}
