package Principal;

import Insumo.PantallaAdministrarInsumo;
import TipoInsumo.PantallaAdministrarTipoInsumo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuPrincipal extends JFrame implements ActionListener {

    private JMenuBar mb;
    private JMenu menu1;
    private JMenuItem mi1, mi2, mi3, mi4, mi5, mi6;

    public MenuPrincipal() {

        mb = new JMenuBar();
        setJMenuBar(mb);
        menu1 = new JMenu("Insumos");
        mb.add(menu1);

        mi1 = new JMenuItem("Administrar Insumo");
        mi1.addActionListener(this);
        menu1.add(mi1);

        mi2 = new JMenuItem("Administrar Tipo de Insumo");
        mi2.addActionListener(this);
        menu1.add(mi2);


    }

    public void actionPerformed(ActionEvent e) {
        Container f = this.getContentPane();
        if (e.getSource() == mi1) {
            PantallaAdministrarInsumo pantallaAdministrarInsumo = new PantallaAdministrarInsumo();
            pantallaAdministrarInsumo.setVisible(true);
            getDefaultCloseOperation();

        }

        if (e.getSource() == mi2) {
            PantallaAdministrarTipoInsumo pantallaAdministrarTipoInsumo = new PantallaAdministrarTipoInsumo();
            pantallaAdministrarTipoInsumo.setVisible(true);
            getDefaultCloseOperation();
        }


    }


}
