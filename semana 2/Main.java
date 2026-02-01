package main;

public class Main {
    public static void main(String[] args) {
        // Paso 3: Crear objetos (usaremos 6km para probar la lógica de Express)
        Pedido p1 = new PedidoComida("C-101", "Av. Central 123", 6.0);
        Pedido p2 = new PedidoEncomienda("E-202", "Calle Roble 45", 6.0);
        Pedido p3 = new PedidoExpress("X-303", "Supermercado El Sol", 6.0);

        System.out.println("=== REPORTE DE LOGÍSTICA SPEEDFAST ===");

        // Array para iterar y mostrar polimorfismo puro
        Pedido[] pedidos = {p1, p2, p3};

        for (Pedido p : pedidos) {
            p.mostrarResumen();
            System.out.println("Tiempo estimado: " + p.calcularTiempoEntrega() + " minutos");
            p.asignarRepartidor(); // Ya no sale en rojo
        }

        System.out.println("\n--- PRUEBA DE SOBRECARGA MANUAL ---");
        p1.asignarRepartidor("Juan Pérez");
    }
}
