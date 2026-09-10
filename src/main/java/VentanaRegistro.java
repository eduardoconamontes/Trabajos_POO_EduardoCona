import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro {

    private final JFrame frame = new JFrame("Registro - Casino Black Cat");

    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();

    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();

    private final JLabel lblNombre = new JLabel("Nombre completo:");
    private final JTextField txtNombre = new JTextField();

    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnVolver = new JButton("Volver");

    public VentanaRegistro() {

        frame.setSize(400, 280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(lblUsuario);
        frame.add(txtUsuario);

        frame.add(lblClave);
        frame.add(txtClave);

        frame.add(lblNombre);
        frame.add(txtNombre);

        frame.add(btnRegistrar);
        frame.add(btnVolver);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverLogin();
            }
        });
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void registrarUsuario() {

        String usuario = txtUsuario.getText();
        String clave = new String(txtClave.getPassword());
        String nombre = txtNombre.getText();

        if (usuario.isEmpty() || clave.isEmpty() || nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Debe completar todos los campos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } else {

            Usuario nuevoUsuario = new Usuario(usuario, clave, nombre);

            VentanaLogin.USUARIOS.add(nuevoUsuario);

            JOptionPane.showMessageDialog(
                    frame,
                    "Usuario registrado correctamente"
            );

            volverLogin();
        }
    }

    private void volverLogin() {

        frame.dispose();

        VentanaLogin ventanaLogin = new VentanaLogin();
        ventanaLogin.mostrarVentana();
    }
}

