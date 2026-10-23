
import modelo.Estadisticas;
import modelo.Resultado;
import modelo.TipoApuesta;
import modelo.Usuario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EstadisticasTest {

    private Usuario crearUsuario() {
        return new Usuario("prueba", "1234", "Usuario Prueba");
    }

    private void agregarResultado(
            Usuario usuario,
            TipoApuesta tipo,
            boolean acierto) {

        Resultado resultado = new Resultado(
                10,
                tipo,
                100,
                acierto
        );

        usuario.agregarResultado(resultado);
    }

    @Test
    void estadisticasSinJugadas() {

        Usuario usuario = crearUsuario();
        Estadisticas estadisticas = new Estadisticas();

        estadisticas.calcular(usuario);

        assertEquals(0, estadisticas.getTotalJugadas());
        assertEquals(0, estadisticas.getVictorias());
        assertEquals(0.0, estadisticas.getPorcentajeVictorias());
        assertEquals(0, estadisticas.getRachaMaxima());
        assertNull(estadisticas.getTipoMasJugado());
    }

    @Test
    void calculaTotalJugadasYVictorias() {

        Usuario usuario = crearUsuario();

        agregarResultado(usuario, TipoApuesta.ROJO, true);
        agregarResultado(usuario, TipoApuesta.NEGRO, false);
        agregarResultado(usuario, TipoApuesta.PAR, true);

        Estadisticas estadisticas = new Estadisticas();
        estadisticas.calcular(usuario);

        assertEquals(3, estadisticas.getTotalJugadas());
        assertEquals(2, estadisticas.getVictorias());
    }

    @Test
    void calculaPorcentajeVictorias() {

        Usuario usuario = crearUsuario();

        agregarResultado(usuario, TipoApuesta.ROJO, true);
        agregarResultado(usuario, TipoApuesta.ROJO, true);
        agregarResultado(usuario, TipoApuesta.PAR, false);
        agregarResultado(usuario, TipoApuesta.NEGRO, false);

        Estadisticas estadisticas = new Estadisticas();
        estadisticas.calcular(usuario);

        assertEquals(
                50.0,
                estadisticas.getPorcentajeVictorias(),
                0.001
        );
    }

    @Test
    void calculaRachaMaxima() {

        Usuario usuario = crearUsuario();

        agregarResultado(usuario, TipoApuesta.ROJO, true);
        agregarResultado(usuario, TipoApuesta.ROJO, true);
        agregarResultado(usuario, TipoApuesta.PAR, false);
        agregarResultado(usuario, TipoApuesta.NEGRO, true);
        agregarResultado(usuario, TipoApuesta.NEGRO, true);
        agregarResultado(usuario, TipoApuesta.IMPAR, true);

        Estadisticas estadisticas = new Estadisticas();
        estadisticas.calcular(usuario);

        assertEquals(3, estadisticas.getRachaMaxima());
    }

    @Test
    void calculaTipoMasJugado() {

        Usuario usuario = crearUsuario();

        agregarResultado(usuario, TipoApuesta.ROJO, true);
        agregarResultado(usuario, TipoApuesta.ROJO, false);
        agregarResultado(usuario, TipoApuesta.ROJO, true);
        agregarResultado(usuario, TipoApuesta.PAR, false);
        agregarResultado(usuario, TipoApuesta.NEGRO, true);

        Estadisticas estadisticas = new Estadisticas();
        estadisticas.calcular(usuario);

        assertEquals(
                TipoApuesta.ROJO,
                estadisticas.getTipoMasJugado()
        );
    }

    @Test
    void recalculaSinAcumularResultadosAnteriores() {

        Usuario usuario = crearUsuario();

        agregarResultado(usuario, TipoApuesta.ROJO, true);

        Estadisticas estadisticas = new Estadisticas();
        estadisticas.calcular(usuario);
        estadisticas.calcular(usuario);

        assertEquals(1, estadisticas.getTotalJugadas());
        assertEquals(1, estadisticas.getVictorias());
        assertEquals(1, estadisticas.getRachaMaxima());
    }
}
