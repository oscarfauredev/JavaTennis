package tennisSchool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import tennisSchool.AverageData.*;
import Menu.*;
import StudentRank.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu principal :");
            System.out.println("6. Supprimer des données");
            System.out.println("5. Modifier des données");
            System.out.println("4. Afficher les matières à retravailler par les élèves");
            System.out.println("3. Meilleur Etudiant par matière");
            System.out.println("2. Moyenne des élèves");
            System.out.println("1. Saisir des données");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choice = scanner.nextInt();

            switch (choice) {
                case 6:
                    DeleteMenu.displayDeleteDataMenu(scanner);
                    break;
                case 5:
                    UpdateMenu.displayUpdateDataMenu(scanner);
                    break;
                case 4:
                    try {
                        Connection conn = DBConfig.getConnection();
                        StudentProgression.displaySubjectsToImprove(conn);
                    } catch (SQLException e) {
                        System.out.println("Erreur lors de l'affichage des matières à retravailler : " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        Connection conn = DBConfig.getConnection();
                        StudentRank.displayBestStudentsBySubject(conn);
                    } catch (SQLException e) {
                        System.out.println("Erreur lors de l'affichage du classement par matière des étudiants : " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        Connection conn = DBConfig.getConnection();
                        StudentAverageList.displayStudentAverages(conn);
                    } catch (SQLException e) {
                        System.out.println("Erreur lors de l'affichage des moyennes des étudiants : " + e.getMessage());
                    }
                    break;
                case 1:
                    AddMenu.displayAddDataMenu(scanner);
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
