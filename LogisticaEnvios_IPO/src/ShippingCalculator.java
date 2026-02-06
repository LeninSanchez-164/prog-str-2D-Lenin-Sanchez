public class ShippingCalculator {

    public ShippingCalculator() {}

    private final double COSTO_PESO = 12.0;
    private final double IVA_TASA = 0.16;

    public double subtotal;
    public double montoIva;
    public double total;

    public void process(double pesoKg, int distanciaKm, int tipoServicio, boolean zonaRemota) {
        calcularSubtotal(pesoKg, distanciaKm, tipoServicio, zonaRemota);
        calcularIva();
        calcularTotal();
    }

    private void calcularSubtotal(double pesoKg, int distanciaKm, int tipoServicio, boolean zonaRemota) {
        double costoBase = (tipoServicio == 1) ? 50.0 : 90.0;
        double costoDistancia = 0;

        if (distanciaKm <= 50) {
            costoDistancia = 20.0;
        } else if (distanciaKm <= 200) {
            costoDistancia = 60.0;
        } else {
            costoDistancia = 120.0;
        }

        this.subtotal = costoBase + (pesoKg * COSTO_PESO) + costoDistancia;

        if (zonaRemota) {
            this.subtotal = this.subtotal * 1.10;
        }
    }

    private void calcularIva() {
        this.montoIva = this.subtotal * IVA_TASA;
    }

    private void calcularTotal() {
        this.total = this.subtotal + this.montoIva;
    }
}