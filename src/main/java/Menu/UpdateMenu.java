package Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import tennisSchool.DBConfig;
import tennisSchool.update.*;

public class UpdateMenu {

    public static void displayUpdateDataMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("\nMenu de mise à jour des données :");
            System.out.println("1. Modifier un élève");
            System.out.println("2. Modifier une matière");
            System.out.println("3. Modifier une note");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choix : ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayUpdateStudentMenu(scanner);
                    break;
                case 2:
                    displayUpdateSubjectMenu(scanner);
                    break;
                case 3:
                    displayUpdateGradeMenu(scanner);
                    break;
                case 0:
                    System.out.println("Retour au menu principal");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choice != 0);
    }

    private static void displayUpdateStudentMenu(Scanner scanner) {
        System.out.println("\nMenu de mise à jour des données d'élève :");
        System.out.print("Entrez l'ID de l'élève à modifier : ");
        String studentId = scanner.next();
        System.out.print("Nouveau prénom : ");
        String newFirstName = scanner.next();
        System.out.print("Nouveau nom : ");
        String newLastName = scanner.next();

        try {
            Connection conn = DBConfig.getConnection();
            UpdateStudent.updateStudent(conn, studentId, newFirstName, newLastName);
            System.out.println("Les données de l'élève ont été mises à jour avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour des données de l'élève : " + e.getMessage());
        }
    }

    private static void displayUpdateSubjectMenu(Scanner scanner) {
        System.out.println("\nMenu de mise à jour des données de matière :");
        System.out.print("Entrez l'ID de la matière à modifier : ");
        String subjectId = scanner.next();
        System.out.print("Nouveau nom de matière : ");
        String newSubjectName = scanner.next();
        System.out.print("Nouveau coefficient : ");
        int newFactor = scanner.nextInt();

        try {
            Connection conn = DBConfig.getConnection();
            UpdateSubject.updateSubject(conn, subjectId, newSubjectName, newFactor);
            System.out.println("Les données de la matière ont été mises à jour avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour des données de la matière : " + e.getMessage());
        }
    }

    private static void displayUpdateGradeMenu(Scanner scanner) {
        System.out.println("\nMenu de mise à jour des données de note :");
        System.out.print("Entrez l'ID de la note à modifier : ");
        int gradeId = scanner.nextInt();
        System.out.print("Nouvelle note : ");
        int newGrade = scanner.nextInt();

        try {
            Connection conn = DBConfig.getConnection();
            UpdateGrade.updateGrade(conn, gradeId, newGrade);
            System.out.println("La note a été mise à jour avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la note : " + e.getMessage());
        }
    }
}