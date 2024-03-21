package es.unican.is2;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Clase que representa un empleado de la franquicia,
 * con sus datos personales
 * y su estado en la franquicia (baja y categoria)
 */
public class Empleado {

	private String DNI;
	private String nombre;
	private Categoria categoria;
	private LocalDate fechaContratacion;
	private boolean baja = false;

	/**
	 * Constructor del empleado con DNI, nombre, categoria y fecha de contratacion.
	 * Por defecto, baja se inicializa a false.
	 * 
	 * @param DNI
	 * @param nombre
	 * @param categoria
	 * @param fechaContratacion
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion)
			throws LongitudIncorrecta, CampoVacio {
		if (!(nombre != null)) {
			throw new CampoVacio();
		}
		this.nombre = nombre;
		if (!(DNI.length() == 9)) {
			throw new LongitudIncorrecta();
		}
		this.DNI = DNI;
		if (!(categoria != null)) {
			throw new CampoVacio();
		}
		this.categoria = categoria;
		if (!(fechaContratacion != null)) {
			throw new CampoVacio();
		}
		this.fechaContratacion = fechaContratacion;
	}

	/**
	 * Retorna el sueldo bruto del empleado
	 */
	public double sueldoBruto() {

		double sueldoFinal = 0.0;
		switch (categoria) {
			case ENCARGADO:
				sueldoFinal += 2000;
				break;
			case VENDEDOR:
				sueldoFinal += 1500;
				break;
			case AUXILIAR:
				sueldoFinal += 1000;
				break;
			default:
				break;
		}
		LocalDate fechaActual = LocalDate.now();
		if (fechaContratacion.isAfter(fechaActual)) {
			throw new IllegalArgumentException("La fecha de contratación no puede ser posterior al día de hoy");
		}
		long antiguedad = ChronoUnit.YEARS.between(fechaContratacion, fechaActual);
		if (antiguedad > 5 && antiguedad <= 10) {
			sueldoFinal += 50;
		} else if (antiguedad > 10 && antiguedad <= 20) {
			sueldoFinal += 100;
		} else if (antiguedad > 20) {
			sueldoFinal += 200;
		}

		return sueldoFinal;
	}

	/**
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		if (this.baja) {
			throw new IllegalStateException();
		}
		this.baja = true;
	}

	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		if (!this.baja) {
			throw new IllegalStateException();
		}
		this.baja = false;
	}

	/**
	 * Retorna el dni del vendedor
	 * 
	 * @return id
	 */
	public String getDNI() {
		return DNI;
	}

	/**
	 * Retorna el nombre del vendedor
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Retorna la categoria del empleado
	 * 
	 * @return categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Retorna la fecha de contrato
	 * 
	 * @return Fecha de contratacion
	 */
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}

	/**
	 * Retorna si el empleado est� de baja
	 * 
	 * @return true si esta de baja
	 *         false si no lo esta
	 */
	public boolean getBaja() {
		return baja;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
