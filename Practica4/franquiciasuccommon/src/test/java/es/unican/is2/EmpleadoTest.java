package es.unican.is2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class EmpleadoTest {

    // Definir empleados estáticos (correctos)
    static Empleado juan;
    static Empleado pedro;
    static Empleado maria;

    @BeforeAll
    public static void beforeAll() throws Exception {
        juan = new Empleado("12345678A", "Juan", Categoria.ENCARGADO, LocalDate.of(2017, 1, 1));
        pedro = new Empleado("87654321B", "Pedro", Categoria.VENDEDOR, LocalDate.of(2010, 1, 1));
        maria = new Empleado("23456789B", "María", Categoria.AUXILIAR, LocalDate.now().plusYears(2));
    }

    public class MiExcepcion extends Exception {
        public MiExcepcion() {
            super();
        }

        public MiExcepcion(String mensaje) {
            super(mensaje);
        }
    }

    @Test
    public void testConstructor() {
        // Utilizar empleados estáticos
        assertEquals("12345678A", juan.getDNI());
        assertEquals("Juan", juan.getNombre());
        assertEquals(Categoria.ENCARGADO, juan.getCategoria());
        assertEquals(LocalDate.of(2017, 1, 1), juan.getFechaContratacion());
        assertFalse(juan.getBaja());
    }

    @Test
    public void testSueldoBruto() {
        // Utilizar empleados estáticos
        assertEquals(2050.0, juan.sueldoBruto(), 0.01);
        assertEquals(1600.0, pedro.sueldoBruto(), 0.01);
        assertEquals(1600.0, pedro.sueldoBruto(), 0.01);
    }

    @Test
    public void testDarDeBajaYAlta() {
        // Utilizar empleados estáticos
        assertFalse(juan.getBaja());
        juan.darDeBaja();
        assertTrue(juan.getBaja());
        juan.darDeAlta();
        assertFalse(juan.getBaja());
    }

    @Test
    void testSueldoBruto_AntiguedadNegativa() {
        // Utilizar empleados estáticos
        Empleado empleado = maria;

        // Intentamos calcular el sueldo con una antigüedad negativa
        assertThrows(MiExcepcion.class, () -> {
            try {
                empleado.sueldoBruto(); // No se pasan argumentos
            } catch (IllegalArgumentException e) {
                throw new MiExcepcion("Se lanzó IllegalArgumentException");
            }
        });
    }

    @Test
    void testDarDeBaja_EmpleadoYaDeBaja() {
        // Utilizar empleados estáticos
        maria.darDeBaja();

        // Intentamos dar de baja a un empleado que ya está de baja
        assertThrows(MiExcepcion.class, () -> {
            try {
                maria.darDeBaja();
            } catch (IllegalStateException e) {
                throw new MiExcepcion("Este empleado ya está de baja");
            }
        });
    }

    @Test
    void testDarDeAlta_EmpleadoActivo() {
        // Utilizar empleados estáticos
        Empleado empleado = pedro;

        // Intentamos dar de alta a un empleado activo
        assertThrows(MiExcepcion.class, () -> {
            try {
                empleado.darDeAlta();
            } catch (IllegalStateException e) {
                throw new MiExcepcion("Este empleado ya esta en activo");
            }
        });
    }
}
