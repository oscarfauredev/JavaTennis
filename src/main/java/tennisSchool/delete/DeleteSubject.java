package tennisSchool.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Logger.Logger;

public class DeleteSubject {
    public static void deleteSubject(Connection conn, String subjectId) throws SQLException {
        String sql = "DELETE FROM subject WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subjectId);
            stmt.executeUpdate();
            Logger.logDelete("subject", "id=" + subjectId);
            System.out.println("Matière supprimée avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de la matière : " + e.getMessage());
        }
    }
}