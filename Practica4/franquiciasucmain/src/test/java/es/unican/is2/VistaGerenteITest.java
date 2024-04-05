package es.unican.is2;

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
    private ITiendasDAO tiendasDAO;
    private IEmpleadosDAO empleadosDAO;

    @BeforeEach
    public void setUp() throws DataAccessException, LongitudIncorrecta, CampoVacio, OperacionNoValidaException {
        //

        this.tiendas = new GestionTiendas(tiendasDAO);
        this.empleados = new GestionEmpleados(tiendasDAO, empleadosDAO);

        gui = new VistaGerente(tiendas, empleados);
        demo = new FrameFixture(gui);
        gui.setVisible(true);
    }

    @Test
    public void testNombreTienda() {

        demo.textBox("txtNombreTienda").setText("");
        demo.textBox("txtNombreTienda").enterText("Tienda A");

        demo.button("btnBuscar").click();

        demo.textBox("txtNombreTienda").requireText("Tienda A");
        demo.textBox("txtDireccionTienda").requireText("Direccion A");
        demo.textBox("listNombreEmpleados").requireText("Juan Perez, Maria Rodriguez");
        demo.textBox("txtTotalContribuyente").requireText("4750.0");

    }
}