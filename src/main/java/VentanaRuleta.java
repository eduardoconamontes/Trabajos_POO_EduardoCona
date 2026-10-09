import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRuleta {

    private final SessionController session;
    private final RuletaController ruletaController;

    private final JFrame frame =
            new JFrame("Ruleta - Casino Black Cat");

    private final JLabel lblTipoApuesta =
            new JLabel("Tipo de apuesta:");

    private final JComboBox<TipoApuesta> cmbTipoApuesta =
            new JComboBox<>(TipoApuesta.values());

    private final JLabel lblMonto =
            new JLabel("Monto a apostar:");

    private final JTextField txtMonto =
            new JTextField();

    private final JLabel lblSaldo =
            new JLabel("", SwingConstants.CENTER);

    private final JLabel lblResultado =
            new JLabel(
                    "Realice una apuesta",
                    SwingConstants.CENTER
            );

    private final JButton btnJugar =
            new JButton("Girar Ruleta");

    private final JButton btnVolver =
            new JButton("Volver al menú");

    public VentanaRuleta(
            SessionController session,
            RuletaController ruletaController) {

        this.session = session;
        this.ruletaController = ruletaController;

        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        frame.add(lblTipoApuesta);
        frame.add(cmbTipoApuesta);

        frame.add(lblMonto);
        frame.add(txtMonto);

        frame.add(lblSaldo);
        frame.add(lblResultado);

        frame.add(btnJugar);
        frame.add(btnVolver);

        actualizarSaldo();

        btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugar();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverMenu();
            }
        });
    }

    public void mostrarVentana() {

        actualizarSaldo();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void jugar() {

        String montoTexto =
                txtMonto.getText().trim();

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

            if (esTextoEntero(montoTexto)) {

                JOptionPane.showMessageDialog(
                        frame,
                        "El monto ingresado es demasiado grande",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        frame,
                        "Ingrese un número válido",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            txtMonto.setText("");
            txtMonto.requestFocus();

            return;
        }

        if (monto <= 0) {

            JOptionPane.showMessageDialog(
                    frame,
                    "El monto debe ser mayor que cero",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        TipoApuesta tipo =
                (TipoApuesta)
                        cmbTipoApuesta.getSelectedItem();

        try {

            Resultado resultado =
                    ruletaController.realizarApuesta(
                            tipo,
                            monto
                    );

            if (resultado.isAcierto()) {

                lblResultado.setText(
                        "Número: "
                                + resultado.getNumero()
                                + " - Ganaste"
                );

            } else {

                lblResultado.setText(
                        "Número: "
                                + resultado.getNumero()
                                + " - Perdiste"
                );
            }

            actualizarSaldo();

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    frame,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private boolean esTextoEntero(String texto) {

        if (texto == null || texto.isEmpty()) {
            return false;
        }

        int inicio = 0;

        if (texto.charAt(0) == '-') {

            if (texto.length() == 1) {
                return false;
            }

            inicio = 1;
        }

        for (int i = inicio; i < texto.length(); i++) {

            if (!Character.isDigit(texto.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private void actualizarSaldo() {

        lblSaldo.setText(
                "Saldo: $"
                        + ruletaController.getSaldo()
        );
    }

    private void volverMenu() {

        frame.dispose();

        VentanaMenu ventanaMenu =
                new VentanaMenu(
                        session,
                        ruletaController
                );

        ventanaMenu.mostrarVentana();
    }
}