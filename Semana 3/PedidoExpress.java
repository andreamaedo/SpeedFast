package main;

public class PedidoExpress extends Pedido {
    public PedidoExpress(String id, String direccion, double distancia) {
        super(id, direccion, distancia);
    }

    @Override
    public int calcularTiempoEntrega() {
        return (distanciaKm > 5) ? 15 : 10;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("[Express] Localizando repartidor por cercanía GPS...");
    }
}