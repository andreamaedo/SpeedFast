package main;

public class PedidoExpress extends Pedido {
    public PedidoExpress(String id, String direccion, double distancia) {
        super(id, direccion, distancia);
    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempo = 10;
        if (distanciaKm > 5) {
            tiempo += 5;
        }
        return tiempo;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("[Express] Localizando repartidor por cercanía GPS...");
    }
}
