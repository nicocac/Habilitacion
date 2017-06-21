package Maquinaria;

import Conexion.Conexion;
import Datos.MaquinariaEntity;
import Datos.TipoEstadoMaquinariaEntity;
import Datos.TipoMaquinariaEntity;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PantallaAdministrarMaquinaria extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnNuevo;
    private JButton btnEditar;
    private JTable tblMaquinaria;
    private JButton btnCancelar;
    public JButton imprimirInformeMaquinariasButton;
    public JPanel panelIni;

    private JTable table1;
    private MaquinariaEntity maquinaria;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public PantallaAdministrarMaquinaria() {


        //INICIO
        JPanel container = new JPanel();

//        container.setPreferredSize(new Dimension(1920, 1900));
//        panel1.setPreferredSize(new Dimension(1900, 1800));
        container.add(panelIni);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);
//        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Maquinaria");
        inicializaTabla();
        buscarMaquinarias();
        container.repaint();
        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession()
            buscarMaquinarias();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
            inicializaTabla();
            txtBuscar.setText("");
        });


        //EDITAR
        btnEditar.addActionListener(e -> {
            int fila = tblMaquinaria.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            int maqId = (int) tblMaquinaria.getModel().getValueAt(fila, 0);
            String nombre = (String) tblMaquinaria.getModel().getValueAt(fila, 1);
            String descripcion = (String) tblMaquinaria.getModel().getValueAt(fila, 2);
            TipoEstadoMaquinariaEntity tipoEstadoMaquinaria = (TipoEstadoMaquinariaEntity) tblMaquinaria.getModel().getValueAt(fila, 3);
            String marca = (String) tblMaquinaria.getModel().getValueAt(fila, 4);
            String modelo = (String) tblMaquinaria.getModel().getValueAt(fila, 5);
            TipoMaquinariaEntity tipoMaquinaria = (TipoMaquinariaEntity) tblMaquinaria.getModel().getValueAt(fila, 6);
            String anio = String.valueOf(tblMaquinaria.getModel().getValueAt(fila, 7));

            CargaMaquinaria carga = new CargaMaquinaria("Modificacion", nombre, descripcion, tipoEstadoMaquinaria, marca, modelo, tipoMaquinaria, anio, maqId);
            carga.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();


        });


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente la maquinaria.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja la maquinaria.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            CargaMaquinaria cargaMaquinaria = new CargaMaquinaria("Carga", "", "", null, "", "", null, "", 0);
            cargaMaquinaria.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());

        imprimirInformeMaquinariasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    // Creacion del parrafo
                    Paragraph paragraph = new Paragraph();
                    Font fontTitulos = FontFactory.getFont(
                            FontFactory.COURIER_BOLD, 14, Font.UNDERLINE,
                            BaseColor.BLACK);

                    paragraph.add(new Phrase("Reporte Stock de Maquinarias:", fontTitulos));
                    paragraph.add(new Phrase(Chunk.NEWLINE));
                    paragraph.add(new Phrase(Chunk.NEWLINE));

                    Document doc = new Document();
                    PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\jagm\\Documents\\Habilitacion\\src\\main\\resources\\ListadosPDF\\testListadoMaq.pdf"));
                    doc.open();
                    PdfPTable pdfTable = new PdfPTable(tblMaquinaria.getColumnCount());
                    //adding table headers
                    for (int i = 0; i < tblMaquinaria.getColumnCount(); i++) {
                        pdfTable.addCell(tblMaquinaria.getColumnName(i));
                    }
                    //extracting data from the JTable and inserting it to PdfPTable
                    for (int rows = 0; rows < tblMaquinaria.getRowCount() ; rows++) {
                        for (int cols = 0; cols < tblMaquinaria.getColumnCount(); cols++) {
                            if(tblMaquinaria.getModel().getValueAt(rows, cols) != null){
                                pdfTable.addCell(tblMaquinaria.getModel().getValueAt(rows, cols).toString());
                            } else {
                                pdfTable.addCell("Sin Datos");
                            }
                        }
                    }

                    doc.add(paragraph);
                    doc.add(pdfTable);
                    doc.close();
                    showMessage("Listado De stock de Maquinarias Impreso");
                    System.out.println("Listado De stock de Maquinarias Impreso");
//                    doc.open();
                    String pdfFile = "C:\\Users\\jagm\\Documents\\Habilitacion\\src\\main\\resources\\ListadosPDF\\testListadoMaq.pdf";
                    try {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFile);
                    }catch (IOException io) {
                        Logger.getLogger(PantallaAdministrarMaquinaria.class.getName()).log(Level.SEVERE, null, io);
                    }

                } catch (DocumentException ex) {
                    Logger.getLogger(PantallaAdministrarMaquinaria.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PantallaAdministrarMaquinaria.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        });
    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Nombre", "Descripcion", "Estado", "Marca", "Modelo", "Tipo", "Anio"};
        Object[][] data = new Object[1][8];
        setModel(columnNames, data, tblMaquinaria);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblMaquinaria.setModel(model);
        tblMaquinaria.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblMaquinaria.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblMaquinaria.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblMaquinaria.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblMaquinaria.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblMaquinaria.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblMaquinaria.getColumnModel().getColumn(5).setPreferredWidth(60);
        tblMaquinaria.getColumnModel().getColumn(6).setPreferredWidth(60);
        tblMaquinaria.getColumnModel().getColumn(7).setPreferredWidth(60);
    }

    private void showMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }


    //METODO DAR BAJA
    public int darBaja() {
        Session session = Conexion.getSessionFactory().openSession();
        Boolean guardado = false;
        try {
            maquinaria = new MaquinariaEntity();
            int fila = tblMaquinaria.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            maquinaria.setMaqId((int) tblMaquinaria.getModel().getValueAt(fila, 0));
            maquinaria.setMaqNombre((String) tblMaquinaria.getModel().getValueAt(fila, 1));
            maquinaria.setMaqDescripcion((String) tblMaquinaria.getModel().getValueAt(fila, 2));
            maquinaria.setTipoEstadoMaquinariaByMaqTestadoId((TipoEstadoMaquinariaEntity) tblMaquinaria.getModel().getValueAt(fila, 3));
            maquinaria.setMaqMarca((String) tblMaquinaria.getModel().getValueAt(fila, 4));
            maquinaria.setMaqModelo((String) tblMaquinaria.getModel().getValueAt(fila, 5));
            maquinaria.setTipoMaquinariaByMaqTmaqId((TipoMaquinariaEntity) tblMaquinaria.getModel().getValueAt(fila, 6));
            maquinaria.setMaqAnioFabricacion((String) tblMaquinaria.getModel().getValueAt(fila, 7));

            maquinaria.setMaqFechaAlta(fechaActual);
            maquinaria.setMaqUsuarioAlta("adminBajaMAQUINARIA");
            maquinaria.setMaqFechaUltMod(fechaActual);
            maquinaria.setMaqUsuarioUtlMod("adminBajaMAQUINARIA");
            maquinaria.setMaqFechaBaja(fechaActual);
            maquinaria.setMaqUsuarioBaja("adminBajaMAQUINARIA");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja de la maquinaria: " + tblMaquinaria.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(maquinaria);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja la maquinaria: " + e.toString());
            return 2;
        } finally {
            session.close();
        }

        return 0;
    }


    //METODO BUSCAR MAQUINARIAS
    public void buscarMaquinarias() {
        Session session = Conexion.getSessionFactory().openSession();
        int i = 0;
        try {
            maquinaria = new MaquinariaEntity();
            Query query = session.createQuery("select t from MaquinariaEntity t where ucase(maqNombre) like ucase(:pNombre) and maqFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Descripcion", "Estado", "Marca", "Modelo", "Tipo", "Anio"};
            Object[][] data = new Object[list.size()][8];

            while (iter.hasNext()) {
                maquinaria = (MaquinariaEntity) iter.next();
                data[i][0] = maquinaria.getMaqId();
                data[i][1] = maquinaria.getMaqNombre();
                data[i][2] = maquinaria.getMaqDescripcion();
                data[i][3] = maquinaria.getTipoEstadoMaquinariaByMaqTestadoId();
                data[i][4] = maquinaria.getMaqMarca();
                data[i][5] = maquinaria.getMaqModelo();
                data[i][6] = maquinaria.getTipoMaquinariaByMaqTmaqId();
                data[i][7] = maquinaria.getMaqAnioFabricacion();
                i++;
            }
            setModel(columnNames, data, tblMaquinaria);
        } finally {
            session.close();
        }

    }
}
