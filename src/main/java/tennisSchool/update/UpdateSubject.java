package tennisSchool.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Logger.Logger;

public class UpdateSubject {
    public static void updateSubject(Connection conn, String id, String newName, int newFactor) throws SQLException {
        String sql = "UPDATE subject SET name = ?, factor = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, newFactor);
            stmt.setString(3, id);
            stmt.executeUpdate();
            Logger.logUpdate("subject", String.format("id=%s, newName=%s, newFactor=%d", id, newName, newFactor));
            System.out.println("Donnée de la matière modifiée avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la modification de la donnée de la matière : " + e.getMessage());
        }
    }
}