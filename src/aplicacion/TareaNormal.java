package aplicacion;

/**
 * Subclase de Tarea que representa una tarea normal.
 * Por defecto, establece la prioridad en 1 (mínima) para tareas normales.
 * 
 * @author SmartTask Team
 * @version 1.0
 */
public class TareaNormal extends Tarea {
    
    /**
     * Constructor sin parámetros que crea una tarea normal con prioridad por defecto (1).
     */
    public TareaNormal() {
        super();
        this.setPrioridad(1);
    }

    /**
     * Constructor que crea una tarea normal con parámetros específicos.
     * La prioridad se establece automáticamente en 1.
     * 
     * @param id el identificador único de la tarea
     * @param nombre el nombre descriptivo de la tarea
     * @param completado indica si la tarea está completada
     */
    public TareaNormal(int id, String nombre, boolean completado) {
        super(id, nombre, 1, completado);
    }

    /**
     * Constructor que crea una tarea normal con todos los parámetros incluyendo prioridad.
     * 
     * @param id el identificador único de la tarea
     * @param nombre el nombre descriptivo de la tarea
     * @param prioridad el nivel de prioridad de la tarea
     * @param completado indica si la tarea está completada
     */
    public TareaNormal(int id, String nombre, int prioridad, boolean completado) {
        super(id, nombre, prioridad, completado);
    }

    /**
     * Retorna una representación en string de la tarea normal con todos sus atributos.
     * 
     * @return string con formato: TareaNormal{id=..., nombre='...', prioridad=..., completado=...}
     */
    @Override
    public String toString() {
        return "TareaNormal{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", prioridad=" + getPrioridad() +
                ", completado=" + isCompletado() +
                '}';
    }
}
