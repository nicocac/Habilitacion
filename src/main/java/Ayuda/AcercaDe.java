package Ayuda;

import javax.swing.*;

/**
 * Created by jagm on 28/01/2017.
 */
public class AcercaDe extends JFrame {

    public JPanel panel1;
    public JTextArea ayudaPantallaAdministrarInsumosTextArea;
    public JTextArea realizadoPorVersion1TextArea;

    public AcercaDe() {


        //INICIO
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JPanel container = new JPanel();
//        container.setPreferredSize(new Dimension(1920, 1900));
//        panel1.setPreferredSize(new Dimension(1900, 1800));
        container.add(panel1);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);

//        setContentPane(panel1);
        setBounds(30, 30, 1800, 11800);
        setSize(1800, 1800);
        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Informacion Acerca de el programa");
    }

}
