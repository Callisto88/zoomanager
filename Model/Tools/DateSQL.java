package Model.Tools;


import java.sql.Date;
import java.text.DateFormatSymbols;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import Controller.Validate.Validate;
import java.time.*;

/**
 * Created by lapin on 11.04.17.
 */
public class DateSQL {



    static public Date convertDateSQL(int annee, int mois, int jour) {

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, annee);
        cal2.set(Calendar.MONTH, mois);
        cal2.set(Calendar.DAY_OF_MONTH, jour);

        java.util.Date d1 = cal2.getTime();
        java.sql.Date d = new java.sql.Date(d1.getTime());

        return d;
    }



    static public short calculateAge(Date dateNaissance) {
        short age;
        LocalDate naissance = dateNaissance.toLocalDate();
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        age = (short) ChronoUnit.YEARS.between(naissance, now);

        return age;
    }


}
