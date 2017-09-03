package Ordenes;

import Conexion.Conexion;
import Datos.InsumoEntity;
import Datos.OrdenTrabajoEntity;
import Laboreo.GenerarOrdenTrabajo;
import Repository.PlanificacionRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.*;

/**
 * Created by jagm on 23/10/2016.
 */
public class AdministrarOrdenes extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnNuevaOrden;
    private JTable tblCampPlanificadas;
    private JButton btnCancelar;
    public JButton btnRegistrarAvance;
    public JPanel panelIni;
    public JButton btnRegistrarOrden;
    private JTable table1;
    private InsumoEntity insumo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    PlanificacionRepository planificacionRepository = new PlanificacionRepository();

    public AdministrarOrdenes() {


        //INICIO

        setContentPane(panelIni);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Ordenes");
        inicializaTabla();
        buscarOrdenes();



        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession()
            buscarOrdenes();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
////            inicializaTabla();
////            txtBuscar.setText("");
//
//            Document document = new Document();
//            try {
//                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\jagm\\Desktop\\test.pdf"));
//                document.open();
//                PdfContentByte contentByte = writer.getDirectContent();
//                PdfTemplate template = contentByte.createTemplate(2600, 1800);
//                Graphics2D g2 = template.createGraphics(900, 100);
//                panelIni.print(g2);
//                g2.dispose();
//                contentByte.addTemplate(template, 30, 300);
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            } finally {
//                if (document.isOpen()) {
//                    document.close();
//                }
//            }
            dispose();

        });


        //EDITAR
        btnEditar.addActionListener(e -> {
            int fila = tblCampPlanificadas.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            Integer ordenId = (Integer) tblCampPlanificadas.getModel().getValueAt(fila, 0);

            GenerarOrdenTrabajo carga = new GenerarOrdenTrabajo("Modificacion", ordenId);
            carga.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente la orden.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja la orden.");
                    break;
                }
            }

        });


        //NUEVO
//        btnNuevaOrden.addActionListener(e -> {
//            int fila = tblCampPlanificadas.getSelectedRow();
//            if (fila == -1) {
//                showMessage("Debe seleccionar una fila para continuar.");
//                return;
//            }
//            Integer planificacion = ((int) tblCampPlanificadas.getModel().getValueAt(fila, 0));
////            Integer campPlanificada = planificacion.getCampania().getCnaId();
//
//            java.util.List<OrdenTrabajoLaboreo> listaLaboreoLote = planificacionRepository.getLaboreosByCampIdPlanificadaId(planificacion);
//            if (listaLaboreoLote.size() == 0) {
//                showMessage("La Campana planificada no posee ordenes pendientes de generar, registre el avance de las mismas " +
//                        "o seleccione otra campana planificada.");
//                return;
//
//            }
//            GenerarOrdenTrabajo generarOrdenTrabajo = new GenerarOrdenTrabajo("Carga", planificacion);
//            generarOrdenTrabajo.setVisible(true);
//            getDefaultCloseOperation();
//            inicializaTabla();
//        });


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
        String[] columnNames = {"Codigo Orden", "Nro", "Fecha", "Planificacion", "Laboreo"};
        Object[][] data = new Object[1][5];
        setModel(columnNames, data, tblCampPlanificadas);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblCampPlanificadas.setModel(model);
        tblCampPlanificadas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblCampPlanificadas.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblCampPlanificadas.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblCampPlanificadas.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblCampPlanificadas.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblCampPlanificadas.getColumnModel().getColumn(4).setPreferredWidth(200);
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
            int fila = tblCampPlanificadas.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }

//            OrdenTrabajoEntity planificacion = ordenRepository.getPlanificadaById((int) tblCampPlanificadas.getModel().getValueAt(fila, 0));

//            planificacion.setFechaAlta(fechaActual);
//            planificacion.setUsuarioAlta("adminBajaLaboreo");
//            planificacion.setFechaUltMod(fechaActual);
//            planificacion.setUsuarioUltMod("adminBajaLaboreo");
//            planificacion.setFechaBaja(fechaActual);
//            planificacion.setUsuarioBaja("adminBajaLaboreo");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja de la orden: " + tblCampPlanificadas.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
//                session.update(planificacion);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja la orden: " + e.toString());
            return 2;
        } finally {
            //session.close();
        }

        return 0;
    }


    //METODO BUSCAR Campanias planificadas
    public void buscarOrdenes() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        int i = 0;
        try {
            OrdenTrabajoEntity planificacion;

            Query query = session.createQuery("select t from OrdenTrabajoEntity t " +
                    "where ucase(nroOrden) like ucase(:pNombre) and t.fechaBaja is null ");

            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Codigo Orden", "Nro", "Fecha", "Planificacion", "Laboreo"};
            Object[][] data = new Object[list.size()][5];

            while (iter.hasNext()) {
                planificacion = (OrdenTrabajoEntity) iter.next();
                    data[i][0] = planificacion.getId();
                    data[i][1] = planificacion.getNroOrden();
                    data[i][2] = planificacion.getFechaAlta();
                    data[i][3] = planificacion.getPlanificacion().getCampania().getCnaDenominacion();
                    data[i][4] = planificacion.getLaboreo().getLboNombre();

                    i++;
            }
            setModel(columnNames, data, tblCampPlanificadas);
        } finally {
            tx.rollback();
        }

    }

}
