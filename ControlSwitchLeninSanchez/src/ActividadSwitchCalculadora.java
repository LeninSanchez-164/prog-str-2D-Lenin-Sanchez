import java.util.Scanner;

public class ActividadSwitchCalculadora {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int opcion;
        double a, b;
        double resultado = 0;
        String nombreOperacion = "";
        boolean mostrarResultado = true;


        System.out.println("--- Calculadora Switch UTEZ ---");
        System.out.println("1) Sumar");
        System.out.println("2) Restar");
        System.out.println("3) Multiplicar");
        System.out.println("4) Dividir");


        System.out.print("Elige una opción: ");
        opcion = sc.nextInt();

        System.out.print("Ingresa el valor de a: ");
        a = sc.nextDouble();
        System.out.print("Ingresa el valor de b: ");
        b = sc.nextDouble();

        System.out.println("\n--- Procesando ---");


        switch (opcion) {
            case 1:
                resultado = a + b;
                nombreOperacion = "Suma";
                break;
            case 2:
                resultado = a - b;
                nombreOperacion = "Resta";
                break;
            case 3:
                resultado = a * b;
                nombreOperacion = "Multiplicación";
                break;
            case 4:
                nombreOperacion = "División";

                if (b == 0) {
                    System.out.println("No se puede dividir entre cero");
                    mostrarResultado = false;
                } else {
                    resultado = a / b;
                }
                break;
            default:
                System.out.println("Opción inválida");
                mostrarResultado = false;
                break;
        }


        if (mostrarResultado) {
            System.out.println("Operación elegida: " + nombreOperacion);
            System.out.println("Valores ingresados: a=" + a + ", b=" + b);
            System.out.println("Resultado: " + resultado);
        }

        sc.close();
    }
}
