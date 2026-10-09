import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void crearUsuarioConDatos() {

        Usuario usuario =
                new Usuario(
                        "eduardo",
                        "1234",
                        "Eduardo"
                );

        assertEquals(
                "eduardo",
                usuario.getUsername()
        );

        assertEquals(
                "Eduardo",
                usuario.getNombre()
        );
    }

    @Test
    void validarCredencialesCorrectas() {

        Usuario usuario =
                new Usuario(
                        "eduardo",
                        "1234",
                        "Eduardo"
                );

        boolean resultado =
                usuario.validarCredenciales(
                        "eduardo",
                        "1234"
                );

        assertTrue(resultado);
    }

    @Test
    void rechazarCredencialesIncorrectas() {

        Usuario usuario =
                new Usuario(
                        "eduardo",
                        "1234",
                        "Eduardo"
                );

        boolean resultado =
                usuario.validarCredenciales(
                        "eduardo",
                        "9999"
                );

        assertFalse(resultado);
    }

    @Test
    void constructorPorDefectoCreaInvitado() {

        Usuario usuario =
                new Usuario();

        assertEquals(
                "invitado",
                usuario.getUsername()
        );

        assertEquals(
                "Invitado",
                usuario.getNombre()
        );
    }

    @Test
    void cambiarNombreValido() {

        Usuario usuario =
                new Usuario(
                        "eduardo",
                        "1234",
                        "Eduardo"
                );

        usuario.setNombre("Eduardo Cona");

        assertEquals(
                "Eduardo Cona",
                usuario.getNombre()
        );
    }

    @Test
    void noCambiarNombrePorTextoVacio() {

        Usuario usuario =
                new Usuario(
                        "eduardo",
                        "1234",
                        "Eduardo"
                );

        usuario.setNombre("");

        assertEquals(
                "Eduardo",
                usuario.getNombre()
        );
    }
}