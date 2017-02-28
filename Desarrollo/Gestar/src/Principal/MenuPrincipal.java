package Principal;

import Acopio.AdministrarAcopio;
import Acopio.PantallaCargaAcopio;
import Acopio.PantallaEgresoAcopio;
import Acopio.TipoAcopio.AdministrarTipoAcopio;
import Ayuda.AcercaDe;
import Campania.PantallaAdministrarCampania;
import Campo.AdministrarCampo;
import Cliente.PantallaAdministrarCliente;
import Cliente.TipoCliente.AdministrarTipoCliente;
import Granos.PantallaAdministrarTipoGranos;
import Imagenes.ImageFondo;
import Insumo.PantallaAdministrarInsumo;
import Laboreo.PantallaAdministrarCampaniaPlanificada;
import Laboreo.PantallaAdministrarLaboreos;
import Laboreo.PantallaLaboreo;
import Laboreo.TipoLaboreo.PantallaAdministrarTipoLaboreo;
import Lote.PantallaAdministrarLote;
import Maquinaria.PantallaAdministrarMaquinaria;
import Maquinaria.PantallaTipoEstado.PantallaAdministrarEstadoMaquinaria;
import Maquinaria.PantallaTipoMaquinaria.PantallaAdministrarTipoMaquinaria;
import Procesos.PantallaAdministrarSolicitudInsumos;
import Procesos.PantallaRegistrarIngresoInsumo;
import Procesos.PantallaSolicitudInsumos;
import TipoInsumo.PantallaAdministrarTipoInsumo;
import TipoMedioAlmacenamiento.PantallaAdministrarMedios;
import Transporte.AdministrarTransporte;
import Transporte.TipoTransporte.AdministrarTipoTransporte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuPrincipal extends JFrame implements ActionListener {

    public JPanel panel1;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    private JMenuBar menuPrincipal;
    private JMenu opciones, insumos, maquinarias, procesos, lotes, tipoGranos, campanias, clientes,
            mediosAlmacenamientos, acopios, ayuda, transportes, campos, laboreos, ordenes;
    private JMenuItem laboreoTemp, laboreo, tipoLaboreo, lote, compra,
            salir, insumo, tipoInsumo,solicitudInsumo,ingresoInsumo, compraInsumo, compraInsumo2, maquinaria, tipoMaquinaria,
            estadoMaquinaria, tipoGrano, campania, cliente, medioAlmacenamiento,
            acopio, manual, acercaDe, transporte, administrarAcopios, tipoAcopio, campo, tipoCampo,
            tipoCliente, tipoTransporte, AdmCampaniaPlanificada, campaniaPlanificada, AdmOrdenesXCamp;

    public MenuPrincipal() {
//        this.setExtendedState(MAXIMIZED_BOTH);
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }

        setBounds(10,10,1300,600);
        ImageFondo image = new ImageFondo();
        image.setImage("/Imagenes/MenuPrincipalFondo2.jpg");
        setContentPane(image);
        setTitle("Sistema de Administracion");



        //BUTTONS

        //BUTTON PLANIFICAR CAMPANA
        JButton btnRegistrarLaboreo = new JButton("Planificar Campa\u00f1a");
        btnRegistrarLaboreo.setBounds(600, 100, 215, 70);
        image.add(btnRegistrarLaboreo);

        btnRegistrarLaboreo.addActionListener(e -> {
            PantallaLaboreo pantallaLaboreo = new PantallaLaboreo();
            pantallaLaboreo.setVisible(true);
            getDefaultCloseOperation();
        });



        //BUTTON REGISTRAR ORDEN DE TRABAJO
        JButton btnRegistrarOrdenTrabajo = new JButton("Generar orden de Trabajo");
        btnRegistrarOrdenTrabajo.setBounds(850, 100, 215, 70);
        image.add(btnRegistrarOrdenTrabajo);

        btnRegistrarOrdenTrabajo.addActionListener(e -> {
            PantallaAdministrarCampaniaPlanificada pantallaAdministrarCampaniaPlanificada = new PantallaAdministrarCampaniaPlanificada();
            pantallaAdministrarCampaniaPlanificada.setVisible(true);
            getDefaultCloseOperation();
        });


        //BUTTON REGISTRAR AVANCE
        JButton btnRegistrarAvance = new JButton("Registrar Avance de Campa\u00f1a");
        btnRegistrarAvance.setBounds(600, 200, 215, 70);
        image.add(btnRegistrarAvance);

        btnRegistrarAvance.addActionListener(e -> {
            PantallaAdministrarCampania pantallaAdministrarCampania = new PantallaAdministrarCampania();
            pantallaAdministrarCampania.setVisible(true);
            getDefaultCloseOperation();
        });


        //BUTTON REGISTRAR EGRESO
        JButton btnAdministrarAcopios = new JButton("Registrar Egreso de Acopio");
        btnAdministrarAcopios.setBounds(850, 200, 215, 70);
        image.add(btnAdministrarAcopios);

        btnAdministrarAcopios.addActionListener(e -> {
            PantallaEgresoAcopio pantallaEgresoAcopio = new PantallaEgresoAcopio("Carga", 0 , "Egreso");
            pantallaEgresoAcopio.setVisible(true);
            getDefaultCloseOperation();
        });



        //BUTTON EXIT
        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(750, 300, 215, 70);
        ImageIcon exitIcon = new ImageIcon("C:\\Users\\jagm\\Documents\\Habilitacion\\Desarrollo\\Gestar\\src\\Imagenes\\exit5.jpg");
        Image img = exitIcon.getImage() ;
        Image newimg = img.getScaledInstance( 230, 100,  java.awt.Image.SCALE_SMOOTH ) ;
        exitIcon = new ImageIcon(newimg );
        btnExit.setIcon(exitIcon);
        image.add(btnExit);

        btnExit.addActionListener(e -> {

            dispose();
        });


        image.setLayout(null);


        //MENUS
        menuPrincipal = new JMenuBar();
        setJMenuBar(menuPrincipal);


        //INSUMOS
        insumos = new JMenu("Insumos");
        menuPrincipal.add(insumos);

            insumo = new JMenuItem("Administrar Insumo");
            insumo.addActionListener(this);
            insumos.add(insumo);

            tipoInsumo = new JMenuItem("Administrar Tipo de Insumo");
            tipoInsumo.addActionListener(this);
            insumos.add(tipoInsumo);

            solicitudInsumo = new JMenuItem("Solicitar pedido de Insumos");
            solicitudInsumo.addActionListener(this);
            insumos.add(solicitudInsumo);

            compraInsumo = new JMenuItem("Administrar pedidos de Insumos");
            compraInsumo.addActionListener(this);
            insumos.add(compraInsumo);

            ingresoInsumo = new JMenuItem("Registrar Ingreso de Insumos");
            ingresoInsumo.addActionListener(this);
            insumos.add(ingresoInsumo);


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

            lote = new JMenuItem("Administrar Lotes");
            lote.addActionListener(this);
            lotes.add(lote);


        //LABOREOS
        laboreos = new JMenu("Laboreos");
        menuPrincipal.add(laboreos);

            laboreo = new JMenuItem("Administrar Laboreos");
            laboreo.addActionListener(this);
            laboreos.add(laboreo);

            tipoLaboreo = new JMenuItem("Administrar Tipo Laboreo");
            tipoLaboreo.addActionListener(this);
            laboreos.add(tipoLaboreo);


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

            campaniaPlanificada = new JMenuItem("Planificar Campa\u00f1a");
            campaniaPlanificada.addActionListener(this);
            campanias.add(campaniaPlanificada);

            AdmCampaniaPlanificada = new JMenuItem("Administrar Campa\u00f1as Planificadas");
            AdmCampaniaPlanificada.addActionListener(this);
            campanias.add(AdmCampaniaPlanificada);



        //CLIENTES
        clientes = new JMenu("Clientes");
        menuPrincipal.add(clientes);

            cliente = new JMenuItem("Administrar cliente");
            cliente.addActionListener(this);
            clientes.add(cliente);

            tipoCliente = new JMenuItem("Administrar Tipo de Cliente");
            tipoCliente.addActionListener(this);
            clientes.add(tipoCliente);

        //TRANSPORTES
        transportes = new JMenu("Transportes");
        menuPrincipal.add(transportes);

            transporte = new JMenuItem("Administrar Transporte");
            transporte.addActionListener(this);
            transportes.add(transporte);

            tipoTransporte = new JMenuItem("Administrar Tipo de Transporte");
            tipoTransporte.addActionListener(this);
            transportes.add(tipoTransporte);

        //CAMPOS
        campos = new JMenu("Campos");
        menuPrincipal.add(campos);

            campo = new JMenuItem("Administrar Campos");
            campo.addActionListener(this);
            campos.add(campo);

//            tipoCampo = new JMenuItem("Administrar Tipo de Campo");
//            tipoCampo.addActionListener(this);
//            campos.add(tipoCampo);


//        //MEDIOS
//        mediosAlmacenamientos = new JMenu("Medios de Almacenamiento");
//        menuPrincipal.add(mediosAlmacenamientos);
//
//        medioAlmacenamiento = new JMenuItem("Administrar Medios");
//        medioAlmacenamiento.addActionListener(this);
//        mediosAlmacenamientos.add(medioAlmacenamiento);

        //ACOPIO
        acopios = new JMenu("Acopio");
        menuPrincipal.add(acopios);

            acopio = new JMenuItem("Registrar Ingreso de Granos");
            acopio.addActionListener(this);
            acopios.add(acopio);

            administrarAcopios = new JMenuItem("Administrar Acopios");
            administrarAcopios.addActionListener(this);
            acopios.add(administrarAcopios);

            tipoAcopio = new JMenuItem("Administrar Tipo de Acopio");
            tipoAcopio.addActionListener(this);
            acopios.add(tipoAcopio);


        //ORDENES
        ordenes = new JMenu("Ordenes");
        menuPrincipal.add(ordenes);

            AdmOrdenesXCamp = new JMenuItem("Administrar Ordenes Segun Campa\u00f1as");
            AdmOrdenesXCamp.addActionListener(this);
            ordenes.add(AdmOrdenesXCamp);


        //AYUDA
        ayuda = new JMenu("Ayuda");
        menuPrincipal.add(ayuda);
            //AYUDA
            manual = new JMenuItem("Manual");
            manual.addActionListener(this);
            ayuda.add(manual);

            //ACERCA DE
            acercaDe = new JMenuItem("Acerca de");
            acercaDe.addActionListener(this);
            ayuda.add(acercaDe);

        //OPCIONES
        opciones = new JMenu("Opciones");
        menuPrincipal.add(opciones);

            //SALIR
            salir = new JMenuItem("Salir");
            salir.addActionListener(this);
            opciones.add(salir);



    }

    public void actionPerformed(ActionEvent e) {
        Container f = this.getContentPane();
        if (e.getSource() == insumo) {
            PantallaAdministrarInsumo pantallaAdministrarInsumo = new PantallaAdministrarInsumo();
            pantallaAdministrarInsumo.setVisible(true);
            pantallaAdministrarInsumo.setBounds(10,10,900,900);
            getDefaultCloseOperation();

        }

        if (e.getSource() == tipoInsumo) {
            PantallaAdministrarTipoInsumo pantallaAdministrarTipoInsumo = new PantallaAdministrarTipoInsumo();
            pantallaAdministrarTipoInsumo.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == solicitudInsumo) {
            PantallaSolicitudInsumos pantallaSolicitudInsumos = new PantallaSolicitudInsumos();
            pantallaSolicitudInsumos.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == compraInsumo) {
            PantallaAdministrarSolicitudInsumos pantallaAdministrarSolicitudInsumos = new PantallaAdministrarSolicitudInsumos();
            pantallaAdministrarSolicitudInsumos.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == ingresoInsumo) {
            PantallaRegistrarIngresoInsumo pantallaRegistrarIngresoInsumo = new PantallaRegistrarIngresoInsumo();
            pantallaRegistrarIngresoInsumo.setVisible(true);
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

        if (e.getSource() == tipoLaboreo) {
            PantallaAdministrarTipoLaboreo pantallaAdministrarTipoLaboreo = new PantallaAdministrarTipoLaboreo();
            pantallaAdministrarTipoLaboreo.setVisible(true);
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

        if (e.getSource() == cliente) {
            PantallaAdministrarCliente pantallaAdministrarCliente = new PantallaAdministrarCliente();
            pantallaAdministrarCliente.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == medioAlmacenamiento) {
            PantallaAdministrarMedios pantallaAdministrarMedios = new PantallaAdministrarMedios();
            pantallaAdministrarMedios.setVisible(true);
            getDefaultCloseOperation();
        }


        if (e.getSource() == acopio) {
            PantallaCargaAcopio pantallaCargaAcopio = new PantallaCargaAcopio("Carga", "", "", null,
                    "", "", null, "", 0);
            pantallaCargaAcopio.setVisible(true);
            pantallaCargaAcopio.setBounds(10, 10, 700, 700);
            getDefaultCloseOperation();
        }

        if (e.getSource() == tipoAcopio) {
            AdministrarTipoAcopio pantallaCargaAcopio = new AdministrarTipoAcopio();
            pantallaCargaAcopio.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == administrarAcopios) {
            AdministrarAcopio pantallaCargaAcopio = new AdministrarAcopio();
            pantallaCargaAcopio.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == acercaDe) {
            AcercaDe acercaDe = new AcercaDe();
            acercaDe.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == AdmOrdenesXCamp) {
            PantallaAdministrarCampaniaPlanificada administrarOrdenesPorCampania = new PantallaAdministrarCampaniaPlanificada();
            administrarOrdenesPorCampania.setVisible(true);
            getDefaultCloseOperation();
        }



        if (e.getSource() == campaniaPlanificada) {
            PantallaLaboreo administrarOrdenesPorCampania = new PantallaLaboreo();
            administrarOrdenesPorCampania.setVisible(true);
            getDefaultCloseOperation();
        }


        if (e.getSource() == AdmCampaniaPlanificada) {
            PantallaAdministrarCampaniaPlanificada administrarOrdenesPorCampania = new PantallaAdministrarCampaniaPlanificada();
            administrarOrdenesPorCampania.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == tipoCliente) {
            AdministrarTipoCliente administrarOrdenesPorCampania = new AdministrarTipoCliente();
            administrarOrdenesPorCampania.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == transporte) {
            AdministrarTransporte administrarOrdenesPorCampania = new AdministrarTransporte();
            administrarOrdenesPorCampania.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == tipoTransporte) {
            AdministrarTipoTransporte administrarOrdenesPorCampania = new AdministrarTipoTransporte();
            administrarOrdenesPorCampania.setVisible(true);
            getDefaultCloseOperation();
        }

        if (e.getSource() == campo) {
            AdministrarCampo administrarOrdenesPorCampania = new AdministrarCampo();
            administrarOrdenesPorCampania.setVisible(true);
            getDefaultCloseOperation();
        }
//
//        if (e.getSource() == manual) {
//             Manual = new AdministrarTipoTransporte();
//            administrarOrdenesPorCampania.setVisible(true);
//            getDefaultCloseOperation();
//        }


        if (e.getSource() == salir) {
            dispose();
        }

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
