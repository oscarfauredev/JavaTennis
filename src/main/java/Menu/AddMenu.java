package Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import tennisSchool.add.*;
import tennisSchool.DBConfig;

public class AddMenu {

    public static void displayAddDataMenu(Scanner scanner) {
        int choice;

        do {
            System.out.println("\nSous-menu de saisie :");
            System.out.println("1. Saisir un élève");
            System.out.println("2. Saisir une matière");
            System.out.println("3. Saisir une note");
            System.out.println("0. Retour au menu principal");
            System.out.print("Choix : ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    addSubject(scanner);
                    break;
                case 3:
                    addGrade(scanner);
                    break;
                case 0:
                    System.out.println("Retour");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choice != 0);
    }

    private static void addStudent(Scanner scanner) {
        try {
            Connection conn = DBConfig.getConnection();
            System.out.print("ID de l'élève : ");
            String id = scanner.next();
            scanner.nextLine();
            System.out.print("Prénom de l'élève : ");
            String firstName = scanner.nextLine();
            System.out.print("Nom de l'élève : ");
            String lastName = scanner.nextLine();
            AddStudent.addStudent(conn, id, firstName, lastName);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'élève : " + e.getMessage());
        }
    }

    private static void addSubject(Scanner scanner) {
        try {
            Connection conn = DBConfig.getConnection();
            System.out.print("ID de la matière : ");
            String id = scanner.next();
            scanner.nextLine();
            System.out.print("Nom de la matière : ");
            String name = scanner.nextLine();
            System.out.print("Facteur de la matière : ");
            int factor = scanner.nextInt();
            AddSubject.addSubject(conn, id, name, factor);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la matière : " + e.getMessage());
        }
    }

    private static void addGrade(Scanner scanner) {
        try {
            Connection conn = DBConfig.getConnection();
            String studentId = SelectId.selectId(scanner, "student");
            String subjectId = SelectId.selectId(scanner, "subject");
            System.out.print("Note : ");
            int grade = scanner.nextInt();
            AddGrade.addGrade(conn, studentId, subjectId, grade);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la note : " + e.getMessage());
        }
    }
}