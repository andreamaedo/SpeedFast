package main;

public class Main {
    public static void main(String[] args) {
        // Instancias de cada subclase
        Pedido pedido1 = new PedidoComida("C-001", "Av. Central 123");
        Pedido pedido2 = new PedidoEncomienda("E-505", "Calle Roble 45");
        Pedido pedido3 = new PedidoExpress("X-999", "Supermercado El Sol");

        System.out.println("--- PRUEBAS DE SOBRESCRITURA (Polimorfismo) ---");
        pedido1.asignarRepartidor();
        pedido2.asignarRepartidor();
        pedido3.asignarRepartidor();

        System.out.println("\n--- PRUEBAS DE SOBRECARGA ---");
        pedido1.asignarRepartidor("Juan Pérez");
        pedido2.asignarRepartidor("María López");
        pedido3.asignarRepartidor("Carlos Ruiz");
    }
}