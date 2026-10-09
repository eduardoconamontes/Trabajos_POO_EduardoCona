import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SessionControllerTest {

    @Test
    void iniciarSesionConUsuarioExistente() {

        SessionController session =
                new SessionController();

        boolean ingreso =
                session.iniciarSesion(
                        "admin",
                        "1234"
                );

        assertTrue(ingreso);
        assertTrue(session.hayUsuario());

        assertEquals(
                "Administrador",
                session.getNombreUsuario()
        );
    }

    @Test
    void rechazarInicioSesionIncorrecto() {

        SessionController session =
                new SessionController();

        boolean ingreso =
                session.iniciarSesion(
                        "admin",
                        "9999"
                );

        assertFalse(ingreso);
        assertFalse(session.hayUsuario());
    }

    @Test
    void registrarNuevoUsuario() {

        SessionController session =
                new SessionController();

        boolean registrado =
                session.registrarUsuario(
                        "eduardo",
                        "1234",
                        "Eduardo"
                );

        assertTrue(registrado);
    }

    @Test
    void iniciarSesionConUsuarioRegistrado() {

        SessionController session =
                new SessionController();

        session.registrarUsuario(
                "eduardo",
                "1234",
                "Eduardo"
        );

        boolean ingreso =
                session.iniciarSesion(
                        "eduardo",
                        "1234"
                );

        assertTrue(ingreso);

        assertEquals(
                "Eduardo",
                session.getNombreUsuario()
        );
    }

    @Test
    void rechazarRegistroConCamposVacios() {

        SessionController session =
                new SessionController();

        boolean registrado =
                session.registrarUsuario(
                        "",
                        "1234",
                        "Eduardo"
                );

        assertFalse(registrado);
    }

    @Test
    void cambiarNombreUsuarioActual() {

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        boolean cambiado =
                session.cambiarNombre(
                        "Nuevo Administrador"
                );

        assertTrue(cambiado);

        assertEquals(
                "Nuevo Administrador",
                session.getNombreUsuario()
        );
    }

    @Test
    void noCambiarNombreSinSesionIniciada() {

        SessionController session =
                new SessionController();

        boolean cambiado =
                session.cambiarNombre(
                        "Nuevo Nombre"
                );

        assertFalse(cambiado);
    }

    @Test
    void cerrarSesion() {

        SessionController session =
                new SessionController();

        session.iniciarSesion(
                "admin",
                "1234"
        );

        assertTrue(
                session.hayUsuario()
        );

        session.cerrarSesion();

        assertFalse(
                session.hayUsuario()
        );
    }
}