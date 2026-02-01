package main;

public abstract class Pedido {
    private String idPedido;
    private String direccionEntrega;
    protected double distanciaKm; 

    public Pedido(String idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    // Método implementado común
    public void mostrarResumen() {
        System.out.println("------------------------------------------");
        System.out.println("ID Pedido: " + idPedido);
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
    }

    // MÉTODO ABSTRACTO (Paso 1 de la evaluación)
    public abstract int calcularTiempoEntrega();

    public void asignarRepartidor() {
        System.out.println("Asignando repartidor genérico...");
    }

    public void asignarRepartidor(String nombre) {
        System.out.println("Asignando manualmente a: " + nombre);
    }

    // Getters
    public String getIdPedido() { return idPedido; }
    public String getDireccionEntrega() { return direccionEntrega; }
}
