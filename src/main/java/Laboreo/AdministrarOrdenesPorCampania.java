package Laboreo;

import Conexion.Conexion;
import Datos.*;
import Repository.CampaniaRepository;
import Repository.InsumoRepository;
import Repository.LaboreoRepository;
import Repository.PlanificacionRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by jagm on 11/11/2016.
 */
public class AdministrarOrdenesPorCampania extends JFrame {
    private JPanel panel1;
    private JTextField txtRRHH;
    private JTable tblDetalles;
    private JList lstInsumos;
    private JList lstMaquinarias;
    private JComboBox cboCampania;
    private JButton btnAgregarItem;
    private JButton btnAgregarMaquinaria;
    private JButton btnCancelar;
    private JButton btnFinalizar;
    private JButton btnLimpiar;
    private JButton btnEliminar;
    private JList lstLotes;
    private JComboBox cboMomentos;
    public JList lstLaboreos;
    public JButton btnActualizar;
    public JButton btnRegistrarAvance;
    public JTextField txtFechaIni;
    public JTextField txtFechaFin;
    public JTextField txtCamp;
    public JTextField txtLaboreo;
    public JTextField txtLote;
    public JList lstOrdenes;
    public JPanel panelIni;
    public JButton btnFinalizarOrden;
    public JButton nuevaCampaniaBtn;
    public JButton nuevoTipoLaboreoBtn;
    public JButton nuevoInsumoBtn;
    public JButton nuevaMaquinariaBtn;
    public JButton actualizarInsumosBtn;
    public JButton actualizarMaqBtn;
    public JComboBox cbxSemillas;
    public JButton nuevaSemillaBtn;
    public JTextField txtMetrica;
    public JComboBox cbxMedida;
    public JTextField txtNombre;
    private DefaultTableModel modelDetalle = new DefaultTableModel();
    private GestorLaboreo gestor = new GestorLaboreo();
    private String tipoOperacion;
    private DefaultTableModel modelInsumoMaquinaria;

    InsumoRepository insumoRepository = new InsumoRepository();
    LaboreoRepository laboreoRepository = new LaboreoRepository();
    PlanificacionRepository planificacionRepository = new PlanificacionRepository();
    CampaniaRepository campaniaRepository = new CampaniaRepository();

    public AdministrarOrdenesPorCampania(String operacion, Integer planificacionId) {


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
        pack();

        tipoOperacion = operacion;
//        if (tipoOperacion.equals("Carga")) {
//            this.setTitle("Generar Ordenes de Trabajo");
//        } else {
//            this.setTitle("Modificar Ordenes de Trabajo");

        PlanificacionCampaniaEntity planificacion = planificacionRepository.getPlanificadaById(planificacionId);
        CampaniaEntity campaniaEntity = campaniaRepository.getCampaniaByNombre(planificacion.getCampania().getCnaDenominacion());

        txtCamp.setText(campaniaEntity.getCnaDenominacion());
        txtFechaIni.setText(campaniaEntity.getCnaFechaInicio().toString());
        txtFechaFin.setText(campaniaEntity.getCnaFechaFinReal().toString());

        List<OrdenTrabajoEntity> listaOrdenes = planificacionRepository.getOrdenesByPlanificadaId(planificacionId);
        cargarOrdenes(listaOrdenes);

//        lstLaboreos.addListSelectionListener(e -> buscarInsumosMaquinariasPorLaboreo((Integer) lstLaboreos.getSelectedValue(), planificacion.getPlanificacionId()));

        lstOrdenes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                lblLotes.setText(String.valueOf(lstLaboreos.getSelectedValuesList().size()));
                OrdenTrabajoEntity orden = (OrdenTrabajoEntity) lstOrdenes.getSelectedValue();
//                txtRRHH.setText(orden.getRecursoHumano());
//                txtLaboreo.setText(orden.getLaboreo().getLboNombre());
//                txtLote.setText(orden.getLote().getLteDenominacion());


            }
        });

//        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Registrar Orden de trabajo");


        //LIMPIAR
        btnLimpiar.addActionListener(e -> limpiarPantalla());

        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());


        //GUARDAR
        btnFinalizar.addActionListener(e -> {

            dispose();
//            }
        });


//        btnActualizar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<LaboreoEntity> listaLaboreoEntity = planificacionRepository.getLaboreosByCampIdPlanificadaId(planificacionId);
//                borrarLstLaboreos();
//                cargarLaboreos(listaLaboreoEntity);
//            }
//        });


        //FINALIZAR ALL ORDEN
        btnFinalizarOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

                if (lstOrdenes.getSelectedValuesList().size() == 0) {
                    showMessage("Debe seleccionar una orden antes de continuar");
                    return;
                }

                int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro que desea Finalizar la orden ?", "Advertencia", JOptionPane.YES_NO_OPTION);
                if (respuesta == 0) {

                    OrdenTrabajoEntity orden = (OrdenTrabajoEntity) lstOrdenes.getSelectedValue();

                    Session session = Conexion.getSessionFactory().getCurrentSession();
                    Transaction tx = session.beginTransaction();

                    String obs = orden.getObservaciones();
                    orden.setObservaciones(obs + " - Finalizacion de orden");
                    orden.setFechaUltMod(fecha);
                    orden.setUsuarioUltMod("Admin que Finaliza la orden");
                    orden.setEstaRegistrada(true);

                    session.update(orden);

                    try {
                        tx.commit();

                        List<OrdenTrabajoLaboreo> listaOrdenesLote =
                                planificacionRepository.getLaboreosByCampIdPlanificadaIdAndLoteId(planificacion.getPlanificacionId(), orden.getLote().getLteId());
                        if (listaOrdenesLote.size() == 0) {
                            Session sessionLote = Conexion.getSessionFactory().getCurrentSession();
                            Transaction txLote = sessionLote.beginTransaction();

                            LoteEntity loteEntity = orden.getLote();
                            loteEntity.setEstado("LIBRE");
                            sessionLote.update(loteEntity);
                            txLote.commit();
//                            sessionLote.close();
                        }


//                        List<OrdenTrabajoLaboreo> listaOrdenesPlanificacion =
//                                planificacionRepository.getLaboreosByCampIdPlanificadaId(planificacion.getPlanificacionId());

                        List<OrdenTrabajoEntity> listaOrdenes = planificacionRepository.getOrdenesByPlanificadaId(planificacionId);

                        if (listaOrdenes.size() == 0) {
                            Session sessionCamp = Conexion.getSessionFactory().getCurrentSession();
                            Transaction txCamp = sessionCamp.beginTransaction();

                            CampaniaEntity campaniaEntity = orden.getPlanificacion().getCampania();
                            campaniaEntity.setEstado("FINALIZADA");
                            sessionCamp.update(campaniaEntity);
                            txCamp.commit();
//                            sessionCamp.close();
                        }

                        JOptionPane.showMessageDialog(null, "La orden fue finalizada en forma definitiva con exito.");
//                        //session.close();
                        dispose();


                    } catch (Exception ex) {
//                        txCamp.rollback();
//                        txLote.rollback();
                        JOptionPane.showMessageDialog(null, "Ocurrio un error al finalizar la orden : " + ex.toString());
                    } finally {
                        //session.close();
                    }
                } else {
                    return;
                }
            }
        });


        //REGISTRAR EL AVANCE
        btnRegistrarAvance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lstOrdenes.getSelectedValuesList().size() == 0) {
                    showMessage("Debe seleccionar una orden antes de continuar");
                    return;
                }


                OrdenTrabajoEntity orden = (OrdenTrabajoEntity) lstOrdenes.getSelectedValue();

                if (orden.getPorcentaje().equals("100")) {
                    showMessage("La orden esta completa al 100% a la fecha. Debe Finalizar la orden");
                    return;
                }

                RegistrarAvanceCampania registrarAvanceCampania = new RegistrarAvanceCampania("Carga", orden);
                setBounds(200, 300, 900, 900);
                registrarAvanceCampania.setVisible(true);
                getDefaultCloseOperation();
                dispose();
            }
        });
    }

    private boolean isCellSelected(JTable tabla) {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            return false;
        }
        return true;
    }

    private void inicializaTabla() {
        String[] columnNamesCompra = {"Tipo", "Nombre", "Unidad de Medida", "Cantidad Necesaria"};
        Object[][] data = new Object[1][5];
        setModelDetalles(columnNamesCompra, data);

    }

    private boolean permiteSeleccion(String tipo, String descripcion) {
        for (int i = 0; i <= tblDetalles.getRowCount() - 1; i++) {
            String str;

            try {
                str = new String((String) tblDetalles.getValueAt(i, 1));
                if (tipo.equals("Maquinaria") && tipo.equals(tblDetalles.getValueAt(i, 0)))
                    str = str.substring(0, descripcion.length());
            } catch (NullPointerException np) {
                str = "aaaaaaaaaaaaa";
            }

            if (tipo.equals(tblDetalles.getValueAt(i, 0)) && descripcion.equals(str)) {
                return false;
            }
        }
        return true;
    }

    private void setModelDetalles(String[] columnames, Object[][] data) {
        TableColumn col;
        modelDetalle.setDataVector(data, columnames);
        tblDetalles.setModel(modelDetalle);
        //tblDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblDetalles.getColumnModel().getColumn(0).setPreferredWidth(180);
        tblDetalles.getColumnModel().getColumn(1).setPreferredWidth(350);
        tblDetalles.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(3).setPreferredWidth(60);
        col = tblDetalles.getColumnModel().getColumn(3);
        col.setCellEditor(new MyTableCellEditor());
        tblDetalles.setCellSelectionEnabled(true);
        //  tblDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    private void cargarItems() {
        DefaultListModel modelo = new DefaultListModel();
        java.util.List listaItems;
        listaItems = gestor.getInsumos();

        Iterator iter = listaItems.iterator();
        while (iter.hasNext()) {
            modelo.addElement(iter.next());
        }
        lstInsumos.setModel(modelo);
    }

    private void cargarMaquinas() {
        DefaultListModel modelo = new DefaultListModel();
        java.util.List listaItems;
        listaItems = gestor.getMaquinaria();

        Iterator iter = listaItems.iterator();
        while (iter.hasNext()) {
            modelo.addElement(iter.next());
        }
        lstMaquinarias.setModel(modelo);
    }

//    private boolean existeLote() {
//        if (lstLotes.getSelectedValuesList().size() == 0) {
//            return false;
//        }
//        return true;
//    }

    private void limpiarPantalla() {
        inicializaTabla();
    }

//    private void cargarCampanias() {
//        CampaniaEntity camp;
//        Campania campania = new Campania();
//        java.util.List listaItems;
//        listaItems = gestor.getCampanias();
//
//        Iterator iter = listaItems.iterator();
//        while (iter.hasNext()) {
//            cboCampania.addItem(iter.next());
//        }
//
//        cboCampania.setSelectedItem(null);
//
//    }

    private void cargarMomentos() {
        java.util.List listaItems;
        listaItems = gestor.getMomentos();

        Iterator iter = listaItems.iterator();
        while (iter.hasNext()) {
            cboMomentos.addItem(iter.next());
        }
//        cboMomentos.setSelectedIndex(0);
    }

    private void showMessage(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }


    private void cargarOrdenes(List<OrdenTrabajoEntity> listaOrdenes) {
        DefaultListModel modelo = new DefaultListModel();
//        List<LaboreoEntity> listaLaboreoSinOrden = new ArrayList<>();
//        for(LaboreoEntity laboreo :listaLaboreoSinOrden){
//            if(laboreo.)
//        }
        Iterator iter = listaOrdenes.iterator();
        while (iter.hasNext()) {
            modelo.addElement(iter.next());
        }
        lstOrdenes.setModel(modelo);
    }


    private void borrarComboBoxCampania() {
//        cboCampania.removeAllItems();
    }

    private void borrarComboBoxTipoLaboreo() {
        cboMomentos.removeAllItems();
    }

    private void borrarLstLaboreos() {
        lstLaboreos.removeAll();
    }


//    //METODO CARGA COMBO CAMPANIA
//    private void cargaComboBoxCampania() {
//        Session session = Conexion.getSessionFactory().openSession()
//        Query query = session.createQuery("SELECT p FROM CampaniaEntity p");
//        java.util.List<CampaniaEntity> listaCampaniaEntity = query.list();
//
////        Vector<String> miVectorcampania = new Vector<>();
//
////        Iterator iter = listaItems.iterator();
////        while (iter.hasNext()) {
////            cboCampania.addItem(iter.next());
////        }
//
////        cboCampania.setSelectedItem(null);
//
//        for (CampaniaEntity campania : listaCampaniaEntity) {
//            //System.out.println(tipoEstado.getTeMaNombre());
////            miVectorcampania.add(campania.getCnaDenominacion());
//            cboCampania.addItem(campania);
//            cboCampania.setSelectedItem(null);
//        }
//
//    }


    //METODO CARGA COMBO TIPO LABOREO
    private void cargaComboBoxTipoLaboreo() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("SELECT p FROM TipoLaboreoEntity p");
        java.util.List<TipoLaboreoEntity> listaTipoLaboreoEntity = query.list();

        Vector<String> miVectorTipoLaboreo = new Vector<>();
        for (TipoLaboreoEntity tipoLaboreo : listaTipoLaboreoEntity) {
            miVectorTipoLaboreo.add(tipoLaboreo.getTpoNombre());
            cboMomentos.addItem(tipoLaboreo);

//            cboCampania.setSelectedItem(null);
        }

    }


    //METODO CARGA COMBO TIPO GRANO
    private void cargaComboBoxTipoGrano() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("SELECT p FROM TipoGranoEntity p");
        java.util.List<TipoGranoEntity> listaTipoGranoEntity = query.list();

        Vector<String> miVectorTipoLaboreo = new Vector<>();
        for (TipoGranoEntity tipoGrano : listaTipoGranoEntity) {
            miVectorTipoLaboreo.add(tipoGrano.getTgrNombre());
            cbxSemillas.addItem(tipoGrano);

//            cboCampania.setSelectedItem(null);
        }

    }


    //METODO BUSCAR INSUMOS DE SOLICUTUDES
    private void buscarInsumosMaquinariasPorLaboreo(LaboreoEntity laboreo, Integer planificacionId) {

        //Carga insumos
        java.util.List<Object[]> listaIns;
        //getInsumos por laboreo
        listaIns = gestor.getInsumosByLaboreoAndPlanificacion(laboreo.getLboId(), planificacionId);

        //Carga maquinarias
        java.util.List<Object[]> listaMaq;
        //getMaquinaria por laboreo
        listaMaq = gestor.getMaquinariasByLaboreoAndPlanificacion(laboreo.getLboId(), planificacionId);


        Object[][] data = new Object[listaIns.size() + listaMaq.size()][4];
        int i = 0;
        if (listaIns.size() != 0) {
            for (Object[] row : listaIns) {
                InsumoEntity insumoEntity = (InsumoEntity) row[0];
                Integer cantidad = (Integer) row[1];

                data[i][0] = "Insumo";
                data[i][1] = insumoEntity.getInsNombre();
                data[i][2] = insumoEntity.getTipoInsumoByInsTinId().getTinNombre();
                data[i][3] = String.valueOf(cantidad);
                i++;
            }
        }

        if (listaMaq.size() != 0) {
            for (Object[] row : listaMaq) {
                MaquinariaEntity maquinariaEntity = (MaquinariaEntity) row[0];
                Integer cantidad = (Integer) row[1];

                data[i][0] = "Maquinaria";
                data[i][1] = maquinariaEntity.getMaqNombre();
                data[i][2] = maquinariaEntity.getTipoMaquinariaByMaqTmaqId().getTmaNombre();
                data[i][3] = String.valueOf(cantidad);
                i++;
            }
        }


        String[] columnNames = {"Clasificacion", "Nombre", "Tipo", "Cantidad",};
        DefaultTableModel model = new DefaultTableModel();
        model.setDataVector(data, columnNames);
        tblDetalles.setModel(model);

//            return;

    }


    private void setModelInsumosMaquinarias(String[] columnames, Object[][] data) {
        modelInsumoMaquinaria = new DefaultTableModel();
        modelInsumoMaquinaria.setDataVector(data, columnames);
        tblDetalles.setModel(modelInsumoMaquinaria);
        tblDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblDetalles.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblDetalles.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblDetalles.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblDetalles.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblDetalles.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblDetalles.getColumnModel().getColumn(5).setPreferredWidth(60);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
