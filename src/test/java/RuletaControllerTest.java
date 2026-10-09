import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RuletaControllerTest {

    @Test
    void controladorIniciaConSaldoCero() {

        RuletaController controller =
                new RuletaController();

        assertEquals(
                0,
                controller.getSaldo()
        );
    }

    @Test
    void controladorPuedeIniciarConSaldo() {

        RuletaController controller =
                new RuletaController(5000);

        assertEquals(
                5000,
                controller.getSaldo()
        );
    }

    @Test
    void depositarSaldoDesdeControlador() {

        RuletaController controller =
                new RuletaController();

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

        RuletaController controller =
                new RuletaController();

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

        RuletaController controller =
                new RuletaController(500);

        assertThrows(
                IllegalArgumentException.class,
                () -> controller.realizarApuesta(
                        TipoApuesta.ROJO,
                        1000
                )
        );
    }

    @Test
    void apuestaSeRegistraEnHistorial() {

        RuletaController controller =
                new RuletaController(5000);

        controller.realizarApuesta(
                TipoApuesta.ROJO,
                500
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

        RuletaController controller =
                new RuletaController(5000);

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
}