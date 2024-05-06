
import java.util.List;

import fundamentos.Menu;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Gestion de las comisiones de vendedores de una tienda
 */
public class GestionComisiones { // VMC = 14 // CCog = 8
	private static final String ERROR = "ERROR";

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {// VMC + 4 // CCog + 3
		// opciones del menu
		final int NUEVA_VENTA = 0, VENDEDOR_DEL_MES = 1, VENDEDORES = 2;
		// crea la tienda
		Tienda tienda = new Tienda(
				"C:\\Users\\carlo\\IngenieriaDelSoftwareII\\IS2_2324\\Practica5\\p5original\\src\\main\\resources\\datosTienda.txt");

		// crea la ventana de menu
		Menu menu = new Menu("Comisiones tienda");
		menu.insertaOpcion("Anhadir venta", NUEVA_VENTA);
		menu.insertaOpcion("Vendedor del mes", VENDEDOR_DEL_MES);
		menu.insertaOpcion("Vendedores por ventas", VENDEDORES);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {// VMC + 1 // CCog + 1
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {// VMC + 0 // CCog + 2
				case NUEVA_VENTA: // VMC + 1
					nuevaVenta(tienda);
					break;

				case VENDEDOR_DEL_MES: // VMC + 1
					vendedorDelMes(tienda);
					break;

				case VENDEDORES:// VMC + 1
					vendedores(tienda);
					break;
				default:
					break;
			}
			break;
		}
	}

	private static void vendedores(Tienda tienda) {// VMC + 2 // CCog + 1
		List<Vendedor> vendedores;
		String msj;
		try {
			vendedores = tienda.vendedores();
			System.out.println(vendedores.size());
			Tienda.ordenarVendedoresPorVentasDescendente(vendedores);
			msj = obtenerTextoVendedores(vendedores);
			mensaje("VENDEDORES", msj);
		} catch (DataAccessException e) {// VMC + 1 // CCog + 1
			mensaje(ERROR, "No se pudo acceder a los datos");
		}
	}

	private static String obtenerTextoVendedores(List<Vendedor> vendedores) {// VMC + 2 // CCog + 1
		String msj = new String();
		for (Vendedor vn : vendedores) {// VMC + 1 // CCog + 1
			msj += vn.getNombre() + " (" + vn.getId() + ") " + vn.getTotalVentas() + "\n";
		}
		return msj;
	}

	private static void vendedorDelMes(Tienda tienda) {// VMC + 2 // CCog + 1
		List<Vendedor> vendedoresOrd;
		Vendedor resultado;
		String msj;
		try {
			vendedoresOrd = tienda.vendedores();
			Tienda.ordenarVendedoresPorVentasDescendente(vendedoresOrd);
			resultado = vendedoresOrd.get(0);
			msj = "";
			msj += resultado.getNombre() + "\n";
			mensaje("VENDEDORES DEL MES", msj);

		} catch (DataAccessException e) { // VMC + 1 // CCog + 1
			mensaje(ERROR, "No se pudo acceder a los datos");
		}
	}

	private static void nuevaVenta(Tienda tienda) {// VMC + 3 // CCog + 2
		String dni;
		Lectura lect;

		try {
			lect = new Lectura("Datos Venta");
			lect.creaEntrada("ID Vendedor", "");
			lect.creaEntrada("Importe", "");
			lect.esperaYCierra();
			dni = lect.leeString("ID Vendedor");
			double importe = lect.leeDouble("Importe");
			if (!tienda.anhadeVenta(dni, importe)) { // VMC + 1 // CCog + 1
				mensaje(ERROR, "El vendedor no existe");
			}
		} catch (DataAccessException e) { // VMC + 1 // CCog + 1
			mensaje(ERROR, "No se pudo guardar el cambio");
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * 
	 * @param titulo Titulo de la ventana
	 * @param txt    Texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) { // VMC + 1 // CCog + 0
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
