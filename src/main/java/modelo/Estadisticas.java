package modelo;

import java.util.List;

public class Estadisticas {

	private int totalJugadas;
	private int victorias;
	private double porcentajeVictorias;
	private int rachaMaxima;
	private TipoApuesta tipoMasJugado;

	public void calcular(Usuario usuario) {

		if (usuario == null) {
			throw new IllegalArgumentException(
					"Debe existir un usuario"
			);
		}

		List<Resultado> historial = usuario.getHistorial();

		totalJugadas = historial.size();
		victorias = 0;
		porcentajeVictorias = 0;
		rachaMaxima = 0;
		tipoMasJugado = null;

		int rachaActual = 0;

		int[] cantidades = new int[TipoApuesta.values().length];

		for (Resultado resultado : historial) {

			if (resultado.isAcierto()) {

				victorias++;

				rachaActual++;

				if (rachaActual > rachaMaxima) {
					rachaMaxima = rachaActual;
				}

			} else {

				rachaActual = 0;
			}

			TipoApuesta tipo = resultado.getTipoApuesta();

			if (tipo != null) {
				cantidades[tipo.ordinal()]++;
			}
		}

		if (totalJugadas > 0) {

			porcentajeVictorias =
					(victorias * 100.0) / totalJugadas;
		}

		int mayorCantidad = 0;

		for (TipoApuesta tipo : TipoApuesta.values()) {

			int cantidad = cantidades[tipo.ordinal()];

			if (cantidad > mayorCantidad) {

				mayorCantidad = cantidad;

				tipoMasJugado = tipo;
			}
		}
	}

	public int getTotalJugadas() {
		return totalJugadas;
	}

	public int getVictorias() {
		return victorias;
	}

	public double getPorcentajeVictorias() {
		return porcentajeVictorias;
	}

	public int getRachaMaxima() {
		return rachaMaxima;
	}

	public TipoApuesta getTipoMasJugado() {
		return tipoMasJugado;
	}
}