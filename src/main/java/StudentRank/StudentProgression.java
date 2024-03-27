package StudentRank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class StudentProgression {

    public static void displaySubjectsToImprove(Connection conn) throws SQLException {
        if (conn == null) {
            System.out.println("La connexion à la base de données est nulle.");
            return;
        }

        try (Statement stmt = conn.createStatement()) {
            String sql = "SELECT s.name AS subject_name, g.student_id, AVG(g.grade) AS average, " +
                         "st.firstname, st.lastname " +
                         "FROM grade g INNER JOIN subject s ON g.subject_id = s.id " +
                         "INNER JOIN student st ON g.student_id = st.id " +
                         "GROUP BY st.id, s.name";
            ResultSet rs = stmt.executeQuery(sql);

            Map<String, Map<String, Double>> studentGrades = new HashMap<>();

            while (rs.next()) {
                String subjectName = rs.getString("subject_name");
                String studentFirstName = rs.getString("firstname");
                String studentLastName = rs.getString("lastname");
                double average = rs.getDouble("average");

                String studentName = studentFirstName + " " + studentLastName;

                if (!studentGrades.containsKey(studentName)) {
                    studentGrades.put(studentName, new HashMap<String, Double>());
                }

                studentGrades.get(studentName).put(subjectName, average);
            }

            for (Map.Entry<String, Map<String, Double>> entry : studentGrades.entrySet()) {
                String studentName = entry.getKey();
                Map<String, Double> subjectGrades = entry.getValue();

                System.out.println("Etudiant : " + studentName);

                for (Map.Entry<String, Double> subjectEntry : subjectGrades.entrySet()) {
                    String subjectName = subjectEntry.getKey();
                    double average = subjectEntry.getValue();

                    if (average < 15) {
                        System.out.println("- " + subjectName);
                    }
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération des données : " + e.getMessage());
        }
    }
}
