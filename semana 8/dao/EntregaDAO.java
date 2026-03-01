package dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Entrega.
 * Gestiona todas las operaciones CRUD contra la tabla 'entrega'.
 */
public class EntregaDAO {

    /**
     * Registra una nueva entrega con la fecha y hora actuales.
     */
    public void guardar(int idPedido, int idRepartidor) {
        String sql = "INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";
        try (Connection con = dao.ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ps.setInt(2, idRepartidor);
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setTime(4, Time.valueOf(LocalTime.now()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene todas las entregas con datos legibles de pedido y repartidor (JOIN).
     * Retorna columnas: id, dirección del pedido, nombre del repartidor, fecha, hora.
     */
    public List<String[]> readAll() {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT e.id, p.direccion, r.nombre, e.fecha, e.hora " +
                "FROM entrega e " +
                "JOIN pedido p ON e.id_pedido = p.id " +
                "JOIN repartidor r ON e.id_repartidor = r.id";
        try (Connection con = dao.ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("direccion"),
                        rs.getString("nombre"),
                        rs.getString("fecha"),
                        rs.getString("hora")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Actualiza el pedido y repartidor asociados a una entrega existente.
     *
     * @return true si se actualizó correctamente
     */
    public boolean update(int id, int idPedido, int idRepartidor) {
        String sql = "UPDATE entrega SET id_pedido = ?, id_repartidor = ? WHERE id = ?";
        try (Connection con = dao.ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ps.setInt(2, idRepartidor);
            ps.setInt(3, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina una entrega por su id.
     *
     * @return true si se eliminó correctamente
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM entrega WHERE id = ?";
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