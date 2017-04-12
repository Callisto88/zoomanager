package Test;

import Controller.Validate.Validate;

/**
 * Created by D.Hamel on 12.04.17.
 */
public class Test {

    public static void testClassValidate () {

        String s1 = "Salut-' ";
        String s2 = "Salut1";
        String s3 = "12345";
        String s4 = "123.456";


        String email1 = "dylan.hamel@outlook.com";
        String email2 = "dylan@hamel@outlook.com";
        String email3 = "dylan@hamel@outlook.com.fr";


        System.out.println("12345.  isNumeric        =  " + Validate.isNumeric(s3));
        System.out.println("Salut1. isAlphabetic     =  " + Validate.isAlphabetic(s2));
        System.out.println("Salut1. isNumeric        =  " + Validate.isNumeric(s2));
        System.out.println( s1 + "isAlphabetic     =  " + Validate.isAlphabetic(s1));

        System.out.println(s3.contains("."));
        System.out.println(s4.contains("."));



    }


}
