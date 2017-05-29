package Acopio;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GraficaAcopio extends JFrame{
    JPanel panel;
    public GraficaAcopio(JTable tblTipos){
        setTitle("Graficos Comparativo cantidad semillas");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        init(tblTipos);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void init(JTable tblTipos) {
        java.util.List<String> listaStock = new ArrayList<>();
        //adding table headers
//        for (int i = 0; i < tblTipos.getColumnCount(); i++) {
//            if(tblTipos.getColumnName(i).equals("Nombre")){
//                listaStock.add();
//            }
//        }
        //extracting data from the JTable and inserting it to PdfPTable
        for (int rows = 0; rows < tblTipos.getRowCount() ; rows++) {
            for (int cols = 0; cols < tblTipos.getColumnCount(); cols++) {
                tblTipos.getModel().getValueAt(rows, 1).toString();
                tblTipos.getModel().getValueAt(rows, 4).toString();
                tblTipos.getModel().getValueAt(rows, 6).toString();

            }
        }


        panel = new JPanel();
        getContentPane().add(panel);
        // Fuente de Datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int rows = 0; rows < tblTipos.getRowCount() ; rows++) {
            for (int cols = 0; cols < tblTipos.getColumnCount(); cols++) {
                dataset.setValue(Integer.parseInt(tblTipos.getModel().getValueAt(rows, 6).toString())
                        , tblTipos.getModel().getValueAt(rows, 4).toString()
                        , tblTipos.getModel().getValueAt(rows, 1).toString());

//                tblTipos.getModel().getValueAt(rows, 1).toString();
//                tblTipos.getModel().getValueAt(rows, 4).toString();
//                tblTipos.getModel().getValueAt(rows, 6).toString();

            }
        }
//        dataset.setValue(256, "Acopio 1", "Silo 1");
//        dataset.setValue(0, "Acopio 2" ,"Silo 1");
//        dataset.setValue(40, "Acopio 2", "Silo 2");
//        dataset.setValue(15, "Acopio 3", "Silo 3");
//        dataset.setValue(4, "Acopio 4", "Silo 4");
//        dataset.setValue(0, "Acopio 3", "Silo 5");
//        dataset.setValue(0, "Acopio 5", "Silo 5");
//        dataset.setValue(5, "Hombres", "Miercoles");
//        dataset.setValue(8, "Mujeres", "Jueves");
//        dataset.setValue(9, "Hombres", "Jueves");
//        dataset.setValue(7, "Mujeres", "Viernes");
//        dataset.setValue(8, "Hombres", "Viernes");
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createBarChart3D
                ("Stock de Semillas por Silo", "Silos", "Cantidad",
                        dataset, PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(Color.cyan);
        chart.getTitle().setPaint(Color.black);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.red);
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
    }

//    public static void main(String args[]){
//        JTable tblTipos = null;
//        new GraficaAcopio(tblTipos).setVisible(true);
//    }
}