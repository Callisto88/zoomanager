package Controller.Show;

import Model.DBInteraction;
import Model.ExceptionDataBase;

import java.sql.SQLException;

/**
 * Created by doriane kaffo  on 19/05/2017.
 */
public class TestQuery {
    public static void main(String arg[]){

        DBInteraction query = null;
        try {
            query = new DBInteraction();
            try {
                int newAnimalEventID = query.insAnimalEvent(1, 2);
                System.out.println(newAnimalEventID);

            } catch (SQLException e) {
                System.out.println("Echec d enregistrement en bd");
            }
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Echec de connexion en bd");
        }
    }
}
