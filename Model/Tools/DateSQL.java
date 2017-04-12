package Model.Tools;

import java.sql.Date;
import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.Calendar;

/**
 * Created by lapin on 11.04.17.
 */
public class DateSQL {

    private int jour;
    private int mois;
    private int annee;

    public java.sql.Date DateSQL(int annee, int mois, int jour) {

        this.jour = jour;
        this.mois = mois;
        this.annee = annee;

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, this.annee);
        cal2.set(Calendar.MONTH, this.mois);
        cal2.set(Calendar.DAY_OF_MONTH, this.jour);

        java.util.Date d1 = cal2.getTime();
        java.sql.Date d = new java.sql.Date(d1.getTime());

        return d;
    }
}
