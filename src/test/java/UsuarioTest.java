import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsuarioTest {

    @Test
    public void credencialesCorrectas() {

        Usuario usuario = new Usuario(
                "admin",
                "1234",
                "Administrador"
        );

        boolean resultado =
                usuario.validarCredenciales("admin", "1234");

        assertTrue(resultado);
    }

    @Test
    public void credencialesIncorrectas() {

        Usuario usuario = new Usuario(
                "admin",
                "1234",
                "Administrador"
        );

        boolean resultado =
                usuario.validarCredenciales("admin", "9999");

        assertFalse(resultado);
    }
}