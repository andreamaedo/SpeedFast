package main;

public class PedidoService {
    public void crearPedido(PedidoComida pedido) {
        System.out.println(">>> Servicio: Procesando COMIDA [" + pedido.getId() + "]");

    }

    public void crearPedido(PedidoEncomienda pedido) {
        System.out.println(">>> Servicio: Procesando ENCOMIENDA [" + pedido.getId() + "]");
    }

    public void crearPedido(PedidoExpress pedido) {
        System.out.println(">>> Servicio: Procesando COMPRA EXPRESS [" + pedido.getId() + "]");
    }
}