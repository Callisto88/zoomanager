import Model.DBInteraction;
import Model.ExceptionDataBase;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 */
public class App {
    public static void main(String[] args) {

        DBInteraction query = null;
        try {
            query = new DBInteraction();
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
        }
    }
}