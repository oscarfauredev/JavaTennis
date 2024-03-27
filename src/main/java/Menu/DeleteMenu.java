package Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import tennisSchool.DBConfig;
import tennisSchool.delete.*;

public class DeleteMenu {

    public static void displayDeleteDataMenu(Scanner scanner) {
        int choice;

        do {
            System.out.println("\nMenu de suppression de données :");
            System.out.println("1. Supprimer un élève");
            System.out.println("2. Supprimer une matière");
            System.out.println("3. Supprimer une note");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choix : ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayDeleteStudentMenu(scanner);
                    break;
                case 2:
                    displayDeleteSubjectMenu(scanner);
                    break;
                case 3:
                    displayDeleteGradeMenu(scanner);
                    break;
                case 0:
                    System.out.println("Retour au menu principal.");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choice != 0);
    }

    private static void displayDeleteStudentMenu(Scanner scanner) {
        System.out.println("\nMenu de suppression d'élève :");
        System.out.print("Entrez l'ID de l'élève à supprimer : ");
        String studentId = scanner.next();

        try {
            Connection conn = DBConfig.getConnection();
            DeleteStudent.deleteStudent(conn, studentId);
            System.out.println("L'élève a été supprimé avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'élève : " + e.getMessage());
        }
    }

    private static void displayDeleteSubjectMenu(Scanner scanner) {
        System.out.println("\nMenu de suppression de matière :");
        System.out.print("Entrez l'ID de la matière à supprimer : ");
        String subjectId = scanner.next();

        try {
            Connection conn = DBConfig.getConnection();
            DeleteSubject.deleteSubject(conn, subjectId);
            System.out.println("La matière a été supprimée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la matière : " + e.getMessage());
        }
    }

    private static void displayDeleteGradeMenu(Scanner scanner) {
        System.out.println("\nMenu de suppression de note :");
        System.out.print("Entrez l'ID de la note à supprimer : ");
        int gradeId = scanner.nextInt();

        try {
            Connection conn = DBConfig.getConnection();
            DeleteGrade.deleteGrade(conn, gradeId);
            System.out.println("La note a été supprimée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la note : " + e.getMessage());
        }
    }
}
