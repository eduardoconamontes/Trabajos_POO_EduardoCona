import java.util.Random;
import java.util.Scanner;

public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int [MAX_HISTORIAL];
    public static int[] historialApuestas = new int [MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean [MAX_HISTORIAL];

    public static int historialSize = 0;

    public static Random rng = new Random();

    public static int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 30, 32, 34, 36
    };
}

public static void main (String[] args){
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

public static void mostrarMenu(){
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
