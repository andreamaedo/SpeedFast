package main;

public class PedidoService {

    // Versión para Restaurante
    public void crearPedido(PedidoComida pedido) {
        System.out.println("--- Registrando Pedido de Comida ---");
        System.out.println("ID Pedido: " + pedido.getIdPedido());
        System.out.println("Dirección: " + pedido.getDireccionEntrega());

        // Llamada al método polimórfico (Sobrescrito)
        pedido.asignarRepartidor();
    }

    // Versión para Encomienda
    public void crearPedido(PedidoEncomienda pedido) {
        System.out.println("--- Registrando Pedido de Encomienda ---");
        System.out.println("ID Pedido: " + pedido.getIdPedido());
        System.out.println("Dirección: " + pedido.getDireccionEntrega());

        // Llamada al método polimórfico (Sobrescrito)
        pedido.asignarRepartidor();
    }

    // Versión para Express
    public void crearPedido(PedidoExpress pedido) {
        System.out.println("--- Registrando Pedido Express ---");
        System.out.println("ID Pedido: " + pedido.getIdPedido());
        System.out.println("Dirección: " + pedido.getDireccionEntrega());

        // Llamada al método polimórfico (Sobrescrito)
        pedido.asignarRepartidor();
    }
}