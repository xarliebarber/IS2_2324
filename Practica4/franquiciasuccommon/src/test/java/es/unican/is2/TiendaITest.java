package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiendaITest {

    private Tienda tienda;
    private Empleado empleado1;
    private Empleado empleado2;

    @BeforeEach
    public void setUp() throws Exception {
        // Inicializar objetos de prueba
        Tienda tiendaAux = new Tienda(); // Uso del constructor vacio para completar cobertura
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

    // Tests auxiliares para comprobar el correcto funcionamiento del resto de
    // metodos
    @Test
    public void testConstructorTienda() {
        assertNotNull(tienda);
    }

    @Test
    public void testSetNombre() {
        String nuevoNombre = "Nueva Tienda";
        tienda.setNombre(nuevoNombre);
        assertEquals(nuevoNombre, tienda.getNombre());
    }

    @Test
    public void testSetDireccion() {
        String nuevaDireccion = "Calle Nueva, 123";
        tienda.setDireccion(nuevaDireccion);
        assertEquals(nuevaDireccion, tienda.getDireccion());
    }

    @Test
    public void testSetId() {
        long nuevoId = 12345;
        tienda.setId(nuevoId);
        assertEquals(nuevoId, tienda.getId());
    }

    @Test
    public void testGetEmpleados() throws LongitudIncorrecta, CampoVacio {
        List<Empleado> empleados = new ArrayList<>();
        Empleado empleado1 = new Empleado("12345678A", "Empleado1", Categoria.ENCARGADO, LocalDate.now());
        Empleado empleado2 = new Empleado("23456789B", "Empleado2", Categoria.AUXILIAR, LocalDate.now());
        empleados.add(empleado1);
        empleados.add(empleado2);

        List<Empleado> empleadosVacio = new ArrayList<>();
        tienda.setEmpleados(empleados);
        assertEquals(empleados, tienda.getEmpleados());
        tienda.setEmpleados(empleadosVacio);
        assertEquals(empleadosVacio, tienda.getEmpleados());
    }
}
