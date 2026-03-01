package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Ventana principal de SpeedFast.
 * Punto de entrada a todas las funcionalidades del sistema:
 * registro de pedidos, gestión de repartidores, entregas y reparto por hilos.
 */
public class VentanaPrincipal extends JFrame {

    private JButton btnRegistrarPedido;
    private JButton btnListarPedidos;
    private JButton btnIniciarEntregas;
    private JButton btnRepartidores;
    private JButton btnEntregas;
    private JTextArea areaConsola;

    private ArrayList<Pedido> listaPedidos = new ArrayList<>();

    public VentanaPrincipal() {
        setTitle("SpeedFast - Gestión de Entregas");
        setSize(620, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        construirUI();
        registrarEventos();
    }

    private void construirUI() {
        setLayout(new BorderLayout(10, 10));

        // --- Panel izquierdo: botones de navegación ---
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 1, 8, 8));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnRegistrarPedido = new JButton("📦 Registrar Pedido");
        btnListarPedidos   = new JButton("📋 Listar Pedidos");
        btnIniciarEntregas = new JButton("🚀 Iniciar Reparto");
        btnRepartidores    = new JButton("🧑 Gestionar Repartidores");
        btnEntregas        = new JButton("📬 Gestionar Entregas");

        panelBotones.add(btnRegistrarPedido);
        panelBotones.add(btnListarPedidos);
        panelBotones.add(btnIniciarEntregas);
        panelBotones.add(btnRepartidores);
        panelBotones.add(btnEntregas);

        // --- Panel central: consola de log ---
        areaConsola = new JTextArea();
        areaConsola.setEditable(false);
        areaConsola.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaConsola.setBorder(BorderFactory.createTitledBorder("Consola de actividad"));

        add(panelBotones, BorderLayout.WEST);
        add(new JScrollPane(areaConsola), BorderLayout.CENTER);
    }

    private void registrarEventos() {

        // Abre ventana para registrar un nuevo pedido
        btnRegistrarPedido.addActionListener(e -> {
            VentanaRegistroPedido v = new VentanaRegistroPedido(listaPedidos);
            v.setVisible(true);
        });

        // Abre la ventana de lista con filtros (conectada a la BD)
        btnListarPedidos.addActionListener(e -> new VentanaListaPedidos().setVisible(true));

        // Inicia el proceso de reparto con hilos
        btnIniciarEntregas.addActionListener(e -> {
            if (listaPedidos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay pedidos pendientes para repartir.");
                return;
            }
            iniciarProcesoDeReparto();
        });

        // Abre ventana CRUD de repartidores
        btnRepartidores.addActionListener(e -> new VentanaRepartidores().setVisible(true));

        // Abre ventana CRUD de entregas
        btnEntregas.addActionListener(e -> new VentanaEntregas().setVisible(true));
    }

    /**
     * Distribuye los pedidos en memoria en la zona de carga
     * y lanza dos hilos de reparto en paralelo.
     */
    private void iniciarProcesoDeReparto() {
        ZonaDeCarga zona = new ZonaDeCarga();
        for (Pedido p : listaPedidos) {
            zona.agregarPedido(p);
        }
        listaPedidos.clear();

        log(">>> Iniciando hilos de reparto...");

        Thread hilo1 = new Thread(new Repartidor(1, "Carlos", zona));
        Thread hilo2 = new Thread(new Repartidor(2, "Elena", zona));

        hilo1.start();
        hilo2.start();

        log(">>> Hilos iniciados: Carlos y Elena en camino.");
    }

    /** Agrega un mensaje con salto de línea al área de consola. */
    private void log(String mensaje) {
        areaConsola.append(mensaje + "\n");
    }
}