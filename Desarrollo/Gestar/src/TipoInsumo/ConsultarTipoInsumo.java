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

/**
 * Created by OWNER on 5/30/2016.
 */
public class ConsultarTipoInsumo extends JFrame{
    private JTextField textField1;
    private JPanel panel1;
    private JButton btnBuscar;
    private JTextField textField2;
    private JTextField textField3;
    private JList list1;
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
    }
}
