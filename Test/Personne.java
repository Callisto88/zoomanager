package Test;

import Model.Adresse;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Intervenant;

import java.sql.Date;
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
        System.out.println("2 - Sélectionner les détails d'un employé");
        System.out.println("3 - Insérer une nouvelle personne");
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

                case 2:
                    System.out.println("Entre l'ID de la personne : ");
                    Scanner input2 = new Scanner(System.in);
                    int idPersonne = Integer.parseInt(input2.next());
                    selEmployeDetails(idPersonne);
                    break;

                case 3:
                    // public Personne(int idPersonne, String noAVS, String prenom, String nom, Adresse adresse, String email,
                    // String telephone, java.sql.Date dateNaissance, int responsable, String statut,
                    //    java.sql.Date dateDebut, String typeContrat) {

                    Model.Personne p = new Model.Personne(
                            12,
                            "123.1234.1234.12",
                            "Cyril",
                            "Balboni",
                            new Adresse(123),
                            "cyril.balboni@heig-vd.ch",
                            "0041797281212",
                            new Date(1988, 06, 12),
                            0,
                            "interne",
                            new Date(2016, 12, 01),
                            "CDD");
                    insertPersonne(p);
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

    public static void selEmployeDetails(int idPersonne) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
            Model.Personne p = query.selEmployeDetails(idPersonne);
            System.out.println(p.toString());

        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertPersonne(Model.Personne p) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
            query.insertPersonne(p);
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // public static void
}