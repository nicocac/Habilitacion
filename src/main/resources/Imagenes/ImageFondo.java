package Imagenes;

import javax.swing.*;
import java.awt.*;


public class ImageFondo extends JPanel{
    private Image fondo=null;
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fondo,0,0,getWidth(),getHeight(),null);
    }
    public void setImage(String image){
        if (image!=null) {
            fondo=new ImageIcon(getClass().getResource(image)).getImage();
        }
    }

}
