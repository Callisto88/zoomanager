package Controller.Show;

import Model.DBInteraction;
import Model.ExceptionDataBase;

import java.sql.SQLException;

/**
 * Created by doriane kaffo  on 19/05/2017.
 */
public class TestQuery {
    public static void main(String arg[]){
        try {
            DBInteraction query = new DBInteraction();
            try {
                query.insAnimalEvent(1,2);
            } catch (SQLException e) {
                System.out.println("Echec d enregistrement en bd");
            }
        } catch (ExceptionDataBase exceptionDataBase) {
            System.out.println("Echec de connexion en bd");
        }

    }
}
