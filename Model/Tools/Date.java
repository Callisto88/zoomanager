package Model.Tools;

import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.Calendar;

/**
 * Created by lapin on 11.04.17.
 */
public class Date {

    public Date(int annee, int mois, int jour) {

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, annee);
        cal2.set(Calendar.MONTH, mois);
        cal2.set(Calendar.DAY_OF_MONTH, jour);
        java.util.Date dateNaissanceR = cal2.getTime();
    }
}
