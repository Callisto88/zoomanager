package Test;

import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Evenement {

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

    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Sélectionner une action : ");
        System.out.println("-------------------------\n");
        System.out.println("1 - Créer une nouvelle commande avec son contenu");
        System.out.println("2 - Afficher une commande d'après son ID");
        System.out.println("3 - Affiche le contenu d'une commande d'après son ID");
        System.out.println("4 - Lister les commandes selon leur statut");
        System.out.println("5 - Lister les commandes selon un range de date");
        System.out.println("6 - Lister les commandes selon un statut et un range de date");
        System.out.println("7 - Quit");

        selection = input.nextInt();
        return selection;
    }

    public static void selAllIntervenantsParEvenementId(int eventID) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            ArrayList<Intervenant> result = query.selAllIntervenantsParEvenementId(eventID);
            for (Intervenant i : result) {
                System.out.println(i.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
}
