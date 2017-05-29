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

    }
}
