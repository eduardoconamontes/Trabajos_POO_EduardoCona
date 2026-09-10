import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {

    public static final List<Usuario> USUARIOS = new ArrayList<>();

    private final JFrame frame = new JFrame("Login - Casino Black Cat");

    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();

    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();

    private final JButton btnIngresar = new JButton("Ingresar");

    public VentanaLogin() {

        USUARIOS.add(new Usuario("admin", "1234", "Administrador"));
        USUARIOS.add(new Usuario("jugador", "1234", "Jugador"));

        frame.setSize(350, 220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        frame.add(lblUsuario);
        frame.add(txtUsuario);

        frame.add(lblClave);
        frame.add(txtClave);

        frame.add(new JLabel(""));
        frame.add(btnIngresar);

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void login() {

        String usuario = txtUsuario.getText();
        String clave = new String(txtClave.getPassword());

        String nombre = validarCredenciales(usuario, clave);

        if (!nombre.isEmpty()) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Bienvenido " + nombre
            );
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    "Usuario o clave incorrectos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private String validarCredenciales(String u, String p) {

        for (int i = 0; i < USUARIOS.size(); i++) {

            Usuario usuario = USUARIOS.get(i);

            if (usuario.validarCredenciales(u, p)) {
                return usuario.getNombre();
            }
        }

        return "";
    }

    public static void main(String[] args) {
        VentanaLogin ventana = new VentanaLogin();
        ventana.mostrarVentana();
    }
}