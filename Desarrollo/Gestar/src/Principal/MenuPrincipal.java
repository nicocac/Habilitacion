package Principal;

import Insumo.CargaInsumo;
import Insumo.ConsultarInsumo;
import Insumo.EliminarInsumo;
import TipoInsumo.CargaTipoInsumo;
import TipoInsumo.ConsultarTipoInsumo;
import TipoInsumo.EliminarTipoInsumo;

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

        mi1 = new JMenuItem("Carga Insumo");
        mi1.addActionListener(this);
        menu1.add(mi1);

        mi2 = new JMenuItem("Consultar/Modificar Insumo");
        mi2.addActionListener(this);
        menu1.add(mi2);

        mi5 = new JMenuItem("Dar de baja Insumo");
        mi5.addActionListener(this);
        menu1.add(mi5);

        mi3 = new JMenuItem("Cargar Tipo de Insumo");
        mi3.addActionListener(this);
        menu1.add(mi3);

        mi4 = new JMenuItem("Consultar/Modificar Tipo de Insumo");
        mi4.addActionListener(this);
        menu1.add(mi4);

        mi6 = new JMenuItem("Dar de baja Tipo de Insumo");
        mi6.addActionListener(this);
        menu1.add(mi6);
    }

    public void actionPerformed(ActionEvent e) {
        Container f = this.getContentPane();
        if (e.getSource() == mi1) {
            CargaInsumo cargaInsumo = new CargaInsumo();
            cargaInsumo.setVisible(true);
            getDefaultCloseOperation();

        }
        if (e.getSource() == mi2) {
            ConsultarInsumo consultarInsumo = new ConsultarInsumo();
            consultarInsumo.setVisible(true);
            getDefaultCloseOperation();
        }
        if (e.getSource() == mi3) {
            CargaTipoInsumo cargaTipoInsumo = new CargaTipoInsumo("Carga","","",0);
            cargaTipoInsumo.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == mi4) {
            ConsultarTipoInsumo consultarTipoInsumo = new ConsultarTipoInsumo();
            consultarTipoInsumo.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == mi5) {
            EliminarInsumo eliminarInsumo = new EliminarInsumo();
            eliminarInsumo.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == mi6) {
            EliminarTipoInsumo eliminarTipoInsumo = new EliminarTipoInsumo();
            eliminarTipoInsumo.setVisible(true);
            getDefaultCloseOperation();
        }
    }


}
