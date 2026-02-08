import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GradeService service = new GradeService();

        String nombre = leerTextoNoVacio(sc, "Nombre del alumno:");
        double p1 = leerDoubleEnRango(sc, "Calificacion Parcial 1 (0-100):", 0, 100);
        double p2 = leerDoubleEnRango(sc, "Calificacion Parcial 2 (0-100):", 0, 100);
        double p3 = leerDoubleEnRango(sc, "Calificacion Parcial 3 (0-100):", 0, 100);
        int asistencia = leerIntEnRango(sc, "Porcentaje de asistencia (0-100):", 0, 100);
        boolean proyecto = leerBoolean(sc, "Entrego proyecto? (true/false):");

        double promedio = service.calcularPromedio(p1, p2, p3);
        double calFinal = service.calcularFinal(promedio, asistencia);
        String estado = service.determinarEstado(calFinal, asistencia, proyecto);

        imprimirReporte(nombre, p1, p2, p3, promedio, asistencia, proyecto, calFinal, estado);
    }

    public static String leerTextoNoVacio(Scanner sc, String msg) {
        System.out.println(msg);
        String texto = sc.next(); // Usamos next() para evitar problemas con buffers
        return texto;
    }

    public static double leerDoubleEnRango(Scanner sc, String msg, double min, double max) {
        double valor;
        while (true) {
            System.out.println(msg);
            if (sc.hasNextDouble()) {
                valor = sc.nextDouble();
                if (valor >= min && valor <= max) return valor;
                System.out.println("Dato fuera de rango.");
            } else {
                System.out.println("Dato no numerico.");
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
                System.out.println("Dato fuera de rango.");
            } else {
                System.out.println("Dato no numerico.");
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
            System.out.println("Solo acepta true/false.");
        }
    }

    public static void imprimirReporte(String nom, double p1, double p2, double p3, double prom, int asis, boolean proy, double fin, String est) {
        System.out.println("\n--- REPORTE FINAL ---");
        System.out.println("Alumno: " + nom);
        System.out.println("Promedio Parciales: " + prom);
        System.out.println("Asistencia: " + asis + "%");
        System.out.println("Estado: " + est);
        System.out.println("Calificacion Final: " + fin);
    }
}