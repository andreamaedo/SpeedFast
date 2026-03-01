package vista;

import dao.PedidoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Ventana para listar pedidos con filtros opcionales por tipo y estado.
 * Permite además eliminar un pedido seleccionado.
 */
public class VentanaListaPedidos extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> comboTipo, comboEstado;
    private JButton btnFiltrar, btnLimpiar, btnEliminar;
    private PedidoDAO dao = new PedidoDAO();

    public VentanaListaPedidos() {
        setTitle("Lista de Pedidos");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        construirUI();
        cargarTabla(null, null); // carga todos al abrir
    }

    private void construirUI() {
        setLayout(new BorderLayout(10, 10));

        // --- Panel de filtros (parte superior) ---
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros opcionales"));

        // Opción vacía "" = sin filtro
        comboTipo   = new JComboBox<>(new String[]{"", "COMIDA", "ENCOMIENDA", "EXPRESS"});
        comboEstado = new JComboBox<>(new String[]{"", "PENDIENTE", "EN_REPARTO", "ENTREGADO"});
        btnFiltrar  = new JButton("Filtrar");
        btnLimpiar  = new JButton("Limpiar filtros");

        panelFiltros.add(new JLabel("Tipo:"));
        panelFiltros.add(comboTipo);
        panelFiltros.add(new JLabel("Estado:"));
        panelFiltros.add(comboEstado);
        panelFiltros.add(btnFiltrar);
        panelFiltros.add(btnLimpiar);

        // --- Tabla central ---
        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Dirección", "Tipo", "Estado"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // --- Panel inferior: botón eliminar ---
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnEliminar = new JButton("Eliminar seleccionado");
        panelInferior.add(btnEliminar);

        add(panelFiltros, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // --- Eventos ---
        btnFiltrar.addActionListener(e -> {
            String tipo   = (String) comboTipo.getSelectedItem();
            String estado = (String) comboEstado.getSelectedItem();
            cargarTabla(tipo, estado);
        });

        btnLimpiar.addActionListener(e -> {
            comboTipo.setSelectedIndex(0);
            comboEstado.setSelectedIndex(0);
            cargarTabla(null, null);
        });

        btnEliminar.addActionListener(e -> eliminarPedido());
    }

    /**
     * Carga la tabla aplicando los filtros indicados.
     * Si tipo o estado son null/vacíos, no se filtra por ese campo.
     */
    private void cargarTabla(String tipo, String estado) {
        modeloTabla.setRowCount(0);
        List<String[]> lista = dao.readAllFiltrado(tipo, estado);
        for (String[] fila : lista) {
            modeloTabla.addRow(fila);
        }
    }

    /** Elimina el pedido seleccionado en la tabla tras confirmación. */
    private void eliminarPedido() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un pedido de la tabla.");
            return;
        }
        int id = Integer.parseInt((String) modeloTabla.getValueAt(fila, 0));
        int conf = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar este pedido?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION) {
            dao.delete(id);
            // Recarga manteniendo los filtros activos
            cargarTabla(
                    (String) comboTipo.getSelectedItem(),
                    (String) comboEstado.getSelectedItem()
            );
            JOptionPane.showMessageDialog(this, "Pedido eliminado correctamente.");
        }
    }
}