import Model.*;

import java.util.Date;
import java.sql.SQLException;
import java.util.Calendar;

public class Start {

    public static void main(String arg[]) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            String[][] data = query.selAllPreNomPersonne();
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

        /*DBInteraction req = null;
        try {
            req = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.JULY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date dateNaissance = cal.getTime();

        Animal lyra = new Felin("Lyra", "femelle", dateNaissance, 1, "Suisse", "Européen", null, 3.6f);
        try {
            req.insAnimal(lyra);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        System.out.println("\n_> Programme terminé ... \n");
    }


}