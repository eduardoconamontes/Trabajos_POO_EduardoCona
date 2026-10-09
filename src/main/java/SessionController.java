import java.util.ArrayList;
import java.util.List;

public class SessionController {

    private List<Usuario> usuarios;
    private Usuario usuarioActual;

    public SessionController() {

        usuarios = new ArrayList<>();

        usuarios.add(
                new Usuario(
                        "admin",
                        "1234",
                        "Administrador"
                )
        );

        usuarios.add(
                new Usuario(
                        "jugador",
                        "1234",
                        "Jugador"
                )
        );

        usuarioActual = null;
    }

    public boolean registrarUsuario(
            String username,
            String clave,
            String nombre) {

        if (username == null || username.isBlank()
                || clave == null || clave.isBlank()
                || nombre == null || nombre.isBlank()) {

            return false;
        }

        Usuario nuevoUsuario =
                new Usuario(username, clave, nombre);

        usuarios.add(nuevoUsuario);

        return true;
    }

    public boolean iniciarSesion(
            String username,
            String clave) {

        for (int i = 0; i < usuarios.size(); i++) {

            Usuario usuario = usuarios.get(i);

            if (usuario.validarCredenciales(username, clave)) {

                usuarioActual = usuario;

                return true;
            }
        }

        return false;
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public String getNombreUsuario() {

        if (hayUsuario()) {
            return usuarioActual.getNombre();
        }

        return "";
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public boolean cambiarNombre(String nombre) {

        if (!hayUsuario()
                || nombre == null
                || nombre.isBlank()) {

            return false;
        }

        usuarioActual.setNombre(nombre);

        return true;
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }
}