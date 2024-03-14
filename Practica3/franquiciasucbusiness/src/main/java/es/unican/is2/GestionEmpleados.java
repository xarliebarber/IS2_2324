package es.unican.is2;

import java.util.List;
import java.util.ArrayList;

public class GestionEmpleados implements IGestionEmpleados, IGestionAltasBajas {
    private ITiendasDAO t;
    private IEmpleadosDAO e;

    public GestionEmpleados(ITiendasDAO tiendasDAO, IEmpleadosDAO empleadosDAO) {
        // TODO Auto-generated constructor stubç
        this.t = tiendasDAO;
        this.e = empleadosDAO;
    }

    @Override
    public boolean bajaMedica(String dni) throws DataAccessException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bajaMedica'");
    }

    @Override
    public boolean altaMedica(String dni) throws DataAccessException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'altaMedica'");
    }

    @Override
    public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nuevoEmpleado'");
    }

    @Override
    public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarEmpleado'");
    }

    @Override
    public boolean trasladarEmpleado(String dni, String actual, String destino)
            throws OperacionNoValidaException, DataAccessException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trasladarEmpleado'");
    }

    @Override
    public Empleado empleado(String dni) throws DataAccessException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'empleado'");
    }

}
