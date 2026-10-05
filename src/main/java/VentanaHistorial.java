import javax.swing.*;
import java.awt.*;

public class VentanaHistorial {

    private final String nombre;

    private final JFrame frame =
            new JFrame("Historial - Casino Black Cat");

    private final JTextArea txtHistorial =
            new JTextArea();

    private final JButton btnVolver =
            new JButton("Volver al menú");

    public VentanaHistorial(String nombre) {

        this.nombre = nombre;

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        txtHistorial.setEditable(false);

        cargarHistorial();

        frame.add(
                new JScrollPane(txtHistorial),
                BorderLayout.CENTER
        );

        frame.add(
                btnVolver,
                BorderLayout.SOUTH
        );

        btnVolver.addActionListener(e -> volverMenu());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void cargarHistorial() {

        StringBuilder texto = new StringBuilder();

        texto.append("HISTORIAL DE PARTIDAS\n\n");

        if (Ruleta.historialSize == 0) {

            texto.append("Todavía no se han realizado apuestas.\n");

        } else {

            for (int i = 0; i < Ruleta.historialSize; i++) {

                texto.append("Ronda ")
                        .append(i + 1)
                        .append(" | Número: ")
                        .append(Ruleta.historialNumeros[i])
                        .append(" | Apuesta: $")
                        .append(Ruleta.historialApuestas[i])
                        .append(" | Resultado: ");

                if (Ruleta.historialAciertos[i]) {
                    texto.append("Ganó");
                } else {
                    texto.append("Perdió");
                }

                texto.append("\n");
            }

            texto.append("\nESTADÍSTICAS\n\n");

            texto.append("Rondas jugadas: ")
                    .append(Ruleta.historialSize)
                    .append("\n");

            texto.append("Total apostado: $")
                    .append(Ruleta.calcularTotalApostado())
                    .append("\n");

            texto.append("Total de aciertos: ")
                    .append(Ruleta.calcularTotalAciertos())
                    .append("\n");

            texto.append("Porcentaje de aciertos: ")
                    .append(Ruleta.calcularPorcentajeAciertos())
                    .append("%\n");

            texto.append("Ganancia o pérdida neta: $")
                    .append(Ruleta.calcularGananciaNeta())
                    .append("\n");
        }

        txtHistorial.setText(texto.toString());
    }

    private void volverMenu() {

        frame.dispose();

        VentanaMenu ventanaMenu =
                new VentanaMenu(nombre);

        ventanaMenu.mostrarVentana();
    }
}