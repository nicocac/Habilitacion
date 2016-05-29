package Principal;

import Insumo.CargaInsumo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuPrincipal extends JFrame implements ActionListener {

    private JMenuBar mb;
    private JMenu menu1;
    private JMenuItem mi1, mi2, mi3;

    public MenuPrincipal() {

        setLayout(null);
        mb = new JMenuBar();
        setJMenuBar(mb);
        menu1 = new JMenu("Opciones");
        mb.add(menu1);
        mi1 = new JMenuItem("Carga Insumo");
        mi1.addActionListener(this);
        menu1.add(mi1);
        mi2 = new JMenuItem("Verde");
        mi2.addActionListener(this);
        menu1.add(mi2);
        mi3 = new JMenuItem("Azul");
        mi3.addActionListener(this);
        menu1.add(mi3);
    }

    public void actionPerformed(ActionEvent e) {
        Container f = this.getContentPane();
        if (e.getSource() == mi1) {
            f.setBackground(new Color(255, 0, 0));
            CargaInsumo cargaInsumo = new CargaInsumo();
            cargaInsumo.setVisible(true);
            getDefaultCloseOperation();

        }
        if (e.getSource() == mi2) {
            f.setBackground(new Color(0, 255, 0));
        }
        if (e.getSource() == mi3) {
            f.setBackground(new Color(0, 0, 255));
        }
    }

}
