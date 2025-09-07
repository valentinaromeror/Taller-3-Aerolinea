package uniandes.dpoo.aerolinea.consola;

import java.io.IOException;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;

public class ConsolaArerolinea extends ConsolaBasica
{
    private Aerolinea unaAerolinea;
    private static void inicializarRedMinimaPara(String codigoRuta, String fecha, uniandes.dpoo.aerolinea.modelo.Aerolinea aerolinea) {
        try {
            var o = new uniandes.dpoo.aerolinea.modelo.Aeropuerto("ORIG-"+codigoRuta, "O"+codigoRuta, "CiudadO-"+codigoRuta, 0.0, 0.0);
            var d = new uniandes.dpoo.aerolinea.modelo.Aeropuerto("DEST-"+codigoRuta, "D"+codigoRuta, "CiudadD-"+codigoRuta, 1.0, 1.0);
            var r = new uniandes.dpoo.aerolinea.modelo.Ruta(o, d, "08:00", "10:00", codigoRuta);
            aerolinea.agregarRuta(r);

            var av = new uniandes.dpoo.aerolinea.modelo.Avion("AV-"+codigoRuta, 300);
            aerolinea.agregarAvion(av);

            aerolinea.programarVuelo(fecha, codigoRuta, av.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Es un método que corre la aplicación y realmente no hace nada interesante: sólo muestra cómo se podría utilizar la clase Aerolínea para hacer pruebas.
     */
    public void correrAplicacion( )
    {
        try
        {
            unaAerolinea = new Aerolinea( );

            // Crear la red mínima 
            if (unaAerolinea.getRuta("4558") == null) {
                inicializarRedMinimaPara("4558", "2024-11-05", unaAerolinea);
            }

            // 2) Ahora sí, cargar clientes + tiquetes del JSON
            String archivo = "tiquetes.json";
            unaAerolinea.cargarTiquetes("./datos/" + archivo, CentralPersistencia.JSON);
            System.out.println("Rutas: " + unaAerolinea.getRutas().size());
            System.out.println("Vuelos: " + unaAerolinea.getVuelos().size());
            System.out.println("Clientes: " + unaAerolinea.getClientes().size());
            System.out.println("Tiquetes (sumando vuelo por vuelo): " + unaAerolinea.getTiquetes().size());

            // Listar tiquetes (útil para ver si se asociaron bien)
            for (var t : unaAerolinea.getTiquetes()) {
                System.out.println(" - " + t.getCodigo()
                    + " | Ruta " + t.getVuelo().getRuta().getCodigoRuta()
                    + " | Fecha " + t.getVuelo().getFecha()
                    + " | Cliente " + t.getCliente().getIdentificador()
                    + " | $" + t.getTarifa()
                    + (t.esUsado() ? " [USADO]" : ""));
            }
            

            System.out.println("Tiquetes cargados correctamente.");
        }
        catch( TipoInvalidoException | IOException | InformacionInconsistenteException e )
        {
            e.printStackTrace();
        }
    }

    public static void main( String[] args )
    {
        ConsolaArerolinea ca = new ConsolaArerolinea( );
        ca.correrAplicacion( );
    }
}
