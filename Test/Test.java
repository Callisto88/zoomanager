package Test;

import Controller.Validate.Validate;
import Model.*;
import Model.Tools.DateSQL;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by D.Hamel on 12.04.17.
 */
public class Test {



    public static void testDataBase () {

        DBInteraction query = null;

        try {
            query = new DBInteraction();

            ArrayList<Animal> animal = query.selAnimaux();

            for (int i = 0; i < animal.size(); i++) {
                System.out.print(animal.get(i).getId() + "    ");
                System.out.println(animal.get(i).getNom());
            }

        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println(exceptionDataBase.getMsg());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testClassValidate () {

        String s1 = "Salut-' ";
        String s2 = "Salut1";
        String s3 = "12345";
        String s4 = "123.456";
        String avs1 = "xxx.xxxx.xxxx.xx";
        String avs2 = "111.1111.1111.11";
        String avs3 = "11.11111.1111.11";
        String avs4 = "11.erere.1111.11";
        String avs5 = "111.1111.1111.@";

        String email1 = "dylan.hamel@outlook.com.uk";
        String email2 = "dylan@hamel@outlook..com";
        String email3 = "dylan@hamel@outlook.com.fr";
        String email4 = "toto@hdweiuhdew";


        System.out.println("12345.  isNumeric        =  " + Validate.isNumeric(s3));
        System.out.println("Salut1. isAlphabetic     =  " + Validate.isAlphabetic(s2));
        System.out.println("Salut1. isNumeric        =  " + Validate.isNumeric(s2));
        System.out.println( s1 + "isAlphabetic     =  " + Validate.isAlphabetic(s1));

        System.out.println(s3.contains("."));
        System.out.println(s4.contains("."));


        System.out.println(Validate.isEmail(email4));
        System.out.println(email1 + " " + Validate.isEmail(email1));

        System.out.println(avs1 + "    " + Validate.isAVS(avs1));
        System.out.println(avs2 + "    " + Validate.isAVS(avs2));
        System.out.println(avs3 + "    " + Validate.isAVS(avs3));
        System.out.println(avs4 + "    " + Validate.isAVS(avs4));
        System.out.println(avs5 + "    " + Validate.isAVS(avs5));
        System.out.println(avs2.length());

    }

    public static void callAnimalTest () {
        Oiseau o1 = new Oiseau();
        Reptile r1 = new Reptile();
        Felin f1 = new Felin();
        Primate p1 = new Primate();
        Animal a1 = new Animal();

        privateCallAnimalTest(o1);
        privateCallAnimalTest(r1);
        privateCallAnimalTest(f1);
        privateCallAnimalTest(p1);
        privateCallAnimalTest(a1);
    }

    private static void privateCallAnimalTest(Animal a) {
        System.out.println("privateCallAnimalTest => ");
        if (a instanceof Oiseau)
            System.out.println("Oiseau");
        else if (a instanceof Reptile)
            System.out.println("Reptile");
        else if (a instanceof Felin)
            System.out.println("Felin");
        else if (a instanceof Primate)
            System.out.println("Primate");
        else
            System.out.println("Rien");

    }


    public static void testUpdateAnimal () {
        java.sql.Date d1 = new java.sql.Date(93,07,14);
        java.sql.Date d2 = new java.sql.Date(93,07,15);
        System.out.println(d1);;


        Felin f1 = new Felin("Dylan", "Mâle", d1, 1, 1,1 ,d2, 1, 2000.00);

        /*try {
            DBInteraction query = new DBInteraction();
            query.updateAnimal(f1);

        } catch (Exception e) {
            System.out.println(e.getMessage() + " UPDATE");
        }*/


        /*
        (String nom, String sexe, java.sql.Date anneeNaissance, int enclos, int origine,
        int race, java.sql.Date dateDeces, int id, double poids)
        */

    }


    public static void testDate() {

    }


}
