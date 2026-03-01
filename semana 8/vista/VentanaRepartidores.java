package vista;

import dao.RepartidorDAO;
import modelo.Repartidor;
import util.Validaciones;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Ventana para gestionar repartidores con operaciones CRUD completas.
 * Incluye validaciones de formato en los campos de entrada.
 */
public class VentanaRepartidores extends JFrame {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre;
    private JButton btnAgregar, btnEditar, btnEliminar, btnRefrescar;
    private RepartidorDAO dao = new RepartidorDAO();

    public VentanaRepartidores() {
        setTitle("Gestión de Repartidores");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        construirUI();
        cargarTabla();
    }

    private void construirUI() {
        setLayout(new BorderLayout(10, 10));

        // --- Panel superior: formulario ---
        JPanel panelForm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del repartidor"));

        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(20);
        panelForm.add(txtNombre);

        btnAgregar   = new JButton("Agregar");
        btnEditar    = new JButton("Editar");
        btnEliminar  = new JButton("Eliminar");
        btnRefrescar = new JButton("Refrescar");

        panelForm.add(btnAgregar);
        panelForm.add(btnEditar);
        panelForm.add(btnEliminar);
        panelForm.add(btnRefrescar);

        // --- Tabla central ---
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Al seleccionar fila, carga el nombre en el campo de texto
        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                txtNombre.setText((String) modeloTabla.getValueAt(fila, 1));
            }
        });

        add(panelForm, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // --- Eventos ---
        btnAgregar.addActionListener(e   -> agregarRepartidor());
        btnEditar.addActionListener(e    -> editarRepartidor());
        btnEliminar.addActionListener(e  -> eliminarRepartidor());
        btnRefrescar.addActionListener(e -> cargarTabla());
    }

    /** Carga o recarga la tabla con todos los repartidores de la BD. */
    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Repartidor> lista = dao.readAll();
        for (Repartidor r : lista) {
            modeloTabla.addRow(new Object[]{r.getId(), r.getNombre()});
        }
    }

    /** Valida y agrega un nuevo repartidor. */
    private void agregarRepartidor() {
        String nombre = txtNombre.getText().trim();

        if (!Validaciones.noEstaVacio(nombre)) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.");
            return;
        }
        if (!Validaciones.soloLetras(nombre)) {
            JOptionPane.showMessageDialog(this, "El nombre solo puede contener letras y espacios.");
            return;
        }

        dao.create(nombre);
        txtNombre.setText("");
        cargarTabla();
        JOptionPane.showMessageDialog(this, "Repartidor agregado correctamente.");
    }

    /** Valida y edita el repartidor seleccionado en la tabla. */
    private void editarRepartidor() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un repartidor de la tabla.");
            return;
        }

        String nombre = txtNombre.getText().trim();

        if (!Validaciones.noEstaVacio(nombre)) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
            return;
        }
        if (!Validaciones.soloLetras(nombre)) {
            JOptionPane.showMessageDialog(this, "El nombre solo puede contener letras y espacios.");
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        dao.update(id, nombre);
        cargarTabla();
        JOptionPane.showMessageDialog(this, "Repartidor actualizado correctamente.");
    }

    /** Elimina el repartidor seleccionado tras confirmación. */
    private void eliminarRepartidor() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un repartidor de la tabla.");
            return;
        }
        int id = (int) modeloTabla.getValueAt(fila, 0);
        int conf = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar este repartidor?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION) {
            dao.delete(id);
            cargarTabla();
            txtNombre.setText("");
            JOptionPane.showMessageDialog(this, "Repartidor eliminado correctamente.");
        }
    }
}