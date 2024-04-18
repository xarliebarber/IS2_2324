/**
 * Vendedor de la tienda. 
 * Por cada vendedor se almacenan sus datos personales 
 * y sus datos sobre ventas y comisiones
 */
public abstract class Vendedor {
	
	private String id;
	private String nombre;
	private double c;
	private double totalVentas;
	
	public Vendedor(String nombre, String id) {
		this.nombre = nombre;
		this.id = id;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna el id del vendedor
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Retorna la comision mensual acumulada
	 * @return Comision total acumulada
	 */
	public double getC() {
		return c;
	}
	
	/**
	 * Asigna valor a la comision mensual acumulada
	 * @param value comision a asignar
	 */
	public void setC(double value) {
		this.c = value;
	}
	
	/**
	 * Retorna el importe total mensual de ventas
	 * @return importe total de ventas acumuladas
	 */
	public double getTotalVentas( ) {
		return totalVentas;
	}
	
	/**
	 * Asigna valor al total de ventas mensual
	 * @param value total de ventas a asignar
	 */
	public void setTotalVentas(double value) {
		totalVentas = value;
	}
	
	/**
	 * Anhade una nueva venta al vendedor
	 * @param importe de la venta
	 */
	public void anhade(double importe)  {
		totalVentas += importe;
	}
}
