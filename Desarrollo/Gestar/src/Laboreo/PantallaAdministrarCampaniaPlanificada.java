package Laboreo;

import Conexion.Coneccion;
import Datos.InsumoEntity;
import Datos.PlanificacionCampaniaEntity;
import Datos.TipoInsumoEntity;
import Insumo.CargaInsumo;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.Iterator;

/**
 * Created by jagm on 23/10/2016.
 */
public class PantallaAdministrarCampaniaPlanificada extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnNuevo;
    private JTable tblCampPlanificadas;
    private JButton btnCancelar;
    public JButton btnRegistrarOrden;
    private JTable table1;
    private InsumoEntity insumo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public PantallaAdministrarCampaniaPlanificada() {


        //INICIO
        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Campa\u00f1as planificadas");
        inicializaTabla();
        buscarLaboreos();


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Coneccion.getSession();
            buscarLaboreos();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
//            inicializaTabla();
//            txtBuscar.setText("");

            Document document = new Document();
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\jagm\\Desktop\\test.pdf"));
                document.open();
                PdfContentByte contentByte = writer.getDirectContent();
                PdfTemplate template = contentByte.createTemplate(2600, 1800);
                Graphics2D g2 = template.createGraphics(900, 100);
                panel1.print(g2);
                g2.dispose();
                contentByte.addTemplate(template, 30, 300);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            finally{
                if(document.isOpen()){
                    document.close();
                }
            }
            dispose();

        });


        //EDITAR
        btnEditar.addActionListener(e -> {
            int fila = tblCampPlanificadas.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            Integer campPlanificada = (Integer) tblCampPlanificadas.getModel().getValueAt(fila, 0);
            String nombre = (String) tblCampPlanificadas.getModel().getValueAt(fila, 1);
            Date fechaIni = (Date) tblCampPlanificadas.getModel().getValueAt(fila, 2);
            Date fechaFin = (Date) tblCampPlanificadas.getModel().getValueAt(fila, 3);
//            String descripcion = (String) tblCampPlanificadas.getModel().getValueAt(fila, 2);
//            String unidadMedida = (String) tblCampPlanificadas.getModel().getValueAt(fila, 3);
//            TipoInsumoEntity tipoInsumo = (TipoInsumoEntity) tblCampPlanificadas.getModel().getValueAt(fila, 4);
//            String stock = String.valueOf(tblCampPlanificadas.getModel().getValueAt(fila, 5));

            GenerarOrdenTrabajo carga = new GenerarOrdenTrabajo("Modificacion", campPlanificada);
            carga.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente la campania planificada.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja la campania planificada.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            int fila = tblCampPlanificadas.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            Integer campPlanificada = (Integer) tblCampPlanificadas.getModel().getValueAt(fila, 0);
            GenerarOrdenTrabajo generarOrdenTrabajo = new GenerarOrdenTrabajo("Carga", campPlanificada);
            generarOrdenTrabajo.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());

   /*     btnRegistrarOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarAvanceCampania registrarAvanceCampania = new RegistrarAvanceCampania("Registrar Orden Trabajo Realizada", 0,null,null,null,null);
                registrarAvanceCampania.setVisible(true);
                getDefaultCloseOperation();
            }
        });*/
    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Campa\u00f1a", "Fecha Inicio", "Fecha Fin"};
        Object[][] data = new Object[1][4];
        setModel(columnNames, data, tblCampPlanificadas);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblCampPlanificadas.setModel(model);
        tblCampPlanificadas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblCampPlanificadas.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblCampPlanificadas.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblCampPlanificadas.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblCampPlanificadas.getColumnModel().getColumn(3).setPreferredWidth(150);
//        tblCampPlanificadas.getColumnModel().getColumn(4).setPreferredWidth(200);
    }

    private void showMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }


    //METODO DAR BAJA
    public int darBaja() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        try {
            insumo = new InsumoEntity();
            int fila = tblCampPlanificadas.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }
            insumo.setInsId((int) tblCampPlanificadas.getModel().getValueAt(fila, 0));
            insumo.setInsNombre((String) tblCampPlanificadas.getModel().getValueAt(fila, 1));
            insumo.setInsDescripcion((String) tblCampPlanificadas.getModel().getValueAt(fila, 2));
            insumo.setInsUnidadMedida((String) tblCampPlanificadas.getModel().getValueAt(fila, 3));
            insumo.setTipoInsumoByInsTinId((TipoInsumoEntity) tblCampPlanificadas.getModel().getValueAt(fila, 4));
            insumo.setInsFechaAlta(fechaActual);
            insumo.setInsUsuarioAlta("adminBajaLaboreo");
            insumo.setInsFechaUltMod(fechaActual);
            insumo.setInsUsuarioUtlMod("adminBajaLaboreo");
            insumo.setInsFechaBaja(fechaActual);
            insumo.setInsUsuarioBaja("adminBajaLaboreo");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del laboreo: " + tblCampPlanificadas.getModel().getValueAt(fila, 1));
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


    //METODO BUSCAR Campanias planificadas
    public void buscarLaboreos() {
        Session session = Coneccion.getSession();
        int i = 0;
        try {
            PlanificacionCampaniaEntity planificacion = new PlanificacionCampaniaEntity();
//            Query query = session.createQuery("select t from PlanificacionCampaniaEntity t " +
//                    "where ucase(t.loteCampania.campaniaByLcpCnaId.cnaDenominacion) like ucase(:pNombre) and t.fechaBaja is null" +
//                    " group by t.loteCampania.campaniaByLcpCnaId.cnaId");

            Query query = session.createQuery("select t from PlanificacionCampaniaEntity t " +
                    "where ucase(t.loteCampania.campaniaByLcpCnaId.cnaDenominacion) like ucase(:pNombre) and t.fechaBaja is null");

            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Campa\u00f1a", "Fecha Inicio", "Fecha Fin"};
            Object[][] data = new Object[list.size()][4];

            while (iter.hasNext()) {
                planificacion = (PlanificacionCampaniaEntity) iter.next();
                data[i][0] = planificacion.getPlanificacionId();
                data[i][1] = planificacion.getLoteCampania().getCampaniaByLcpCnaId().getCnaDenominacion();
                data[i][2] = planificacion.getLoteCampania().getCampaniaByLcpCnaId().getCnaFechaInicio();
                data[i][3] = planificacion.getLoteCampania().getCampaniaByLcpCnaId().getCnaFechaFinReal();
//                data[i][4] = planificacion.getTipoInsumoByInsTinId();

                i++;
            }
            setModel(columnNames, data, tblCampPlanificadas);
        } finally {
            session.close();
        }

    }

}
