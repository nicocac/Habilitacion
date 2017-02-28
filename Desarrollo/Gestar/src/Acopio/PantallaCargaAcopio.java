package Acopio;

import Acopio.Acopio.CargaAcopio;
import Acopio.TipoAcopio.CargaTipoAcopio;
import Campania.CargaCampania;
import Cliente.CargaCliente;
import Conexion.Coneccion;
import Datos.*;
import Granos.CargaTipoGrano;
import Granos.TipoGrano;
import Lote.CargaLote;
import Maquinaria.PantallaTipoEstado.CargaEstadoMaquinaria;
import Maquinaria.PantallaTipoMaquinaria.CargaTipoMaquinaria;
import Repository.*;
import TipoMedioAlmacenamiento.CargaMedio;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.util.Properties;
import java.util.Vector;

import Date.DateLabelFormatter;

/**
 * Created by jagm on 10/10/2016.
 */
public class PantallaCargaAcopio extends JFrame {
    public JPanel panel1;
    public JPanel alta;
    public JComboBox cbxAlmacenamiento;
    public JButton nuevoAlmacButton;
    public JTextField txtFecha;
    public JTextField txtNombre;
    public JTextArea txtDescripcion;
    public JTextField cantidadPeso;
    public JComboBox cbxEstado;
    public JButton nvoBtnEstadoGrano;
    public JButton cancelarButton;
    public JButton guardarButton;
    public JButton nvoBtnCliente;
    public JButton nvoBtnLote;
    public JButton nvoBtnCamp;
    public JButton nvoBtnTipoGrano;
    public JComboBox cbxCamp;
    public JComboBox cbxGrano;
    public JComboBox cbxLote;
    public JComboBox cbxCliente;
    public JComboBox cbxTipoAcopio;
    public JButton BtnFechaIni;
    public JButton nvoBtnTipoAcopio;
    public JButton nvoBtnAcopio;
    public JComboBox cbxAcopio;

    private String tipoOperacion;
    private int maqId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());


    CampaniaRepository campaniaRepository = new CampaniaRepository();
    TipoGranoRepository tipoGranoRepository = new TipoGranoRepository();
    TipoAcopioRepository tipoAcopioRepository = new TipoAcopioRepository();
    TipoMedioRepository tipoMedioRepository = new TipoMedioRepository();
    LoteRepository loteRepository = new LoteRepository();
    ClienteRepository clienteRepository = new ClienteRepository();
    AcopioRepository acopioRepository = new AcopioRepository();


    public PantallaCargaAcopio(String operacion, String nombre, String descripcion, TipoEstadoMaquinariaEntity tipoEstadoMaquinaria,
                               String marca, String modelo, TipoMaquinariaEntity tipoMaquinaria, String anioFabricacion, int id) {

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
        pack();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Registrar Ingreso de Granos");
        } else {
            this.setTitle("Modificar Ingreso de Granos");
        }

        //----------------------------------------------------------------------
        //FECHA
        SqlDateModel modelIni = new SqlDateModel();
        modelIni.setDate(2016, 04, 20);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanelIni = new JDatePanelImpl(modelIni, p);
        //the formatter,  there it is...
        JDatePickerImpl datePickerIni = new JDatePickerImpl(datePanelIni, new DateLabelFormatter());

        BtnFechaIni.add(datePickerIni);
        //----------------------------------------------------------------------


        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save(datePickerIni)) {
                JOptionPane.showMessageDialog(null, "Se guarda la alta de maquinaria con exito.");
                dispose();
            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> dispose());


        if (nombre.length() > 1 && descripcion.length() > 1 && id != 0) {
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            cbxAlmacenamiento.setSelectedItem(tipoEstadoMaquinaria);
            txtFecha.setText(marca);
            cantidadPeso.setText(modelo);
            cbxEstado.setSelectedItem(tipoMaquinaria);
//            txtAnio.setText(anioFabricacion);
            maqId = id;
        }


        //COMBO BOXS

        //Acopio
        nvoBtnAcopio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargaAcopio cargaAcopio = new CargaAcopio("Carga", "", 0, 0);
                cargaAcopio.setVisible(true);
                getDefaultCloseOperation();
            }
        });
        nvoBtnAcopio.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxAcopio();
                cargaComboBoxAcopio();
            }
        });


        //Tipo Estado Granos
        nvoBtnEstadoGrano.addActionListener(e -> {
//            CargaTipoMaquinaria cargaTipoMaquinaria = new CargaTipoMaquinaria("Carga", "", "", 0);
//            cargaTipoMaquinaria.setVisible(true);
            getDefaultCloseOperation();
        });


        //Tipo Acopio
        nvoBtnTipoAcopio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargaTipoAcopio cargaMedio = new CargaTipoAcopio("Carga", "", "", 0);
                cargaMedio.setVisible(true);
                getDefaultCloseOperation();
            }
        });
        cbxTipoAcopio.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxTipoAcopio();
                cargaComboBoxTipoAcopio();
            }
        });


        //Tipo Grano
        nvoBtnTipoGrano.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargaTipoGrano cargaTipoGrano = new CargaTipoGrano("Carga", "", "", 0);
                cargaTipoGrano.setVisible(true);
                getDefaultCloseOperation();

            }
        });
        cbxGrano.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxTipoGrano();
                cargaComboBoxTipoGrano();
            }
        });


        //Campania
        nvoBtnCamp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargaCampania cargaCampania = new CargaCampania("Carga", 0, "", null, null, null);
                cargaCampania.setVisible(true);
                getDefaultCloseOperation();

            }
        });
        cbxCamp.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxCamp();
                cargaComboBoxCamp();
            }
        });


        //Lote
        nvoBtnLote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargaLote cargaLote = new CargaLote("Carga", "", 0, 0);
                cargaLote.setVisible(true);
                getDefaultCloseOperation();

            }
        });
        cbxLote.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxLote();
                cargaComboBoxLote();
            }
        });


        //Cliente
        nvoBtnCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CargaCliente cargaCliente = new CargaCliente("Carga", "", "", null, "", 0);
                cargaCliente.setVisible(true);
                getDefaultCloseOperation();

            }
        });
        cbxCliente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                borrarComboBoxCliente();
                cargaComboBoxCliente();
            }
        });
    }


    //METODO GUARDAR
    private Boolean save(JDatePickerImpl datePickerIni) {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        IngresoAcopioEntity ingreso = new IngresoAcopioEntity();
        if (validaCarga().equals("S")) {
            try {

                ingreso.setDescripcion(txtDescripcion.getText());

                java.util.Date selectedDateIni = (java.util.Date) datePickerIni.getModel().getValue();
                ingreso.setIngresoFecha((java.sql.Date) selectedDateIni);

                ingreso.setIngresoFechaAlta(fechaActual);
                ingreso.setEstadoSemilla(cbxEstado.getSelectedItem().toString());
                ingreso.setIngresoCantidadTotal(Integer.parseInt(cantidadPeso.getText()));

                String tipoAcopio = cbxTipoAcopio.getSelectedItem().toString();
                TipoAcopioEntity tipoAcopioEntity = tipoAcopioRepository.getTipoAcopioByNombre(tipoAcopio);
                ingreso.setTipoAcopio(tipoAcopioEntity);

                ingreso.setEstadoSemilla(cbxEstado.getSelectedItem().toString());

                String tipoGrano = cbxGrano.getSelectedItem().toString();
                TipoGranoEntity tipoGranoEntity = tipoGranoRepository.getTipoGranoByNombre(tipoGrano);
                ingreso.setTipoGrano(tipoGranoEntity);

                String camp = cbxCamp.getSelectedItem().toString();
                CampaniaEntity campaniaEntity = campaniaRepository.getCampaniaByNombre(camp);
                ingreso.setCampania(campaniaEntity);

                String lote = cbxLote.getSelectedItem().toString();
                LoteEntity loteEntity = loteRepository.getLoteByDenominacion(lote);
                ingreso.setLote(loteEntity);

                String cliente = cbxCliente.getSelectedItem().toString();
                ClienteEntity clienteEntity = clienteRepository.getClienteByNombre(cliente);
                ingreso.setCliente(clienteEntity);

//                AcopioEntity acopioEntity = (AcopioEntity) cbxAcopio.getModel().getSelectedItem();
                Integer acopio = Integer.parseInt(cbxAcopio.getSelectedItem().toString());
                AcopioEntity acopioEntity = acopioRepository.getAcopioByCodigo(acopio);
                ingreso.setAcopio(acopioEntity);




                Transaction tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(ingreso);

                    Integer cantidadTotal = Integer.parseInt(cantidadPeso.getText()) + acopioEntity.getCantidadGrano();
                    acopioEntity.setCantidadGrano(cantidadTotal);
                    acopioEntity.setTipoGrano(tipoGranoEntity);
                    acopioEntity.setEstadoGrano(cbxEstado.getSelectedItem().toString());

                    session.save(acopioEntity);

                } else {
                    ingreso.setIngresoId(maqId);
                    session.update(ingreso);
                }

                tx.commit();
                guardado = tx.wasCommitted();
//                session.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error al cargar el ingreso: " + e.toString());
            } finally {
                session.close();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos para continuar.");
        }

        return guardado;
    }


    //METODO VALIDAR CARGA
    private String validaCarga() {
        if (txtNombre.getText().replaceAll(" ", "").length() == 0) return "N";

        if (txtDescripcion.getText().replaceAll(" ", "").length() == 0) return "N";

        return "S";
    }


    //---CARGA DE COMBO BOXS

    //METODO CARGA COMBO  Acopio
    private void cargaComboBoxAcopio() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM AcopioEntity p");
        java.util.List<AcopioEntity> listaTipoMaquinaria = query.list();

        Vector<String> miVectorTipoMaquinaria = new Vector<>();
        for (AcopioEntity tipoMaquinaria : listaTipoMaquinaria) {
            //System.out.println(tipoMaquinaria.getTeMaNombre());
            miVectorTipoMaquinaria.add(tipoMaquinaria.getNombre());
            cbxAcopio.addItem(tipoMaquinaria);
        }

    }

    private void borrarComboBoxAcopio() {
        cbxAcopio.removeAllItems();
    }


    //METODO CARGA COMBO TIPO Acopio
    private void cargaComboBoxTipoAcopio() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM TipoAcopioEntity p");
        java.util.List<TipoAcopioEntity> listaTipoMaquinaria = query.list();

        Vector<String> miVectorTipoMaquinaria = new Vector<>();
        for (TipoAcopioEntity tipoMaquinaria : listaTipoMaquinaria) {
            //System.out.println(tipoMaquinaria.getTeMaNombre());
            miVectorTipoMaquinaria.add(tipoMaquinaria.getTipoAcopioNombre());
            cbxTipoAcopio.addItem(tipoMaquinaria);
        }

    }

    private void borrarComboBoxTipoAcopio() {
        cbxTipoAcopio.removeAllItems();
    }


    //METODO CARGA COMBO TIPO ESTADO
//    private void cargaComboBoxTipoEstado() {
//        Session session = Coneccion.getSession();
//        Query query = session.createQuery("SELECT p FROM TipoEstadoMaquinariaEntity p");
//        java.util.List<TipoEstadoMaquinariaEntity> listaTipoEstadoMaquinaria = query.list();
//
//        Vector<String> miVectorEstado = new Vector<>();
//        for (TipoEstadoMaquinariaEntity tipoEstado : listaTipoEstadoMaquinaria) {
//            //System.out.println(tipoEstado.getTeMaNombre());
//            miVectorEstado.add(tipoEstado.getTeMaNombre());
//            cbxAlmacenamiento.addItem(tipoEstado);
//        }
//
//    }
//    private void borrarComboBoxTipoEstado() {
//        cbxTipoAcopio.removeAllItems();
//    }


    //METODO CARGA COMBO TIPO Grano
    private void cargaComboBoxTipoGrano() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM TipoGranoEntity p");
        java.util.List<TipoGranoEntity> listaTipoMaquinaria = query.list();

        Vector<String> miVectorTipoMaquinaria = new Vector<>();
        for (TipoGranoEntity tipoMaquinaria : listaTipoMaquinaria) {
            //System.out.println(tipoMaquinaria.getTeMaNombre());
            miVectorTipoMaquinaria.add(tipoMaquinaria.getTgrNombre());
            cbxGrano.addItem(tipoMaquinaria);
        }

    }

    private void borrarComboBoxTipoGrano() {
        cbxGrano.removeAllItems();
    }


    //METODO CARGA COMBO TIPO Campania
    private void cargaComboBoxCamp() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM CampaniaEntity p");
        java.util.List<CampaniaEntity> listaTipoMaquinaria = query.list();

        Vector<String> miVectorTipoMaquinaria = new Vector<>();
        for (CampaniaEntity tipoMaquinaria : listaTipoMaquinaria) {
            //System.out.println(tipoMaquinaria.getTeMaNombre());
            miVectorTipoMaquinaria.add(tipoMaquinaria.getCnaDenominacion());
            cbxCamp.addItem(tipoMaquinaria);
        }

    }

    private void borrarComboBoxCamp() {
        cbxCamp.removeAllItems();
    }


    //METODO CARGA COMBO Lote
    private void cargaComboBoxLote() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM LoteEntity p");
        java.util.List<LoteEntity> listaTipoMaquinaria = query.list();

        Vector<String> miVectorTipoMaquinaria = new Vector<>();
        for (LoteEntity tipoMaquinaria : listaTipoMaquinaria) {
            //System.out.println(tipoMaquinaria.getTeMaNombre());
            miVectorTipoMaquinaria.add(tipoMaquinaria.getLteDenominacion());
            cbxLote.addItem(tipoMaquinaria);
        }

    }

    private void borrarComboBoxLote() {
        cbxLote.removeAllItems();
    }


    //METODO CARGA COMBO Cliente
    private void cargaComboBoxCliente() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM ClienteEntity p");
        java.util.List<ClienteEntity> listaTipoMaquinaria = query.list();

        Vector<String> miVectorTipoMaquinaria = new Vector<>();
        for (ClienteEntity tipoMaquinaria : listaTipoMaquinaria) {
            //System.out.println(tipoMaquinaria.getTeMaNombre());
            miVectorTipoMaquinaria.add(tipoMaquinaria.getClienteCuitCuil());
            cbxCliente.addItem(tipoMaquinaria);
        }

    }

    private void borrarComboBoxCliente() {
        cbxCliente.removeAllItems();
    }

}

