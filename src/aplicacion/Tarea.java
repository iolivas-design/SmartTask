package aplicacion;

/**
 * Clase que representa una tarea en el sistema SmartTask.
 * Contiene los atributos básicos de una tarea: identificador, nombre,
 * prioridad y estado de completitud.
 * 
 * @author SmartTask Team
 * @version 1.0
 */
public class Tarea {
    private int id;
    private String nombre;
    private int prioridad;
    private boolean completado;

    /**
     * Constructor sin parámetros que inicializa una tarea con valores por defecto.
     * ID = 0, nombre = "", prioridad = 0, completado = false.
     */
    public Tarea() {
        this.id = 0;
        this.nombre = "";
        this.prioridad = 0;
        this.completado = false;
    }

    /**
     * Constructor con parámetros que inicializa una tarea con valores específicos.
     * 
     * @param id el identificador único de la tarea
     * @param nombre el nombre descriptivo de la tarea
     * @param prioridad el nivel de prioridad de la tarea
     * @param completado indica si la tarea está completada
     */
    public Tarea(int id, String nombre, int prioridad, boolean completado) {
        this.id = id;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.completado = completado;
    }

    /**
     * Obtiene el identificador único de la tarea.
     * 
     * @return el ID de la tarea
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre descriptivo de la tarea.
     * 
     * @return el nombre de la tarea
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el nivel de prioridad de la tarea.
     * 
     * @return la prioridad de la tarea
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * Indica si la tarea está completada.
     * 
     * @return true si la tarea está completada, false en caso contrario
     */
    public boolean isCompletado() {
        return completado;
    }

    /**
     * Establece el identificador único de la tarea.
     * 
     * @param id el identificador a asignar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Establece el nombre descriptivo de la tarea.
     * 
     * @param nombre el nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el nivel de prioridad de la tarea.
     * 
     * @param prioridad el nivel de prioridad a asignar
     */
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Establece el estado de completitud de la tarea.
     * 
     * @param completado true para marcar como completada, false en caso contrario
     */
    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    /**
     * Retorna una representación en string de la tarea con todos sus atributos.
     * 
     * @return string con formato: Tarea{id=..., nombre='...', prioridad=..., completado=...}
     */
    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", prioridad=" + prioridad +
                ", completado=" + completado +
                '}';
    }
}
