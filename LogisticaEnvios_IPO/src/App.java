import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        ShippingCalculator shipping1 = new ShippingCalculator();

        double peso = leerDoubleEnRango(sc, "Ingresa el peso (0.1 a 50.0):", 0.1, 50.0);
        int distancia = leerIntEnRango(sc, "Ingresa la distancia (1 a 2000):", 1, 2000);
        int servicio = leerIntEnRango(sc, "Tipo de servicio (1-Estandar, 2-Express):", 1, 2);
        boolean remoto = leerBoolean(sc, "Es zona remota? (true/false):");

        shipping1.process(peso, distancia, servicio, remoto);

        imprimirTicket(servicio, peso, distancia, remoto, shipping1.subtotal, shipping1.montoIva, shipping1.total);

    }

    public static double leerDoubleEnRango(Scanner sc, String msg, double min, double max) {
        double valor;
        while (true) {
            System.out.println(msg);
            if (sc.hasNextDouble()) {
                valor = sc.nextDouble();
                if (valor >= min && valor <= max) return valor;
                System.out.println("Dato fuera de rango");
            } else {
                System.out.println("Dato no numerico");
                sc.next();
            }
        }
    }

    public static int leerIntEnRango(Scanner sc, String msg, int min, int max) {
        int valor;
        while (true) {
            System.out.println(msg);
            if (sc.hasNextInt()) {
                valor = sc.nextInt();
                if (valor >= min && valor <= max) return valor;
                System.out.println("Dato fuera de rango");
            } else {
                System.out.println("Dato no numerico");
                sc.next();
            }
        }
    }

    public static boolean leerBoolean(Scanner sc, String msg) {
        while (true) {
            System.out.println(msg);
            String input = sc.next().toLowerCase();
            if (input.equals("true")) return true;
            if (input.equals("false")) return false;
            System.out.println("Solo acepta true/false");
        }
    }

    public static void imprimirTicket(int servicio, double peso, int dist, boolean remoto, double sub, double iva, double tot) {
        System.out.println("--- TICKET DE ENVIO ---");
        System.out.println("Servicio: " + (servicio == 1 ? "Estandar" : "Express"));
        System.out.println("Peso: " + peso);
        System.out.println("Distancia: " + dist);
        System.out.println("Zona Remota: " + remoto);
        System.out.println("Subtotal: " + sub);
        System.out.println("Iva: " + iva);
        System.out.println("Total: " + tot);
    }
}