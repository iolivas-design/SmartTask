package aplicacion;

/**
 * Interfaz que define las operaciones comunes para la gestión de tareas.
 * Actúa como contrato que deben cumplir los gestores de tareas del sistema SmartTask.
 * 
 * @author SmartTask Team
 * @version 1.0
 */
public interface Accionable {
    
    /**
     * Agrega una nueva tarea al gestor.
     * 
     * @param nombre el nombre de la tarea
     * @param prioridad el nivel de prioridad de la tarea
     * @param esUrgente indica si la tarea es urgente
     */
    void agregarTarea(String nombre, int prioridad, boolean esUrgente);
    
    /**
     * Lista todas las tareas (activas y completadas).
     */
    void listarTareas();
    
    /**
     * Muestra los detalles formateados de una tarea.
     * 
     * @param tarea la tarea a mostrar
     */
    void mostrarTarea(Tarea tarea);
    
    /**
     * Marca una tarea como completada.
     * 
     * @param id el identificador único de la tarea
     */
    void marcarCompletada(int id);
    
    /**
     * Elimina una tarea del gestor.
     * 
     * @param id el identificador único de la tarea
     */
    void eliminarTarea(int id);
}
