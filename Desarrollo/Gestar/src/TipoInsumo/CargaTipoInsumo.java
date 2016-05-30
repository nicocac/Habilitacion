package TipoInsumo;

import Datos.TipoInsumoEntity;
import Conexion.Coneccion;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;


public class CargaTipoInsumo extends JFrame{

    private JTextField textField1;
    private JTextField textField2;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JPanel panel1;


    public CargaTipoInsumo() {
        setContentPane(panel1);
        pack();

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(save()){
                    System.out.println("Se guardo correctamente el tipo insumo");
                    JOptionPane.showMessageDialog(null, "Se guardo correctamente el tipo insumo");
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }



    public Boolean save() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        try {
            TipoInsumoEntity tipo = new TipoInsumoEntity();
            tipo.setTinNombre(textField1.getText());
            tipo.setTinDescripcion(textField2.getText());
            tipo.setTinFechaAlta(new Date(2016, 05, 30));
            tipo.setTinUsuarioAlta("andres");
            Transaction tx = session.beginTransaction();
            session.save(tipo);
            tx.commit();
            guardado = tx.wasCommitted();
//            session.close();
        } finally {
            session.close();
        }
        return guardado;
    }
}
