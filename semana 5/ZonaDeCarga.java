package main;
import java.util.LinkedList;
import java.util.Queue;

public class ZonaDeCarga {
    private Queue<Pedido> pedidosPendientes = new LinkedList<>();

    public synchronized void agregarPedido(Pedido p) {
        pedidosPendientes.add(p);
    }

    public synchronized Pedido retirarPedido() {
        return pedidosPendientes.poll();
    }
}