public class Tarea {
    // Atributos privados
    private int id;
    private String nombre;
    private int prioridad;
    private boolean completado;

    // Constructor sin parámetros
    public Tarea() {
        this.id = 0;
        this.nombre = "";
        this.prioridad = 0;
        this.completado = false;
    }

    // Constructor con parámetros
    public Tarea(int id, String nombre, int prioridad, boolean completado) {
        this.id = id;
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.completado = completado;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public boolean isCompletado() {
        return completado;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    // Método toString para representación en string
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
