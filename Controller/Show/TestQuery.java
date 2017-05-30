package Controller.Show;

import Model.*;

import javax.sound.midi.SysexMessage;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestQuery {
    public static void main(String arg[]){
        DBInteraction query=null;
        try {
            query = new DBInteraction();
//            try {
//                query.insAnimalEvent(1,2);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }

//            try {
//                query.insEventType("www");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            ArrayList<Intervenant> a=null;
//            try {
//                query.assignEvenementAnimal(0,2);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                query.delPersonneEvenement(111, 21);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Echec de connexion en bd");
        }
        try {
            query.delEventByID(105);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
        /*
        * DETRUIRE INTERVENANT EVENEMENT FONCTIONNE
        * */
//        try {
//            query.delIntervenantEvenement(77,18);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            query.delPersonneEvenement(111,21);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        /*
        CETTE FONCTION FONCTIONNE SUPPRIMER ANIMAL
         */
//        try {
//            query.delAnimalEvent(575,82);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
