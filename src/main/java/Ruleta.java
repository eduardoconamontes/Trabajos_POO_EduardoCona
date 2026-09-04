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
        return in.nextInt();
    }

    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {
            case 1:
                System.out.println("Iniciar ronda");
                break;
            case 2:
                System.out.println("Ver estadisticas");
                break;
            case 3:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }
    public static char leerTipoApuesta(Scanner in) {
        char tipo;

        do {
            System.out.print("Tipo de apuesta (R/N/P/I): ");
            tipo = in.next().toUpperCase().charAt(0);

            if (tipo != 'R' && tipo != 'N' && tipo != 'P' && tipo != 'I') {
                System.out.println("Tipo de apuesta no valido.");
            }

        } while (tipo != 'R' && tipo != 'N' && tipo != 'P' && tipo != 'I');

        return tipo;
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


















}