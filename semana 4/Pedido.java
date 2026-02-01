package main;
import java.util.ArrayList;
import java.util.List;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {
    private String idPedido;
    private String direccionEntrega;
    protected double distanciaKm;

    // Lista estática para guardar el historial de todos los pedidos
    protected static List<String> historialEntregas = new ArrayList<>();

    public Pedido(String idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    public void mostrarResumen() {
        System.out.println("------------------------------------------");
        System.out.println("ID: " + idPedido + " | Destino: " + direccionEntrega + " | Distancia: " + distanciaKm + "km");
    }

    // Método abstracto para polimorfismo
    public abstract int calcularTiempoEntrega();

    // Sobrecarga de métodos
    public void asignarRepartidor() {
        System.out.println("Buscando repartidor disponible en la zona...");
    }

    public void asignarRepartidor(String nombre) {
        System.out.println("ASIGNACIÓN MANUAL: El repartidor " + nombre + " ha aceptado el pedido.");
    }

    // Implementación de Interfaces
    @Override
    public void despachar() {
        String registro = "Pedido " + idPedido + " ha salido hacia " + direccionEntrega;
        historialEntregas.add(registro);
        System.out.println("[DESPACHO] " + registro);
    }

    @Override
    public void cancelar() {
        System.out.println("[CANCELACIÓN] El pedido " + idPedido + " ha sido anulado.");
    }

    @Override
    public void verHistorial() {
        System.out.println("\n--- HISTORIAL GLOBAL DE VELOCIDAD SPEEDFAST ---");
        for (String registro : historialEntregas) {
            System.out.println(">> " + registro);
        }
    }

    public String getIdPedido() { return idPedido; }
}