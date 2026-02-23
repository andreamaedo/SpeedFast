import vista.VentanaPrincipal;
import dao.ConexionBD;
import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("🚀 Iniciando SpeedFast...");
        try (Connection testCon = ConexionBD.conectar()) {
            if (testCon != null) {
                System.out.println("✅ Conexión con MySQL establecida correctamente.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error crítico: No se pudo conectar a la base de datos.\n" +
                            "Verifica que MySQL esté corriendo y la contraseña sea correcta.\n" +
                            "Detalle: " + e.getMessage(),
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            System.exit(0); // Cierra la app si no hay BD
        }

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}