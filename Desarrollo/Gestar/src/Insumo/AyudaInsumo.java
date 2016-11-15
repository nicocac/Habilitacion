package Insumo;

import javax.swing.*;

/**
 * Created by jagm on 13/11/2016.
 */
public class AyudaInsumo extends JFrame{

    public JPanel panel1;
    public JTextArea ayudaPantallaAdministrarInsumosTextArea;

    public AyudaInsumo() {


        //INICIO
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentPane(panel1);
        setBounds(30, 30, 1800, 11800);
        setSize(1800, 1800);
        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Insumo");
    }

}
