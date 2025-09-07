package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTemporadaBaja extends CalculadoraTarifas {
    private static final int    COSTO_POR_KM_NATURAL     = 600;
    private static final int    COSTO_POR_KM_CORPORATIVO = 900;

    private static final double DESCUENTO_PEQ      = 0.02;
    private static final double DESCUENTO_MEDIANAS = 0.10;
    private static final double DESCUENTO_GRANDES  = 0.20;

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int km = calcularDistanciaVuelo(vuelo.getRuta());
        int costoKm = (cliente instanceof ClienteCorporativo)
                ? COSTO_POR_KM_CORPORATIVO
                : COSTO_POR_KM_NATURAL;
        return km * costoKm;
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        if (cliente instanceof ClienteCorporativo) {
            ClienteCorporativo cc = (ClienteCorporativo) cliente;
            int t = cc.getTamanoEmpresa();
            if (t == ClienteCorporativo.PEQUENA) return DESCUENTO_PEQ;
            if (t == ClienteCorporativo.MEDIANA) return DESCUENTO_MEDIANAS;
            if (t == ClienteCorporativo.GRANDE)  return DESCUENTO_GRANDES;
        }
        return 0.0;
    }
}
