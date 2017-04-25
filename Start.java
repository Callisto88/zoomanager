import Model.*;
import Test.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class Start {

    public static void main(String arg[]) {


        Test.testAdresse();


        // Test.testClassValidate();
        /*
        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            String artPendingDelivery = query.selCommandeEnCours(1);
            System.out.println(artPendingDelivery);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        /*try {
            ArrayList<Personne> listEmployes = query.selAllEmployes();
            for (Personne s : listEmployes) {
                System.out.println(s.toString());
            }
        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            ArrayList<String> listStatuts = query.getAllStatuts();
            for (String s : listStatuts) {
                System.out.println(s);
            }
        } catch (ExceptionDataBase e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = null;

        /*
        try {
            int nb = query.nombrePersonne();
            System.out.println(nb);
        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        try {
            String typeEvenement = query.selTypeEvenement(2);
            System.out.println(typeEvenement);
        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        try {
            Personne p = query.selEmployeDetails(11);
            System.out.println("\n\nDétails de l'employé");
            System.out.println(p.toString());
        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        */

        /*
         *  INSERTION D'UN FELIN

        DBInteraction req = null;
        try {
            req = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        Date dateNaissance = new Date(2017, 8, 4);
        Animal lyra = new Felin("Lyra", "femelle", dateNaissance, 1, "Suisse", "Européen", null, 3.6f);
        try {
            req.insAnimal(lyra);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        /*
         *  INSERTION D'UN REPTILE

        Date dateNaissanceR = new Date(2016, 8, 1);
        Animal keshi = new Reptile("Keshi", "mâle", dateNaissanceR, 1, "Espagne", "Méditéranéen", null, 28.3f);

        try {
            req.insAnimal(keshi);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
         *  INSERTION D'UN OISEAU
         */
        /*Animal ernest = new Oiseau(12.50, "7612345092");
        Oiseau serena = new Oiseau(5.60, "1983273990");

        try {
            req.insAnimal(serena);
            req.insAnimal(ernest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */


        System.out.println("\n_> Programme terminé ... \n");
    }


}