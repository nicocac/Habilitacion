package TipoInsumo;

import Conexion.Coneccion;
import Datos.TipoInsumoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;


public class CargaTipoInsumo extends JFrame{

    private JTextField txtDescripcion;
    private JTextField txtNombre;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JPanel panel1;


    public CargaTipoInsumo() {
        setContentPane(panel1);
        pack();

        this.setTitle("Cargar Tipo de Insumo");
        try {
            Image imgSave = ImageIO.read(getClass().getResource("rsz_guardar.png"));
            Image imgCancel= ImageIO.read(getClass().getResource("rsz_cancelar.png"));
            guardarButton.setIcon(new ImageIcon(imgSave));
            cancelarButton.setIcon(new ImageIcon(imgCancel));
        } catch (IOException ex) {
        }

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(save()){
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
        if (validaCarga()=="S") {
            try {
                TipoInsumoEntity tipo = new TipoInsumoEntity();
                tipo.setTinNombre(txtNombre.getText());
                tipo.setTinDescripcion(txtDescripcion.getText());
                tipo.setTinFechaAlta(new Date(2016, 05, 30));
                tipo.setTinUsuarioAlta("admin");
                Transaction tx = session.beginTransaction();
                session.save(tipo);
                tx.commit();
                guardado = tx.wasCommitted();
//            session.close();
            }catch (Exception e){JOptionPane.showMessageDialog(this, "Ocurrió un error al guardar el tipo de insumo.");}
             finally {
                session.close();
            }
        }else{
            JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos para continuar.");
        }

        return guardado;
    }

    public String validaCarga(){
        if (txtNombre.getText().replaceAll(" ", "").length() == 0) return "N";

        if (txtDescripcion.getText().replaceAll(" ", "").length() ==0) return "N";

        return "S";

    }


}
