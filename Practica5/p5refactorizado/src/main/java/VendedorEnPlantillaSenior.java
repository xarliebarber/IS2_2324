public class VendedorEnPlantillaSenior extends VendedorEnPlantilla {// VMC + 2
  private double multiplicadorComision = 0;

  public VendedorEnPlantillaSenior(String nombre, String id, String dni) { // VMC + 1
    super(nombre, id, dni);
    this.multiplicadorComision = 0.01;
  }

  @Override
  public void anhadeComision(double importe) {// VMC + 1
    double comision = importe * multiplicadorComision;
    this.anhade(importe);
    this.setComision(this.getComision() + comision);
  }

}
