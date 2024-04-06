package es.unican.is2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
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
    @Order(1)
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

    @Test
    public void testConstructor_NombreNulo() {
        assertThrows(CampoVacio.class, () -> {
            new Empleado("12345678A", null, Categoria.ENCARGADO, LocalDate.of(2017, 1, 1));
        });
    }

    @Test
    public void testConstructor_LongitudDNICorrecta() {
        assertThrows(LongitudIncorrecta.class, () -> {
            new Empleado("1234567", "Juan", Categoria.ENCARGADO, LocalDate.of(2017, 1, 1));
        });
    }

    @Test
    public void testConstructor_CategoriaNula() {
        assertThrows(CampoVacio.class, () -> {
            new Empleado("12345678A", "Juan", null, LocalDate.of(2017, 1, 1));
        });
    }

    @Test
    public void testConstructor_FechaContratacionNula() {
        assertThrows(CampoVacio.class, () -> {
            new Empleado("12345678A", "Juan", Categoria.ENCARGADO, null);
        });
    }

    @Test
    public void testSueldoBruto_FechaContratacionPosterior() {
        Empleado empleado = null;
        try {
            empleado = new Empleado("12345678A", "Juan", Categoria.ENCARGADO, LocalDate.now().plusDays(1));
        } catch (LongitudIncorrecta | CampoVacio e) {
            e.printStackTrace();
        }
        assertThrows(IllegalArgumentException.class, empleado::sueldoBruto);
    }

    @Test
    @Order(2)
    public void testSueldoBruto_Encargado_Menos5Anios() {
        // Utilizar empleado estático juan
        juan.setFechaContratacion(LocalDate.now().minusDays(1));
        assertEquals(2000.0, juan.sueldoBruto(), 0.01);
        // Devolvemos a juan su fecha original
        juan.setFechaContratacion(LocalDate.of(2017, 1, 1));
    }

    @Test
    public void testSueldoBruto_Encargado_5a10Anios() {
        // Utilizar empleado estático pedro
        pedro.setFechaContratacion(LocalDate.now().minusYears(7));
        assertEquals(1550.0, pedro.sueldoBruto(), 0.01);
    }

    @Test
    public void testSueldoBruto_Encargado_10a20Anios() {
        // Utilizar empleado estático maria
        maria.setFechaContratacion(LocalDate.now().minusYears(15));
        maria.setCategoria(Categoria.ENCARGADO);
        assertEquals(2100.0, maria.sueldoBruto(), 0.01);
    }

    @Test
    public void testSueldoBruto_Encargado_Mas20Anios() throws LongitudIncorrecta, CampoVacio {
        // Crear nuevo empleado para tener más de 20 años de antigüedad
        Empleado empleado = new Empleado("98765432C", "Carlos", Categoria.ENCARGADO, LocalDate.now().minusYears(21));
        assertEquals(2200.0, empleado.sueldoBruto(), 0.01);
    }

    @Test
    public void testSueldoBruto_Vendedor_Menos5Anios() {
        // Utilizar empleado estático pedro
        pedro.setFechaContratacion(LocalDate.now().minusDays(1));
        pedro.setCategoria(Categoria.VENDEDOR);
        assertEquals(1500.0, pedro.sueldoBruto(), 0.01);
    }

    @Test
    public void testSueldoBruto_Vendedor_5a10Anios() {
        // Utilizar empleado estático maria
        maria.setFechaContratacion(LocalDate.now().minusYears(7));
        maria.setCategoria(Categoria.VENDEDOR);
        assertEquals(1550.0, maria.sueldoBruto(), 0.01);
    }

    @Test
    public void testSueldoBruto_Vendedor_10a20Anios() throws LongitudIncorrecta, CampoVacio {
        // Crear nuevo empleado para tener entre 10 y 20 años de antigüedad
        Empleado empleado = new Empleado("98765432C", "Carlos", Categoria.VENDEDOR, LocalDate.now().minusYears(15));
        assertEquals(1600.0, empleado.sueldoBruto(), 0.01);
    }

    @Test
    public void testSueldoBruto_Vendedor_Mas20Anios() throws LongitudIncorrecta, CampoVacio {
        // Crear nuevo empleado para tener más de 20 años de antigüedad
        Empleado empleado = new Empleado("98765432C", "Carlos", Categoria.VENDEDOR, LocalDate.now().minusYears(21));
        assertEquals(1700.0, empleado.sueldoBruto(), 0.01);
    }

    @Test
    public void testSueldoBruto_Auxiliar_Menos5Anios() {
        // Utilizar empleado estático maria
        maria.setFechaContratacion(LocalDate.now().minusDays(1));
        maria.setCategoria(Categoria.AUXILIAR);
        assertEquals(1000.0, maria.sueldoBruto(), 0.01);
    }

    @Test
    public void testSueldoBruto_Auxiliar_5a10Anios() throws LongitudIncorrecta, CampoVacio {
        // Crear nuevo empleado para tener entre 5 y 10 años de antigüedad
        Empleado empleado = new Empleado("98765432C", "Carlos", Categoria.AUXILIAR, LocalDate.now().minusYears(7));
        assertEquals(1050.0, empleado.sueldoBruto(), 0.01);
    }

    @Test
    public void testSueldoBruto_Auxiliar_10a20Anios() throws LongitudIncorrecta, CampoVacio {
        // Crear nuevo empleado para tener entre 10 y 20 años de antigüedad
        Empleado empleado = new Empleado("98765432C", "Carlos", Categoria.AUXILIAR, LocalDate.now().minusYears(15));
        assertEquals(1100.0, empleado.sueldoBruto(), 0.01);
    }

    @Test
    public void testSueldoBruto_Auxiliar_Mas20Anios() throws LongitudIncorrecta, CampoVacio {
        // Crear nuevo empleado para tener más de 20 años de antigüedad
        Empleado empleado = new Empleado("98765432C", "Carlos", Categoria.AUXILIAR, LocalDate.now().minusYears(21));
        assertEquals(1200.0, empleado.sueldoBruto(), 0.01);
    }

    // Tests auxiliares para comprobar el correcto funcionamiento de los metodos
    // sets y
    // completar la cobertura

    @Test
    public void testSetDNI() {
        Empleado empleado = new Empleado();
        String nuevoDNI = "98765432Z";
        empleado.setDNI(nuevoDNI);
        assertEquals(nuevoDNI, empleado.getDNI());
    }

    @Test
    public void testSetNombre() {
        Empleado empleado = new Empleado();
        String nuevoNombre = "Nuevo Nombre";
        empleado.setNombre(nuevoNombre);
        assertEquals(nuevoNombre, empleado.getNombre());
    }

    @Test
    public void testSetFechaContratacion() {
        Empleado empleado = new Empleado();
        LocalDate nuevaFechaContratacion = LocalDate.of(2022, 4, 1);
        empleado.setFechaContratacion(nuevaFechaContratacion);
        assertEquals(nuevaFechaContratacion, empleado.getFechaContratacion());
    }

    @Test
    public void testSetBaja() {
        Empleado empleado = new Empleado();
        empleado.setBaja(true);
        assertTrue(empleado.getBaja());
    }

    @Test
    public void testSetCategoria() {
        Empleado empleado = new Empleado();
        Categoria nuevaCategoria = Categoria.ENCARGADO;
        empleado.setCategoria(nuevaCategoria);
        assertEquals(nuevaCategoria, empleado.getCategoria());
    }
}
