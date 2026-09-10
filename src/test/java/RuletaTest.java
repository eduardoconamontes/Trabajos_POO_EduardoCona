import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuletaTest {

    @Test
    public void numeroRojo() {

        boolean resultado = Ruleta.esRojo(7);

        assertTrue(resultado);
    }

    @Test
    public void apuestaParCorrecta() {

        boolean resultado = Ruleta.evaluarResultado(8, 'P');

        assertTrue(resultado);
    }
}