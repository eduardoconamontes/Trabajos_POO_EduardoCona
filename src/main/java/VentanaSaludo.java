import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSaludo {

    private final JFrame frame = new JFrame("Casino Black Cat");
    private final JLabel lblSaludo = new JLabel("", SwingConstants.CENTER);
    private final JButton btnRuleta = new JButton("Iniciar Ruleta");

    public VentanaSaludo(String nombre) {

        lblSaludo.setText("Bienvenido " + nombre);

        frame.setSize(350, 180);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1, 10, 10));

        frame.add(lblSaludo);
        frame.add(btnRuleta);

        btnRuleta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRuleta();
            }
        });
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirRuleta() {
        frame.dispose();
        Ruleta.main(new String[0]);
    }
}
