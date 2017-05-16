package Test;

import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Intervenant;
import Model.Statut;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe de test pour les méthodes relatives aux personnes et notamment les employés et les intervenants
 */
public class Personne {

    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Sélectionner une action : ");
        System.out.println("-------------------------\n");
        System.out.println("1 - Sélectionner tous les intervenants impliqués dans un événement");
        System.out.println("7 - Quit");

        selection = input.nextInt();
        return selection;
    }

    public static void main(String arg[]) throws SQLException {

        int userChoice;

        do {
            userChoice = menu();

            switch (userChoice) {

                case 1:
                    System.out.println("Entrer l'ID de l'événement : ");
                    Scanner input = new Scanner(System.in);
                    int eventID = Integer.parseInt(input.next());
                    selAllIntervenantsParEvenementId(eventID);
                    break;

                case 7:
                    break;
            }

        } while (userChoice != 7);
    }

    public static void selAllIntervenantsParEvenementId(int eventID) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
            ArrayList<Intervenant> result = query.selAllIntervenantsParEvenementId(eventID);

            for (Intervenant i : result) {
                System.out.println(i.toString());
            }

        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // public static void
}