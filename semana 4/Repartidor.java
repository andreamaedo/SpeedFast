package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Repartidor implements Runnable {
    private String nombre;
    private List<Pedido> pedidosAsignados;
    private Random random = new Random();

    public Repartidor(String nombre) {
        this.nombre = nombre;
        this.pedidosAsignados = new ArrayList<>();
    }

    public void agregarPedido(Pedido p) {
        pedidosAsignados.add(p);
    }

    @Override
    public void run() {
        System.out.println(">>> [INICIO] El repartidor " + nombre + " ha comenzado su ruta.");

        for (Pedido p : pedidosAsignados) {
            try {
                System.out.println("[" + nombre + "] Entregando pedido: " + p.getIdPedido() + "...");

                // Simulación de entrega con pausa aleatoria entre 1 y 4 segundos
                int tiempoSimulado = (random.nextInt(4) + 1) * 1000;
                Thread.sleep(tiempoSimulado);

                p.despachar(); // Usa el método de la interfaz implementado en Pedido
                System.out.println("[" + nombre + "] Pedido " + p.getIdPedido() + " ENTREGADO con éxito.");

            } catch (InterruptedException e) {
                System.err.println("Error en la entrega de " + nombre + ": " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("<<< [FIN] El repartidor " + nombre + " ha finalizado todas sus entregas.");
    }
}