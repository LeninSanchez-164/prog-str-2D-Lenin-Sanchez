import java.util.Scanner;

public class App {

    static Alumno[] alumnos = new Alumno[25];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = -1;

        do {
            System.out.println("\n- SISTEMA DE ALUMNOS -");
            System.out.println("1) Alta");
            System.out.println("2) Buscar (Activos)");
            System.out.println("3) Actualizar Promedio");
            System.out.println("4) Baja Logica");
            System.out.println("5) Listar Activos");
            System.out.println("6) Reportes");
            System.out.println("0) Salir");
            System.out.print("Seleccione: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1: alta(); break;
                    case 2: buscar(); break;
                    case 3: actualizar(); break;
                    case 4: baja(); break;
                    case 5: listar(); break;
                    case 6: reportes(); break;
                    case 0: System.out.println("Saliendo..."); break;
                    default: System.out.println("Opcion no valida.");
                }
            } else {
                System.out.println("Error: Ingrese un numero.");
                scanner.next();
            }
        } while (opcion != 0);
    }

    public static void alta() {
        int pos = -1;
        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] == null) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            System.out.println("Arreglo lleno.");
            return;
        }

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id <= 0 || existeId(id)) {
            System.out.println("ID no valido o repetido.");
            return;
        }

        System.out.print("Nombre: ");
        String nom = scanner.nextLine();

        System.out.print("Promedio: ");
        double prom = scanner.nextDouble();

        if (prom >= 0 && prom <= 10 && !nom.isEmpty()) {
            alumnos[pos] = new Alumno(id, nom, prom);
            System.out.println("Registrado.");
        } else {
            System.out.println("Datos invalidos.");
        }
    }

    public static void buscar() {
        System.out.print("ID a buscar: ");
        int id = scanner.nextInt();
        boolean found = false;

        for (Alumno a : alumnos) {
            if (a != null && a.getId() == id && a.isActivo()) {
                System.out.println(a.toString());
                found = true;
                break;
            }
        }
        if (!found) System.out.println("No encontrado.");
    }

    public static void actualizar() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        for (Alumno a : alumnos) {
            if (a != null && a.getId() == id && a.isActivo()) {
                System.out.print("Nuevo promedio: ");
                double np = scanner.nextDouble();
                if (np >= 0 && np <= 10) a.setPromedio(np);
                return;
            }
        }
        System.out.println("No encontrado.");
    }

    public static void baja() {
        System.out.print("ID para baja: ");
        int id = scanner.nextInt();
        for (Alumno a : alumnos) {
            if (a != null && a.getId() == id) {
                a.setActivo(false);
                System.out.println("Baja aplicada.");
                return;
            }
        }
    }

    public static void listar() {
        System.out.println("--- ACTIVOS ---");
        for (Alumno a : alumnos) {
            if (a != null && a.isActivo()) {
                System.out.println(a.toString());
            }
        }
    }

    public static void reportes() {
        double suma = 0;
        int cont = 0;
        int m8 = 0;
        Alumno max = null;
        Alumno min = null;

        for (Alumno a : alumnos) {
            if (a != null && a.isActivo()) {
                suma += a.getPromedio();
                cont++;
                if (a.getPromedio() >= 8) m8++;
                if (max == null || a.getPromedio() > max.getPromedio()) max = a;
                if (min == null || a.getPromedio() < min.getPromedio()) min = a;
            }
        }

        if (cont > 0) {
            System.out.println("Promedio Gral: " + (suma / cont));
            System.out.println("Mayor: " + max.getNombre() + " (" + max.getPromedio() + ")");
            System.out.println("Menor: " + min.getNombre() + " (" + min.getPromedio() + ")");
            System.out.println("Promedios >= 8: " + m8);
        }
    }

    public static boolean existeId(int id) {
        for (Alumno a : alumnos) {
            if (a != null && a.getId() == id) return true;
        }
        return false;
    }
}