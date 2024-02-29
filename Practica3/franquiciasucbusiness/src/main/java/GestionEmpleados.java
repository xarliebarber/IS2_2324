import java.util.List;
import java.util.ArrayList;

public class GestionEmpleados implements IGestionEmpleados {

    // Asumiendo que hay una estructura de datos para almacenar los empleados
    private List<Empleado> empleados;

    public GestionEmpleados() {
        this.empleados = new ArrayList<>();
    }

    @Override
    public Empleado nuevoEmpleado(Empleado empleado, String nombre) {
        // Implementación
        return null;
    }

    @Override
    public Empleado eliminarEmpleado(String dni, String nombre) {
        // Implementación
        return null;
    }

    @Override
    public Empleado empleado(String dni) {
        // Implementación
        return null;
    }

    @Override
    public boolean trasladarEmpleado(String dni, String actual, String destino) {
        // Implementación
        return false;
    }
}
