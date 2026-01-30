import java.util.Scanner;

public class conversor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        // Contadores para cada tipo de conversión
        int contCtoF = 0;
        int contFtoC = 0;
        int contKmToMi = 0;
        int contMiToKm = 0;

        do {
            System.out.println("\n--- MENÚ DE CONVERSIONES ---");
            System.out.println("1) °C a °F");
            System.out.println("2) °F a °C");
            System.out.println("3) Km a Millas");
            System.out.println("4) Millas a Km");
            System.out.println("5) Salir");
            System.out.print("Elige una opción: ");


            if (sc.hasNextInt()) {
                opcion = sc.nextInt();

                // Procesar la opción
                switch (opcion) {
                    case 1:
                        double c = pedirValor(sc, "Ingresa los grados Centígrados: ");
                        double f = (c * 9/5) + 32;
                        System.out.printf("%.2f °C equivalen a %.2f °F%n", c, f);
                        contCtoF++;
                        break;
                    case 2:
                        double far = pedirValor(sc, "Ingresa los grados Fahrenheit: ");
                        double cel = (far - 32) * 5/9;
                        System.out.printf("%.2f °F equivalen a %.2f °C%n", far, cel);
                        contFtoC++;
                        break;
                    case 3:
                        double km = pedirValor(sc, "Ingresa los Kilómetros: ");
                        double mi = km * 0.621371;
                        System.out.printf("%.2f Km equivalen a %.2f Millas%n", km, mi);
                        contKmToMi++;
                        break;
                    case 4:
                        double millas = pedirValor(sc, "Ingresa las Millas: ");
                        double kms = millas * 1.60934;
                        System.out.printf("%.2f Millas equivalen a %.2f Km%n", millas, kms);
                        contMiToKm++;
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Error: Por favor elige un número entre 1 y 5.");
                }
            } else {
                System.out.println("Error: Debes ingresar un número válido.");
                sc.next(); // Limpiar entrada incorrecta
                opcion = 0; // Asignar 0 para que el bucle continúe
            }

        } while (opcion != 5);

        // Resumen final al salir
        int totalConversiones = contCtoF + contFtoC + contKmToMi + contMiToKm;

        System.out.println("\n================ RESUMEN ================");
        System.out.println("Total de conversiones realizadas: " + totalConversiones);
        System.out.println(" - °C a °F: " + contCtoF);
        System.out.println(" - °F a °C: " + contFtoC);
        System.out.println(" - Km a Millas: " + contKmToMi);
        System.out.println(" - Millas a Km: " + contMiToKm);
        System.out.println("=========================================");
    }

    // Método auxiliar para pedir valores numericos sin que el programa truene
    public static double pedirValor(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            } else {
                System.out.println("Error: Dato no numérico. Intenta de nuevo.");
                sc.next();
            }
        }
    }
}