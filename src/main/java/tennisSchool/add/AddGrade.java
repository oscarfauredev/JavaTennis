package tennisSchool.add;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Logger.Logger;

public class AddGrade {
    public static void addGrade(Connection conn, String studentId, String subjectId, int grade) throws SQLException {
        String sql = "INSERT INTO grade (student_id, subject_id, grade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            stmt.setString(2, subjectId);
            stmt.setInt(3, grade);
            stmt.executeUpdate();

            Logger.logAdd("grade", String.format("student_id=%s, subject_id=%s, grade=%s ", studentId, subjectId, grade));
            System.out.println("Note ajoutée avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'ajout de la note : " + e.getMessage());
        }
    }
}
