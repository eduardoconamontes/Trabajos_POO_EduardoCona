import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaMenu {

    private final SessionController session;
    private final RuletaController ruletaController;

    private final JFrame frame =
            new JFrame("Menú - Casino Black Cat");

    private final JLabel lblBienvenida =
            new JLabel("", SwingConstants.CENTER);

    private final JLabel lblSaldo =
            new JLabel("", SwingConstants.CENTER);

    private final JButton btnJugar =
            new JButton("Jugar");

    private final JButton btnHistorial =
            new JButton("Historial");

    private final JButton btnCambiarNombre =
            new JButton("Cambiar nombre");

    private final JButton btnRecargarSaldo =
            new JButton("Recargar saldo");

    private final JButton btnCerrarSesion =
            new JButton("Cerrar sesión");

    public VentanaMenu(
            SessionController session,
            RuletaController ruletaController) {

        this.session = session;
        this.ruletaController = ruletaController;

        frame.setSize(450, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 1, 10, 10));

        frame.add(lblBienvenida);
        frame.add(lblSaldo);
        frame.add(btnJugar);
        frame.add(btnHistorial);
        frame.add(btnCambiarNombre);
        frame.add(btnRecargarSaldo);
        frame.add(btnCerrarSesion);

        actualizarDatos();

        btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRuleta();
            }
        });

        btnHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarHistorial();
            }
        });

        btnCambiarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarNombre();
            }
        });

        btnRecargarSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recargarSaldo();
            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
    }

    public void mostrarVentana() {

        actualizarDatos();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void actualizarDatos() {

        lblBienvenida.setText(
                "Bienvenido " + session.getNombreUsuario()
        );

        lblSaldo.setText(
                "Saldo actual: $" + ruletaController.getSaldo()
        );
    }

    private void abrirRuleta() {

        frame.dispose();

        VentanaRuleta ventanaRuleta =
                new VentanaRuleta(
                        session,
                        ruletaController
                );

        ventanaRuleta.mostrarVentana();
    }

    private void cambiarNombre() {

        String nuevoNombre =
                JOptionPane.showInputDialog(
                        frame,
                        "Ingrese el nuevo nombre:"
                );

        if (nuevoNombre == null) {
            return;
        }

        boolean cambiado =
                session.cambiarNombre(nuevoNombre);

        if (cambiado) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Nombre actualizado correctamente"
            );

            actualizarDatos();

        } else {

            JOptionPane.showMessageDialog(
                    frame,
                    "El nombre no puede estar vacío",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void recargarSaldo() {

        String montoTexto =
                JOptionPane.showInputDialog(
                        frame,
                        "Ingrese el monto a depositar:"
                );

        if (montoTexto == null) {
            return;
        }

        montoTexto = montoTexto.trim();

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
                        "Debe ingresar un número válido",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

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

        long saldoFinal =
                (long) ruletaController.getSaldo() + monto;

        if (saldoFinal > Integer.MAX_VALUE) {

            JOptionPane.showMessageDialog(
                    frame,
                    "La recarga supera el saldo máximo permitido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        boolean deposito =
                ruletaController.depositar(monto);

        if (deposito) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Saldo recargado correctamente"
            );

            actualizarDatos();

        } else {

            JOptionPane.showMessageDialog(
                    frame,
                    "No fue posible realizar la recarga",
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

    private void mostrarHistorial() {

        ResultadoController resultadoController =
                ruletaController.getResultadoController();

        List<Resultado> historial =
                resultadoController.getHistorial();

        StringBuilder texto =
                new StringBuilder();

        texto.append("HISTORIAL DE PARTIDAS\n\n");

        if (historial.isEmpty()) {

            texto.append(
                    "Todavía no se han realizado apuestas."
            );

        } else {

            for (int i = 0; i < historial.size(); i++) {

                Resultado resultado =
                        historial.get(i);

                texto.append("Ronda ")
                        .append(i + 1)
                        .append(" | Número: ")
                        .append(resultado.getNumero())
                        .append(" | Tipo: ")
                        .append(resultado.getTipoApuesta())
                        .append(" | Apuesta: $")
                        .append(resultado.getMonto())
                        .append(" | Resultado: ");

                if (resultado.isAcierto()) {
                    texto.append("Ganó");
                } else {
                    texto.append("Perdió");
                }

                texto.append("\n");
            }

            texto.append("\nESTADÍSTICAS\n\n");

            texto.append("Rondas jugadas: ")
                    .append(resultadoController.getCantidadResultados())
                    .append("\n");

            texto.append("Total apostado: $")
                    .append(
                            resultadoController
                                    .calcularTotalApostado()
                    )
                    .append("\n");

            texto.append("Total de aciertos: ")
                    .append(
                            resultadoController
                                    .calcularTotalAciertos()
                    )
                    .append("\n");

            texto.append("Porcentaje de aciertos: ")
                    .append(
                            resultadoController
                                    .calcularPorcentajeAciertos()
                    )
                    .append("%\n");

            texto.append("Ganancia o pérdida neta: $")
                    .append(
                            resultadoController
                                    .calcularGananciaNeta()
                    );
        }

        JTextArea areaHistorial =
                new JTextArea(texto.toString());

        areaHistorial.setEditable(false);
        areaHistorial.setRows(20);
        areaHistorial.setColumns(55);
        areaHistorial.setCaretPosition(0);

        JScrollPane scrollHistorial =
                new JScrollPane(areaHistorial);

        scrollHistorial.setPreferredSize(
                new Dimension(650, 450)
        );

        JOptionPane.showMessageDialog(
                frame,
                scrollHistorial,
                "Historial",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void cerrarSesion() {

        session.cerrarSesion();

        frame.dispose();

        VentanaLogin ventanaLogin =
                new VentanaLogin(session);

        ventanaLogin.mostrarVentana();
    }
}