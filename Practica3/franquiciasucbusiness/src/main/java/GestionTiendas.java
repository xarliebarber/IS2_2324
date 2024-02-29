import java.util.List;
import java.util.ArrayList;

public class GestionTiendas implements IGestionTiendas {
    
    // Asumiendo que hay una estructura de datos para almacenar las tiendas
    private List<Tienda> tiendas;

    public GestionTiendas() {
        this.tiendas = new ArrayList<>();
    }

    @Override
    public Tienda nuevaTienda(Tienda tienda) {
        // Implementación
        return null;
    }

    @Override
    public Tienda eliminarTienda(String nombre) {
        // Implementación
        return null;
    }

    @Override
    public Tienda tienda(String nombre) {
        // Implementación
        return null;
    }
}
