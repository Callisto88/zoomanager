package Test;

import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Pays;
import Model.Ville;

import java.sql.SQLException;

/**
 * Simple classe de test qui a été utilisé comme "sandbox" ainsi que pour le debug de certaines méthodes
 */
public class Adresse {

    public static void main(String arg[]) throws SQLException {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        Pays p = new Pays();
        p.setPays("Suisse");

        Ville v = new Ville();
        v.setVille("Macau");
        v.setPays(p);

        Model.Adresse a = new Model.Adresse("Rue du Marché 10", v);
        System.out.println("Testing adresse.toString : " + a.toString());
        System.out.println("Testing ville.toString : " + v.toString());
        System.out.println("Testing pays.toString : " + p.toString());
    }
}
