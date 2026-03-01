package vista;

import dao.PedidoDAO;
import modelo.*;
import util.Validaciones;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Ventana para registrar un nuevo pedido.
 * Incluye validaciones de formato antes de persistir en la BD.
 */
public class VentanaRegistroPedido extends JFrame {

    private JTextField txtId, txtDireccion;
    private JComboBox<String> comboTipo;
    private JButton btnGuardar, btnCancelar;

    private ArrayList<Pedido> listaLocal;

    public VentanaRegistroPedido(ArrayList<Pedido> lista) {
        this.listaLocal = lista;

        setTitle("Registrar Pedido");
        setSize(380, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        construirUI();
    }

    private void construirUI() {
        setLayout(new GridLayout(5, 2, 8, 8));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(new JLabel("ID pedido:"));
        txtId = new JTextField("Auto");
        txtId.setEditable(false);
        add(txtId);

        add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Comida", "Encomienda", "Express"});
        add(comboTipo);

        btnGuardar  = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        add(btnGuardar);
        add(btnCancelar);

        btnGuardar.addActionListener(e  -> registrarPedido());
        btnCancelar.addActionListener(e -> dispose());
    }

    /** Valida los datos ingresados y guarda el pedido en la BD. */
    private void registrarPedido() {
        String direccion = txtDireccion.getText().trim();
        String tipo      = (String) comboTipo.getSelectedItem();

        // Validación: campo obligatorio
        if (!Validaciones.noEstaVacio(direccion)) {
            JOptionPane.showMessageDialog(this, "La dirección es obligatoria.");
            return;
        }

        // Validación: formato de dirección
        if (!Validaciones.esDireccionValida(direccion)) {
            JOptionPane.showMessageDialog(this,
                    "La dirección contiene caracteres no válidos.\n" +
                            "Solo se permiten letras, números, espacios y: # . , -");
            return;
        }

        // Crea el objeto según el tipo seleccionado
        Pedido nuevo;
        if ("Comida".equals(tipo))          nuevo = new PedidoComida(0, direccion);
        else if ("Encomienda".equals(tipo)) nuevo = new PedidoEncomienda(0, direccion);
        else                                nuevo = new PedidoExpress(0, direccion);

        try {
            new PedidoDAO().guardar(nuevo); // persiste y asigna el id generado
            listaLocal.add(nuevo);
            JOptionPane.showMessageDialog(this, "¡Pedido registrado con éxito! ID: " + nuevo.getId());
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + ex.getMessage());
        }
    }
}