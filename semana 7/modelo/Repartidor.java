package modelo;
import dao.EntregaDAO;
import java.util.Random;

public class Repartidor implements Runnable {
    private int id;
    private String nombre;
    private ZonaDeCarga zona;
    private EntregaDAO entregaDAO = new EntregaDAO();

    // Constructor para DAO (Listar)
    public Repartidor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Constructor para Hilos (Procesar)
    public Repartidor(int id, String nombre, ZonaDeCarga zona) {
        this.id = id;
        this.nombre = nombre;
        this.zona = zona;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Pedido p = zona.retirarPedido();
                if (p == null) break;
                p.setEstado(EstadoPedido.EN_REPARTO);
                Thread.sleep(new Random().nextInt(2000) + 2000);
                p.setEstado(EstadoPedido.ENTREGADO);
                entregaDAO.guardar(p.getId(), this.id);
            }
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
}