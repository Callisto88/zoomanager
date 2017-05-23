package View.Show;

import Controller.Show.ImpressionController;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * Created by doriane kaffo on 14/05/2017.
 */
public class TestPrint {
    public static void main (String []args){
        (new ImpressionController()).imprimer();
    }
}
