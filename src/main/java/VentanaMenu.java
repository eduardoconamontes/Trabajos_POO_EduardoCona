import javax.swing.*;
import java.awt.*;

public class VentanaMenu {

    private final String nombre;
    private final JFrame frame = new JFrame("Menú - Casino Black Cat");

    private final JLabel lblBienvenida = new JLabel("", SwingConstants.CENTER);

    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnCerrarSesion = new JButton("Cerrar sesión");

    public VentanaMenu(String nombre) {

        this.nombre = nombre;
        lblBienvenida.setText("Bienvenido " + nombre);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        frame.add(lblBienvenida);
        frame.add(btnJugar);
        frame.add(btnHistorial);
        frame.add(btnCerrarSesion);
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        btnJugar.addActionListener(e -> abrirRuleta());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void cerrarSesion() {
        frame.dispose();

        VentanaLogin ventanaLogin = new VentanaLogin();
        ventanaLogin.mostrarVentana();
    }

    private void abrirRuleta() {
        frame.dispose();

        VentanaRuleta ventanaRuleta = new VentanaRuleta(nombre);
        ventanaRuleta.mostrarVentana();
    }
}