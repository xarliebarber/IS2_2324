public class VendedorEnPlantilla extends Vendedor {
	
	private TipoVendedor tipo;
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni, TipoVendedor tipo) {
		super(nombre, id);
		this.tipo = tipo;
		this.dni=dni;
	}
	
	public TipoVendedor tipo() {
		return tipo;
	}
	
	public String dni() {
		return dni;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof VendedorEnPlantilla)) 
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.dni().equals(dni()));
	}
}
