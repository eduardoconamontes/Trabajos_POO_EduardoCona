package controlador;

import modelo.Estadisticas;
import modelo.TipoApuesta;
import modelo.Usuario;

public class EstadisticasController {

    private final SessionController session;

    public EstadisticasController(SessionController session) {
        this.session = session;
    }

    public String obtenerEstadisticas() {

        Usuario usuario = session.getUsuarioActual();

        if (usuario == null) {
            return "No hay un usuario con sesión iniciada.";
        }

        Estadisticas estadisticas = new Estadisticas();
        estadisticas.calcular(usuario);

        TipoApuesta tipo = estadisticas.getTipoMasJugado();

        String tipoTexto = "Sin apuestas";

        if (tipo != null) {
            tipoTexto = tipo.toString();
        }

        return "ESTADÍSTICAS DE " + usuario.getNombre()
                + "\n\nTotal de jugadas: " + estadisticas.getTotalJugadas()
                + "\nVictorias: " + estadisticas.getVictorias()
                + "\nPorcentaje de victorias: "
                + String.format("%.2f", estadisticas.getPorcentajeVictorias()) + "%"
                + "\nRacha máxima: " + estadisticas.getRachaMaxima()
                + "\nTipo más jugado: " + tipoTexto;
    }
}