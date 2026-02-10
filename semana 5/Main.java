package main;

public class Main {
    public static void main(String[] args) {
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        zonaDeCarga.agregarPedido(new PedidoComida(101, "Av. Siempre Viva 742"));
        zonaDeCarga.agregarPedido(new PedidoExpress(102, "Calle 2 123"));
        zonaDeCarga.agregarPedido(new PedidoEncomienda(103, "Pasaje Retiro 45"));
        zonaDeCarga.agregarPedido(new PedidoComida(104, "Boulevard Central 99"));
        zonaDeCarga.agregarPedido(new PedidoExpress(105, "Camino Real 10"));

        Thread r1 = new Thread(new Repartidor("Carlos", zonaDeCarga));
        Thread r2 = new Thread(new Repartidor("Elena", zonaDeCarga));
        Thread r3 = new Thread(new Repartidor("Roberto", zonaDeCarga));

        System.out.println("=== INICIANDO COORDINACIÓN DE CARGA SPEEDFAST ===\n");

        r1.start();
        r2.start();
        r3.start();

        try {
            r1.join();
            r2.join();
            r3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== TODOS LOS PEDIDOS HAN SIDO ENTREGADOS CORRECTAMENTE ===");
    }
}