package Test;

import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;

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
