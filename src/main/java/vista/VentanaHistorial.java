package vista;

import controlador.ResultadoController;
import controlador.RuletaController;
import controlador.SessionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaHistorial {

    private final SessionController session;
    private final RuletaController ruletaController;
    private final ResultadoController resultadoController;

    private final JFrame frame =
            new JFrame("Historial - Casino Black Cat");

    private final JLabel lblUsuario =
            new JLabel("", SwingConstants.CENTER);

    private final JTextArea areaHistorial =
            new JTextArea();

    private final JScrollPane scrollHistorial =
            new JScrollPane(areaHistorial);

    private final JButton btnVolver =
            new JButton("Volver al menú");

    public VentanaHistorial(
            SessionController session,
            RuletaController ruletaController) {

        this.session = session;
        this.ruletaController = ruletaController;

        this.resultadoController =
                ruletaController.getResultadoController();

        frame.setSize(750, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        lblUsuario.setText(
                "Historial de " + session.getNombreUsuario()
        );

        areaHistorial.setEditable(false);
        areaHistorial.setLineWrap(false);

        frame.add(
                lblUsuario,
                BorderLayout.NORTH
        );

        frame.add(
                scrollHistorial,
                BorderLayout.CENTER
        );

        frame.add(
                btnVolver,
                BorderLayout.SOUTH
        );

        btnVolver.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        volverMenu();
                    }
                }
        );
    }

    public void mostrarVentana() {

        actualizarHistorial();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void actualizarHistorial() {

        String texto =
                resultadoController
                        .generarTextoHistorial();

        areaHistorial.setText(texto);

        areaHistorial.setCaretPosition(0);
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