package StudentRank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class StudentRank {

    public static void displayBestStudentsBySubject(Connection conn) throws SQLException {
        if (conn == null) {
            System.out.println("La connexion à la base de données est nulle.");
            return;
        }

        try (Statement stmt = conn.createStatement()) {
            String sql = "SELECT s.name AS subject_name, g.student_id, AVG(g.grade) AS average, " +
                         "st.firstname, st.lastname " +
                         "FROM grade g INNER JOIN subject s ON g.subject_id = s.id " +
                         "INNER JOIN student st ON g.student_id = st.id " +
                         "GROUP BY s.name, g.student_id";
            ResultSet rs = stmt.executeQuery(sql);

            Map<String, String> bestStudents = new HashMap<>();

            while (rs.next()) {
                String subjectName = rs.getString("subject_name");
                String studentFirstName = rs.getString("firstname");
                String studentLastName = rs.getString("lastname");
                Double average = rs.getDouble("average");

                String studentFullName = studentFirstName + " " + studentLastName;

                if (!bestStudents.containsKey(subjectName) || 
				    (bestStudents.get(subjectName).equals("Non noté") || average > Double.parseDouble(bestStudents.get(subjectName).split(":")[1]))) {
				    bestStudents.put(subjectName, studentFullName + ":" + average);
				}
            }

            for (Map.Entry<String, String> entry : bestStudents.entrySet()) {
                String subjectName = entry.getKey();
                String[] studentData = entry.getValue().split(":");
                String studentName = studentData[0];
                String average = studentData[1];

                System.out.println("Matière : " + subjectName);
                System.out.println("Meilleur étudiant : " + studentName);
                System.out.println("Meilleure moyenne : " + (average.equals("Non noté") ? "Non noté" : Double.parseDouble(average)));
                System.out.println();
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des données : " + e.getMessage());
        }
    }
}