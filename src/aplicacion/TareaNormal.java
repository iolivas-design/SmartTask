package aplicacion;
public class TareaNormal extends Tarea {
    
    // Constructor sin parámetros
    public TareaNormal() {
        super();
        this.setPrioridad(1); // Prioridad baja por defecto
    }

    // Constructor con parámetros
    public TareaNormal(int id, String nombre, boolean completado) {
        super(id, nombre, 1, completado); // Prioridad 1 para tareas normales
    }

    // Constructor con todos los parámetros
    public TareaNormal(int id, String nombre, int prioridad, boolean completado) {
        super(id, nombre, prioridad, completado);
    }

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
