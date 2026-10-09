import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RuletaTest {

    @Test
    void ruletaIniciaConSaldoCero() {

        Ruleta ruleta =
                new Ruleta();

        assertEquals(
                0,
                ruleta.getSaldo()
        );
    }

    @Test
    void ruletaPuedeIniciarConSaldoDeterminado() {

        Ruleta ruleta =
                new Ruleta(5000);

        assertEquals(
                5000,
                ruleta.getSaldo()
        );
    }

    @Test
    void depositarMontoValido() {

        Ruleta ruleta =
                new Ruleta();

        boolean resultado =
                ruleta.depositar(5000);

        assertTrue(resultado);

        assertEquals(
                5000,
                ruleta.getSaldo()
        );
    }

    @Test
    void rechazarDepositoNegativo() {

        Ruleta ruleta =
                new Ruleta();

        boolean resultado =
                ruleta.depositar(-500);

        assertFalse(resultado);

        assertEquals(
                0,
                ruleta.getSaldo()
        );
    }

    @Test
    void rechazarDepositoCero() {

        Ruleta ruleta =
                new Ruleta();

        boolean resultado =
                ruleta.depositar(0);

        assertFalse(resultado);

        assertEquals(
                0,
                ruleta.getSaldo()
        );
    }

    @Test
    void reconocerNumeroRojo() {

        Ruleta ruleta =
                new Ruleta();

        assertTrue(
                ruleta.esRojo(7)
        );
    }

    @Test
    void reconocerNumeroNegro() {

        Ruleta ruleta =
                new Ruleta();

        assertFalse(
                ruleta.esRojo(8)
        );
    }

    @Test
    void evaluarApuestaRojo() {

        Ruleta ruleta =
                new Ruleta();

        boolean resultado =
                ruleta.evaluarResultado(
                        7,
                        TipoApuesta.ROJO
                );

        assertTrue(resultado);
    }

    @Test
    void evaluarApuestaNegro() {

        Ruleta ruleta =
                new Ruleta();

        boolean resultado =
                ruleta.evaluarResultado(
                        8,
                        TipoApuesta.NEGRO
                );

        assertTrue(resultado);
    }

    @Test
    void evaluarApuestaPar() {

        Ruleta ruleta =
                new Ruleta();

        boolean resultado =
                ruleta.evaluarResultado(
                        8,
                        TipoApuesta.PAR
                );

        assertTrue(resultado);
    }

    @Test
    void evaluarApuestaImpar() {

        Ruleta ruleta =
                new Ruleta();

        boolean resultado =
                ruleta.evaluarResultado(
                        9,
                        TipoApuesta.IMPAR
                );

        assertTrue(resultado);
    }

    @Test
    void numeroCeroPierde() {

        Ruleta ruleta =
                new Ruleta();

        assertFalse(
                ruleta.evaluarResultado(
                        0,
                        TipoApuesta.ROJO
                )
        );

        assertFalse(
                ruleta.evaluarResultado(
                        0,
                        TipoApuesta.NEGRO
                )
        );

        assertFalse(
                ruleta.evaluarResultado(
                        0,
                        TipoApuesta.PAR
                )
        );

        assertFalse(
                ruleta.evaluarResultado(
                        0,
                        TipoApuesta.IMPAR
                )
        );
    }

    @Test
    void giroEntregaNumeroEntreCeroYTreintaYSeis() {

        Ruleta ruleta =
                new Ruleta();

        int numero =
                ruleta.girarRuleta();

        assertTrue(
                numero >= 0 && numero <= 36
        );
    }

    @Test
    void noPermitirApuestaMayorAlSaldo() {

        Ruleta ruleta =
                new Ruleta(500);

        assertThrows(
                IllegalArgumentException.class,
                () -> ruleta.apostar(
                        TipoApuesta.ROJO,
                        1000
                )
        );
    }

    @Test
    void apuestaValidaModificaSaldo() {

        Ruleta ruleta =
                new Ruleta(1000);

        Resultado resultado =
                ruleta.apostar(
                        TipoApuesta.ROJO,
                        100
                );

        if (resultado.isAcierto()) {

            assertEquals(
                    1100,
                    ruleta.getSaldo()
            );

        } else {

            assertEquals(
                    900,
                    ruleta.getSaldo()
            );
        }
    }

    @Test
    void evitarQueSaldoSupereMaximoInt() {

        Ruleta ruleta =
                new Ruleta(Integer.MAX_VALUE);

        boolean resultado =
                ruleta.depositar(1);

        assertFalse(resultado);

        assertEquals(
                Integer.MAX_VALUE,
                ruleta.getSaldo()
        );
    }
}