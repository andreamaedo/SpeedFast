package main;

public class Main {
    public static void main(String[] args) {
        // Creación de objetos usando Polimorfismo
        Pedido p1 = new PedidoComida("C-101", "Av. Central 123", 6.0);
        Pedido p2 = new PedidoEncomienda("E-202", "Calle Roble 45", 10.0);
        Pedido p3 = new PedidoExpress("X-303", "Supermercado El Sol", 3.0);

        System.out.println("=== REPORTE DE LOGÍSTICA SPEEDFAST ===");

        Pedido[] pedidos = {p1, p2, p3};

        for (Pedido p : pedidos) {
            p.mostrarResumen();
            System.out.println("Tiempo estimado: " + p.calcularTiempoEntrega() + " min");
            p.asignarRepartidor(); // Polimorfismo puro
            p.despachar();         // Uso de interfaz
        }

        System.out.println("\n--- PRUEBAS DE FUNCIONALIDADES EXTRA ---");
        p1.asignarRepartidor("Juan Pérez"); // Sobrecarga
        p3.cancelar();                      // Uso de interfaz

        // Mostrar el historial acumulado
        p1.verHistorial();
    }
}