package dao;
import modelo.Pedido;
import java.sql.*;

public class PedidoDAO {
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
        } catch (SQLException e) { e.printStackTrace(); }
    }
}