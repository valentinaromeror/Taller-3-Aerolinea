package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class Tiquete {
    private final String codigo;
    private final Vuelo vuelo;
    private final Cliente cliente; 
    private final int tarifa;
    private boolean usado;

    public Tiquete(String codigo, Vuelo vuelo, Cliente cliente, int tarifa) {
        this.codigo = codigo;
        this.vuelo = vuelo;
        this.cliente = cliente;
        this.tarifa = tarifa;
        this.usado = false;
    }

    public String getCodigo() { return codigo; }
    public Vuelo getVuelo() { return vuelo; }
    public Cliente getCliente() { return cliente; }
    public Cliente getClienteComprador() { return cliente; }
    public int getTarifa() { return tarifa; }
    public boolean esUsado() { return usado; }
    public void marcarComoUsado() { usado = true; }
}
