package tennisSchool.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Logger.Logger;

public class UpdateStudent {
    public static void updateStudent(Connection conn, String id, String newFirstName, String newLastName) throws SQLException {
        String sql = "UPDATE student SET firstname = ?, lastname = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newFirstName);
            stmt.setString(2, newLastName);
            stmt.setString(3, id);
            stmt.executeUpdate();
            Logger.logUpdate("student", String.format("id=%s, newFirstName=%s, newLastName=%s", id, newFirstName, newLastName));
            System.out.println("Donnée de l'étudiant modifiée avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la modification de la donnée de l'étudiant : " + e.getMessage());
        }
    }
}