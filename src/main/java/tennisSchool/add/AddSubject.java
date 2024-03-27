package tennisSchool.add;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Logger.Logger;

public class AddSubject {
    public static void addSubject(Connection conn, String id, String name, int factor) throws SQLException {
        String sql = "INSERT INTO subject (id, name, factor) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, factor);
            stmt.executeUpdate();
            
            Logger.logAdd("subject", String.format("name=%s, factor=%s", name, factor));
            System.out.println("Matière ajoutée avec succès !");
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'ajout de la matière : " + e.getMessage());
        }
    }
}
