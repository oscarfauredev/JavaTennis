package tennisSchool.AverageData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Average {
    public static Double calculateAverage(Connection conn, String studentId, String subjectId) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT grade FROM grade WHERE student_id = ? AND subject_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            stmt.setString(2, subjectId);
            rs = stmt.executeQuery();

            int totalGrades = 0;
            int numberOfGrades = 0;

            while (rs.next()) {
                int grade = rs.getInt("grade");
                totalGrades += grade;
                numberOfGrades++;
            }

            if (numberOfGrades == 0) {
                return null;
            }

            return (double) totalGrades / numberOfGrades;

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static Double calculateTotalAverage(Connection conn, String studentId) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT id, factor FROM subject";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            double totalWeightedAverage = 0;
            int totalFactor = 0;

            while (rs.next()) {
                String subjectId = rs.getString("id");
                int factor = rs.getInt("factor");
                Double subjectAverage = calculateAverage(conn, studentId, subjectId);
                if (subjectAverage != null) {
                    totalWeightedAverage += subjectAverage * factor;
                    totalFactor += factor;
                }
            }

            if (totalFactor == 0) {
                return null;
            }

            return totalWeightedAverage / totalFactor;

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