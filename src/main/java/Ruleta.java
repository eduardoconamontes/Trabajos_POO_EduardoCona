import java.util.Random;
import java.util.Scanner;

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

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner in = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(in);
            ejecutarOpcion(opcion, in);
        } while (opcion != 3);
        in.close();
    }

    public static void mostrarMenu() {
        System.out.println("\nRULETA");
        System.out.println("1. Iniciar ronda");
        System.out.println("2. Ver estadisticas");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    public static int leerOpcion(Scanner in) {
        while (!in.hasNextInt()) {
            System.out.println("Opcion no valida.");
            in.next();
            System.out.print("Seleccione una opcion: ");
        }

        return in.nextInt();
    }

    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {
            case 1:
                iniciarRonda(in);
                break;
            case 2:
                mostrarEstadisticas();
                break;
            case 3:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    public static char leerTipoApuesta(Scanner in) {
        String entrada;

        do {
            System.out.print("Tipo de apuesta (R/N/P/I): ");
            entrada = in.next().toUpperCase();

            if (entrada.length() == 1) {
                char tipo = entrada.charAt(0);

                if (tipo == 'R' || tipo == 'N' || tipo == 'P' || tipo == 'I') {
                    return tipo;
                }
            }

            System.out.println("Tipo de apuesta no valido.");

        } while (true);
    }

    public static int girarRuleta() {
        return rng.nextInt(37);
    }

    public static boolean esRojo(int n) {
        for (int numeroRojo : numerosRojos) {
            if (n == numeroRojo) {
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

    public static void iniciarRonda(Scanner in) {
        char tipo = leerTipoApuesta(in);

        int monto;

        do {
            System.out.print("Ingrese monto a apostar: ");

            if (in.hasNextInt()) {
                monto = in.nextInt();

                if (monto <= 0) {
                    System.out.println("El monto debe ser mayor que 0.");
                }
            } else {
                System.out.println("Monto no valido.");
                in.next();
                monto = 0;
            }

        } while (monto <= 0);

        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);

        registrarResultado(numero, monto, acierto);
        mostrarResultado(numero, tipo, monto, acierto);
    }

    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }

    public static void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        System.out.println("Numero obtenido: " + numero);
        System.out.println("Tipo de apuesta: " + tipo);
        System.out.println("Monto apostado: " + monto);

        if (acierto) {
            System.out.println("Ganaste");
        } else {
            System.out.println("Perdiste");
        }
    }

    public static void mostrarEstadisticas() {
        int totalApostado = 0;
        int totalAciertos = 0;
        int gananciaNeta = 0;

        for (int i = 0; i < historialSize; i++) {
            totalApostado += historialApuestas[i];

            if (historialAciertos[i]) {
                totalAciertos++;
                gananciaNeta += historialApuestas[i];
            } else {
                gananciaNeta -= historialApuestas[i];
            }
        }

        double porcentajeAciertos = 0;

        if (historialSize > 0) {
            porcentajeAciertos = (totalAciertos * 100.0) / historialSize;
        }

        System.out.println("\nESTADISTICAS");
        System.out.println("Rondas jugadas: " + historialSize);
        System.out.println("Monto total apostado: " + totalApostado);
        System.out.println("Total de aciertos: " + totalAciertos);
        System.out.println("Porcentaje de aciertos: " + porcentajeAciertos + "%");
        System.out.println("Ganancia o perdida neta: " + gananciaNeta);
    }
}