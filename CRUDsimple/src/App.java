import java.util.Scanner;

public class App {

    static Persona[] personas = new Persona[20];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = -1;

        do {
            System.out.println("\n--- MENÚ DE GESTIÓN DE PERSONAS ---");
            System.out.println("1) Alta");
            System.out.println("2) Buscar por ID (solo activas)");
            System.out.println("3) Baja lógica por ID");
            System.out.println("4) Listar activas");
            System.out.println("5) Actualizar nombre por ID (solo activas)");
            System.out.println("0) Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1: alta(); break;
                    case 2: buscar(); break;
                    case 3: bajaLogica(); break;
                    case 4: listarActivas(); break;
                    case 5: actualizarNombre(); break;
                    case 0: System.out.println("Saliendo del sistema..."); break;
                    default: System.out.println(">> Error: Opción inválida. Intente de nuevo.");
                }
            } else {
                System.out.println(">> Error: Debe ingresar un número entero.");
                scanner.next();
            }

        } while (opcion != 0);
    }

    public static void alta() {
        int indice = -1;
        for (int i = 0; i < personas.length; i++) {
            if (personas[i] == null) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            System.out.println(">> Error: El arreglo está lleno (Ya hay 20 personas).");
            return;
        }

        int id = 0;
        while (true) {
            System.out.print("Ingresa el ID (mayor a 0): ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine();

                if (id <= 0) {
                    System.out.println(">> Error: El ID debe ser positivo.");
                } else if (existeId(id)) {
                    System.out.println(">> Error: Ese ID ya existe. Intenta con otro.");
                } else {
                    break;
                }
            } else {
                System.out.println(">> Error: Ingrese un número válido.");
                scanner.next();
            }
        }

        String nombre = "";
        while (true) {
            System.out.print("Ingresa el Nombre: ");
            nombre = scanner.nextLine();
            if (!nombre.trim().isEmpty()) {
                break;
            }
            System.out.println(">> Error: El nombre no puede estar vacío.");
        }

        personas[indice] = new Persona(id, nombre);
        System.out.println(">> Alta exitosa. Persona registrada.");
    }

    public static void buscar() {
        System.out.print("Ingresa ID a buscar: ");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();
            boolean encontrada = false;

            for (Persona p : personas) {
                if (p != null && p.getId() == id && p.isActiva()) {
                    System.out.println("Resultado: " + p.toString());
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) System.out.println(">> Persona no encontrada o inactiva.");
        } else {
            System.out.println(">> Error: ID inválido.");
            scanner.next();
        }
    }

    public static void bajaLogica() {
        System.out.print("Ingresa ID para dar de baja: ");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();
            boolean encontrada = false;

            for (Persona p : personas) {
                if (p != null && p.getId() == id) {
                    if (p.isActiva()) {
                        p.setActiva(false);
                        System.out.println(">> Baja lógica realizada correctamente.");
                    } else {
                        System.out.println(">> Esa persona ya estaba inactiva.");
                    }
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) System.out.println(">> No existe ninguna persona con ese ID.");
        } else {
            System.out.println(">> Error: ID inválido.");
            scanner.next();
        }
    }

    public static void listarActivas() {
        System.out.println("--- LISTA DE PERSONAS ACTIVAS ---");
        boolean hayRegistros = false;
        for (Persona p : personas) {
            if (p != null && p.isActiva()) {
                System.out.println(p.toString());
                hayRegistros = true;
            }
        }
        if (!hayRegistros) System.out.println("(No hay personas activas registradas)");
    }

    public static void actualizarNombre() {
        System.out.print("Ingresa el ID de la persona a editar: ");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            scanner.nextLine();
            boolean encontrada = false;

            for (Persona p : personas) {
                if (p != null && p.getId() == id && p.isActiva()) {
                    System.out.println("Nombre actual: " + p.getNombre());

                    String nuevoNombre = "";
                    while (true) {
                        System.out.print("Ingresa el nuevo nombre: ");
                        nuevoNombre = scanner.nextLine();
                        if (!nuevoNombre.trim().isEmpty()) break;
                        System.out.println(">> Error: El nombre no puede estar vacío.");
                    }

                    p.setNombre(nuevoNombre);
                    System.out.println(">> Nombre actualizado correctamente.");
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) System.out.println(">> Persona no encontrada o inactiva.");
        } else {
            System.out.println(">> Error: ID inválido.");
            scanner.next();
        }
    }

    public static boolean existeId(int id) {
        for (Persona p : personas) {
            if (p != null && p.getId() == id) {
                return true;
            }
        }
        return false;
    }
}