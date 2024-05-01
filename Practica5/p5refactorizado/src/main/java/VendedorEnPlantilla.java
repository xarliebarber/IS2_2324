public class VendedorEnPlantilla extends Vendedor {

	private String dni;

	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * 
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni) {// VMC+1
		super(nombre, id);
		this.dni = dni;
	}

	public String dni() {// VMC+1
		return dni;
	}

	@Override
	public boolean equals(Object obj) {// VMC+3 // CCog + 2
		if (!(obj instanceof VendedorEnPlantilla))
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.dni().equals(dni()));
	}

	@Override
	public void anhadeComision(double importe) {
		/* Metodo subimplementado en las clases hijas */
	}
}
