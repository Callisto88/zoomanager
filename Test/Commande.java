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
    }
}
