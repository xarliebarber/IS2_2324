
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import fundamentos.Menu;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Gestion de las comisiones de vendedores de una tienda
 */
public class GestionComisiones {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {// VMC + 15 // CCog + 32
		// opciones del menu
		final int NUEVA_VENTA = 0, VENDEDOR_DEL_MES = 1, VENDEDORES = 2;

		// variables auxiliares
		String dni;
		Lectura lect;

		List<Vendedor> vendedores;
		List<Vendedor> resultado;
		String msj;

		// crea la tienda
		Tienda tienda = new Tienda("C:\\temp\\datosTienda.txt");

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
				case NUEVA_VENTA:// VMC + 1
					lect = new Lectura("Datos Venta");
					lect.creaEntrada("ID Vendedor", "");
					lect.creaEntrada("Importe", "");
					lect.esperaYCierra();
					dni = lect.leeString("ID Vendedor");
					double importe = lect.leeDouble("Importe");
					try {
						if (!tienda.anhadeVenta(dni, importe)) {// VMC + 1 // CCog + 3
							mensaje("ERROR", "El vendedor no existe");
						}
					} catch (DataAccessException e) {// VMC + 1 // CCog + 3
						mensaje("ERROR", "No se pudo guardar el cambio");
					}
					break;

				case VENDEDOR_DEL_MES:// VMC + 1
					try {
						vendedores = tienda.vendedores();
						resultado = new LinkedList<Vendedor>();
						double maxVentas = 0.0;
						for (Vendedor v : vendedores) {// VMC + 1 // CCog + 3
							if (v.getTotalVentas() > maxVentas) {// VMC + 1 // CCog + 4
								maxVentas = v.getTotalVentas();
								resultado.clear();
								resultado.add(v);
							} else if (v.getTotalVentas() == maxVentas) {// VMC + 1 // CCog + 1
								resultado.add(v);
							}
						}

						msj = "";
						for (Vendedor vn : resultado) {// VMC + 1 // CCog + 3
							msj += vn.getNombre() + "\n";
						}
						mensaje("VENDEDORES DEL MES", msj);

					} catch (DataAccessException e) {// VMC + 1 // CCog + 3
						mensaje("ERROR", "No se pudo acceder a los datos");
					}
					break;

				case VENDEDORES:// VMC + 1
					try {
						vendedores = tienda.vendedores();
						System.out.println(vendedores.size());
						Collections.sort(vendedores, new Comparator<Vendedor>() {
							public int compare(Vendedor o1, Vendedor o2) {
								if (o1.getTotalVentas() > o2.getTotalVentas())// VMC + 1 // CCog + 4
									return -1;
								else if (o1.getTotalVentas() < o2.getTotalVentas())// VMC + 1 // CCog + 0
									return 1;
								return 0;
							}
						});
						msj = "";
						for (Vendedor vn : vendedores) {// VMC + 1 // CCog + 3
							msj += vn.getNombre() + " (" + vn.getId() + ") " + vn.getTotalVentas() + "\n";
						}
						mensaje("VENDEDORES", msj);
					} catch (DataAccessException e) {// VMC + 1 // CCog + 3
						mensaje("ERROR", "No se pudo acceder a los datos");
					}
					break;
			}
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
