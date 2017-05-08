package Test;

import Model.*;

import javax.jws.WebParam;
import java.sql.SQLException;
import java.util.ArrayList;

public class Commande {

    public static void main(String arg[]) throws SQLException {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        // Test de création d'une nouvelle commande
        try {
            int newOrder = query.creerCommande();
            System.out.println(newOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Récupération des infos d'une commande d'après son ID
        Model.Commande order;
        try {
            order = query.selCommande(501);

            System.out.println("======= INFOS COMMANDE =======");
            System.out.println("ORDER ID : " + order.getId());
            System.out.println("ORDER DATETIME : " + order.getDate());
            System.out.println("ORDER STATUS : " + order.getStatut());

        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Model.Commande order2 = query.selCommande(506);
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

        try {
            // Model.Commande order3 = query.selCommande(102);
            int orderID = 477;
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
        }


        /**
         * Création d'une commande avec son contenu en paramètres
         */

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
        int newOrder2ID = query.creerCommande(orderItems);

        // Affichage des infos de la commmande
        try {
            ArrayList<Contenu_Commande> orderContent = query.selAllContenuCommandeParID(newOrder2ID);
            System.out.println("\nContenu de la commande ID " + newOrder2ID);
            System.out.println("La commande contient : " + orderContent.size() + " éléments");

            for (Contenu_Commande c : orderContent) {

                Stock article = query.selArticleByRef(c.getRefArticle());
                String descriptionArticle = article.getNom();

                System.out.println("====================");
                System.out.println("Réf. Article : " + descriptionArticle);
                System.out.println("Quantité : " + c.getQuantite() + " " + article.getUnite());
                System.out.println("Quantité min. : " + article.getQuantiteMin());
            }

        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        System.out.println("\n_> Programme terminé ... \n");


        /**
         * Liste des commande ayant un statut en particulier
         */
        ArrayList<Model.Commande> freshOrders = null;
        try {
            freshOrders = query.selOrdersByStatut(Statut.CREEE);
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
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
}
