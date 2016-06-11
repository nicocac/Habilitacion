package Insumo;

import Conexion.Coneccion;
import Datos.InsumoEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Vector;


public class CargaInsumo extends JFrame {
    private JPanel panel1;
    private JTextField txtDescripcion;
    private JTextField txtNombre;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JComboBox cbxTipoInsumo;
    private JTextField textUnidadMedida;

    private String tipoOperacion;
    private int insId;

    java.util.Date fecha = new java.util.Date();
    Date fechaActual = new Date(fecha.getTime());

    public CargaInsumo(String operacion, String nombre, String descripcion, String unidadMedida, TipoInsumoEntity tipoInsumo, int id) {

        //INICIO
        setContentPane(panel1);
        pack();
        tipoOperacion = operacion;
        if (tipoOperacion == "Carga") {
            this.setTitle("Cargar Insumo");
        }else{
            this.setTitle("Modificar Insumo");
        }
        cargaComboBoxTipo();



        //GUARDAR
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(save()){
                    JOptionPane.showMessageDialog(null, "Se guardo correctamente la alta del nuevo insumo");
                    dispose();
                }
            }
        });



        //CANCELAR
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        if (nombre != "" && descripcion!= ""&& id!= 0){
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            textUnidadMedida.setText(unidadMedida);
            cbxTipoInsumo.setSelectedItem(tipoInsumo);
            insId = id;
        }

    }



    //METODO GUARDAR
    public Boolean save() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        if (validaCarga()=="S") {
            try {
                InsumoEntity insumo = new InsumoEntity();
                insumo.setInsNombre(txtNombre.getText());
                insumo.setInsDescripcion(txtDescripcion.getText());
                insumo.setInsFechaAlta(fechaActual);
                insumo.setInsUsuarioAlta("admin");
                insumo.setInsUnidadMedida(textUnidadMedida.getText());
                String tipoInsumo = cbxTipoInsumo.getSelectedItem().toString();
//                TipoInsumoEntity tipoInsumoEntity = (TipoInsumoEntity) session.get(TipoInsumoEntity.class, 7);
                TipoInsumoEntity tipoInsumoEntity = (TipoInsumoEntity) session.createQuery("select x from TipoInsumoEntity x where x.tinNombre = :pNombre").setParameter("pNombre", tipoInsumo).uniqueResult();
                insumo.setTipoInsumoByInsTinId(tipoInsumoEntity);
                Transaction tx = session.beginTransaction();
                if (tipoOperacion == "Carga") {
                    session.save(insumo);
                }else{
                    insumo.setInsId(insId);
                    session.update(insumo);
                }

                tx.commit();
                guardado = tx.wasCommitted();
//            session.close();
            }catch (Exception e){JOptionPane.showMessageDialog(this, "Ocurri� un error al cargar el insumo.");}
            finally {
                session.close();
            }
        }else{
            JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos para continuar.");
        }

        return guardado;
    }



    //METODO VALIDAR CARGA
    public String validaCarga(){
        if (txtNombre.getText().replaceAll(" ", "").length() == 0) return "N";

        if (txtDescripcion.getText().replaceAll(" ", "").length() ==0) return "N";

        return "S";
    }



    //METODO CARGA COMBO
    private void cargaComboBoxTipo() {
        Session session = Coneccion.getSession();
        Query query = session.createQuery("SELECT p FROM TipoInsumoEntity p");
        java.util.List<TipoInsumoEntity> listaTipoInsumo = query.list();

        Vector<String> mivector = new Vector<>();
        for (TipoInsumoEntity tipoInsumo : listaTipoInsumo) {
            System.out.println(tipoInsumo.getTinNombre());
            mivector.add(tipoInsumo.getTinNombre());
            cbxTipoInsumo.addItem(tipoInsumo);
        }

    }

}
