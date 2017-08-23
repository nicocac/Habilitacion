package Cliente;

import Conexion.Conexion;
import Datos.ClienteEntity;
import Datos.TipoClienteEntity;
import Repository.ClienteRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.Iterator;

/**
 * Created by jagm on 10/10/2016.
 */
public class PantallaAdministrarCliente extends JFrame {
    private JPanel panel1;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnEditar;
    private JButton btnNuevo;
    private JTable tblClientes;
    private JButton btnCancelar;
    private JTable table1;
    private ClienteEntity cliente;
    private Transaction tx;
    private DefaultTableModel model;

    ClienteRepository clienteRepository = new ClienteRepository();

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public PantallaAdministrarCliente() {


        //INICIO
        JPanel container = new JPanel();
//        container.setPreferredSize(new Dimension(1920, 1900));
//        panel1.setPreferredSize(new Dimension(1900, 1800));
        container.add(panel1);
        JScrollPane jsp = new JScrollPane(container);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        jsp.setBounds(50, 30, 900, 900);
        this.add(jsp);
//        setContentPane(panel1);
//        this.setExtendedState(MAXIMIZED_BOTH);
        pack();
        this.setTitle("Consultar Clientes");
        inicializaTabla();


        //BUSCAR
        btnBuscar.addActionListener(e -> {
//            Session session = Conexion.getSessionFactory().openSession()
            buscarClientes();
        });


        //LIMPIAR
        btnLimpiar.addActionListener(e -> {
            inicializaTabla();
            txtBuscar.setText("");
        });


        //EDITAR
        btnEditar.addActionListener(e -> {
            int fila = tblClientes.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return;
            }
            int insId = (int) tblClientes.getModel().getValueAt(fila, 0);
            String nombre = (String) tblClientes.getModel().getValueAt(fila, 1);
            String apellido = (String) tblClientes.getModel().getValueAt(fila, 2);
            TipoClienteEntity tipoCliente = (TipoClienteEntity) tblClientes.getModel().getValueAt(fila, 3);

            String cuitCuil = (String) tblClientes.getModel().getValueAt(fila, 4);

            CargaCliente carga = new CargaCliente("Modificacion", nombre, apellido, tipoCliente, cuitCuil, insId);
            carga.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //BAJA
        btnEliminar.addActionListener(e -> {
            switch (darBaja()) {
                case 0: {
                    showMessage("Se dio de baja exitosamente el cliente.");
                    inicializaTabla();
                    break;
                }
                case 1: {
                    showMessage("Operacion Cancelada.");
                    break;
                }
                case 2: {
                    showMessage("No se pudo dar de baja el Cliente.");
                    break;
                }
            }

        });


        //NUEVO
        btnNuevo.addActionListener(e -> {
            CargaCliente cargaCliente = new CargaCliente("Carga", "", "", null,"", 0);
            cargaCliente.setVisible(true);
            getDefaultCloseOperation();
            inicializaTabla();
        });


        //CANCELAR
        btnCancelar.addActionListener(e -> dispose());

    }

    //METODOS
    private void inicializaTabla() {
        String[] columnNames = {"Cod", "Nombre", "Apellido", "Tipo Cliente", "Cuit/Cuil"};
        Object[][] data = new Object[1][4];
        setModel(columnNames, data, tblClientes);
    }

    private void setModel(String[] columnames, Object[][] data, JTable tabla) {
        model = new DefaultTableModel();
        model.setDataVector(data, columnames);
        tblClientes.setModel(model);
        tblClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblClientes.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblClientes.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblClientes.getColumnModel().getColumn(4).setPreferredWidth(150);
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
            cliente = new ClienteEntity();
            int fila = tblClientes.getSelectedRow();
            if (fila == -1) {
                showMessage("Debe seleccionar una fila para continuar.");
                return -1;
            }

            cliente = clienteRepository.getClienteById((Long) tblClientes.getModel().getValueAt(fila, 0));

//            cliente.setClienteId((int) tblClientes.getModel().getValueAt(fila, 0));
//            cliente.setClienteNombre((String) tblClientes.getModel().getValueAt(fila, 1));
//            cliente.setClienteApellido((String) tblClientes.getModel().getValueAt(fila, 2));
//            cliente.setClienteCuitCuil((String) tblClientes.getModel().getValueAt(fila, 3));
//            cliente.setClienteFechaAlta(fechaActual);
//            cliente.setClienteUsuarioAlta("adminBajaCliente");
            cliente.setClienteFechaUltMod(fechaActual);
            cliente.setClienteUsuarioUltMod("adminBajaCliente");
            cliente.setClienteFechaBaja(fechaActual);
            cliente.setClienteUsuarioBaja("adminBajaCliente");
            int i = JOptionPane.showConfirmDialog(null, "Confirma la baja del cliente: " + tblClientes.getModel().getValueAt(fila, 1));
            if (i == 0) {
                tx = session.beginTransaction();
                session.update(cliente);
                tx.commit();
                guardado = tx.wasCommitted();
            } else {
                return 1;
            }
        } catch (Exception e) {
            showMessage("Ocurrio un error al dar de baja el cliente: " + e.toString());
            return 2;
        } finally {
            //session.close();
        }

        return 0;
    }


    //METODO BUSCAR CLIENTES
    public void buscarClientes() {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        int i = 0;
        try {
            cliente = new ClienteEntity();
            Query query = session.createQuery("select t from ClienteEntity t where ucase(clienteNombre) like ucase(:pNombre) and clienteFechaBaja is null");
            query.setParameter("pNombre", "%" + txtBuscar.getText() + "%");
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            String[] columnNames = {"Cod", "Nombre", "Apellido", "Tipo Cliente", "Cuit/Cuil"};
            Object[][] data = new Object[list.size()][5];

            while (iter.hasNext()) {
                cliente = (ClienteEntity) iter.next();
                data[i][0] = cliente.getClienteId();
                data[i][1] = cliente.getClienteNombre();
                data[i][2] = cliente.getClienteApellido();
                if(cliente.getTipoClienteEntity() == null){
                    data[i][3] = null;
                } else {
                    data[i][3] = cliente.getTipoClienteEntity().getTinNombre();
                }
                data[i][4] = cliente.getClienteCuitCuil();
                i++;
            }
            setModel(columnNames, data, tblClientes);
        } finally {
            //session.close();
        }

    }

}
