public class Persona {

    private int id;
    private String nombre;
    private boolean activa;

    public Persona() {}

    public Persona(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.activa = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Estado: " + (activa ? "Activa" : "Inactiva");
    }
}