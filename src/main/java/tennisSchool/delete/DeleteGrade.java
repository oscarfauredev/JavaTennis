package tennisSchool.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Logger.Logger;

public class DeleteGrade {
    public static void deleteGrade(Connection conn, int gradeId) throws SQLException {
        String sql = "DELETE FROM grade WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gradeId);
            stmt.executeUpdate();
            Logger.logDelete("grade", "id=" + gradeId);
            System.out.println("Note supprimée avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de la note : " + e.getMessage());
        }
    }
}