package Principal;

import Campania.PantallaAdministrarCampania;
import Granos.PantallaAdministrarTipoGranos;
import Imagenes.ImageFondo;
import Insumo.PantallaAdministrarInsumo;
import Laboreo.PantallaAdministrarLaboreos;
import Laboreo.PantallaLaboreo;
import Lote.PantallaAdministrarLote;
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

    private JMenuBar menuPrincipal;
    private JMenu opciones, insumos, maquinarias, procesos, lotes, tipoGranos,campanias;
    private JMenuItem laboreoTemp, laboreo, lote, compra, salir, insumo, tipoInsumo,compraInsumo, maquinaria, tipoMaquinaria, estadoMaquinaria, tipoGrano, campania;

    public MenuPrincipal() {

        //ImageFondo image=new ImageFondo();
        //image.setImage("/Imagenes/gestion.png");
        //setContentPane(image);

        this.setExtendedState(MAXIMIZED_BOTH);

        menuPrincipal = new JMenuBar();
        setJMenuBar(menuPrincipal);

        //OPCIONES
        opciones = new JMenu("Opciones");
        menuPrincipal.add(opciones);

        //SALIR
        salir = new JMenuItem("Salir");
        salir.addActionListener(this);
        opciones.add(salir);

        //INSUMOS
        insumos = new JMenu("Insumos");
        menuPrincipal.add(insumos);

        insumo = new JMenuItem("Administrar Insumo");
        insumo.addActionListener(this);
        insumos.add(insumo);

        tipoInsumo = new JMenuItem("Administrar Tipo de Insumo");
        tipoInsumo.addActionListener(this);
        insumos.add(tipoInsumo);

        compraInsumo = new JMenuItem("Comprar Insumos");
        compraInsumo.addActionListener(this);
        insumos.add(compraInsumo);


        //MAQUINARIA
        maquinarias = new JMenu("Maquinarias");
        menuPrincipal.add(maquinarias);

        maquinaria = new JMenuItem("Administrar Maquinaria");
        maquinaria.addActionListener(this);
        maquinarias.add(maquinaria);

        tipoMaquinaria = new JMenuItem("Administrar Tipo Maquinaria");
        tipoMaquinaria.addActionListener(this);
        maquinarias.add(tipoMaquinaria);

        estadoMaquinaria = new JMenuItem("Administrar Estado");
        estadoMaquinaria.addActionListener(this);
        maquinarias.add(estadoMaquinaria);

        //LOTE
        lotes = new JMenu("Lotes");
        menuPrincipal.add(lotes);

        laboreoTemp = new JMenuItem("Registrar Laboreo");
        laboreoTemp.addActionListener(this);
        lotes.add(laboreoTemp);

        laboreo = new JMenuItem("Administrar Laboreo");
        laboreo.addActionListener(this);
        lotes.add(laboreo);

        lote = new JMenuItem("Administrar Lotes");
        lote.addActionListener(this);
        lotes.add(lote);

        //GRANOS
        tipoGranos = new JMenu("Granos");
        menuPrincipal.add(tipoGranos);

        tipoGrano = new JMenuItem("Administrar Tipo Granos");
        tipoGrano.addActionListener(this);
        tipoGranos.add(tipoGrano);

        //CAMPANIAS
        campanias = new JMenu("Campa\u00f1a");
        menuPrincipal.add(campanias);

        campania = new JMenuItem("Administrar Campa\u00f1as");
        campania.addActionListener(this);
        campanias.add(campania);

//        mi5 = new JMenuItem("Administrar Tipo Compra");
//        mi5.addActionListener(this);
//        menu2.add(mi5);


    }

    public void actionPerformed(ActionEvent e) {
        Container f = this.getContentPane();
        if (e.getSource() == insumo) {
            PantallaAdministrarInsumo pantallaAdministrarInsumo = new PantallaAdministrarInsumo();
            pantallaAdministrarInsumo.setVisible(true);
            getDefaultCloseOperation();

        }

        if (e.getSource() == tipoInsumo) {
            PantallaAdministrarTipoInsumo pantallaAdministrarTipoInsumo = new PantallaAdministrarTipoInsumo();
            pantallaAdministrarTipoInsumo.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == compraInsumo) {
            PantallaAdministrarCompra pantallaAdministrarCompra = new PantallaAdministrarCompra();
            pantallaAdministrarCompra.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == maquinaria) {
            PantallaAdministrarMaquinaria pantallaAdministrarMaquinaria = new PantallaAdministrarMaquinaria();
            pantallaAdministrarMaquinaria.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == tipoMaquinaria) {
            PantallaAdministrarTipoMaquinaria pantallaAdministrarTipoMaquinaria = new PantallaAdministrarTipoMaquinaria();
            pantallaAdministrarTipoMaquinaria.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == estadoMaquinaria) {
            PantallaAdministrarEstadoMaquinaria pantallaAdministrarEstadoMaquinaria = new PantallaAdministrarEstadoMaquinaria();
            pantallaAdministrarEstadoMaquinaria.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == laboreoTemp) {
            PantallaLaboreo pantallaLaboreo = new PantallaLaboreo();
            pantallaLaboreo.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == laboreo) {
            PantallaAdministrarLaboreos pantallaAdministrarLaboreos = new PantallaAdministrarLaboreos();
            pantallaAdministrarLaboreos.setVisible(true);
            getDefaultCloseOperation();
        }
//
//
        if (e.getSource() == lote) {
            PantallaAdministrarLote pantallaAdministrarLote = new PantallaAdministrarLote();
            pantallaAdministrarLote.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == tipoGrano) {
            PantallaAdministrarTipoGranos pantallaAdministrarTipoGranos = new PantallaAdministrarTipoGranos();
            pantallaAdministrarTipoGranos.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == campania) {
            PantallaAdministrarCampania pantallaAdministrarCampania = new PantallaAdministrarCampania();
            pantallaAdministrarCampania.setVisible(true);
            getDefaultCloseOperation();
        }


        if (e.getSource() == salir) {
            dispose();
        }

    }


}
