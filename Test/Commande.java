package Test;

import Model.*;

import java.sql.SQLException;

public class Commande {

    public static void main(String arg[]) {

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
        try {
            Model.Commande order = query.selCommande(501);

            System.out.println("======= INFOS COMMANDE =======");
            System.out.println("ORDER ID : " + order.getId());
            System.out.println("ORDER DATETIME : " + order.getDate());
            System.out.println("ORDER STATUS : " + order.getStatut());

        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\n_> Programme terminé ... \n");
    }
}
