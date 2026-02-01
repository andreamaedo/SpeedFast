package main;

public class PedidoService {

    public void crearPedido(PedidoComida pedido) {
        System.out.println(">>> Servicio: Procesando Pedido de COMIDA [" + pedido.getIdPedido() + "]");
        pedido.asignarRepartidor(); // Llama a la versión polimórfica
    }

    public void crearPedido(PedidoEncomienda pedido) {
        System.out.println(">>> Servicio: Procesando ENCOMIENDA [" + pedido.getIdPedido() + "]");
        pedido.asignarRepartidor(); // Llama a la versión polimórfica
    }

    public void crearPedido(PedidoExpress pedido) {
        System.out.println(">>> Servicio: Procesando COMPRA EXPRESS [" + pedido.getIdPedido() + "]");
        pedido.asignarRepartidor(); // Llama a la versión polimórfica
    }
}