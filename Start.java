import Model.*;
import Model.Tools.DateSQL;

import java.sql.Date;
import java.sql.SQLException;

public class Start {

    public static void main(String arg[]) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            String[][] data = query.selAllFirstLastNameEmployee();
            for (String[] s : data) {
                for (String s2 : s) {
                    System.out.print(s2 + " ");
                }
                System.out.println();
            }
        } catch (ExceptionDataBase e) {
            System.out.println(e.getMsg());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
         */
        DBInteraction req = null;
        try {
            req = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        Date dateNaissance = DateSQL.convertDateSQL(2017, 8, 04);
        Animal lyra = new Felin("Lyra", "femelle", dateNaissance, 1, "Suisse", "Européen", null, 3.6f);
        try {
            req.insAnimal(lyra);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
         *  INSERTION D'UN REPTILE
         */
        Date dateNaissanceR = DateSQL.convertDateSQL(2016, 8, 1);
        Animal keshi = new Reptile("Keshi", "mâle", dateNaissanceR, 1, "Espagne", "Méditéranéen", null, 28.3f);

        try {
            req.insAnimal(keshi);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
         *  INSERTION D'UN OISEAU
         */
        Oiseau serena = new Oiseau("Keita", "femelle", dateNaissance, 4, "USA", "Aigrette", null, 6.30, "AIS9282711");

        try {
            System.out.println("Insertion d'un " + serena.getClass().getSimpleName());
            System.out.println(serena.toString());
            req.insAnimal(serena);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\n_> Programme terminé ... \n");
    }


}