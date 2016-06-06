package TipoInsumo;

import Conexion.Coneccion;
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
 * Created by OWNER on 5/30/2016.
 */
public class ConsultarTipoInsumo extends JFrame{
    private JTextField txtBuscar;
    private JPanel panel1;
    private JButton btnBuscar;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JList jlTipos;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnGuardar;
    private JButton btnCancelar;

    public ConsultarTipoInsumo()  {
        setContentPane(panel1);
        pack();
        this.setTitle("Consultar Tipo de Insumo");
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



        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session session = Coneccion.getSession();
                try {
                    TipoInsumoEntity entity;
                    Query query = session.createQuery("select t from TipoInsumoEntity t where ucase(tinNombre) like ucase(:pNombre)");
                    query.setParameter("pNombre", "%"+txtBuscar.getText()+"%");
                    java.util.List list = query.list();
                    Iterator iter = list.iterator();
                    DefaultListModel listModel = new DefaultListModel();
                    while (iter.hasNext()) {
                        entity = (TipoInsumoEntity) iter.next();
                        //JOptionPane.showMessageDialog(panel1, entity.toString());
                        listModel.addElement(entity);
                        jlTipos.setModel(listModel);
                    }
                }finally{
                    session.close();
                }
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deshabilitarEdicion();
                DefaultListModel listModel = new DefaultListModel();
                jlTipos.setModel(listModel);
                txtBuscar.setText("");
            }
        });
//        btnBuscar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Session session = Coneccion.getSession();
//                String nombre = txtBuscar.getText();
////                TipoInsumoEntity tipoInsumoEntity = (TipoInsumoEntity) session.get(TipoInsumoEntity.class, 7);
//                Query query = session.createQuery("SELECT p FROM TipoInsumoEntity p where p.tinNombre =  :nombre").setParameter("nombre", nombre);
////                Query query = session.createQuery("SELECT p FROM TipoInsumoEntity p where p.tinNombre =  '" + nombre + "' + '\" + % + \"' ").setParameter("nombre", nombre);
//                List<TipoInsumoEntity> listaTipoInsumo = query.list();
//
//                Vector<String> mivector = new Vector<String>();
//                for (TipoInsumoEntity tipoInsumo : listaTipoInsumo) {
//                    System.out.println(tipoInsumo.getTinNombre());
//                    mivector.add(tipoInsumo.getTinNombre());
//                }
////                list1 = new JList(datos);
//                jlTipos.setListData(mivector);
//
//            }
//        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session session = Coneccion.getSession();
                TipoInsumoEntity tipoInsumoEntity =(TipoInsumoEntity) jlTipos.getSelectedValue();
                if (tipoInsumoEntity==null){
                    showError("Debe seleccionar un tipo de Insumo para modificar");
                    return;
                }
                habilitarEdicion();
                txtNombre.setText(tipoInsumoEntity.getTinNombre());
                txtDescripcion.setText(tipoInsumoEntity.getTinDescripcion());
            }
        });
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session session = Coneccion.getSession();
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                if(modify(session, nombre, descripcion)){
                    JOptionPane.showMessageDialog(null, "Se modifico correctamente el tipo insumo");
                    deshabilitarEdicion();
                }
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void showError(String error){
        JOptionPane.showMessageDialog(this,error);
    }

    private void deshabilitarEdicion(){
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
    }

    public Boolean modify(Session session, String nombre, String descripcion) {
        Boolean guardado = false;
        try {
            TipoInsumoEntity tipoInsumoEntity = (TipoInsumoEntity) session.createQuery("select x from TipoInsumoEntity x where x.tinNombre = :pNombre").setParameter("pNombre", nombre).uniqueResult();
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            tipoInsumoEntity.setTinNombre(txtNombre.getText());
            tipoInsumoEntity.setTinDescripcion(txtDescripcion.getText());
            tipoInsumoEntity.setTinFechaAlta(new Date(2016, 05, 30));
            tipoInsumoEntity.setTinUsuarioAlta("admin");
            Transaction tx = session.beginTransaction();
            session.save(tipoInsumoEntity);
            tx.commit();
            guardado = tx.wasCommitted();
//            session.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurri� un error al guardar el tipo de insumo.");
        } finally {
            session.close();
        }

        return guardado;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
