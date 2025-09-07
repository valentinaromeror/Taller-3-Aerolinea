package uniandes.dpoo.aerolinea.modelo;

import java.util.ArrayList;
import java.util.Collection;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;


public class Vuelo {
    private final Ruta ruta;
    private final String fecha; 
    private final Avion avion;
    private final Collection<Tiquete> tiquetes = new ArrayList<>();
    public void agregarTiqueteCargado(uniandes.dpoo.aerolinea.tiquetes.Tiquete t) {
        this.tiquetes.add(t);
    }

    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.ruta = ruta; this.fecha = fecha; this.avion = avion;
    }
    public Ruta getRuta() { return ruta; }
    public String getFecha() { return fecha; }
    public Avion getAvion() { return avion; }
    public Collection<Tiquete> getTiquetes() { return new ArrayList<>(tiquetes); }
    
    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calc, int cantidad) throws VueloSobrevendidoException {

        if (tiquetes.size() + cantidad > avion.getCapacidad()) {
        	 throw new VueloSobrevendidoException(this);
        }

        int total = 0;
        for (int i = 0; i < cantidad; i++) {
            int tarifa = calc.calcularTarifa(this, cliente);
            Tiquete t = uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes
                            .generarTiquete(this, cliente, tarifa);
            uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes.registrarTiquete(t);
            tiquetes.add(t);
            cliente.agregarTiquete(t);
            total += tarifa;
        }
        return total;
    }
}
