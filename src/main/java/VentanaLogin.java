import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin {

    private final SessionController session;

    private final JFrame frame =
            new JFrame("Login - Casino Black Cat");

    private final JLabel lblUsuario =
            new JLabel("Usuario:");

    private final JTextField txtUsuario =
            new JTextField();

    private final JLabel lblClave =
            new JLabel("Clave:");

    private final JPasswordField txtClave =
            new JPasswordField();

    private final JButton btnIngresar =
            new JButton("Ingresar");

    private final JButton btnRegistrarse =
            new JButton("Registrarse");

    public VentanaLogin(SessionController session) {

        this.session = session;

        frame.setSize(350, 220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        frame.add(lblUsuario);
        frame.add(txtUsuario);

        frame.add(lblClave);
        frame.add(txtClave);

        frame.add(btnRegistrarse);
        frame.add(btnIngresar);

        btnIngresar.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        login();
                    }
                }
        );

        btnRegistrarse.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        abrirRegistro();
                    }
                }
        );
    }

    public void mostrarVentana() {

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void login() {

        String usuario =
                txtUsuario.getText();

        String clave =
                new String(txtClave.getPassword());

        boolean ingreso =
                session.iniciarSesion(
                        usuario,
                        clave
                );

        if (ingreso) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Bienvenido "
                            + session.getNombreUsuario()
            );

            frame.dispose();

            RuletaController ruletaController =
                    new RuletaController();

            VentanaMenu ventanaMenu =
                    new VentanaMenu(
                            session,
                            ruletaController
                    );

            ventanaMenu.mostrarVentana();


        } else {

            JOptionPane.showMessageDialog(
                    frame,
                    "Usuario o clave incorrectos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void abrirRegistro() {

        frame.dispose();

        VentanaRegistro ventanaRegistro =
                new VentanaRegistro(session);

        ventanaRegistro.mostrarVentana();
    }
}