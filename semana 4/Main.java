package main;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // 1. Instanciar Repartidores
        Repartidor r1 = new Repartidor("Carlos (Moto)");
        Repartidor r2 = new Repartidor("Elena (Bici)");
        Repartidor r3 = new Repartidor("Roberto (Furgón)");

        // 2. Asignar pedidos
        r1.agregarPedido(new PedidoComida("C-101", "Av. Central 123", 4.5));
        r1.agregarPedido(new PedidoExpress("X-505", "Farmacia Vida", 2.0));

        r2.agregarPedido(new PedidoComida("C-202", "Calle Roble 45", 3.0));
        r2.agregarPedido(new PedidoComida("C-203", "Pasaje Luna 12", 1.5));

        r3.agregarPedido(new PedidoEncomienda("E-909", "Bodega Norte", 15.0));
        r3.agregarPedido(new PedidoEncomienda("E-910", "Puerto Sur", 22.0));

        // 3. Crear el ExecutorService para manejar los hilos
        // Usamos un pool de hilos fijo según la cantidad de repartidores
        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("=== SISTEMA DE ENTREGAS CONCURRENTE SPEEDFAST ===\n");

        // Lanzar los hilos
        executor.execute(r1);
        executor.execute(r2);
        executor.execute(r3);

        // 4. Cerrar el executor y esperar a que terminen
        executor.shutdown();
        try {
            // Esperar hasta que todos los hilos terminen o pase el tiempo límite
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("\n=== TODAS LAS RUTAS HAN FINALIZADO ===");

        // Opcional: Ver el historial acumulado
        // Usamos cualquier instancia para acceder al método verHistorial
        new PedidoComida("INFO", "INFO", 0).verHistorial();
    }
}