package main;

public class PedidoComida extends Pedido {
    public PedidoComida(String id, String direccion) {
        super(id, direccion, "Comida");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Buscando repartidor con mochila térmica para pedido de Comida...");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Asignando a " + nombreRepartidor + ". Verificando que tenga mochila térmica activa.");
    }
}