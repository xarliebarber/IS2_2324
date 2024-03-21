package es.unican.is2;

import java.util.List;
import java.util.ArrayList;

/*
 * public class GestionTiendas implements IGestionTiendas {
 * private ITiendasDAO t;
 * 
 * public GestionTiendas(ITiendasDAO tiendasDAO) {
 * // TODO Auto-generated constructor stub
 * this.t = tiendasDAO;
 * }
 * 
 * @Override
 * public Tienda nuevaTienda(Tienda t) throws DataAccessException {
 * // TODO Auto-generated method stub
 * throw new
 * UnsupportedOperationException("Unimplemented method 'nuevaTienda'");
 * }
 * 
 * @Override
 * public Tienda eliminarTienda(String nombre) throws
 * OperacionNoValidaException, DataAccessException {
 * // TODO Auto-generated method stub
 * throw new
 * UnsupportedOperationException("Unimplemented method 'eliminarTienda'");
 * }
 * 
 * @Override
 * public Tienda tienda(String nombre) throws DataAccessException {
 * // TODO Auto-generated method stub
 * throw new UnsupportedOperationException("Unimplemented method 'tienda'");
 * }
 * 
 * }
 * 
 */

public class GestionTiendas implements IGestionTiendas {
    private ITiendasDAO tiendasDAO;

    public GestionTiendas(ITiendasDAO tiendasDAO) {
        this.tiendasDAO = tiendasDAO;
    }

    @Override
    public Tienda nuevaTienda(Tienda t) throws DataAccessException {
        if (t == null) {
            throw new IllegalArgumentException("La tienda no puede ser nula");
        }
        if (tiendasDAO.tiendaPorNombre(t.getNombre()) != null) {
            return null;
        }

        return tiendasDAO.crearTienda(t);
    }

    @Override
    public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
        if (tiendasDAO.tiendaPorNombre(nombre) == null) {
            return null;
        }
        return this.tiendasDAO.eliminarTienda(tiendasDAO.tiendaPorNombre(nombre).getId());

    }

    @Override
    public Tienda tienda(String nombre) throws DataAccessException {
        try {
            return tiendasDAO.tiendaPorNombre(nombre);
        } catch (DataAccessException e) {
            throw e;
        }
    }
}
