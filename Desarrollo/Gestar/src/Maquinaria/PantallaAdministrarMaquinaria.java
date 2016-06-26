package Maquinaria;

import Conexion.Coneccion;
import Datos.MaquinariaEntity;
import Datos.TipoEstadoMaquinariaEntity;
import Datos.TipoMaquinariaEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Iterator;


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

    private JTable table1;
    private MaquinariaEntity maquinaria;
    private Transaction tx;
    private DefaultTableModel model;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public PantallaAdministrarMaquinaria() {


        //INICIO
        setContentPane(panel1);
        pack();
        this.setTitle("Consultar Maquinaria");
        inicializaTabla();


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Coneccion.getSession();
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
        tblMaquinaria.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblMaquinaria.getColumnModel().getColumn(2).setPreferredWidth(100);
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
        Session session = Coneccion.getSession();
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
        Session session = Coneccion.getSession();
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
