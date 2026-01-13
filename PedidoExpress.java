package main;

public class PedidoExpress extends Pedido {
    public PedidoExpress(String id, String direccion) {
        super(id, direccion, "Compra Express");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Localizando al repartidor más cercano con disponibilidad inmediata para Compra Express...");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Asignando a " + nombreRepartidor + " por cercanía GPS inmediata.");
    }
}