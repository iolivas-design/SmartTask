public class TareaUrgente extends Tarea {
    
    // Constructor sin parámetros
    public TareaUrgente() {
        super();
        this.setPrioridad(3); // Prioridad alta por defecto
    }

    // Constructor con parámetros
    public TareaUrgente(int id, String nombre, boolean completado) {
        super(id, nombre, 3, completado); // Prioridad 3 para tareas urgentes
    }

    // Constructor con todos los parámetros
    public TareaUrgente(int id, String nombre, int prioridad, boolean completado) {
        super(id, nombre, prioridad, completado);
    }

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
