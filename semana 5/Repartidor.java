package main;

public class Repartidor implements Runnable {
    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        while (true) {
            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                break;
            }

            try {
                pedido.setEstado(EstadoPedido.EN_REPARTO);
                System.out.println("[" + nombre + "] Retiró " + pedido);

                Thread.sleep(2000);

                pedido.setEstado(EstadoPedido.ENTREGADO);
                System.out.println("[" + nombre + "] ENTREGADO: " + pedido);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}