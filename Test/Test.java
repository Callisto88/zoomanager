package Test;

import Controller.Validate.Validate;
import Model.*;

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

            ArrayList<Animal> animal = query.getAnimals();

            for (int i = 0; i < animal.size(); i++) {
                System.out.print(animal.get(i).getId() + "    ");
                System.out.println(animal.get(i).getNom());
            }

        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
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


}
