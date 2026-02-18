package aplicacion;
import java.util.ArrayList;
import java.util.List;

public class GestorTareas implements Accionable {
    private List<Tarea> tareas;
    private int siguienteId;

    public GestorTareas() {
        this.tareas = new ArrayList<>();
        this.siguienteId = 1;
    }

    // Agregar una nueva tarea
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

    // Listar todas las tareas
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

    // Mostrar una tarea formateada
    @Override
    public void mostrarTarea(Tarea tarea) {
        String tipo = tarea instanceof TareaUrgente ? "[URGENTE]" : "[NORMAL]";
        String estado = tarea.isCompletado() ? "[✓]" : "[ ]";
        System.out.printf("%s ID: %d | %s | Prioridad: %d | %s\n",
                estado, tarea.getId(), tarea.getNombre(), tarea.getPrioridad(), tipo);
    }

    // Marcar una tarea como completada
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

    // Eliminar una tarea
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

    // Obtener el número de tareas
    public int obtenerCantidadTareas() {
        return tareas.size();
    }
}
