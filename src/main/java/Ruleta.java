import java.util.Random;

public class Ruleta {

    private static final int[] NUMEROS_ROJOS = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };

    private int saldo;
    private Random rng;

    public Ruleta() {
        this(0);
    }

    public Ruleta(int saldoInicial) {

        if (saldoInicial >= 0) {
            this.saldo = saldoInicial;
        } else {
            this.saldo = 0;
        }

        this.rng = new Random();
    }

    public int getSaldo() {
        return saldo;
    }

    public boolean depositar(int monto) {

        if (monto <= 0) {
            return false;
        }

        saldo += monto;
        return true;
    }

    public int girarRuleta() {
        return rng.nextInt(37);
    }

    public boolean esRojo(int numero) {

        for (int numeroRojo : NUMEROS_ROJOS) {

            if (numero == numeroRojo) {
                return true;
            }
        }

        return false;
    }

    public boolean evaluarResultado(int numero, TipoApuesta tipo) {

        if (numero == 0 || tipo == null) {
            return false;
        }

        switch (tipo) {

            case ROJO:
                return esRojo(numero);

            case NEGRO:
                return !esRojo(numero);

            case PAR:
                return numero % 2 == 0;

            case IMPAR:
                return numero % 2 != 0;

            default:
                return false;
        }
    }

    public Resultado apostar(TipoApuesta tipo, int monto) {

        if (tipo == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un tipo de apuesta"
            );
        }

        if (monto <= 0) {
            throw new IllegalArgumentException(
                    "El monto debe ser mayor que cero"
            );
        }

        if (monto > saldo) {
            throw new IllegalArgumentException(
                    "Saldo insuficiente"
            );
        }

        int numero = girarRuleta();

        boolean acierto =
                evaluarResultado(numero, tipo);

        if (acierto) {
            saldo += monto;
        } else {
            saldo -= monto;
        }

        return new Resultado(
                numero,
                tipo,
                monto,
                acierto
        );
    }
}