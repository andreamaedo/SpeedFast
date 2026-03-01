package dao;

import modelo.Repartidor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Repartidor.
 * Gestiona todas las operaciones CRUD contra la tabla 'repartidor'.
 */
public class RepartidorDAO {

    /**
     * Inserta un nuevo repartidor y retorna el id generado por la BD.
     *
     * @return id generado, o -1 si hubo error
     */
    public int create(String nombre) {
        String sql = "INSERT INTO repartidor (nombre) VALUES (?)";
        try (Connection con = dao.ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Obtiene todos los repartidores de la BD.
     */
    public List<Repartidor> readAll() {
        List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT * FROM repartidor";
        try (Connection con = dao.ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Repartidor(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Actualiza el nombre de un repartidor por su id.
     *
     * @return true si se actualizó correctamente
     */
    public boolean update(int id, String nuevoNombre) {
        String sql = "UPDATE repartidor SET nombre = ? WHERE id = ?";
        try (Connection con = dao.ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoNombre);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina un repartidor por su id.
     *
     * @return true si se eliminó correctamente
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM repartidor WHERE id = ?";
        try (Connection con = dao.ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** Alias para compatibilidad con código anterior */
    public List<Repartidor> listarTodos() {
        return readAll();
    }
}