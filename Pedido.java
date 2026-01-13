package main;

public class Pedido {
    private String idPedido;
    private String direccionEntrega;
    private String tipoPedido;

    public Pedido(String idPedido, String direccionEntrega, String tipoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
    }

    // Método que será SOBRESCRITO (Polimorfismo)
    public void asignarRepartidor() {
        System.out.println("Asignando un repartidor genérico para el pedido: " + idPedido);
    }

    // Método SOBRECARGADO (Paso 3)
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Asignando manualmente a: " + nombreRepartidor);
    }

    // Getters
    public String getIdPedido() { return idPedido; }
    public String getDireccionEntrega() { return direccionEntrega; }
}