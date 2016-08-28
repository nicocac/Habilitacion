package Principal;

import Insumo.PantallaAdministrarInsumo;
import Laboreo.PantallaLaboreo;
import Maquinaria.PantallaAdministrarMaquinaria;
import Maquinaria.PantallaTipoEstado.PantallaAdministrarEstadoMaquinaria;
import Maquinaria.PantallaTipoMaquinaria.PantallaAdministrarTipoMaquinaria;
import Procesos.PantallaAdministrarCompra;
import TipoInsumo.PantallaAdministrarTipoInsumo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuPrincipal extends JFrame implements ActionListener {

    private JMenuBar mb1;
    private JMenu menu1, menu2, menu3;
    private JMenuItem mi1, mi2, mi3, mi4, mi5, mi6, mi7;

    public MenuPrincipal() {

        mb1 = new JMenuBar();
        setJMenuBar(mb1);

        //MAQUINARIA
        menu3 = new JMenu("Opciones");
        mb1.add(menu3);

        //SALIR
        mi7 = new JMenuItem("Salir");
        mi7.addActionListener(this);
        menu3.add(mi7);

        //INSUMOS
        menu1 = new JMenu("Insumos");
        mb1.add(menu1);

        mi1 = new JMenuItem("Administrar Insumo");
        mi1.addActionListener(this);
        menu1.add(mi1);

        mi2 = new JMenuItem("Administrar Tipo de Insumo");
        mi2.addActionListener(this);
        menu1.add(mi2);

        mi3 = new JMenuItem("Comprar Insumos");
        mi3.addActionListener(this);
        menu1.add(mi3);


        //MAQUINARIA
        menu2 = new JMenu("Maquinarias");
        mb1.add(menu2);

        mi4 = new JMenuItem("Administrar Maquinaria");
        mi4.addActionListener(this);
        menu2.add(mi4);

        mi5 = new JMenuItem("Administrar Tipo Maquinaria");
        mi5.addActionListener(this);
        menu2.add(mi5);

        mi6 = new JMenuItem("Administrar Estado");
        mi6.addActionListener(this);
        menu2.add(mi6);

        //Procesos
        menu3 = new JMenu("Procesos");
        mb1.add(menu3);

        mi7 = new JMenuItem("Registrar Laboreo");
        mi7.addActionListener(this);
        menu3.add(mi7);



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

        if (e.getSource() == mi3) {
            PantallaAdministrarCompra pantallaAdministrarCompra = new PantallaAdministrarCompra();
            pantallaAdministrarCompra.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == mi4) {
            PantallaAdministrarMaquinaria pantallaAdministrarMaquinaria = new PantallaAdministrarMaquinaria();
            pantallaAdministrarMaquinaria.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == mi5) {
            PantallaAdministrarTipoMaquinaria pantallaAdministrarTipoMaquinaria = new PantallaAdministrarTipoMaquinaria();
            pantallaAdministrarTipoMaquinaria.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == mi6) {
            PantallaAdministrarEstadoMaquinaria pantallaAdministrarEstadoMaquinaria = new PantallaAdministrarEstadoMaquinaria();
            pantallaAdministrarEstadoMaquinaria.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == mi7) {
            PantallaLaboreo pantallaLaboreo = new PantallaLaboreo();
            pantallaLaboreo.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == mi7) {
            dispose();
        }

    }


}
