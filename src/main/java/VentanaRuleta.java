import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {

    private final String nombre;
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");

    private final JLabel lblTipoApuesta = new JLabel("Tipo de apuesta:");
    private final JComboBox<String> cmbTipoApuesta = new JComboBox<>(
            new String[]{"Rojo", "Negro", "Par", "Impar"}
    );

    private final JLabel lblMonto = new JLabel("Monto a apostar:");
    private final JTextField txtMonto = new JTextField();

    private final JButton btnJugar = new JButton("Girar Ruleta");
    private final JButton btnVolver = new JButton("Volver al menú");

    private final JLabel lblResultado = new JLabel(
            "Realice una apuesta",
            SwingConstants.CENTER
    );

    public VentanaRuleta(String nombre) {

        this.nombre = nombre;
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(lblTipoApuesta);
        frame.add(cmbTipoApuesta);

        frame.add(lblMonto);
        frame.add(txtMonto);

        frame.add(btnJugar);
        frame.add(btnVolver);

        frame.add(lblResultado);

        btnVolver.addActionListener(e -> volverMenu());
        btnJugar.addActionListener(e -> jugar());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void jugar() {

        String montoTexto = txtMonto.getText();

        if (montoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Debe ingresar un monto",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        int monto;

        try {
            monto = Integer.parseInt(montoTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    "El monto debe ser un número",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (monto <= 0) {
            JOptionPane.showMessageDialog(
                    frame,
                    "El monto debe ser mayor que 0",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        char tipo = obtenerTipoApuesta();

        int numero = Ruleta.girarRuleta();
        boolean acierto = Ruleta.evaluarResultado(numero, tipo);

        Ruleta.registrarResultado(numero, monto, acierto);

        if (acierto) {
            lblResultado.setText("Número: " + numero + " - Ganaste");
        } else {
            lblResultado.setText("Número: " + numero + " - Perdiste");
        }
    }

    private char obtenerTipoApuesta() {

        String seleccion = (String) cmbTipoApuesta.getSelectedItem();

        switch (seleccion) {
            case "Rojo":
                return 'R';
            case "Negro":
                return 'N';
            case "Par":
                return 'P';
            case "Impar":
                return 'I';
            default:
                return 'R';
        }
    }

    private void volverMenu() {
        frame.dispose();

        VentanaMenu ventanaMenu = new VentanaMenu(nombre);
        ventanaMenu.mostrarVentana();
    }
}