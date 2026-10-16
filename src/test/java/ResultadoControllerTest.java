import controlador.ResultadoController;
import controlador.SessionController;
import modelo.Resultado;
import modelo.TipoApuesta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResultadoControllerTest {

    @Test
    void historialComienzaVacio() {

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        ResultadoController controller =
                new ResultadoController(session);

        assertEquals(
                0,
                controller.getCantidadResultados()
        );

        assertTrue(
                controller.getHistorial().isEmpty()
        );
    }

    @Test
    void obtenerResultadoDelUsuarioActual() {

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        ResultadoController controller =
                new ResultadoController(session);

        Resultado resultado =
                new Resultado(
                        7,
                        TipoApuesta.ROJO,
                        1000,
                        true
                );

        session.getUsuarioActual()
                .agregarResultado(resultado);

        assertEquals(
                1,
                controller.getCantidadResultados()
        );
    }

    @Test
    void calcularTotalApostado() {

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        ResultadoController controller =
                new ResultadoController(session);

        session.getUsuarioActual()
                .agregarResultado(
                        new Resultado(
                                7,
                                TipoApuesta.ROJO,
                                1000,
                                true
                        )
                );

        session.getUsuarioActual()
                .agregarResultado(
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

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        ResultadoController controller =
                new ResultadoController(session);

        session.getUsuarioActual()
                .agregarResultado(
                        new Resultado(
                                7,
                                TipoApuesta.ROJO,
                                1000,
                                true
                        )
                );

        session.getUsuarioActual()
                .agregarResultado(
                        new Resultado(
                                8,
                                TipoApuesta.PAR,
                                500,
                                false
                        )
                );

        session.getUsuarioActual()
                .agregarResultado(
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

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        ResultadoController controller =
                new ResultadoController(session);

        session.getUsuarioActual()
                .agregarResultado(
                        new Resultado(
                                7,
                                TipoApuesta.ROJO,
                                100,
                                true
                        )
                );

        session.getUsuarioActual()
                .agregarResultado(
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

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        ResultadoController controller =
                new ResultadoController(session);

        session.getUsuarioActual()
                .agregarResultado(
                        new Resultado(
                                7,
                                TipoApuesta.ROJO,
                                1000,
                                true
                        )
                );

        session.getUsuarioActual()
                .agregarResultado(
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

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        ResultadoController controller =
                new ResultadoController(session);

        assertEquals(
                0.0,
                controller.calcularPorcentajeAciertos()
        );
    }

    @Test
    void usuariosMantienenHistorialesSeparados() {

        SessionController session =
                new SessionController();

        ResultadoController controller =
                new ResultadoController(session);

        session.iniciarSesion(
                "admin",
                "1234"
        );

        session.getUsuarioActual()
                .agregarResultado(
                        new Resultado(
                                7,
                                TipoApuesta.ROJO,
                                500,
                                true
                        )
                );

        assertEquals(
                1,
                controller.getCantidadResultados()
        );

        session.cerrarSesion();

        session.iniciarSesion(
                "jugador",
                "1234"
        );

        assertEquals(
                0,
                controller.getCantidadResultados()
        );
    }
}