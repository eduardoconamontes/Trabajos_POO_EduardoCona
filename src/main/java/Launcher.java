public class Launcher {

    public static void main(String[] args) {

        SessionController session = new SessionController();

        VentanaLogin ventanaLogin =
                new VentanaLogin(session);

        ventanaLogin.mostrarVentana();
    }
}