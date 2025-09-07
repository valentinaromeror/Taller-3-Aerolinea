package uniandes.dpoo.aerolinea.modelo;

import java.util.HashSet;
import java.util.Set;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;

public class Aeropuerto {
	
    public static final int RADIO_TERRESTRE = 6371; // km
    private static final Set<String> codigosUsados = new HashSet<>();

    private final String nombre;
    private final String codigo;       // ej. "BOG"
    private final String nombreCiudad; // ej. "Bogotá"
    private final double latitud;
    private final double longitud;

    public Aeropuerto(String nombre, String codigo, String nombreCiudad,
                      double latitud, double longitud) throws AeropuertoDuplicadoException {
        if (codigosUsados.contains(codigo))
            throw new AeropuertoDuplicadoException(codigo);
        this.nombre = nombre;
        this.codigo = codigo;
        this.nombreCiudad = nombreCiudad;
        this.latitud = latitud;
        this.longitud = longitud;
        codigosUsados.add(codigo);
    }

    public String getNombre()       { return nombre; }
    public String getCodigo()       { return codigo; }
    public String getNombreCiudad() { return nombreCiudad; }
    public double getLatitud()      { return latitud; }
    public double getLongitud()     { return longitud; }

    public static int calcularDistancia(Aeropuerto a1, Aeropuerto a2) {
        double lat1 = Math.toRadians(a1.getLatitud());
        double lon1 = Math.toRadians(a1.getLongitud());
        double lat2 = Math.toRadians(a2.getLatitud());
        double lon2 = Math.toRadians(a2.getLongitud());
        double dx = (lon2 - lon1) * Math.cos((lat1 + lat2) / 2);
        double dy = (lat2 - lat1);
        double distancia = Math.sqrt(dx*dx + dy*dy) * RADIO_TERRESTRE;
        return (int)Math.round(distancia);
    }
}

