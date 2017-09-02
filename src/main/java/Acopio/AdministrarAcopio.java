package Acopio;

import Acopio.Acopio.CargaAcopio;
import Conexion.Conexion;
import Datos.AcopioEntity;
import Laboreo.GestorLaboreo;
import Repository.AcopioRepository;
import TipoInsumo.CargaTipoInsumo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jagm on 17/02/2017.
 */
public class AdministrarAcopio extends JFrame {
    private JTextField txtBuscar;
    private JPanel panel1;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnCancelar;
    private JTable tblTipos;
    private JButton btnEliminar;
    private JButton btnNuevo;
    public JPanel panel3;
    public JButton imprimirStockAcopioButton;
    public JButton graficoBtn;
    public JPanel panelImage;
    private AcopioEntity tipo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    AcopioRepository acopioRepository = new AcopioRepository();
    GestorLaboreo gest = new GestorLaboreo();

    public AdministrarAcopio() {


        //INICIO
        JPanel container = new JPanel();
//        container.setPreferredSize(new Dimension(1920, 1900));
//        panel1.setPreferredSize(new Dimension(1900, 1800));
        container.add(panel3);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);

//        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Silos");
        inicializaTabla();
        buscarTiposAcopio();


//        ImageFondo image = new ImageFondo();
//        image.setImage("/Imagenes.Imagenes/MenuPrincipalBanner.jpg");
//        image.setAlignmentX(10);
////
////        panelImage.paint(image); //setContentPane(image);
////        panelImage.setVisible(true);
//        this.add(image);
        setTitle("Sistema de Administracion");

        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().getCurrentSession();
            buscarTiposAcopio();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
            inicializaTabla();
            txtBuscar.setText("");
        });

        //EDITAR
        btnEditar.addActionListener(e -> {
            int fila = tblTipos.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            int tinId = (int) tblTipos.getModel().getValueAt(fila, 0);
            String nombre = (String) tblTipos.getModel().getValueAt(fila, 1);
            String descripcion = (String) tblTipos.getModel().getValueAt(fila, 2);
            CargaTipoInsumo carga = new CargaTipoInsumo("Modificacion", nombre, descripcion, tinId);
            carga.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente el  Silo.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el  Silo.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            CargaAcopio cargaTipoInsumo = new CargaAcopio("Carga", "", 0, 0);
            cargaTipoInsumo.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        imprimirStockAcopioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    // Creacion del parrafo
                    Paragraph paragraph = new Paragraph();
                    com.itextpdf.text.Font fontTitulos = FontFactory.getFont(
                            FontFactory.COURIER_BOLD, 14, com.itextpdf.text.Font.UNDERLINE,
                            BaseColor.BLACK);

                    paragraph.add(new Phrase("Reporte Stock de Semillas:", fontTitulos));
                    paragraph.add(new Phrase(Chunk.NEWLINE));
                    paragraph.add(new Phrase(Chunk.NEWLINE));

                    Document doc = new Document();
                    PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\jagm\\Documents\\Habilitacion\\src\\main\\resources\\ListadosPDF\\testListadoAcopio.pdf"));
                    doc.open();
                    PdfPTable pdfTable = new PdfPTable(tblTipos.getColumnCount());
                    //adding table headers
                    for (int i = 0; i < tblTipos.getColumnCount(); i++) {
                        pdfTable.addCell(tblTipos.getColumnName(i));
                    }
                    //extracting data from the JTable and inserting it to PdfPTable
                    for (int rows = 0; rows < tblTipos.getRowCount(); rows++) {
                        for (int cols = 0; cols < tblTipos.getColumnCount(); cols++) {
                            pdfTable.addCell(tblTipos.getModel().getValueAt(rows, cols).toString());

                        }
                    }

                    doc.add(paragraph);
                    doc.add(pdfTable);
                    doc.close();
                    showMessage("Listado De stock de Semillas Impreso");
                    System.out.println("Listado De stock de Semillas Impreso");
//                    doc.open();
                    String pdfFile = "C:\\Users\\jagm\\Documents\\Habilitacion\\src\\main\\resources\\ListadosPDF\\testListadoAcopio.pdf";
                    try {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFile);
                    } catch (IOException io) {
                        Logger.getLogger(AdministrarAcopio.class.getName()).log(Level.SEVERE, null, io);
                    }

                } catch (DocumentException ex) {
                    Logger.getLogger(AdministrarAcopio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AdministrarAcopio.class.getName()).log(Level.SEVERE, null, ex);
                }


            }

        });
        graficoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GraficaAcopio graficaAcopio = new GraficaAcopio(tblTipos);
//                graficaAcopio.setBounds(50,50,400,500);
                graficaAcopio.setVisible(true);
            }
        });
    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Nombre", "Codigo", "Tipo Silo", "Semilla", "Estado", "Cantidad Total"};
        Object[][] data = new Object[1][6];
        setModel(columnNames, data, tblTipos);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblTipos.setModel(model);
        tblTipos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblTipos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblTipos.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblTipos.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblTipos.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblTipos.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblTipos.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblTipos.getColumnModel().getColumn(6).setPreferredWidth(100);
    }

    private void showMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    //METODO DAR BAJA
    public int darBaja() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        Boolean guardado = false;
        try {
            tipo = new AcopioEntity();
            int fila = tblTipos.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            if (Integer.parseInt(tblTipos.getModel().getValueAt(fila, 5).toString()) > 0) {
                showMessage("El Acopio debe estar vacio para poder darse de baja.");
                return 3;
            }
            AcopioEntity acopioEntity = acopioRepository.getAcopioById((Long) tblTipos.getModel().getValueAt(fila, 0));
            acopioEntity.setAcopioFechaUltMod(fechaActual);
            acopioEntity.setAcopioFechaBaja(fechaActual);
            acopioEntity.setAcopioUsuarioBaja("adminBAJA");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del Acopio: " + tblTipos.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(acopioEntity);
                tx.commit();
                guardado = tx.wasCommitted();
//                guardado = true;
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja el Acopio: " + e.toString());
            return 2;
        } finally {
            tx.rollback();        }

        return 0;
    }


    //METODO BUSCAR TIPOS
    public void buscarTiposAcopio() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        int i = 0;
        try {
            java.util.List<Object[]> list;
            Object[] acopio;

            list = gest.getStockByAcopio();
            //
            List<Object[]> listEgreso = gest.getStockEgresoByAcopio();

            List<Object[]> listStockFinal = new ArrayList<>();

            for (Object[] ingreso : list) {

                for (Object[] egreso : listEgreso) {
                    if (ingreso[0].equals(egreso[0])) {

                        Long cantidadFinal = (Long) ingreso[6] - (Long) egreso[1];
                        ingreso[6] = cantidadFinal;
                    }

                }
                listStockFinal.add(ingreso);
            }
            //

            Iterator iter = listStockFinal.iterator();
            String[] columnNames = {"Cod", "Nombre", "Nro", "Tipo Silos", "Semilla", "Estado", "Cantidad Total"};
            Object[][] data = new Object[listStockFinal.size()][7];

            while (iter.hasNext()) {
                acopio = (Object[]) iter.next();
                data[i][0] = acopio[0];
                data[i][1] = acopio[1];
                data[i][2] = acopio[2];
                data[i][3] = acopio[3];
                data[i][4] = acopio[4];
                data[i][5] = acopio[5];
                data[i][6] = acopio[6];
                i++;
            }
            setModel(columnNames, data, tblTipos);
            tx.rollback();
        } catch (Exception e) {
        } finally {
            ////session.close();
        }

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
