package tennisSchool.add;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Logger.Logger;

public class AddStudent {
    public static void addStudent(Connection conn, String id, String firstName, String lastName) throws SQLException {
        String sql = "INSERT INTO student (id, firstname, lastname) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.executeUpdate();
            
            Logger.logAdd("student", String.format("firstName=%s, lastName=%s", firstName, lastName));
            System.out.println("Élève ajouté avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'ajout de l'élève : " + e.getMessage());
        }
    }
 }