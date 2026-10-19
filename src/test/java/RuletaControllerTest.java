import controlador.RuletaController;
import controlador.SessionController;
import modelo.Resultado;
import modelo.TipoApuesta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RuletaControllerTest {

    @Test
    void controladorIniciaConSaldoCero() {

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        RuletaController controller =
                new RuletaController(session);

        assertEquals(
                0,
                controller.getSaldo()
        );
    }

    @Test
    void controladorPuedeIniciarConSaldo() {

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        RuletaController controller =
                new RuletaController(
                        5000,
                        session
                );

        assertEquals(
                5000,
                controller.getSaldo()
        );
    }

    @Test
    void depositarSaldoDesdeControlador() {

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        RuletaController controller =
                new RuletaController(session);

        boolean resultado =
                controller.depositar(5000);

        assertTrue(resultado);

        assertEquals(
                5000,
                controller.getSaldo()
        );
    }

    @Test
    void rechazarDepositoInvalido() {

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        RuletaController controller =
                new RuletaController(session);

        boolean resultado =
                controller.depositar(-500);

        assertFalse(resultado);

        assertEquals(
                0,
                controller.getSaldo()
        );
    }

    @Test
    void noPermitirApuestaSinSaldoSuficiente() {

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        RuletaController controller =
                new RuletaController(
                        500,
                        session
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> controller.realizarApuesta(
                        TipoApuesta.ROJO,
                        1000
                )
        );
    }

    @Test
    void apuestaSeRegistraEnHistorialDelUsuario() {

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        RuletaController controller =
                new RuletaController(
                        5000,
                        session
                );

        controller.realizarApuesta(
                TipoApuesta.ROJO,
                500
        );

        assertEquals(
                1,
                session.getUsuarioActual()
                        .getHistorial()
                        .size()
        );

        assertEquals(
                1,
                controller
                        .getResultadoController()
                        .getCantidadResultados()
        );
    }

    @Test
    void apuestaActualizaSaldo() {

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        RuletaController controller =
                new RuletaController(
                        5000,
                        session
                );

        Resultado resultado =
                controller.realizarApuesta(
                        TipoApuesta.ROJO,
                        500
                );

        if (resultado.isAcierto()) {

            assertEquals(
                    5500,
                    controller.getSaldo()
            );

        } else {

            assertEquals(
                    4500,
                    controller.getSaldo()
            );
        }
    }

    @Test
    void noPermitirApuestaSinUsuarioAutenticado() {

        SessionController session =
                new SessionController();

        RuletaController controller =
                new RuletaController(
                        5000,
                        session
                );

        assertThrows(
                IllegalStateException.class,
                () -> controller.realizarApuesta(
                        TipoApuesta.ROJO,
                        500
                )
        );
    }
}