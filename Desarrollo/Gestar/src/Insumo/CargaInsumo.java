package Insumo;

import Conexion.Coneccion;
import Datos.InsumoEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.sql.Date;
import java.util.Vector;


public class CargaInsumo extends JFrame {
    private JPanel panel1;
    private JTextArea txtDescripcion;
    private JTextField txtNombre;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JComboBox cbxTipoInsumo;
    private JPanel alta;
    private JComboBox textUnidadMedida;
    private JTextField txtStock;

    private String tipoOperacion;
    private int insId;

    private java.util.Date fecha = new java.util.Date();
    private Date fechaActual = new Date(fecha.getTime());

    public CargaInsumo(String operacion, String nombre, String descripcion, String unidadMedida, TipoInsumoEntity tipoInsumo, String stock, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        tipoOperacion = operacion;
        if (tipoOperacion.equals("Carga")) {
            this.setTitle("Cargar Insumo");
        } else {
            this.setTitle("Modificar Insumo");
        }
        cargaComboBoxTipo();


        //GUARDAR
        guardarButton.addActionListener(e -> {
            if (save()) {
                JOptionPane.showMessageDialog(null, "La operacion fue realizada con exito.");
                dispose();
            }
        });


        //CANCELAR
        cancelarButton.addActionListener(e -> dispose());


        if (nombre.length() > 1 && descripcion.length() > 1 && id != 0) {
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            textUnidadMedida.setSelectedItem(unidadMedida); //setText(unidadMedida);
            cbxTipoInsumo.setSelectedItem(tipoInsumo);
            txtStock.setText(stock);
            insId = id;
        }

    }


    //METODO GUARDAR
    private Boolean save() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        InsumoEntity insumo = new InsumoEntity();
        if (validaCarga().equals("S")) {
            try {

                insumo.setInsNombre(txtNombre.getText());
                insumo.setInsDescripcion(txtDescripcion.getText());
                insumo.setInsFechaAlta(fechaActual);
                insumo.setInsUsuarioAlta("admin");
                insumo.setInsUnidadMedida(textUnidadMedida.getSelectedItem().toString());
                String tipoInsumo = cbxTipoInsumo.getSelectedItem().toString();
//                TipoInsumoEntity tipoInsumoEntity = (TipoInsumoEntity) session.get(TipoInsumoEntity.class, 7);
                TipoInsumoEntity tipoInsumoEntity = (TipoInsumoEntity) session.createQuery("select x from TipoInsumoEntity x where x.tinNombre = :pNombre").setParameter("pNombre", tipoInsumo).uniqueResult();
                insumo.setTipoInsumoByInsTinId(tipoInsumoEntity);

                Transaction tx = session.beginTransaction();
                if (tipoOperacion.equals("Carga")) {
                    session.save(insumo);
                } else {
                    insumo.setInsId(insId);
                    session.update(insumo);
                }

                tx.commit();
                guardado = tx.wasCommitted();
//                session.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurri� un error al cargar el insumo: " + e.toString());
            } finally {
                session.close();
                //guardaStock(insumo);
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


    //METODO GUARDAR STOCK
    /*private void guardaStock(InsumoEntity insumo) {
        //Carga Stock
        Session session = Coneccion.getSession();
        try {
            int stockInsId = 0;
            StockInsumoEntity stockInsumo = (StockInsumoEntity) session.createQuery("select x from StockInsumoEntity x where x.insumoBySinInsId = :pNombre").setParameter("pNombre", insumo).uniqueResult();
            if (stockInsumo != null) {
                stockInsId = stockInsumo.getSinId();
            } else {
                stockInsumo = new StockInsumoEntity();
            }
            if (insumo.getInsId() == 0) {
                insumo = (InsumoEntity) session.createQuery("select x from InsumoEntity x where x.insNombre = :pNombre").setParameter("pNombre", insumo.getInsNombre()).uniqueResult();
            }
            stockInsumo.setInsumoBySinInsId(insumo);
            stockInsumo.setSinFechaAlta(fechaActual);
            stockInsumo.setSinFechaUltMod(fechaActual);
            stockInsumo.setSinUsuarioAlta("admin");
            stockInsumo.setSinUsuarioUtlMod("admin");
            stockInsumo.setSinTotal(Integer.parseInt(txtStock.getText()));

            Transaction tx = session.beginTransaction();
            if (stockInsId == 0) {
                session.save(stockInsumo);
            } else {
                stockInsumo.setSinId(stockInsId);
                session.update(stockInsumo);
            }
            tx.commit();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this, "Ocurri� un error al cargar el insumo.");
        } finally {
            session.close();

        }
    }*/


    //METODO CARGA COMBO
    private void cargaComboBoxTipo() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM TipoInsumoEntity p");
        java.util.List<TipoInsumoEntity> listaTipoInsumo = query.list();

        Vector<String> mivector = new Vector<>();
        for (TipoInsumoEntity tipoInsumo : listaTipoInsumo) {
            //System.out.println(tipoInsumo.getTinNombre());
            mivector.add(tipoInsumo.getTinNombre());
            cbxTipoInsumo.addItem(tipoInsumo);
        }

    }

}
