package tennisSchool.add;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import tennisSchool.DBConfig;

public class SelectId {
	public static String selectId(Scanner scanner, String tableName) {
        System.out.println("Liste des " + tableName + " disponibles :");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConfig.getConnection();
            String sql = "SELECT id, lastname FROM student";
            if (tableName.equals("subject")) {
                sql = "SELECT id, name FROM subject";
            }
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            int count = 0;
            Map<Integer, String> idMap = new HashMap<>();
            while (rs.next()) {
                count++;
                String id = rs.getString("id");
                String name = rs.getString(2);
                idMap.put(count, id);
                System.out.println(count + " - " + name);
            }

            if (count == 0) {
                System.out.println("Aucun élément trouvé dans la table " + tableName);
                return null;
            }

            System.out.print("Sélectionnez l'ID de " + tableName + " : ");
            int choice = scanner.nextInt();

            if (choice < 1 || choice > count) {
                System.out.println("Choix invalide. Veuillez réessayer.");
                return selectId(scanner, tableName);
            }

            return idMap.get(choice);

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des données depuis la base de données : " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }
}

