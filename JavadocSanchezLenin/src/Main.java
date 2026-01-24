import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("\n- MENU DE OPERACIONES -");
            System.out.println("1. Calcular IMC");
            System.out.println("2. Calcular area de un rectangulo");
            System.out.println("3. Convertir °C a °F");
            System.out.println("4. Calcular área de un circulo");
            System.out.println("5. Salir");
            System.out.print("Elige una opcion: ");


            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
            } else {
                opcion = 0;
                scanner.next();
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa tu peso en kg: ");
                    double peso = scanner.nextDouble();
                    System.out.print("Ingresa tu altura en metros (ejemplo. 1.75): ");
                    double altura = scanner.nextDouble();
                    double imc = calcularIMC(peso, altura);
                    System.out.printf("Tu IMC es: %.2f%n", imc);
                    break;

                case 2:
                    System.out.print("Ingresa la base del rectangulo: ");
                    double base = scanner.nextDouble();
                    System.out.print("Ingresa la altura del rectangulo: ");
                    double alturaRect = scanner.nextDouble();
                    double areaRect = calcularAreaRectangulo(base, alturaRect);
                    System.out.println("El area del rectangulo es: " + areaRect);
                    break;

                case 3:
                    System.out.print("Ingresa los grados Celsius: ");
                    double celsius = scanner.nextDouble();
                    double fahrenheit = convertirCelsiusAFahrenheit(celsius);
                    System.out.println(celsius + "°C equivalen a " + fahrenheit + "°F");
                    break;

                case 4:
                    System.out.print("Ingresa el radio del circulo: ");
                    double radio = scanner.nextDouble();
                    double areaCirculo = calcularAreaCirculo(radio);
                    System.out.printf("El area del circulo es: %.4f%n", areaCirculo);
                    break;

                case 5:
                    System.out.println("Saliendo del programa... ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción inválida. Por favor intenta de nuevo.");
            }

        } while (opcion != 5);

        scanner.close();
    }

    /**
     * Calcula el Indice de Masa Corporal (IMC) basado en peso y altura.
     * Fórmula: peso / (altura * altura).
     *
     * @param peso   El peso de la persona en kilogramos.
     * @param altura La altura de la persona en metros.
     * @return El valor calculado del IMC.
     */
    public static double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    /**
     * Calcula el área de un rectangulo dada su base y altura.
     * Fórmula: base * altura.
     *
     * @param base   La longitud de la base del rectangulo.
     * @param altura La longitud de la altura del rectangulo.
     * @return El area total del rectangulo.
     */
    public static double calcularAreaRectangulo(double base, double altura) {
        return base * altura;
    }

    /**
     * Convierte una temperatura de grados Celsius a Fahrenheit.
     * Formula: (C * 1.8) + 32.
     *
     * @param celsius La temperatura en grados Celsius.
     * @return La temperatura equivalente en grados Fahrenheit.
     */
    public static double convertirCelsiusAFahrenheit(double celsius) {
        return (celsius * 1.8) + 32;
    }

    /**
     * Calcula el area de un circulo dado su radio.
     * Fórmula: PI * radio^2.
     *
     * @param radio El radio del circulo.
     * @return El area calculada del circulo.
     */
    public static double calcularAreaCirculo(double radio) {
        return Math.PI * Math.pow(radio, 2);
    }
}