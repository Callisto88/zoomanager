package Test;

import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>Classe de test pour certaines requêtes relatives aux animaux. Notamment la gestion des classes mère - fille
 * avec les instances de sous-classes ( Animal _> Reptile, Animal _> Primate, etc... )</p>
 * <p>
 * <p>A noter que cette classe de test a été conçue pour tester les requêtes en ligne de commande et ainsi gagner du temps
 * par rapport à des tests sur l'interface graphique qui nécessite le remplissage de formulaire complets</p>
 */
public class Animal {

    /**
     * <p>Méthode principale, utilise un simple menu avec sélection numérique pour tester les requêtes séparément</p>
     * @param arg
     * @throws SQLException
     */
    public static void main(String arg[]) throws SQLException {

        int userChoice;

        do {
            userChoice = menu();

            switch (userChoice) {

                case 1:
                    selAnimaux();
                    break;

                case 2:
                    Model.Animal a = new Model.Felin(12, "Panther", "Albert", "Mâle",
                            new java.sql.Date(2015, 04, 12), new Enclos(1), new Pays(37),
                            new Race(17), new java.sql.Date(0), Double.parseDouble("14.78"));

                    udpateAnimal(a);
                    break;

                case 7:
                    break;
            }

        } while (userChoice != 7);
    }

    /**
     * Menu de sélection pour les différents tests
     *
     * @return
     */
    private static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Sélectionner une action : ");
        System.out.println("-------------------------\n");
        System.out.println("1 - Sélectionner tous les animaux");
        System.out.println("2 - Mettre à jour un animal");
        System.out.println("7 - Quit");

        selection = input.nextInt();
        return selection;
    }


    /**
     * <p>Toutes les méthodes ci-dessous sont des simples exemples d'utilisation des différentes requêtes.
     * Chaque méthode, instancie une connexion à la base de donnée, effectue une requête en particulier
     * et en affiche le résultat s'il y en a un</p>
     * <p>Cette classe de test visaient deux objectifs précis</p>
     * <ul>
     * <li>Le premier, relativement évident ; tester le bon fonctionnement des requêtes</li>
     * <li>Le deuxième : fournir un exemple d'utilisation des méthodes mises à dispotition par la partie
     * Model de l'application (ref. Model/DBInteraction.java)</li>
     * </ul>
     */


    private static void selAnimaux() {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            ArrayList<Model.Animal> result = query.selAnimaux();
            for (Model.Animal a : result) {

                if (a instanceof Felin) {
                    System.out.println("Felin");
                    System.out.println("Poids : " + ((Felin) a).getPoids());
                } else if (a instanceof Oiseau) {
                    System.out.println("Oiseau");
                    System.out.println("No de bague : " + ((Oiseau) a).getBague());
                    System.out.println("Envergure : " + ((Oiseau) a).getEnvergure());
                } else if (a instanceof Primate) {
                    System.out.println("Primate");
                    System.out.println("Température" + ((Primate) a).getTemperature());
                } else if (a instanceof Reptile) {
                    System.out.println("Reptile");
                    System.out.println("Température : " + ((Reptile) a).getTemperature());
                }

                a.toString();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }

    private static void udpateAnimal(Model.Animal a) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            query.updateAnimal(a);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
}
