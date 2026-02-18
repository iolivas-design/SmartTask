package aplicacion;
import java.util.Scanner;

public class App {
    private static GestorTareas gestor = new GestorTareas();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║   BIENVENIDO A SMARTTASK           ║");
        System.out.println("║   Gestor de Tareas Inteligente     ║");
        System.out.println("╚════════════════════════════════════╝\n");

        boolean ejecutando = true;
        while (ejecutando) {
            mostrarMenu();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    agregarTarea();
                    break;
                case 2:
                    listarTareas();
                    break;
                case 3:
                    marcarCompletada();
                    break;
                case 4:
                    eliminarTarea();
                    break;
                case 5:
                    System.out.println("\n¡Hasta luego! Gracias por usar SmartTask.");
                    ejecutando = false;
                    break;
                default:
                    System.out.println("✗ Opción no válida. Por favor, intente nuevamente.\n");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n========== MENÚ PRINCIPAL ==========");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Marcar tarea como completada");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Salir");
        System.out.println("====================================");
    }

    private static int leerOpcion() {
        System.out.print("Seleccione una opción: ");
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            return opcion;
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar el buffer en caso de error
            return -1;
        }
    }

    private static void agregarTarea() {
        System.out.println("\n========== AGREGAR TAREA ==========");
        System.out.print("Ingrese el nombre de la tarea: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la prioridad (1-3, donde 1 es baja y 3 es alta): ");
        int prioridad;
        try {
            prioridad = scanner.nextInt();
            scanner.nextLine();
            if (prioridad < 1 || prioridad > 3) {
                System.out.println("✗ Prioridad inválida. Se establecerá en 1.");
                prioridad = 1;
            }
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("✗ Entrada inválida. Prioridad establecida en 1.");
            prioridad = 1;
        }

        System.out.print("¿Es una tarea urgente? (s/n): ");
        String respuesta = scanner.nextLine().toLowerCase();
        boolean esUrgente = respuesta.equals("s") || respuesta.equals("sí");

        gestor.agregarTarea(nombre, prioridad, esUrgente);
    }

    private static void listarTareas() {
        if (gestor.obtenerCantidadTareas() == 0) {
            System.out.println("\n✗ No hay tareas registradas.");
        } else {
            gestor.listarTareas();
        }
    }

    private static void marcarCompletada() {
        System.out.println("\n========== MARCAR COMO COMPLETADA ==========");
        if (gestor.obtenerCantidadTareas() == 0) {
            System.out.println("✗ No hay tareas registradas.");
            return;
        }

        gestor.listarTareas();
        System.out.print("Ingrese el ID de la tarea a marcar como completada: ");
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            gestor.marcarCompletada(id);
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("✗ ID inválido.");
        }
    }

    private static void eliminarTarea() {
        System.out.println("\n========== ELIMINAR TAREA ==========");
        if (gestor.obtenerCantidadTareas() == 0) {
            System.out.println("✗ No hay tareas registradas.");
            return;
        }

        gestor.listarTareas();
        System.out.print("Ingrese el ID de la tarea a eliminar: ");
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            gestor.eliminarTarea(id);
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("✗ ID inválido.");
        }
    }
}
