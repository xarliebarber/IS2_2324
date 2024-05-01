public class VendedorEnPlantillaJunior extends VendedorEnPlantilla {
  private double multiplicadorComision = 0;

  public VendedorEnPlantillaJunior(String nombre, String id, String dni) {
    super(nombre, id, dni);
    this.multiplicadorComision = 0.005;
  }

  @Override
  public void anhadeComision(double importe) {
    double comision = importe * multiplicadorComision;
    this.anhade(importe);
    this.setComision(this.getComision() + comision);
  }

}
