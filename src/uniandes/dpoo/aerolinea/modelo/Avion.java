package uniandes.dpoo.aerolinea.modelo;

public class Avion {
    private final String nombre;
    private final int capacidad;

    public Avion(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }
}
