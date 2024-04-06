package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;

import es.unican.is2.EmpleadosDAO;
import es.unican.is2.GestionEmpleados;
import es.unican.is2.GestionTiendas;
import es.unican.is2.TiendasDAO;
import es.unican.is2.VistaGerente;

@SuppressWarnings("unused")

public class VistaGerenteITest {
    private FrameFixture demo;

    private IGestionTiendas tiendas;
    private IGestionEmpleados empleados;
    private VistaGerente gui;

    @BeforeEach
    public void setUp() throws DataAccessException {
        //

        ITiendasDAO tiendasDAO = new TiendasDAO();
        IEmpleadosDAO empleadosDAO = new EmpleadosDAO();

        this.tiendas = new GestionTiendas(tiendasDAO);
        this.empleados = new GestionEmpleados(tiendasDAO, empleadosDAO);

        gui = new VistaGerente(tiendas, empleados);
        demo = new FrameFixture(gui);
        gui.setVisible(true);
    }

    @Test
    public void testNombreTiendasABC() {
        // Comprobaciones exitosas
        demo.textBox("txtNombreTienda").enterText("Tienda A");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreTienda").requireText("Tienda A");
        demo.textBox("txtDireccionTienda").requireText("Direccion A");
        demo.list("listNombreEmpleados").requireSelectedItems("Juan Perez, Maria Rodriguez");
        demo.textBox("txtTotalContribuyente").requireText("4750.0");

        // Comprobacion de errores
        assertThrows(AssertionError.class, () -> {
            demo.textBox("txtNombreTienda").requireText("Tienda B");
        });
        assertThrows(AssertionError.class, () -> {
            demo.textBox("txtDireccionTienda").requireText("Direccion Extraña");
        });

        assertThrows(AssertionError.class, () -> {
            demo.textBox("txtTotalContribuyente").requireText("9999999999999.0");
        });

        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        demo.textBox("txtNombreTienda").deleteText();
        demo.textBox("txtNombreTienda").enterText("Tienda B");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreTienda").requireText("Tienda B");
        demo.textBox("txtDireccionTienda").requireText("Direccion B");
        demo.list("listNombreEmpleados").requireSelectedItems("Lucia Ibañez");
        demo.textBox("txtTotalContribuyente").requireText("2100.0");

        // Comprobacion de errores
        assertThrows(AssertionError.class, () -> {
            demo.textBox("txtNombreTienda").requireText("Tienda A");
        });
        assertThrows(AssertionError.class, () -> {
            demo.textBox("txtDireccionTienda").requireText("Direccion Extraña");
        });

        assertThrows(AssertionError.class, () -> {
            demo.textBox("txtTotalContribuyente").requireText("9999999999999.0");
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        demo.textBox("txtNombreTienda").deleteText();
        demo.textBox("txtNombreTienda").enterText("Tienda C");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreTienda").requireText("Tienda C");
        demo.textBox("txtDireccionTienda").requireText("Direccion C");
        demo.list("listNombreEmpleados").requireEnabled();
        demo.textBox("txtTotalContribuyente").requireText("0.0");

        // Comprobacion de errores
        assertThrows(AssertionError.class, () -> {
            demo.textBox("txtNombreTienda").requireText("Tienda A");
        });
        assertThrows(AssertionError.class, () -> {
            demo.textBox("txtDireccionTienda").requireText("Direccion Extraña");
        });

        assertThrows(AssertionError.class, () -> {
            demo.textBox("txtTotalContribuyente").requireText("9999999999999.0");
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demo.textBox("txtNombreTienda").deleteText();
        demo.textBox("txtNombreTienda").enterText("Tienda Inexistente");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreTienda").requireText("Tienda Inexistente");
        demo.textBox("txtDireccionTienda").requireText("Tienda no existe");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
