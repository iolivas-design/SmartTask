package aplicacion;
import java.util.Scanner;

/**
 * Clase principal de la aplicación SmartTask.
 * Proporciona una interfaz interactiva de menú para gestionar tareas.
 * Permite agregar, listar, marcar como completadas y eliminar tareas mediante entrada de usuario.
 * 
 * @author SmartTask Team
 * @version 1.0
 */
public class App {
    private static GestorTareas gestor = new GestorTareas();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que inicia la aplicación SmartTask.
     * Muestra un menú interactivo que permite al usuario gestionar tareas.
     * El programa continúa ejecutándose hasta que el usuario selecciona la opción de salir.
     * 
     * @param args argumentos de línea de comandos (no utilizados)
     */
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

    /**
     * Muestra el menú principal con las opciones disponibles al usuario.
     * Las opciones permitidas son: agregar, listar, marcar completada, eliminar y salir.
     */
    private static void mostrarMenu() {
        System.out.println("\n========== MENÚ PRINCIPAL ==========");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Marcar tarea como completada");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Salir");
        System.out.println("====================================");
    }

    /**
     * Lee la opción ingresada por el usuario desde la entrada estándar.
     * Maneja excepciones si el usuario ingresa un valor no numérico.
     * 
     * @return la opción seleccionada por el usuario, o -1 si la entrada es inválida
     */
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

    /**
     * Permite al usuario agregar una nueva tarea al sistema.
     * Solicita el nombre, prioridad y si es urgente, luego crea la tarea.
     * Valida la prioridad (debe estar entre 1 y 3).
     */
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

    /**
     * Muestra todas las tareas registradas en el sistema.
     * Si no hay tareas registradas, muestra un mensaje informativo.
     */
    private static void listarTareas() {
        if (gestor.obtenerCantidadTareas() == 0) {
            System.out.println("\n✗ No hay tareas registradas.");
        } else {
            gestor.listarTareas();
        }
    }

    /**
     * Marca una tarea como completada solicitando su ID al usuario.
     * Primero muestra la lista de tareas y luego solicita el ID de la tarea a completar.
     * Valida la entrada del usuario y maneja excepciones.
     */
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

    /**
     * Elimina una tarea del sistema solicitando su ID al usuario.
     * Primero muestra la lista de tareas y luego solicita el ID de la tarea a eliminar.
     * Valida la entrada del usuario y maneja excepciones.
     */
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
