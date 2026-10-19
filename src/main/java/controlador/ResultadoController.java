package controlador;

import modelo.Resultado;
import modelo.Usuario;

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

    public String generarTextoHistorial() {

        StringBuilder texto =
                new StringBuilder();

        texto.append("HISTORIAL DE PARTIDAS\n\n");

        List<Resultado> historial =
                getHistorial();

        if (historial.isEmpty()) {

            texto.append(
                    "Todavía no se han realizado apuestas."
            );

            return texto.toString();
        }

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
                .append(getCantidadResultados())
                .append("\n");

        texto.append("Total apostado: $")
                .append(calcularTotalApostado())
                .append("\n");

        texto.append("Total de aciertos: ")
                .append(calcularTotalAciertos())
                .append("\n");

        texto.append("Porcentaje de aciertos: ")
                .append(calcularPorcentajeAciertos())
                .append("%\n");

        texto.append("Ganancia o pérdida neta: $")
                .append(calcularGananciaNeta());

        return texto.toString();
    }
}