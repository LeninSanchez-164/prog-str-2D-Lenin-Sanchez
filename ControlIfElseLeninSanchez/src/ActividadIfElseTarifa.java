import java.util.Scanner;

public class ActividadIfElseTarifa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int edad;
        boolean esEstudiante;
        double tarifa = 0;

        System.out.println("--- Sistema de Tarifas UTEZ ---");


        System.out.print("Ingresa tu edad: ");
        edad = sc.nextInt();


        if (edad < 0 || edad > 120) {
            System.out.println("Edad inválida");

            return;
        }

        System.out.print("¿Es estudiante? (true/false): ");
        esEstudiante = sc.nextBoolean();


        if (edad < 12) {
            tarifa = 50;
        } else if (edad >= 12 && edad <= 17) {
            if (esEstudiante) {
                tarifa = 60;
            } else {
                tarifa = 80;
            }
        } else {
            if (esEstudiante) {
                tarifa = 90;
            } else {
                tarifa = 120;
            }
        }


        System.out.println("\n--- Resumen de Cobro ---");
        System.out.println("Edad ingresada: " + edad);

        System.out.println("Es estudiante: " + (esEstudiante ? "Sí" : "No"));
        System.out.println("Tarifa final: $" + tarifa);

        sc.close();
    }
}
