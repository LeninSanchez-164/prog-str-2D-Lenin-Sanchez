import java.util.Random;
import java.util.Scanner;

public class App {

    static int contadorFueraRango = 0;
    static int contadorNoNumerico = 0;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int min = 1;
        int max = 100;
        int secreto = random.nextInt(max) + 1;
        int intentos = 0;
        int limiteIntentos = 7;
        boolean gano = false;

        System.out.println("--- Juego: Adivina el Número Secreto (1-100) ---");


        while (intentos < limiteIntentos) {
            int numero = obtenerNumeroValido(sc, "Intento " + (intentos + 1) + ": ", min, max);
            intentos++;

            if (numero == secreto) {
                System.out.println("¡Ganaste!");
                gano = true;
                break;
            } else if (numero > secreto) {
                System.out.println("El número secreto es menor.");
            } else {
                System.out.println("El número secreto es mayor.");
            }
        }

        if (!gano) {
            System.out.println("Perdiste. El número secreto era: " + secreto);
        }


        System.out.println("\n--- Estadísticas de validación ---");
        System.out.println("Ingresos fuera de rango: " + contadorFueraRango);
        System.out.println("Ingresos no numéricos: " + contadorNoNumerico);
    }

    public static int obtenerNumeroValido(Scanner sc, String mensaje, int min, int max) {
        int valor;
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextInt()) {
                valor = sc.nextInt();
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("Error: El número debe estar entre " + min + " y " + max);
                contadorFueraRango++;
            } else {
                System.out.println("Error: Debes ingresar un número válido.");
                contadorNoNumerico++;
                sc.next();
            }
        }
    }
}