public class Alumno {

    protected int id;
    protected String nombre;
    protected double promedio;
    protected boolean activo;

    public Alumno(int id, String nombre, double promedio) {
        this.id = id;
        this.nombre = nombre;
        this.promedio = promedio;
        this.activo = true;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPromedio() { return promedio; }
    public boolean isActivo() { return activo; }

    public void setPromedio(double promedio) { this.promedio = promedio; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Promedio: " + promedio;
    }
}