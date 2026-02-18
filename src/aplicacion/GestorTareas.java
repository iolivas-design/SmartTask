package aplicacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor centralizado de tareas del sistema SmartTask.
 * Permite crear, listar, marcar como completadas y eliminar tareas.
 * Diferencia entre tareas urgentes y normales.
 * 
 * @author SmartTask Team
 * @version 1.0
 */
public class GestorTareas implements Accionable {
    private List<Tarea> tareas;
    private int siguienteId;

    /**
     * Constructor que inicializa el gestor de tareas.
     * Crea una lista vacía y establece el contador de IDs en 1.
     */
    public GestorTareas() {
        this.tareas = new ArrayList<>();
        this.siguienteId = 1;
    }

    /**
     * Agrega una nueva tarea a la lista.
     * Crea automáticamente una TareaUrgente o TareaNormal según el parámetro.
     * 
     * @param nombre el nombre descriptivo de la tarea
     * @param prioridad el nivel de prioridad (escala numérica)
     * @param esUrgente true si la tarea es urgente, false si es normal
     */
    @Override
    public void agregarTarea(String nombre, int prioridad, boolean esUrgente) {
        Tarea tarea;
        if (esUrgente) {
            tarea = new TareaUrgente(siguienteId, nombre, prioridad, false);
        } else {
            tarea = new TareaNormal(siguienteId, nombre, prioridad, false);
        }
        tareas.add(tarea);
        System.out.println("✓ Tarea agregada: " + nombre + " (ID: " + siguienteId + ")");
        siguienteId++;
    }

    /**
     * Lista todas las tareas separadas en dos grupos:
     * tareas activas (no completadas) y tareas completadas.
     * Muestra un mensaje si no hay tareas registradas.
     */
    @Override
    public void listarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("\nNo hay tareas registradas.");
            return;
        }

        System.out.println("\n========== TAREAS ACTIVAS ==========");
        boolean hayActivas = false;
        for (Tarea tarea : tareas) {
            if (!tarea.isCompletado()) {
                mostrarTarea(tarea);
                hayActivas = true;
            }
        }
        if (!hayActivas) {
            System.out.println("No hay tareas activas.");
        }

        System.out.println("\n========== TAREAS COMPLETADAS ==========");
        boolean hayCompletadas = false;
        for (Tarea tarea : tareas) {
            if (tarea.isCompletado()) {
                mostrarTarea(tarea);
                hayCompletadas = true;
            }
        }
        if (!hayCompletadas) {
            System.out.println("No hay tareas completadas.");
        }
        System.out.println();
    }

    /**
     * Muestra una tarea formateada con su información completa.
     * Incluye estado, ID, nombre, prioridad y tipo (urgente/normal).
     * 
     * @param tarea la tarea a mostrar
     */
    @Override
    public void mostrarTarea(Tarea tarea) {
        String tipo = tarea instanceof TareaUrgente ? "[URGENTE]" : "[NORMAL]";
        String estado = tarea.isCompletado() ? "[✓]" : "[ ]";
        System.out.printf("%s ID: %d | %s | Prioridad: %d | %s\n",
                estado, tarea.getId(), tarea.getNombre(), tarea.getPrioridad(), tipo);
    }

    /**
     * Marca una tarea como completada por su ID.
     * Si la tarea ya está completada, muestra un mensaje de advertencia.
     * 
     * @param id el identificador único de la tarea
     */
    @Override
    public void marcarCompletada(int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                if (tarea.isCompletado()) {
                    System.out.println("⚠ Esta tarea ya está completada.");
                } else {
                    tarea.setCompletado(true);
                    System.out.println("✓ Tarea marcada como completada: " + tarea.getNombre());
                }
                return;
            }
        }
        System.out.println("✗ No se encontró tarea con ID: " + id);
    }

    /**
     * Elimina una tarea de la lista por su ID.
     * Muestra un mensaje de error si la tarea no existe.
     * 
     * @param id el identificador único de la tarea a eliminar
     */
    @Override
    public void eliminarTarea(int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                String nombre = tarea.getNombre();
                tareas.remove(tarea);
                System.out.println("✓ Tarea eliminada: " + nombre);
                return;
            }
        }
        System.out.println("✗ No se encontró tarea con ID: " + id);
    }

    /**
     * Obtiene el número total de tareas registradas.
     * 
     * @return la cantidad de tareas en la lista
     */
    public int obtenerCantidadTareas() {
        return tareas.size();
    }
}
