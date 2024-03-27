package tennisSchool.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Logger.Logger;

public class UpdateGrade {
    public static void updateGrade(Connection conn, int id, int newGrade) throws SQLException {
        String sql = "UPDATE grade SET grade = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newGrade);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            Logger.logUpdate("grade", String.format("id=%d, newGrade=%d", id, newGrade));
            System.out.println("Donnée de la note modifiée avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la modification de la donnée de la note : " + e.getMessage());
        }
    }
}