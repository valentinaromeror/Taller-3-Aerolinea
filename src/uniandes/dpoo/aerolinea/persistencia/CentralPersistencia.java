package uniandes.dpoo.aerolinea.persistencia;

public final class CentralPersistencia {
    public static final String JSON  = "JSON";
    public static final String PLAIN = "PLAIN";

    private CentralPersistencia() {}

    public static IPersistenciaAerolinea getPersistenciaAerolinea(String tipoArchivo)
            throws TipoInvalidoException {
        if (PLAIN.equalsIgnoreCase(tipoArchivo)) {
            return new PersistenciaAerolineaPlaintext(); 
        }
        throw new TipoInvalidoException("Tipo de archivo inválido/no soportado para aerolínea: " + tipoArchivo);
    }

    public static IPersistenciaTiquetes getPersistenciaTiquetes(String tipoArchivo)
            throws TipoInvalidoException {
        if (JSON.equalsIgnoreCase(tipoArchivo)) {
            return new PersistenciaTiquetesJson();
        }
        throw new TipoInvalidoException("Para tiquetes solo se soporta JSON");
    }
}

