package main;

public class PedidoEncomienda extends Pedido {
    public PedidoEncomienda(String id, String direccion, double distancia) {
        super(id, direccion, distancia);
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (20 + (1.5 * distanciaKm));
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("[Encomienda] Validando peso para asignar vehículo de carga...");
    }
}
