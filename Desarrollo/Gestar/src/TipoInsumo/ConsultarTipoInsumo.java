package TipoInsumo;

import Conexion.Coneccion;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

/**
 * Created by OWNER on 5/30/2016.
 */
public class ConsultarTipoInsumo extends JFrame{
    private JTextField txtBuscar;
    private JPanel panel1;
    private JButton btnBuscar;
    //private JTextField txtNombre;
    //private JTextField txtDescripcion;
    //private JList jlTipos;
    private JButton btnLimpiar;
    private JButton btnEditar;
    //private JButton btnGuardar;
    private JButton btnCancelar;
    private JTable tblTipos;
    private JButton btnEliminar;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public ConsultarTipoInsumo()  {


        //INICIO
        setContentPane(panel1);
        pack();
        this.setTitle("Consultar Tipo de Insumo");
        try {
            Image imgSave = ImageIO.read(getClass().getResource("rsz_guardar.png"));
            Image imgCancel = ImageIO.read(getClass().getResource("rsz_cancelar.png"));
            Image imgFind = ImageIO.read(getClass().getResource("rsz_buscar.png"));
            Image imgEdit = ImageIO.read(getClass().getResource("rsz_editar.png"));
            Image imgClean = ImageIO.read(getClass().getResource("rsz_clean.png"));
            Image imgBaja = ImageIO.read(getClass().getResource("rsz_baja.png"));
            btnBuscar.setIcon(new ImageIcon(imgFind));
            btnCancelar.setIcon(new ImageIcon(imgCancel));
            btnEditar.setIcon(new ImageIcon(imgEdit));
            btnLimpiar.setIcon(new ImageIcon(imgClean));
            btnEliminar.setIcon(new ImageIcon(imgBaja));
            inicializaTabla();
        } catch (IOException ex) {
        }



        //BUSCAR
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session session = Coneccion.getSession();
                buscarTiposInsumo();
            }
        });


        //LIMPIAR
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializaTabla();
                txtBuscar.setText("");
            }
        });



        //EDITAR
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tblTipos.getSelectedRow();
                if (fila == -1){
                    JOptionPane.showMessageDialog(null,"Debe seleccionar una fila para continuar.");
                    return;
                }
                int tinId = (int)tblTipos.getModel().getValueAt(fila,0);
                String nombre = (String)tblTipos.getModel().getValueAt(fila,1);
                String descripcion = (String) tblTipos.getModel().getValueAt(fila,2);
                CargaTipoInsumo carga = new CargaTipoInsumo("Modificacion",nombre,descripcion,tinId);
                carga.setVisible(true);
                inicializaTabla();
            }
        });

        //CANCELAR
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //BAJA
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (darBaja()){
                    JOptionPane.showMessageDialog(null,"Se dio de baja exitosamente el tipo de insumo.");
                }else{
                    JOptionPane.showMessageDialog(null,"No se pudo dar de baja el tipo de insumo.");
                }
                inicializaTabla();
            }
        });
    }



    //METODOS
    private void inicializaTabla(){
        DefaultTableModel model;
        model = new DefaultTableModel();
        String[] columnNames ={"Cod","Nombre", "Descripcion"};
        Object[][] data = new Object[1][3];
        model.setDataVector(data,columnNames);
        tblTipos.setModel(model);
    }

    private void showError(String error){
        JOptionPane.showMessageDialog(this, error);
    }

    //METODO DAR BAJA
    public Boolean darBaja() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        try {
            TipoInsumoEntity tipo = new TipoInsumoEntity();
            int fila = tblTipos.getSelectedRow();
            if (fila == -1){
                JOptionPane.showMessageDialog(null,"Debe seleccionar una fila para continuar.");
                return false;
            }
            tipo.setTinId((int)tblTipos.getModel().getValueAt(fila,0));
            tipo.setTinNombre((String)tblTipos.getModel().getValueAt(fila,1));
            tipo.setTinDescripcion((String)tblTipos.getModel().getValueAt(fila,2));
            tipo.setTinFechaAlta(fechaActual);
            tipo.setTinUsuarioAlta("adminBAJA");
            tipo.setTinFechaUltMod(fechaActual);
            tipo.setTinUsuarioUtlMod("adminBAJA");
            tipo.setTinFechaBaja(fechaActual);
            tipo.setTinUsuarioBaja("adminBAJA");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del tipo de insumo: " + (String) tblTipos.getModel().getValueAt(fila, 1));
            if (i==0){
            Transaction tx = session.beginTransaction();
            session.update(tipo);
            tx.commit();
            guardado = tx.wasCommitted();
            }else{
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al dar de baja el tipo de insumo: "+e.toString());
            return false;
        } finally {
            session.close();
        }

        return true;
    }

    /*private void deshabilitarEdicion(){
        txtNombre.setEnabled(false);
        txtDescripcion.setEnabled(false);
        btnGuardar.setEnabled(false);
        txtNombre.setText("");
        txtDescripcion.setText("");
    }



    private void habilitarEdicion(){
        txtNombre.setEnabled(true);
        txtDescripcion.setEnabled(true);
        btnGuardar.setEnabled(true);
    }*/



    //METODO BUSCAR TIPOS
    public void buscarTiposInsumo() {
        Session session = Coneccion.getSession();
        int i = 0;
        try {
            TipoInsumoEntity entity;
            Query query = session.createQuery("select t from TipoInsumoEntity t where ucase(tinNombre) like ucase(:pNombre) and tinFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            DefaultTableModel model;
            model = new DefaultTableModel();
            String[] columnNames ={"Cod","Nombre", "Descripcion"};
            Object[][] data = new Object[list.size()][3];

            while (iter.hasNext()) {
                entity = (TipoInsumoEntity) iter.next();
                //JOptionPane.showMessageDialog(panel1, entity.toString());
                data[i][0]= entity.getTinId();
                data[i][1]= entity.getTinNombre();
                data[i][2]=entity.getTinDescripcion();
                i++;
            }
            model.setDataVector(data,columnNames);
            tblTipos.setModel(model);
        }finally{
            session.close();
        }

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
