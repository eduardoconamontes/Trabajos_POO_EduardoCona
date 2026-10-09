import java.util.ArrayList;
import java.util.List;

public class ResultadoController {

    private List<Resultado> historial;

    public ResultadoController() {
        historial = new ArrayList<>();
    }

    public void registrarResultado(Resultado resultado) {

        if (resultado != null) {
            historial.add(resultado);
        }
    }

    public List<Resultado> getHistorial() {
        return historial;
    }

    public int getCantidadResultados() {
        return historial.size();
    }

    public long calcularTotalApostado() {

        long total = 0;

        for (int i = 0; i < historial.size(); i++) {

            Resultado resultado =
                    historial.get(i);

            total += resultado.getMonto();
        }

        return total;
    }

    public int calcularTotalAciertos() {

        int total = 0;

        for (int i = 0; i < historial.size(); i++) {

            Resultado resultado = historial.get(i);

            if (resultado.isAcierto()) {
                total++;
            }
        }

        return total;
    }

    public double calcularPorcentajeAciertos() {

        if (historial.isEmpty()) {
            return 0;
        }

        return (calcularTotalAciertos() * 100.0)
                / historial.size();
    }

    public long calcularGananciaNeta() {

        long ganancia = 0;

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