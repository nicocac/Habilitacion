package Campania;

import Datos.CampaniaEntity;
import Insumo.Insumo;
import Laboreo.GestorLaboreo;
import Maquinaria.Maquinaria;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.Iterator;

/**
 * Created by jagm on 05/09/2016.
 */
public class CargaCampania extends JFrame{
    private JPanel panel1;
    private JTextField txtFecha;
    private JButton btnFinalizar;
    private JList lstLotes;
    private JLabel lblLotes;
    private JTextField textField1;
    private JButton cancelarButton;

    private GestorCampania gestor= new GestorCampania();

    public CargaCampania() {


        //INICIO
        setContentPane(panel1);
        pack();
        this.setTitle("Registrar Campania");
        inicializaTabla();
        cargarLotes();

        lstLotes.addListSelectionListener(e -> lblLotes.setText(String.valueOf(lstLotes.getSelectedValuesList().size())));

    }

    private boolean isCellSelected(JTable tabla){
        int fila = tabla.getSelectedRow();
        if (fila == -1){
            return false;
        }
        return true;
    }

    private void inicializaTabla() {
        String[] columnNamesCompra = {"Tipo", "Descripcion", "Unidad de Medida", "Cantidad"};
        Object[][] data = new Object[1][5];

    }


    private boolean existeLote(){
        if (lstLotes.getSelectedValuesList().size()==0){
            return false;
        }
        return true;
    }

    private void limpiarPantalla(){
        inicializaTabla();
    }


    private void cargarLotes(){
        java.util.List lista;
        lista = gestor.getLotes();
        DefaultListModel modelo = new DefaultListModel();

        Iterator iter = lista.iterator();
        while (iter.hasNext()) {
            modelo.addElement(iter.next());
        }
        lstLotes.setModel(modelo);
    }
}
