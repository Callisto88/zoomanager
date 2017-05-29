package Test;

import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>Classe de test pour toutes les requêtes relatives aux commandes et au stock</p>
 * <p>A noter que cette classe de test a été conçue pour tester les requêtes en ligne de commande et ainsi gagner du temps
 * par rapport à des tests sur l'interface graphique qui nécessite le remplissage de formulaire complets</p>
 */
public class Commande {

    /**
     * <p>Méthode principale, utilise un simple menu avec sélection numérique pour tester les requêtes séparément</p>
     *
     * @param arg
     * @throws SQLException
     */
    public static void main(String arg[]) throws SQLException {

        int userChoice;

        do {
            userChoice = menu();

            switch (userChoice) {

                case 1:
                    creerNouvelleCommande();
                    break;

                case 2:
                    System.out.println("Entrer l'ID de la commande que vous souhaitez afficher : ");
                    Scanner input2 = new Scanner(System.in);
                    int orderID2 = Integer.parseInt(input2.next());
                    afficherCommande(orderID2);
                    break;

                case 3:
                    System.out.println("Entrer l'ID de la commande que vous souhaitez afficher : ");
                    Scanner input3 = new Scanner(System.in);
                    int orderID3 = Integer.parseInt(input3.next());
                    afficherContenuCommande(orderID3);
                    break;

                case 4:
                    System.out.println("Entrer le statut souhaité : ");
                    Scanner input4 = new Scanner(System.in);
                    String s = String.valueOf(input4.next());
                    afficherCommandeParStatut(Model.Statut.valueOf(s));
                    break;

                case 5:
                    java.sql.Date startDate = Model.Tools.DateSQL.convertDateSQL(2016, 6, 1);
                    java.sql.Date endDate = Model.Tools.DateSQL.convertDateSQL(2016, 7, 16);
                    afficherCommandeParDate(startDate, endDate);
                    break;

                case 6:
                    java.sql.Date startDate2 = Model.Tools.DateSQL.convertDateSQL(2016, 6, 1);
                    java.sql.Date endDate2 = Model.Tools.DateSQL.convertDateSQL(2016, 7, 16);
                    afficherCommandeParStatutEtDate(Statut.ANNULEE, startDate2, endDate2);
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

    private static void creerNouvelleCommande() {

        /**
         * Création d'une commande avec son contenu en paramètres
         */
        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        // Element du stock
        Stock s1 = new Stock(12);
        Stock s2 = new Stock(45);
        Stock s3 = new Stock(78);

        // Liaison article quantité via Contenu_Commande
        Contenu_Commande cc1 = new Contenu_Commande(s1.getId(), 10);
        Contenu_Commande cc2 = new Contenu_Commande(s2.getId(), 114);
        Contenu_Commande cc3 = new Contenu_Commande(s3.getId(), 212);

        // Tableau d'entité "Contenu Commande"
        ArrayList<Contenu_Commande> orderItems = new ArrayList<Contenu_Commande>();
        orderItems.add(cc1);
        orderItems.add(cc2);
        orderItems.add(cc3);

        // Création de la commande
        try {
            int newOrder2ID = query.creerCommande(orderItems);
            System.out.println("La commande a été crééé avec succès ! Son ID est : " + newOrder2ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void afficherCommande(int orderID) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        // Récupération des infos d'une commande d'après son ID
        Model.Commande order;
        try {
            order = query.selCommande(orderID);

            System.out.println("======= INFOS COMMANDE =======");
            System.out.println("ORDER ID : " + order.getId());
            System.out.println("ORDER DATETIME : " + order.getDate());
            System.out.println("ORDER STATUS : " + order.getStatut());

        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateStatutCommande(Model.Commande c) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            Model.Commande order2 = query.selCommande(c.getId());
            System.out.println("======= UPDATE COMMANDE =======");
            System.out.println("Current statut : " + String.valueOf(order2.getStatut()));
            order2.setStatut(Statut.ANNULEE);
            query.updateCommande(order2);
            System.out.println("New statut : " + String.valueOf(order2.getStatut()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }

    private static void afficherContenuCommande(int orderID) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            ArrayList<Contenu_Commande> orderContent = query.selAllContenuCommandeParID(orderID);
            System.out.println("\nContenu de la commande ID " + orderID);
            System.out.println("La commande contient : " + orderContent.size() + " éléments");

            for (Contenu_Commande c : orderContent) {

                Stock article = query.selArticleByRef(c.getRefArticle());
                String descriptionArticle = article.getDescription();

                System.out.println("====================");
                System.out.println("Réf. Article : " + descriptionArticle);
                System.out.println("Quantité : " + c.getQuantite() + " " + article.getUnite());
                System.out.println("Quantité min. : " + article.getQuantiteMin());
            }

        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Liste des commande ayant un statut en particulier
     */
    private static void afficherCommandeParStatut(Statut statut) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        ArrayList<Model.Commande> freshOrders = null;
        try {
            freshOrders = query.selOrdersByStatut(Statut.CREEE);
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (freshOrders != null) {
            for (Model.Commande o : freshOrders) {
                System.out.println("====================");
                System.out.println("Order no : " + o.getId());
                System.out.println("Order date time : " + o.getDate().toString());
                System.out.println("Order state : " + o.getStatut());
            }
        }
    }

    /**
     * Liste des commandes passé entre le 1er juin 2001 et le 16 décembre 2009 et ayant le statut ANNULEE
     */
    private static void afficherCommandeParStatutEtDate(Statut statut, java.sql.Date startDate, java.sql.Date endDate) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        ArrayList<Model.Commande> customOrders = null;
        try {
            customOrders = query.selOrdersByStateAndDate(statut, startDate, endDate);

            for (Model.Commande output : customOrders) {
                System.out.println("====================");
                System.out.println("Order no : " + output.getId());
                System.out.println("Order date time : " + output.getDate().toString());
                System.out.println("Order state : " + output.getStatut());
            }

        } catch (ExceptionDataBase e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affichage des commandes passée entre le 1er juin 2016 et le 16 juillet 2016
     */
    private static void afficherCommandeParDate(java.sql.Date startDate, java.sql.Date endDate) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        ArrayList<Model.Commande> rangeOrders = null;
        try {
            rangeOrders = query.selAllCommandeParDate(startDate, endDate);

            for (Model.Commande output : rangeOrders) {
                System.out.println("====================");
                System.out.println("Order no : " + output.getId());
                System.out.println("Order date time : " + output.getDate().toString());
                System.out.println("Order state : " + output.getStatut());
            }

        } catch (ExceptionDataBase e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
