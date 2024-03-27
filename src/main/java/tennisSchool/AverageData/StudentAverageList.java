package tennisSchool.AverageData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StudentAverageList {

    public static void displayStudentAverages(Connection conn) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String studentSql = "SELECT id, firstname, lastname FROM student";
            stmt = conn.prepareStatement(studentSql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String studentId = rs.getString("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");

                System.out.println("Nom de l'étudiant : " + firstname + " " + lastname);

                Map<String, Double> subjectAverages = new HashMap<>();

                String subjectSql = "SELECT id, name FROM subject";
                PreparedStatement subjectStmt = conn.prepareStatement(subjectSql);
                ResultSet subjectRs = subjectStmt.executeQuery();

                while (subjectRs.next()) {
                    String subjectId = subjectRs.getString("id");
                    String subjectName = subjectRs.getString("name");

                    Double subjectAverage = Average.calculateAverage(conn, studentId, subjectId);
                    if (subjectAverage != null) {
                        subjectAverages.put(subjectName, subjectAverage);
                    }
                }

                for (Map.Entry<String, Double> entry : subjectAverages.entrySet()) {
                    String subjectName = entry.getKey();
                    Double subjectAverage = entry.getValue();
                    System.out.println("Matière : " + subjectName + " - Moyenne : " + (subjectAverage != null ? subjectAverage : "Non noté"));
                }

                Double totalAverage = Average.calculateTotalAverage(conn, studentId);
                System.out.println("Moyenne générale : " + (totalAverage != null ? totalAverage : "Non noté"));

                System.out.println();
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
