package Login;

import Principal.MenuPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginUsers extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField jpassClave;
    private JButton btnIngresar;
    private JPanel panel1;

    public LoginUsers() {

        //INICIO
        setContentPane(panel1);
        pack();
        this.setTitle("Administracion de Usuarios");

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                char clave[]=jpassClave.getPassword();

                String clavedef=new String(clave);


                if (txtUsuario.getText().equals("admin") && clavedef.equals("gestar123")){

                    JOptionPane.showMessageDialog(null, "Bienvenido\n"
                                    + "Has ingresado satisfactoriamente al sistema", "Mensaje de bienvenida",
                            JOptionPane.INFORMATION_MESSAGE);

                    dispose();


                    MenuPrincipal menuPrincipal = new MenuPrincipal();
                    menuPrincipal.setBounds(10, 20, 600, 600);
                    menuPrincipal.setVisible(true);
//                    menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                }else {


                    JOptionPane.showMessageDialog(null, "Acceso denegado:\n"
                                    + "Por favor ingrese un usuario y/o contraseña correctos", "Acceso denegado",
                            JOptionPane.ERROR_MESSAGE);


                }

            }
        });
    }
}
