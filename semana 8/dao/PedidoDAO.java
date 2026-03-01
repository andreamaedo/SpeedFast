package dao;

import modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Pedido.
 * Gestiona todas las operaciones CRUD contra la tabla 'pedido'.
 */
public class PedidoDAO {

    /**
     * Inserta un nuevo pedido en la BD y asigna el id generado al objeto.
     */
    public void guardar(Pedido p) {
        String sql = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";
        try (Connection con = dao.ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getDireccionEntrega());
            ps.setString(2, p.getClass().getSimpleName().replace("Pedido", "").toUpperCase());
            ps.setString(3, p.getEstado().toString());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) p.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene todos los pedidos sin filtros.
     */
    public List<String[]> readAll() {
        return readAllFiltrado(null, null);
    }

    /**
     * Obtiene pedidos filtrados por tipo y/o estado.
     * Si algún parámetro es null o vacío, no se aplica ese filtro.
     *
     * @param tipo   "COMIDA", "ENCOMIENDA", "EXPRESS" o null para todos
     * @param estado "PENDIENTE", "EN_REPARTO", "ENTREGADO" o null para todos
     */
    public List<String[]> readAllFiltrado(String tipo, String estado) {
        List<String[]> lista = new ArrayList<>();

        // WHERE 1=1 permite encadenar condiciones opcionales fácilmente
        StringBuilder sql = new StringBuilder("SELECT * FROM pedido WHERE 1=1");
        if (tipo  != null && !tipo.isEmpty())   sql.append(" AND tipo = ?");
        if (estado != null && !estado.isEmpty()) sql.append(" AND estado = ?");

        try (Connection con = dao.ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {

            int idx = 1;
            if (tipo  != null && !tipo.isEmpty())   ps.setString(idx++, tipo);
            if (estado != null && !estado.isEmpty()) ps.setString(idx, estado);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("direccion"),
                        rs.getString("tipo"),
                        rs.getString("estado")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Actualiza dirección, tipo y estado de un pedido existente.
     *
     * @return true si se actualizó correctamente
     */
    public boolean update(int id, String direccion, String tipo, String estado) {
        String sql = "UPDATE pedido SET direccion = ?, tipo = ?, estado = ? WHERE id = ?";
        try (Connection con = dao.ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, direccion);
            ps.setString(2, tipo);
            ps.setString(3, estado);
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina un pedido por su id.
     *
     * @return true si se eliminó correctamente
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM pedido WHERE id = ?";
        try (Connection con = dao.ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}