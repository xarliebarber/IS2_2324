import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Clase que representa una tienda con un conjunto de vendedores. Gestiona las
 * ventas realizadas y las comisiones asignadas a cada vendedor. Los datos de la
 * tienda se almacenan en un fichero de texto que se pasa como parametro al
 * crear la tienda
 */
public class Tienda {// VMC + 41 // CCog + 30
	private static final String JUNIOR = "Junior";

	private List<Vendedor> lista = new LinkedList<>();
	private String direccion;
	private String nombre;

	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * 
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) { // VMCmet+1
		this.datos = datos;
	}

	/**
	 * Retorna la direccion de la tienda
	 * 
	 * @return Direccion de la tienda
	 */
	public String direccion() {// VMCmet+1
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * 
	 * @return Nombre de la tienda
	 */
	public String nombre() {// VMCmet+1
		return nombre;
	}

	/**
	 * Anhade un nuevo vendedor a la tienda
	 * 
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido
	 *         false si ya existe el vendedor
	 */
	public boolean anhade(Vendedor nuevo) throws DataAccessException {// VMCmet + 2 //CCog + 1
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) {// VMC + 1 // CCog + 1
			return false;
		}
		lista.add(nuevo);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo id se pasa como argumento
	 * 
	 * @param id
	 * @return true si se elimina el vendedor false si no existe el vendedor
	 */
	public boolean eliminaVendedor(String id) throws DataAccessException {// VMCmet + 2//CCog + 1
		Vendedor v = buscaVendedor(id);
		if (v == null) {// VMC + 1 // CCog + 1
			return false;
		}
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Anhade una venta a un vendedor
	 * 
	 * @param id      Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se anhade la venta false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws DataAccessException {// VMCmetodo +3 //CCogmetodo + 2
		Vendedor v = buscaVendedor(id);
		if (v == null) {// VMC + 1 // CCog + 1
			return false;
		}

		if (v instanceof VendedorEnPlantilla) {// VMC + 1 // CCog + 1
			VendedorEnPlantilla vEP = (VendedorEnPlantilla) (v);
			vEP.anhadeComision(importe);
		}

		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese dni o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) throws DataAccessException {// VMCmetodo+3//CCogmetodo + 3

		lista = new LinkedList<>();

		// abre el fichero y lee los datos
		procesaVendedores();

		for (Vendedor v : lista) { // VMC + 1 // CCog + 1
			if (v.getId().equals(id)) { // VMC + 1 // CCog + 2
				return v;
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda
	 * 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> vendedores() throws DataAccessException {// VMCmetodo+1//CCogmetodo + 0
		lista = new LinkedList<>();

		procesaVendedores();

		return lista;

	}

	private void procesaVendedores() throws DataAccessException {// VMCmet + 7 // CCogmet + 5
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(datos));
			// configura el formato de numeros
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven;
			String tipo;
			while (in.hasNext() && !(tipo = in.next()).equals(JUNIOR)) { // VMC + 2 // CCog + 1
				procesaVendedor(in, tipo);
			}
			// lee los vendedores junior
			while (in.hasNext() && !(tipo = in.next()).equals("Practicas")) { // VMC + 2 // CCog + 1
				procesaVendedor(in, tipo);
			}
			while (in.hasNext()) { // VMC + 1 // CCog + 1
				in.next();
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni = in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new vendedorEnPracticas(nombre, idIn, dni);
				ven.setTotalVentas(totalVentas);
				lista.add(ven);
			}
		} catch (FileNotFoundException e) { // VMC + 1 //CCog + 1
			throw new DataAccessException();
		} finally {
			if (in != null) { // VMC + 1 // CCog + 1
				in.close();
			}
		} // try
	}

	private void procesaVendedor(Scanner in, String tipo) {// VMCmet + 4 // CCogmet + 2

		Vendedor ven;
		double totalComision = 0.0;
		String nombre = in.next();
		in.next();
		String idIn = in.next();
		in.next();
		String dni = in.next();
		in.next();
		double totalVentas = in.nextDouble();
		in.next();
		if (in.hasNextDouble()) {// VMC + 1 // CCog + 1
			totalComision = in.nextDouble();
		}
		switch (tipo) {// VMC + 0 // CCog + 1
			case (JUNIOR):// VMC + 1 // CCog +0
				ven = new VendedorEnPlantillaJunior(nombre, idIn, dni);
				break;
			default:// VMC + 1 // CCog + 0
				ven = new VendedorEnPlantillaSenior(nombre, idIn, dni);
				break;
		}
		ven.setTotalVentas(totalVentas);
		ven.setComision(totalComision);
		lista.add(ven);
	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de
	 * los vendedores
	 */
	private void vuelcaDatos() throws DataAccessException {// VMCmetodo + 9//CCogmetodo + 11

		PrintWriter out = null;

		try {
			List<Vendedor> senior = new LinkedList<>();
			List<Vendedor> junior = new LinkedList<>();
			List<Vendedor> practicas = new LinkedList<>();

			for (Vendedor v : lista) {// VMC + 1 // CCog + 1
				if (v instanceof vendedorEnPracticas) {// VMC + 1 // CCog + 2
					practicas.add(v);
				} else if (v instanceof VendedorEnPlantilla) {// VMC + 1 // CCog + 1
					VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
					if (vp instanceof VendedorEnPlantillaJunior)// VMC + 1 // CCog + 3
						junior.add(vp);
					else // VMC + 1 // CCog + 1
						senior.add(vp);
				}
			}

			out = new PrintWriter(new FileWriter(datos));

			out.println(nombre);
			out.println(direccion);
			vuelcaVendedor(out, senior);
			out.println();
			out.println("Practicas");
			for (Vendedor v : practicas) { // VMC + 1 // CCog + 1
				vendedorEnPracticas v3 = (vendedorEnPracticas) v;
				out.println("  Nombre: " + v3.getNombre() + " Id: " + v3.getId() + " DNI: " + v3.getDni()
						+ " TotalVentasMes: " + v3.getTotalVentas());
			}
		} catch (IOException e) {// VMC + 1 // CCog + 1
			throw new DataAccessException();

		} finally {
			if (out != null)// VMC + 1 // CCog + 1
				out.close();
		}
	}

	private void vuelcaVendedor(PrintWriter out, List<Vendedor> senior) {// VMCmet + 2 // CCogmet
		// + 1
		String[] longevidadVendedor = { "Senior", JUNIOR };
		for (int i = 0; i < 2; i++) { // VMC + 1 // CCog + 1
			out.println();
			out.println(longevidadVendedor[i]);
			for (Vendedor v : senior) {// VMC + 1 // CCog + 1
				VendedorEnPlantilla v1 = (VendedorEnPlantilla) v;
				out.println("  Nombre: " + v1.getNombre() + " Id: " + v1.getId() + " DNI: " + v1.dni()
						+ " TotalVentasMes: " + v1.getTotalVentas() + " TotalComision: " + v1.getComision());
			}
		}

	}

	static void ordenarVendedoresPorVentasDescendente(List<Vendedor> vendedores) {// VMCmet + 3 // CCogmet + 3
		Collections.sort(vendedores, new Comparator<>() {
			public int compare(Vendedor o1, Vendedor o2) {
				if (o1.getTotalVentas() > o2.getTotalVentas())// VMC + 1 // CCog + 2
					return -1;
				else if (o1.getTotalVentas() < o2.getTotalVentas())// VMC + 1 // CCog + 1
					return 1;
				return 0;
			}
		});
	}

}
