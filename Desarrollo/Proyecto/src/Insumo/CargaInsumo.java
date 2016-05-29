package Insumo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CargaInsumo extends JFrame {
    JButton empezarButton;
    JPanel panel1;

    public CargaInsumo() {
        setContentPane(panel1);
        pack();

        empezarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hello World");
            }
        });
    }

}
