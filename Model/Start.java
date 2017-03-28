package Model;

// import com.mysql.jdbc.Statement;
import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Start {
    public static void main(String arg[]) {

        DBConnection db = new DBConnection();

        boolean dbb = db.init();
        if (dbb)
            System.out.println("OK");
        else
            System.out.println("NOK");

        String[][] data = db.selAllPreNomPersonne();

        for (String[] s : data) {
            for (String s2 : s) {
                System.out.print(s2 + " ");
            }
            System.out.println();
        }

        boolean dbc = db.close();
        if (dbc)
            System.out.println("OK");
        else
            System.out.println("NOK");
    }

}