public class RuletaController {

    private Ruleta ruleta;
    private ResultadoController resultadoController;

    public RuletaController() {

        ruleta = new Ruleta();
        resultadoController = new ResultadoController();
    }

    public RuletaController(int saldoInicial) {

        ruleta = new Ruleta(saldoInicial);
        resultadoController = new ResultadoController();
    }

    public Resultado realizarApuesta(
            TipoApuesta tipo,
            int monto) {

        Resultado resultado =
                ruleta.apostar(tipo, monto);

        resultadoController.registrarResultado(resultado);

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