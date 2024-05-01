public class VendedorEnPlantillaSenior extends VendedorEnPlantilla {
  private double multiplicadorComision = 0;

  public VendedorEnPlantillaSenior(String nombre, String id, String dni) {
    super(nombre, id, dni);
    this.multiplicadorComision = 0.01;
  }

  @Override
  public void anhadeComision(double importe) {
    double comision = importe * multiplicadorComision;
    this.anhade(importe);
    this.setComision(this.getComision() + comision);
  }

}
