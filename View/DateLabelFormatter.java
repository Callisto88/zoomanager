package View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 *
 * Cette classe permet de créer un format de date. Puis, elle permet grâce aux deux
 * méthodes qu'elle a de convertir un String en Object ou inversément. Elle est
 * utilisé comme paramètre pour instancier JDatePickerImpl. C'est grâce à cela
 * qu'on peut récupérer les valeurs de JDatePickerImpl sous forme de String
 *
 * @author M.Silva
 *
 * @version 1.0
 *
 * @date    17.04.2017
 *
 */
public class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    /**
     * Méthode qui permet de convertir une String en Object (date)
     * @param text
     * @return Object qui est une date
     * @throws ParseException
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    /**
     * Méthode qui permet de convertir un Objet (date) en String
     * @param value
     * @return String qui est une date
     * @throws ParseException
     */
    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}