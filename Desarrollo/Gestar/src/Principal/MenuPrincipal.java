package Principal;

import Insumo.PantallaAdministrarInsumo;
import Laboreo.GestorLaboreo;
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
    private JMenu menu1, menu2, menu3, menu4, menu5;
    private JMenuItem mi1, mi2, mi3, mi4, mi5, mi6, mi7, mi8, mi9, mi10;

    public MenuPrincipal() {

        mb1 = new JMenuBar();
        setJMenuBar(mb1);

        //OPCIONES
        menu3 = new JMenu("Opciones");
        mb1.add(menu3);

        //SALIR
        mi10 = new JMenuItem("Salir");
        mi10.addActionListener(this);
        menu3.add(mi10);

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
        //LOTE
        menu3 = new JMenu("Lotes");
        mb1.add(menu3);

        mi7 = new JMenuItem("Registrar Laboreo");
        mi7.addActionListener(this);
        menu3.add(mi7);
        mi7 = new JMenuItem("Administrar Lotes");
        mi7.addActionListener(this);
        menu3.add(mi7);



        mi8 = new JMenuItem("Administrar Tipo Lotes");
        mi8.addActionListener(this);
        menu3.add(mi8);

        //COMPRAS
        menu4 = new JMenu("Compras");
        mb1.add(menu4);

        mi9 = new JMenuItem("Administrar Compras");
        mi9.addActionListener(this);
        menu4.add(mi9);

//        mi5 = new JMenuItem("Administrar Tipo Compra");
//        mi5.addActionListener(this);
//        menu2.add(mi5);


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

//        if (e.getSource() == mi7) {
//            PantallaLaboreo pantallaLaboreo = new PantallaLaboreo();
//            pantallaLaboreo.setVisible(true);
//            getDefaultCloseOperation();
//        }
//
//        if (e.getSource() == mi7) {
//            PantallaAdministrarLote pantallaAdministrarLote = new PantallaAdministrarLote();
//            pantallaAdministrarLote.setVisible(true);
//            getDefaultCloseOperation();
//        }
//
//        if (e.getSource() == mi8) {
//            PantallaAdministrarTipoLote pantallaAdministrarTipoLote = new PantallaAdministrarTipoLote();
////            pantallaAdministrarTipoLote.setVisible(true);
//            getDefaultCloseOperation();
//        }

        if (e.getSource() == mi9) {
            PantallaAdministrarCompra pantallaAdministrarCompra = new PantallaAdministrarCompra();
            pantallaAdministrarCompra.setVisible(true);
            getDefaultCloseOperation();
        }


        if (e.getSource() == mi10) {
            dispose();
        }

    }


}
