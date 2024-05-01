public class vendedorEnPracticas extends Vendedor {
	
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en practicas
	 * @param nombre
	 * @param dni
	 */
	public vendedorEnPracticas(String nombre, String id, String dni) {//VMC + 1 
		super(nombre, id);
		this.dni= dni;
	}
	
	public String getDni() {//VMC + 1 
		return dni;
	}

	@Override
	public boolean equals(Object obj) {//VMC + 3 //Cog + 2 
		if (!(obj instanceof vendedorEnPracticas)) 
			return false;
		vendedorEnPracticas v = (vendedorEnPracticas) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));
	}
}
