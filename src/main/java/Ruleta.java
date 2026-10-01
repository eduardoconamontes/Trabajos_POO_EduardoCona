import java.util.Random;

public class Ruleta {

    public static final int MAX_HISTORIAL = 100;

    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];

    public static int historialSize = 0;

    public static Random rng = new Random();

    public static int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };

    public static int girarRuleta() {
        return rng.nextInt(37);
    }

    public static boolean esRojo(int numero) {

        for (int numeroRojo : numerosRojos) {

            if (numero == numeroRojo) {
                return true;
            }
        }

        return false;
    }

    public static boolean evaluarResultado(int numero, char tipo) {

        if (numero == 0) {
            return false;
        }

        switch (tipo) {
            case 'R':
                return esRojo(numero);

            case 'N':
                return !esRojo(numero);

            case 'P':
                return numero % 2 == 0;

            case 'I':
                return numero % 2 != 0;

            default:
                return false;
        }
    }

    public static void registrarResultado(int numero, int apuesta, boolean acierto) {

        if (historialSize < MAX_HISTORIAL) {

            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;

            historialSize++;
        }
    }

    public static int calcularTotalApostado() {

        int total = 0;

        for (int i = 0; i < historialSize; i++) {
            total += historialApuestas[i];
        }

        return total;
    }

    public static int calcularTotalAciertos() {

        int total = 0;

        for (int i = 0; i < historialSize; i++) {

            if (historialAciertos[i]) {
                total++;
            }
        }

        return total;
    }

    public static double calcularPorcentajeAciertos() {

        if (historialSize == 0) {
            return 0;
        }

        return (calcularTotalAciertos() * 100.0) / historialSize;
    }

    public static int calcularGananciaNeta() {

        int ganancia = 0;

        for (int i = 0; i < historialSize; i++) {

            if (historialAciertos[i]) {
                ganancia += historialApuestas[i];
            } else {
                ganancia -= historialApuestas[i];
            }
        }

        return ganancia;
    }
}