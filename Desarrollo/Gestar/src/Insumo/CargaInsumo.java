package Insumo;

import Conexion.Coneccion;
import Datos.InsumoEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;


public class CargaInsumo extends JFrame {
    JPanel panel1;
    private JTextField txtDescripcion;
    private JTextField txtNombre;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JComboBox comboBox1;
    private JTextField textUnidadMedida;

    public CargaInsumo() {
        setContentPane(panel1);
        pack();
        this.setTitle("Cargar Tipo de Insumo");
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(save()){
                    JOptionPane.showMessageDialog(null, "Se guardo correctamente la alta del nuevo insumo");
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public Boolean save() {
        Session session = Coneccion.getSession();
        Boolean guardado = false;
        if (validaCarga()=="S") {
            try {
                InsumoEntity insumo = new InsumoEntity();
                insumo.setInsNombre(txtNombre.getText());
                insumo.setInsDescripcion(txtDescripcion.getText());
                insumo.setInsFechaAlta(new Date(2016, 05, 30));
                insumo.setInsUsuarioAlta("admin");
                insumo.setInsUnidadMedida(textUnidadMedida.getText());
                String unidad = comboBox1.getSelectedItem().toString();
//                TipoInsumoEntity tipoInsumoEntity = (TipoInsumoEntity) session.get(TipoInsumoEntity.class, 7);
                TipoInsumoEntity tipoInsumoEntity = (TipoInsumoEntity) session.createQuery("select x from TipoInsumoEntity x where x.tinNombre = :pNombre").setParameter("pNombre", unidad).uniqueResult();
                insumo.setTipoInsumoByInsTinId(tipoInsumoEntity);
                Transaction tx = session.beginTransaction();
                session.save(insumo);
                tx.commit();
                guardado = tx.wasCommitted();
//            session.close();
            }catch (Exception e){JOptionPane.showMessageDialog(this, "Ocurrió un error al cargar el insumo.");}
            finally {
                session.close();
            }
        }else{
            JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos para continuar.");
        }

        return guardado;
    }

    public String validaCarga(){
        if (txtNombre.getText().replaceAll(" ", "").length() == 0) return "N";

        if (txtDescripcion.getText().replaceAll(" ", "").length() ==0) return "N";

        return "S";

    }

}
