package main;

public class PedidoEncomienda extends Pedido {
    public PedidoEncomienda(String id, String direccion) {
        super(id, direccion, "Encomienda");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Validando peso y embalaje para asignar repartidor de Encomienda...");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Asignando a " + nombreRepartidor + ". Validando peso del paquete antes de entregar.");
    }
}