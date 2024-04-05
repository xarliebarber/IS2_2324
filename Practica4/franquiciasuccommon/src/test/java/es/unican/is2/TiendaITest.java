package es.unican.is2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class TiendaITest {

    private Tienda tienda;
    private Empleado empleado1;
    private Empleado empleado2;

    @Before
    public void setUp() throws Exception {
        // Inicializar objetos de prueba
        tienda = new Tienda("Tienda 1", "Dirección 1");
        empleado1 = new Empleado("123456789", "Juan", Categoria.VENDEDOR, LocalDate.of(2020, 1, 1));
        empleado2 = new Empleado("987654321", "María", Categoria.ENCARGADO, LocalDate.of(2015, 1, 1));

        // Agregar empleados a la tienda
        tienda.getEmpleados().add(empleado1);
        tienda.getEmpleados().add(empleado2);
    }

    @Test
    public void testGastoMensualSueldos() {
        // Calcular el gasto mensual de sueldos
        double gastoMensual = tienda.gastoMensualSueldos();

        // Verificar que el gasto mensual sea el esperado
        assertEquals(3550.0, gastoMensual, 0.01);
    }

    @Test
    public void testBuscaEmpleadoExistente() {
        // Buscar un empleado existente en la tienda
        Empleado empleadoEncontrado = tienda.buscaEmpleado("123456789");

        // Verificar que el empleado encontrado no sea nulo
        assertNotNull(empleadoEncontrado);
        // Verificar que el nombre del empleado coincida
        assertEquals("Juan", empleadoEncontrado.getNombre());
    }

    @Test
    public void testBuscaEmpleadoNoExistente() {
        // Buscar un empleado no existente en la tienda
        Empleado empleadoEncontrado = tienda.buscaEmpleado("111111111");

        // Verificar que el empleado encontrado sea nulo
        assertNull(empleadoEncontrado);
    }
}
