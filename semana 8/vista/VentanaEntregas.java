package vista;

import dao.EntregaDAO;
import dao.PedidoDAO;
import dao.RepartidorDAO;
import modelo.Repartidor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Ventana para gestionar entregas con operaciones CRUD completas.
 * Usa JComboBox para seleccionar Pedido y Repartidor desde la BD.
 */
public class VentanaEntregas extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> comboPedido, comboRepartidor;
    private JButton btnRegistrar, btnEditar, btnEliminar, btnRefrescar;

    private EntregaDAO entregaDAO       = new EntregaDAO();
    private PedidoDAO pedidoDAO         = new PedidoDAO();
    private RepartidorDAO repartidorDAO = new RepartidorDAO();

    // Listas para obtener los ids reales detrás del texto del combo
    private List<String[]> listaPedidos;
    private List<Repartidor> listaRepartidores;

    public VentanaEntregas() {
        setTitle("Gestión de Entregas");
        setSize(700, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        construirUI();
        cargarCombos();
        cargarTabla();
    }

    private void construirUI() {
        setLayout(new BorderLayout(10, 10));

        // --- Panel superior: selección de pedido y repartidor ---
        JPanel panelForm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelForm.setBorder(BorderFactory.createTitledBorder("Registrar / Editar entrega"));

        comboPedido      = new JComboBox<>();
        comboRepartidor  = new JComboBox<>();
        btnRegistrar     = new JButton("Registrar");
        btnEditar        = new JButton("Editar");
        btnEliminar      = new JButton("Eliminar");
        btnRefrescar     = new JButton("Refrescar");

        panelForm.add(new JLabel("Pedido:"));
        panelForm.add(comboPedido);
        panelForm.add(new JLabel("Repartidor:"));
        panelForm.add(comboRepartidor);
        panelForm.add(btnRegistrar);
        panelForm.add(btnEditar);
        panelForm.add(btnEliminar);
        panelForm.add(btnRefrescar);

        // --- Tabla central ---
        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Pedido (dirección)", "Repartidor", "Fecha", "Hora"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Al seleccionar fila, sincroniza los combos con los valores de esa entrega
        tabla.getSelectionModel().addListSelectionListener(e -> sincronizarCombos());

        add(panelForm, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // --- Eventos ---
        btnRegistrar.addActionListener(e  -> registrarEntrega());
        btnEditar.addActionListener(e     -> editarEntrega());
        btnEliminar.addActionListener(e   -> eliminarEntrega());
        btnRefrescar.addActionListener(e  -> { cargarCombos(); cargarTabla(); });
    }

    /**
     * Carga los JComboBox con los pedidos y repartidores actuales de la BD.
     * Muestra texto legible "id - descripción" pero guarda el id internamente.
     */
    private void cargarCombos() {
        comboPedido.removeAllItems();
        comboRepartidor.removeAllItems();

        listaPedidos = pedidoDAO.readAll();
        for (String[] p : listaPedidos) {
            comboPedido.addItem(p[0] + " - " + p[1]); // "1 - Av. Siempre Viva 123"
        }

        listaRepartidores = repartidorDAO.readAll();
        for (Repartidor r : listaRepartidores) {
            comboRepartidor.addItem(r.getId() + " - " + r.getNombre()); // "1 - Carlos"
        }
    }

    /** Carga la tabla con todas las entregas desde la BD. */
    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<String[]> lista = entregaDAO.readAll();
        for (String[] fila : lista) {
            modeloTabla.addRow(fila);
        }
    }

    /**
     * Al seleccionar una fila, intenta apuntar los combos al pedido
     * y repartidor correspondientes para facilitar la edición.
     */
    private void sincronizarCombos() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) return;

        String direccion    = (String) modeloTabla.getValueAt(fila, 1);
        String nombreRep    = (String) modeloTabla.getValueAt(fila, 2);

        // Busca en el combo el item que contenga la dirección o nombre
        for (int i = 0; i < comboPedido.getItemCount(); i++) {
            if (comboPedido.getItemAt(i).contains(direccion)) {
                comboPedido.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < comboRepartidor.getItemCount(); i++) {
            if (comboRepartidor.getItemAt(i).contains(nombreRep)) {
                comboRepartidor.setSelectedIndex(i);
                break;
            }
        }
    }

    /** Registra una nueva entrega con la fecha y hora actuales. */
    private void registrarEntrega() {
        int idxPedido      = comboPedido.getSelectedIndex();
        int idxRepartidor  = comboRepartidor.getSelectedIndex();

        if (idxPedido < 0 || idxRepartidor < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un pedido y un repartidor.");
            return;
        }

        int idPedido      = Integer.parseInt(listaPedidos.get(idxPedido)[0]);
        int idRepartidor  = listaRepartidores.get(idxRepartidor).getId();

        entregaDAO.guardar(idPedido, idRepartidor);
        cargarTabla();
        JOptionPane.showMessageDialog(this, "Entrega registrada correctamente.");
    }

    /** Edita la entrega seleccionada cambiando pedido y/o repartidor. */
    private void editarEntrega() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una entrega de la tabla.");
            return;
        }

        int idEntrega      = Integer.parseInt((String) modeloTabla.getValueAt(fila, 0));
        int idxPedido      = comboPedido.getSelectedIndex();
        int idxRepartidor  = comboRepartidor.getSelectedIndex();

        if (idxPedido < 0 || idxRepartidor < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un pedido y un repartidor.");
            return;
        }

        int idPedido      = Integer.parseInt(listaPedidos.get(idxPedido)[0]);
        int idRepartidor  = listaRepartidores.get(idxRepartidor).getId();

        boolean ok = entregaDAO.update(idEntrega, idPedido, idRepartidor);
        if (ok) {
            cargarTabla();
            JOptionPane.showMessageDialog(this, "Entrega actualizada correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar la entrega.");
        }
    }

    /** Elimina la entrega seleccionada tras confirmación. */
    private void eliminarEntrega() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una entrega de la tabla.");
            return;
        }
        int id = Integer.parseInt((String) modeloTabla.getValueAt(fila, 0));
        int conf = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar esta entrega?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION) {
            entregaDAO.delete(id);
            cargarTabla();
            JOptionPane.showMessageDialog(this, "Entrega eliminada correctamente.");
        }
    }
}