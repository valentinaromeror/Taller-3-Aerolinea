package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {
    protected final List<Tiquete> tiquetes = new ArrayList<>();

    public abstract String getIdentificador();
    public abstract String getTipoCliente();

    public void agregarTiquete(Tiquete t) { tiquetes.add(t); }
    public List<Tiquete> getTiquetes() { return Collections.unmodifiableList(tiquetes); }

    public void usarTiquetes(Vuelo vuelo) {
        for (Tiquete t : tiquetes)
            if (t.getVuelo().equals(vuelo) && !t.esUsado())
                t.marcarComoUsado();
    }
}
