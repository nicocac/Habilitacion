package Laboreo;

import Conexion.Conexion;
import Datos.InsumoEntity;
import Datos.OrdenTrabajoEntity;
import Datos.PlanificacionCampaniaEntity;
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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.*;

/**
 * Created by jagm on 23/10/2016.
 */
public class PantallaAdministrarCampaniaPlanificada extends JFrame {
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
    public JPanel panel1;
    public JButton btnRegistrarOrden;
    private JTable table1;
    private InsumoEntity insumo;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    PlanificacionRepository planificacionRepository = new PlanificacionRepository();

    public PantallaAdministrarCampaniaPlanificada(String tipo) {
        //this.setExtendedState(MAXIMIZED_BOTH);

        JPanel container = new JPanel();
//        container.setPreferredSize(new Dimension(1920, 1900));
//        panel1.setPreferredSize(new Dimension(1900, 1800));
        container.add(panelIni);
        JScrollPane jsp = new JScrollPane(container);
        //jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);
        setContentPane(panel1);
        pack();


        //INICIO
        if (tipo.equals("Generar")) {
            btnRegistrarAvance.setEnabled(false);
        } else {
            if (tipo.equals("Avance")) {
                this.btnNuevaOrden.setEnabled(false);
            }
        }
//        setContentPane(panelIni);
//        this.setExtendedState(MAXIMIZED_BOTH);
//        pack();
        this.setTitle("Consultar Campa\u00f1as planificadas");
        inicializaTabla();
        buscarCampaniasPlanificadas(tipo);


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession()
            buscarCampaniasPlanificadas(tipo);
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
                panelIni.print(g2);
                g2.dispose();
                contentByte.addTemplate(template, 30, 300);
            } catch (Exception e2) {
                e2.printStackTrace();
            } finally {
                if (document.isOpen()) {
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
        btnNuevaOrden.addActionListener(e -> {
            int fila = tblCampPlanificadas.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            Integer planificacion = ((int) tblCampPlanificadas.getModel().getValueAt(fila, 0));
//            Integer campPlanificada = planificacion.getCampania().getCnaId();

            java.util.List<OrdenTrabajoLaboreo> listaLaboreoLote = planificacionRepository.getLaboreosByCampIdPlanificadaId(planificacion);
            if (listaLaboreoLote.size() == 0) {
                showMessage("La Campana planificada no posee ordenes pendientes de generar, registre el avance de las mismas " +
                        "o seleccione otra campana planificada.");
                return;

            }
            GenerarOrdenTrabajo generarOrdenTrabajo = new GenerarOrdenTrabajo("Carga", planificacion);
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


        //REGISTRAR AVANCE
        btnRegistrarAvance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tblCampPlanificadas.getSelectedRow();
                if (fila == -1) {
                    showMessage("Debe seleccionar una fila para continuar.");
                    return;
                }

                Integer planificacion = ((int) tblCampPlanificadas.getModel().getValueAt(fila, 0));
//            Integer campPlanificada = planificacion.getCampania().getCnaId();

//
//                int camId = (int) tblCampania.getModel().getValueAt(fila, 0);
//                String denominacion = (String) tblCampania.getModel().getValueAt(fila, 1);
//                Date fechaInicio = (Date) tblCampania.getModel().getValueAt(fila, 2);
//                Date fechaFinEstimada = (Date) tblCampania.getModel().getValueAt(fila, 3);
//                Date fechaFinReal = (Date) tblCampania.getModel().getValueAt(fila, 4);


                AdministrarOrdenesPorCampania ordenesCampania = new AdministrarOrdenesPorCampania("Carga", planificacion);
                ordenesCampania.setVisible(true);
                getDefaultCloseOperation();

//            RegistrarAvanceCampania avanceCampania = new RegistrarAvanceCampania("Carga",camId, denominacion, fechaInicio, fechaFinEstimada, fechaFinReal);
//            avanceCampania.setVisible(true);
//            getDefaultCloseOperation();

                inicializaTabla();
            }
        });
    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Codigo Planificacion", "Campa\u00f1a", "Fecha Inicio", "Fecha Fin", "Estado"};
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
        tblCampPlanificadas.getColumnModel().getColumn(4).setPreferredWidth(100);
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

            PlanificacionCampaniaEntity planificacion = planificacionRepository.getPlanificadaById((int) tblCampPlanificadas.getModel().getValueAt(fila, 0));

            planificacion.setFechaAlta(fechaActual);
            planificacion.setUsuarioAlta("adminBajaLaboreo");
            planificacion.setFechaUltMod(fechaActual);
            planificacion.setUsuarioUltMod("adminBajaLaboreo");
            planificacion.setFechaBaja(fechaActual);
            planificacion.setUsuarioBaja("adminBajaLaboreo");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja de la campania planificada: " + tblCampPlanificadas.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(planificacion);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja la campania planificada: " + e.toString());
            return 2;
        } finally {
            //session.close();
        }

        return 0;
    }


    //METODO BUSCAR Campanias planificadas
    public void buscarCampaniasPlanificadas(String tipo) {
        Session session = null;
        Transaction tx = null;

        int i = 0;
        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            PlanificacionCampaniaEntity planificacion;

            Query query = session.createQuery("select t from PlanificacionCampaniaEntity t " +
                    "where ucase(t.campania.cnaDenominacion) like ucase(:pNombre) and t.fechaBaja is null " +
                    "and t.campania.estaPlanificada = true");

            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Codigo Planificacion", "Campa\u00f1a", "Fecha Inicio", "Fecha Fin", "Estado"};
            Object[][] data = new Object[list.size()][5];

            while (iter.hasNext()) {
                planificacion = (PlanificacionCampaniaEntity) iter.next();
                java.util.List<OrdenTrabajoLaboreo> listaLaboreoLote = planificacionRepository.getLaboreosByCampIdPlanificadaIdSinTX(planificacion.getPlanificacionId());
                java.util.List<OrdenTrabajoEntity> listaOrdenes = planificacionRepository.getOrdenesByPlanificadaIdSinTX(planificacion.getPlanificacionId());
                if (listaLaboreoLote.size() != 0 && tipo.equals("Generar")) {
                    data[i][0] = planificacion.getPlanificacionId();
                    data[i][1] = planificacion.getCampania().getCnaDenominacion();
                    data[i][2] = planificacion.getCampania().getCnaFechaInicio();
                    data[i][3] = planificacion.getCampania().getCnaFechaFinReal();
                    data[i][4] = planificacion.getCampania().getEstado();

                    i++;
                }
                if (listaOrdenes.size() != 0 && tipo.equals("Avance")) {
                    data[i][0] = planificacion.getPlanificacionId();
                    data[i][1] = planificacion.getCampania().getCnaDenominacion();
                    data[i][2] = planificacion.getCampania().getCnaFechaInicio();
                    data[i][3] = planificacion.getCampania().getCnaFechaFinReal();
                    data[i][4] = planificacion.getCampania().getEstado();

                    i++;
                }

            }
            setModel(columnNames, data, tblCampPlanificadas);
            tx.rollback();
        } catch (Exception e) {
        } finally {
//            //session.close();
        }

    }

}
