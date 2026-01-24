import java.util.Scanner;

public class Main {
    // AQUÍ es donde deben ir las constantes para que todo el código las vea
    // (Esto es lo que te faltaba en la foto)
    static final double IVA = 0.16;
    static final double DESCUENTO = 0.10;
    static final double UMBRAL_DESCUENTO = 1000.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Pedimos el subtotal
        double subtotal = pedirDouble(scanner, "Subtotal: ");

        // 2. Calculamos con IVA (llamando al método de abajo)
        double totalConIva = calcularTotalConIva(subtotal);

        // 3. Aplicamos descuento si corresponde
        double totalFinal = aplicarDescuentoSiAplica(totalConIva, subtotal);

        System.out.printf("Total a pagar: %.2f%n", totalFinal);
    }

    // Método para pedir datos
    public static double pedirDouble(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextDouble();
    }

    // Método que calcula IVA
    public static double calcularTotalConIva(double subtotal) {
        return subtotal + (subtotal * IVA);
    }

    // Método que aplica descuento
    public static double aplicarDescuentoSiAplica(double total, double subtotalOriginal) {
        // Usamos la constante UMBRAL_DESCUENTO que declaramos arriba
        if (subtotalOriginal > UMBRAL_DESCUENTO) {
            return total - (total * DESCUENTO);
        }
        return total;
    }
}