import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String username;
    private String password;
    private String nombre;

    private List<Resultado> historial;

    public Usuario(
            String username,
            String password,
            String nombre) {

        this.username = username;
        this.password = password;
        this.nombre = nombre;

        this.historial = new ArrayList<>();
    }

    public Usuario() {

        this(
                "invitado",
                "",
                "Invitado"
        );
    }

    public boolean validarCredenciales(
            String usuario,
            String clave) {

        return username.equals(usuario)
                && password.equals(clave);
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        if (nombre != null
                && !nombre.isBlank()) {

            this.nombre = nombre;
        }
    }

    public void agregarResultado(
            Resultado resultado) {

        if (resultado != null) {
            historial.add(resultado);
        }
    }

    public List<Resultado> getHistorial() {

        return new ArrayList<>(historial);
    }
}