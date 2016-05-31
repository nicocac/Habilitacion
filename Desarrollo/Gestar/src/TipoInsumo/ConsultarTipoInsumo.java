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
import java.util.*;

/**
 * Created by OWNER on 5/30/2016.
 */
public class ConsultarTipoInsumo extends JFrame{
    private JTextField txtBuscar;
    private JPanel panel1;
    private JButton btnBuscar;
    private JTextField textField2;
    private JTextField textField3;
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
            btnBuscar.setIcon(new ImageIcon(imgFind));
            btnCancelar.setIcon(new ImageIcon(imgCancel));
            btnEditar.setIcon(new ImageIcon(imgEdit));
            btnGuardar.setIcon(new ImageIcon(imgSave));
        } catch (IOException ex) {
        }

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session session = Coneccion.getSession();
                try {
                    TipoInsumoEntity entity;
                    Query query = session.createQuery("select t from TipoInsumoEntity t where tinNombre like :pNombre");
                    query.setParameter("pNombre", txtBuscar.getText());
                    java.util.List list = query.list();
                    Iterator iter = list.iterator();
                    while (iter.hasNext()) {
                        entity = (TipoInsumoEntity) iter.next();
                        //JOptionPane.showMessageDialog(panel1, entity.toString());
                        DefaultListModel listModel = new DefaultListModel();
                        listModel.addElement(entity);
                        jlTipos.setModel(listModel);
                    }
                }finally{
                    session.close();
                }
            }
        });
    }
}
