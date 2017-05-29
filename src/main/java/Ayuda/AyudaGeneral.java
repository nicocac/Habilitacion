package Ayuda;

import javax.swing.*;

/**
 * Created by jagm on 28/01/2017.
 */
public class AyudaGeneral extends JFrame {

    public JPanel panel1;
    public JTextArea ayudaPantallaAdministrarInsumosTextArea;
    public JTextArea ayudaGeneralDelSistemaTextArea;

    public AyudaGeneral() {


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
