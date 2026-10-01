import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RuletaTest {

    @BeforeEach
    public void reiniciarHistorial() {
        Ruleta.historialSize = 0;
    }

    @Test
    public void numeroRojo() {

        boolean resultado = Ruleta.esRojo(7);

        assertTrue(resultado);
    }

    @Test
    public void numeroNegro() {

        boolean resultado = Ruleta.esRojo(8);

        assertFalse(resultado);
    }

    @Test
    public void apuestaParCorrecta() {

        boolean resultado = Ruleta.evaluarResultado(8, 'P');

        assertTrue(resultado);
    }

    @Test
    public void apuestaImparCorrecta() {

        boolean resultado = Ruleta.evaluarResultado(7, 'I');

        assertTrue(resultado);
    }

    @Test
    public void ceroPierdeApuesta() {

        boolean resultado = Ruleta.evaluarResultado(0, 'R');

        assertFalse(resultado);
    }

    @Test
    public void registrarResultado() {

        Ruleta.registrarResultado(7, 1000, true);

        assertEquals(1, Ruleta.historialSize);
        assertEquals(7, Ruleta.historialNumeros[0]);
        assertEquals(1000, Ruleta.historialApuestas[0]);
        assertTrue(Ruleta.historialAciertos[0]);
    }

    @Test
    public void calcularEstadisticas() {

        Ruleta.registrarResultado(7, 1000, true);
        Ruleta.registrarResultado(8, 500, false);

        assertEquals(1500, Ruleta.calcularTotalApostado());
        assertEquals(1, Ruleta.calcularTotalAciertos());
        assertEquals(50.0, Ruleta.calcularPorcentajeAciertos());
        assertEquals(500, Ruleta.calcularGananciaNeta());
    }
}