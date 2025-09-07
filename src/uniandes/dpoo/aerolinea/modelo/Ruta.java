package uniandes.dpoo.aerolinea.modelo;

public class Ruta {
    private final Aeropuerto origen;
    private final Aeropuerto destino;
    private final String horaSalida;  
    private final String horaLlegada;  
    private final String codigoRuta;

    public Ruta(Aeropuerto origen, Aeropuerto destino, String horaSalida, String horaLlegada, String codigoRuta) {
        this.origen = origen; this.destino = destino;
        this.horaSalida = horaSalida; this.horaLlegada = horaLlegada;
        this.codigoRuta = codigoRuta;
    }

    public Aeropuerto getOrigen() { return origen; }
    public Aeropuerto getDestino() { return destino; }
    public String getHoraSalida() { return horaSalida; }
    public String getHoraLlegada() { return horaLlegada; }
    public String getCodigoRuta() { return codigoRuta; }
}
