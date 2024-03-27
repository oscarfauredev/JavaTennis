package tennisSchool.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Logger.Logger;

public class DeleteStudent {
    public static void deleteStudent(Connection conn, String studentId) throws SQLException {
        String sql = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            stmt.executeUpdate();
            Logger.logDelete("student", "id=" + studentId);
            System.out.println("Élève supprimé avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de l'élève : " + e.getMessage());
        }
    }
}