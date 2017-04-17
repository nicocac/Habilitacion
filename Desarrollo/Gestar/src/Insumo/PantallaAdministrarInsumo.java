package Insumo;

import Conexion.Coneccion;
import Datos.InsumoEntity;
import Datos.TipoInsumoEntity;
import Repository.InsumoRepository;
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


public class PantallaAdministrarInsumo extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnNuevo;
    private JTable tblInsumos;
    private JButton btnCancelar;
    public JButton imprimirStockInsumosButton;
    public JButton bntAyuda;
    public JScrollPane jscroll;
    private JTable table1;
    private InsumoEntity insumo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    InsumoRepository insumoRepository = new InsumoRepository();


    public PantallaAdministrarInsumo() {


        //INICIO


        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(900, 800));
        panel1.setPreferredSize(new Dimension(900, 800));
        container.add(panel1);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);


//        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Insumo");
        inicializaTabla();
        buscarInsumos();


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Coneccion.getSession();
            buscarInsumos();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
            inicializaTabla();
            txtBuscar.setText("");
        });


        //EDITAR
        btnEditar.addActionListener(e -> {
            int fila = tblInsumos.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            int insId = (int) tblInsumos.getModel().getValueAt(fila, 0);
            String nombre = (String) tblInsumos.getModel().getValueAt(fila, 1);
            String descripcion = (String) tblInsumos.getModel().getValueAt(fila, 2);
            String unidadMedida = (String) tblInsumos.getModel().getValueAt(fila, 3);
            TipoInsumoEntity tipoInsumo = (TipoInsumoEntity) tblInsumos.getModel().getValueAt(fila, 4);
            String stock = String.valueOf(tblInsumos.getModel().getValueAt(fila, 5));

            CargaInsumo carga = new CargaInsumo("Modificacion", nombre, descripcion, unidadMedida, tipoInsumo, stock, insId);
            carga.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente el insumo.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el insumo.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            CargaInsumo cargaInsumo = new CargaInsumo("Carga", "", "", "", null, "", 0);
            cargaInsumo.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());

        imprimirStockInsumosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    // Creacion del parrafo
                    Paragraph paragraph = new Paragraph();
                    Font fontTitulos = FontFactory.getFont(
                            FontFactory.COURIER_BOLD, 14, Font.UNDERLINE,
                            BaseColor.BLACK);

                    paragraph.add(new Phrase("Reporte Stock de Insumos:", fontTitulos));
                    paragraph.add(new Phrase(Chunk.NEWLINE));
                    paragraph.add(new Phrase(Chunk.NEWLINE));

                    Document doc = new Document();
                    PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\jagm\\Documents\\Habilitacion\\Desarrollo\\Gestar\\src\\Imagenes\\testListado.pdf"));
                    doc.open();
                    PdfPTable pdfTable = new PdfPTable(tblInsumos.getColumnCount());
                    //adding table headers
                    for (int i = 0; i < tblInsumos.getColumnCount(); i++) {
                        pdfTable.addCell(tblInsumos.getColumnName(i));
                    }
                    //extracting data from the JTable and inserting it to PdfPTable
                    for (int rows = 0; rows < tblInsumos.getRowCount(); rows++) {
                        for (int cols = 0; cols < tblInsumos.getColumnCount(); cols++) {
                            pdfTable.addCell(tblInsumos.getModel().getValueAt(rows, cols).toString());

                        }
                    }

                    doc.add(paragraph);
                    doc.add(pdfTable);
                    doc.close();
                    showMessage("Listado De stock de Insumos Impreso");
                    System.out.println("Listado De stock de Insumos Impreso");
//                    doc.open();
                    String pdfFile = "C:\\Users\\jagm\\Documents\\Habilitacion\\Desarrollo\\Gestar\\src\\Imagenes\\testListado.pdf";
                    try {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFile);
                    } catch (IOException io) {
                        Logger.getLogger(PantallaAdministrarInsumo.class.getName()).log(Level.SEVERE, null, io);
                    }

                } catch (DocumentException ex) {
                    Logger.getLogger(PantallaAdministrarInsumo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PantallaAdministrarInsumo.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        });


        bntAyuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AyudaInsumo ayudaInsumo = new AyudaInsumo();
                ayudaInsumo.setVisible(true);
                getDefaultCloseOperation();

//                JasperReport reporte = (JasperReport) JRLoader.loadObject("reporte1.jasper");
            }
        });
    }


    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Nombre", "Descripcion", "Unidad de Medida", "Tipo Insumo", "Stock"};
        Object[][] data = new Object[1][6];
        setModel(columnNames, data, tblInsumos);
        jscroll.setBounds(10, 10, 1300, 800);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblInsumos.setModel(model);
        tblInsumos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblInsumos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblInsumos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblInsumos.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblInsumos.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblInsumos.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblInsumos.getColumnModel().getColumn(5).setPreferredWidth(60);
    }

    private void showMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }


    //METODO DAR BAJA
    public int darBaja() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        try {
//            insumo = new InsumoEntity();
            int fila = tblInsumos.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            insumo = insumoRepository.getInsumoById((Integer) tblInsumos.getModel().getValueAt(fila, 0));

//            insumo.setInsId((int) tblInsumos.getModel().getValueAt(fila, 0));
//            insumo.setInsNombre((String) tblInsumos.getModel().getValueAt(fila, 1));
//            insumo.setInsDescripcion((String) tblInsumos.getModel().getValueAt(fila, 2));
//            insumo.setInsUnidadMedida((String) tblInsumos.getModel().getValueAt(fila, 3));
//            insumo.setTipoInsumoByInsTinId((TipoInsumoEntity) tblInsumos.getModel().getValueAt(fila, 4));
//            insumo.setInsFechaAlta(fechaActual);
//            insumo.setInsUsuarioAlta("adminBajaINSUMO");
            insumo.setInsFechaUltMod(fechaActual);
            insumo.setInsUsuarioUtlMod("adminBajaINSUMO");
            insumo.setInsFechaBaja(fechaActual);
            insumo.setInsUsuarioBaja("adminBajaINSUMO");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del insumo: " + tblInsumos.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(insumo);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja el insumo: " + e.toString());
            return 2;
        } finally {
            session.close();
        }

        return 0;
    }


    //METODO BUSCAR INSUMOS
    public void buscarInsumos() {
        Session session = Coneccion.getSession();
        int i = 0;
        try {
            insumo = new InsumoEntity();
            Query query = session.createQuery("select t from InsumoEntity t where ucase(insNombre) like ucase(:pNombre) and insFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Descripcion", "Unidad de Medida", "Tipo Insumo", "Stock"};
            Object[][] data = new Object[list.size()][6];

            while (iter.hasNext()) {
                insumo = (InsumoEntity) iter.next();
                data[i][0] = insumo.getInsId();
                data[i][1] = insumo.getInsNombre();
                data[i][2] = insumo.getInsDescripcion();
                data[i][3] = insumo.getInsUnidadMedida();
                if (insumo.getTipoInsumoByInsTinId() == null) {
                    data[i][4] = null;
                } else {
                    data[i][4] = insumo.getTipoInsumoByInsTinId().getTinNombre();
                }

                //TODO verificar como manejamos el stock
//                StockInsumoEntity stockInsumoEntity = (StockInsumoEntity) session.createQuery("select x from StockInsumoEntity x where x.insumoBySinInsId = :pNombre").setParameter("pNombre", insumo).uniqueResult();
//                if (stockInsumoEntity != null) {
//                    data[i][5] = stockInsumoEntity.getSinTotal();
//                } else {
//                    data[i][5] = 0;
//                }
                data[i][5] = insumo.getInsStock();
                i++;
            }
            setModel(columnNames, data, tblInsumos);
        } finally {
            session.close();
        }

    }

}
