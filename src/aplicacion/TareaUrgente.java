package aplicacion;

/**
 * Subclase de Tarea que representa una tarea urgente.
 * Por defecto, establece la prioridad en 3 (máxima) para tareas urgentes.
 * 
 * @author SmartTask Team
 * @version 1.0
 */
public class TareaUrgente extends Tarea {
    
    /**
     * Constructor sin parámetros que crea una tarea urgente con prioridad por defecto (3).
     */
    public TareaUrgente() {
        super();
        this.setPrioridad(3);
    }

    /**
     * Constructor que crea una tarea urgente con parámetros específicos.
     * La prioridad se establece automáticamente en 3.
     * 
     * @param id el identificador único de la tarea
     * @param nombre el nombre descriptivo de la tarea
     * @param completado indica si la tarea está completada
     */
    public TareaUrgente(int id, String nombre, boolean completado) {
        super(id, nombre, 3, completado);
    }

    /**
     * Constructor que crea una tarea urgente con todos los parámetros incluyendo prioridad.
     * 
     * @param id el identificador único de la tarea
     * @param nombre el nombre descriptivo de la tarea
     * @param prioridad el nivel de prioridad de la tarea
     * @param completado indica si la tarea está completada
     */
    public TareaUrgente(int id, String nombre, int prioridad, boolean completado) {
        super(id, nombre, prioridad, completado);
    }

    /**
     * Retorna una representación en string de la tarea urgente con todos sus atributos.
     * 
     * @return string con formato: TareaUrgente{id=..., nombre='...', prioridad=..., completado=...}
     */
    @Override
    public String toString() {
        return "TareaUrgente{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", prioridad=" + getPrioridad() +
                ", completado=" + isCompletado() +
                '}';
    }
}
