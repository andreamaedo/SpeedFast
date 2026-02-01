package main;

public class PedidoComida extends Pedido {
    public PedidoComida(String id, String direccion, double distancia) {
        super(id, direccion, distancia);
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (15 + (2 * distanciaKm));
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("[Comida] Buscando repartidor con mochila térmica...");
    }
}
