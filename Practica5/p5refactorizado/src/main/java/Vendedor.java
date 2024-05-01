/**
 * Vendedor de la tienda.
 * Por cada vendedor se almacenan sus datos personales
 * y sus datos sobre ventas y comisiones
 */
public abstract class Vendedor {// VMC + 9

	private String id;
	private String nombre;
	private double comision;
	private double totalVentas;

	public Vendedor(String nombre, String id) {// VMC+1
		this.nombre = nombre;
		this.id = id;
	}

	/**
	 * Retorna el nombre del vendedor
	 * 
	 * @return nombre
	 */
	public String getNombre() {// VMC+1
		return nombre;
	}

	/**
	 * Retorna el id del vendedor
	 * 
	 * @return id
	 */
	public String getId() {// VMC+1
		return id;
	}

	/**
	 * Retorna la comision mensual acumulada
	 * 
	 * @return Comision total acumulada
	 */
	public double getComision() {// VMC+1
		return comision;
	}

	/**
	 * Asigna valor a la comision mensual acumulada
	 * 
	 * @param value comision a asignar
	 */
	public void setComision(double value) {// VMC+1
		this.comision = value;
	}

	/**
	 * Retorna el importe total mensual de ventas
	 * 
	 * @return importe total de ventas acumuladas
	 */
	public double getTotalVentas() {// VMC+1
		return totalVentas;
	}

	/**
	 * Asigna valor al total de ventas mensual
	 * 
	 * @param value total de ventas a asignar
	 */
	public void setTotalVentas(double value) {// VMC+1
		totalVentas = value;
	}

	/**
	 * Anhade una nueva venta al vendedor
	 * 
	 * @param importe de la venta
	 */
	public void anhade(double importe) {// VMC+1
		totalVentas += importe;
	}

	public void anhadeComision(double importe) {// VMC + 1
		/* Metodo subimplementado en las clases hijas */
	}
}
