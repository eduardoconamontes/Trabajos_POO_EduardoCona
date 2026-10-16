import java.util.ArrayList;
import java.util.List;

public class ResultadoController {

    private final SessionController session;

    public ResultadoController(SessionController session) {
        this.session = session;
    }

    public List<Resultado> getHistorial() {

        Usuario usuarioActual =
                session.getUsuarioActual();

        if (usuarioActual == null) {
            return new ArrayList<>();
        }

        return usuarioActual.getHistorial();
    }

    public int getCantidadResultados() {
        return getHistorial().size();
    }

    public long calcularTotalApostado() {

        long total = 0;

        List<Resultado> historial =
                getHistorial();

        for (int i = 0; i < historial.size(); i++) {

            Resultado resultado =
                    historial.get(i);

            total += resultado.getMonto();
        }

        return total;
    }

    public int calcularTotalAciertos() {

        int total = 0;

        List<Resultado> historial =
                getHistorial();

        for (int i = 0; i < historial.size(); i++) {

            Resultado resultado =
                    historial.get(i);

            if (resultado.isAcierto()) {
                total++;
            }
        }

        return total;
    }

    public double calcularPorcentajeAciertos() {

        List<Resultado> historial =
                getHistorial();

        if (historial.isEmpty()) {
            return 0;
        }

        return (calcularTotalAciertos() * 100.0)
                / historial.size();
    }

    public long calcularGananciaNeta() {

        long ganancia = 0;

        List<Resultado> historial =
                getHistorial();

        for (int i = 0; i < historial.size(); i++) {

            Resultado resultado =
                    historial.get(i);

            if (resultado.isAcierto()) {
                ganancia += resultado.getMonto();
            } else {
                ganancia -= resultado.getMonto();
            }
        }

        return ganancia;
    }
}