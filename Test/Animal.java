package Test;

import Model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Animal {

    public static void main(String arg[]) throws SQLException {

        int userChoice;

        do {
            userChoice = menu();

            switch (userChoice) {

                case 1:
                    selAnimaux();
                    break;

                case 2:
                    Model.Animal a = new Model.Felin(12, "Panther", "Albert", "Mâle",
                            new java.sql.Date(2015, 04, 12), new Enclos(1), new Pays(37),
                            new Race(17), new java.sql.Date(0), Double.parseDouble("14.78"));

                    udpateAnimal(a);
                    break;

                case 7:
                    break;
            }

        } while (userChoice != 7);
    }

    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Sélectionner une action : ");
        System.out.println("-------------------------\n");
        System.out.println("1 - Sélectionner tous les animaux");
        System.out.println("2 - Mettre à jour un animal");
        System.out.println("7 - Quit");

        selection = input.nextInt();
        return selection;
    }

    public static void selAnimaux() {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            ArrayList<Model.Animal> result = query.selAnimaux();
            for (Model.Animal a : result) {

                if (a instanceof Felin) {
                    System.out.println("Felin");
                    System.out.println("Poids : " + ((Felin) a).getPoids());
                } else if (a instanceof Oiseau) {
                    System.out.println("Oiseau");
                    System.out.println("No de bague : " + ((Oiseau) a).getBague());
                    System.out.println("Envergure : " + ((Oiseau) a).getEnvergure());
                } else if (a instanceof Primate) {
                    System.out.println("Primate");
                    System.out.println("Température" + ((Primate) a).getTemperature());
                } else if (a instanceof Reptile) {
                    System.out.println("Reptile");
                    System.out.println("Température : " + ((Reptile) a).getTemperature());
                }

                a.toString();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }

    public static void udpateAnimal(Model.Animal a) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }

        try {
            query.updateAnimal(a);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
}
