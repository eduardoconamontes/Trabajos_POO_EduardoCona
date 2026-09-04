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
