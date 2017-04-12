package Controller.Validate;

import Model.Tools.Date;

/**
 * Cette classes contient les fonctions necessaires aux verifications de saisie des utilisateurs
 * @project zoomanager
 *
 * @package Controller.Validate
 *
 * @author D.Hamel
 *
 * @version 1.0
 *
 * @date    12.04.2017 (Création)
 * @date    12.04.2017 (version 1.0)    Manque l'implementation de la fonction "isDate(Date date)"
 *
 */
public class Validate {
    /**
     * Permet de verifier que la chaine de caractere est vide
     *
     * @param s(String)
     *
     * @return boolean  True si la chaine est vide
     */
    public static boolean isEmpty (String s) {
        if (s.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Permet de verifier que la chaine de caractere n'est pas vide
     *
     * @param s(String)
     *
     * @return boolean  True si la chaine n'est pas vide
     *
     */
    public static boolean isNotEmpty (String s) {
        return !Validate.isEmpty(s);
    }


    /**
     * Permet de verifier que la chaine de caractere est composee uniquement de caracteres numeriques
     *
     * @param s(String)
     *
     * @return boolean
     *
     */
    public static boolean isNumeric(String s) {
        if (Validate.isNotEmpty(s)) {
            for (char caractere : s.toCharArray()) {
                if (!(Character.isDigit(caractere)))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Permet de verifier qu'un nombre est plus grand que 0
     *
     * @param value(int)
     *
     * @return boolean
     *
     */
    public static boolean isNumericPositive(int value) {
        if (value > 0)
            return true;
        return false;
    }

    /**
     * Permet de verifier que la chaine de caractere est composee uniquement de lettres
     * ou des caracteres "'" / "-" / " "
     *
     * @param s(String)
     *
     * @return boolean
     */
    public static boolean isAlphabetic(String s) {
        if (Validate.isNotEmpty(s)) {
            for (char caractere : s.toCharArray()) {
                if (!Character.isAlphabetic(caractere) && caractere != '\''
                        && caractere != '-' && caractere != ' ')
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Permet de verifier que la chaine de caractere respecte la syntaxe des e-mail
     *
     * @param s(String)
     *
     * @return boolean
     */
    public static boolean isEmail(String s) {
        if(Validate.isNotEmpty(s)) {
            if (Validate.countAt(s) == 1) {
                int longueur   = s.length();
                int indexAt    = s.indexOf("@");
                String local   = s.substring(0,indexAt);
                String domain  = s.substring(indexAt+1, longueur);

                // check si l'adresses est sous forme "xxxxxx.@yyyy.zz" = False
                // ainsi que sous la forme ".xxx@yy.zz"
                if (local.charAt((local.length())) == '.' || local.charAt(0) == '.')
                    return false;

                // check si l'adresses ne contient pas deux points de suites "xxx..xxx@yy.zz" = False
                boolean point = false;
                for (int i = 0; i < local.length(); i++) {
                    if (local.charAt(i) == '.' && point == true)
                        return false;

                    if (local.charAt(i) == '.')
                        point = true;
                    else
                        point = false;

                }

                // check si la partie domaine contient bien un point
                if (!domain.contains(".")) {
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * Permet de verifier que la date passee en parametre respecte la syntaxe
     *
     * @param date(Date)
     *
     * @return boolean
     */
    public static boolean isDate(Date date) {



        return true;
    }

    /**
     * Permet de compter combien de @ contient une chaine de caractere
     *
     * @param s(String)
     *
     * @return int(nombre de fois que le @ est present)
     */
    private static int countAt(String s) {
        if(Validate.isNotEmpty(s)) {
            int ref = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '@')
                    ref++;
            }
            return ref;
        }
        return 0;
    }


    /**
     * Permet de savoir si une annee est bisextille
     *
     * @param year(int)
     *
     * @return boolean  true si l'annee est bisextille
     */
    private static boolean isBisextille (int year) {
        if(year % 400 == 0 || (year % 4 == 0 && (year % 100 != 0))) {
            return true;
        } else {
            return false;
        }
    }
}
