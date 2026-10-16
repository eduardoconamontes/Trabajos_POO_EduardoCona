package controlador;

import modelo.Resultado;
import modelo.Ruleta;
import modelo.TipoApuesta;
import modelo.Usuario;

public class RuletaController {

    private final Ruleta ruleta;
    private final SessionController session;
    private final ResultadoController resultadoController;

    public RuletaController(SessionController session) {

        this(
                new Ruleta(),
                session
        );
    }

    public RuletaController(
            int saldoInicial,
            SessionController session) {

        this(
                new Ruleta(saldoInicial),
                session
        );
    }

    public RuletaController(
            Ruleta ruleta,
            SessionController session) {

        this.ruleta = ruleta;
        this.session = session;

        this.resultadoController =
                new ResultadoController(session);
    }

    public Resultado realizarApuesta(
            TipoApuesta tipo,
            int monto) {

        if (!session.hayUsuario()) {

            throw new IllegalStateException(
                    "No existe un usuario con sesión iniciada"
            );
        }

        Resultado resultado =
                ruleta.apostar(
                        tipo,
                        monto
                );

        Usuario usuarioActual =
                session.getUsuarioActual();

        usuarioActual.agregarResultado(
                resultado
        );

        return resultado;
    }

    public boolean depositar(int monto) {
        return ruleta.depositar(monto);
    }

    public int getSaldo() {
        return ruleta.getSaldo();
    }

    public ResultadoController getResultadoController() {
        return resultadoController;
    }
}