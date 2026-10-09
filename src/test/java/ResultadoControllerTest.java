import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResultadoControllerTest {

    @Test
    void historialComienzaVacio() {

        ResultadoController controller =
                new ResultadoController();

        assertEquals(
                0,
                controller.getCantidadResultados()
        );

        assertTrue(
                controller.getHistorial().isEmpty()
        );
    }

    @Test
    void registrarResultadoEnHistorial() {

        ResultadoController controller =
                new ResultadoController();

        Resultado resultado =
                new Resultado(
                        7,
                        TipoApuesta.ROJO,
                        1000,
                        true
                );

        controller.registrarResultado(resultado);

        assertEquals(
                1,
                controller.getCantidadResultados()
        );
    }

    @Test
    void calcularTotalApostado() {

        ResultadoController controller =
                new ResultadoController();

        controller.registrarResultado(
                new Resultado(
                        7,
                        TipoApuesta.ROJO,
                        1000,
                        true
                )
        );

        controller.registrarResultado(
                new Resultado(
                        8,
                        TipoApuesta.PAR,
                        500,
                        false
                )
        );

        assertEquals(
                1500,
                controller.calcularTotalApostado()
        );
    }

    @Test
    void calcularTotalAciertos() {

        ResultadoController controller =
                new ResultadoController();

        controller.registrarResultado(
                new Resultado(
                        7,
                        TipoApuesta.ROJO,
                        1000,
                        true
                )
        );

        controller.registrarResultado(
                new Resultado(
                        8,
                        TipoApuesta.PAR,
                        500,
                        false
                )
        );

        controller.registrarResultado(
                new Resultado(
                        9,
                        TipoApuesta.IMPAR,
                        200,
                        true
                )
        );

        assertEquals(
                2,
                controller.calcularTotalAciertos()
        );
    }

    @Test
    void calcularPorcentajeAciertos() {

        ResultadoController controller =
                new ResultadoController();

        controller.registrarResultado(
                new Resultado(
                        7,
                        TipoApuesta.ROJO,
                        100,
                        true
                )
        );

        controller.registrarResultado(
                new Resultado(
                        8,
                        TipoApuesta.PAR,
                        100,
                        false
                )
        );

        assertEquals(
                50.0,
                controller.calcularPorcentajeAciertos()
        );
    }

    @Test
    void calcularGananciaNeta() {

        ResultadoController controller =
                new ResultadoController();

        controller.registrarResultado(
                new Resultado(
                        7,
                        TipoApuesta.ROJO,
                        1000,
                        true
                )
        );

        controller.registrarResultado(
                new Resultado(
                        8,
                        TipoApuesta.ROJO,
                        400,
                        false
                )
        );

        assertEquals(
                600,
                controller.calcularGananciaNeta()
        );
    }

    @Test
    void porcentajeEsCeroSinResultados() {

        ResultadoController controller =
                new ResultadoController();

        assertEquals(
                0.0,
                controller.calcularPorcentajeAciertos()
        );
    }
}