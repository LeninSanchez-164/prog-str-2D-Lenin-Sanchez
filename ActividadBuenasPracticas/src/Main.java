import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int limite = pedirEntero();
        int resultado = sumarHastaN(limite);
        System.out.println("La suma de los números del 1 al " + limite + " es: " + resultado);
    }

    public static int pedirEntero() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, introduce un número entero: ");
        int numero = scanner.nextInt();
        return numero;
    }

    public static int sumarHastaN(int numeroLimite) {
        int sumaTotal = 0;


        for (int i = 1; i <= numeroLimite; i++) {
            sumaTotal = sumaTotal + i;
        }

        return sumaTotal;
    }
}