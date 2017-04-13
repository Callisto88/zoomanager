package Controller.Validate;

import java.sql.Date;

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
     * Permet de verifier que la chaine de caractere est composee uniquement de lettres
     * ou des caracteres "'" / "-" / " "
     *
     * Format d'un numero AVS :     xxx.xxxx.xxxx.xx    x = chiffre
     *
     * @param s(String)
     *
     * @return boolean
     */
    private static final int indexAVS1 = 3;
    private static final int indexAVS2 = 8;
    private static final int indexAVS3 = 13;
    public static boolean isAVS(String s) {
        if(Validate.isNotEmpty(s) || s.length() != 16) {

            // Check que toutes les caracteres sont soit des chiffres soit des .
            for (char caractere : s.toCharArray()) {
                if ((!Character.isDigit(caractere)) && caractere != '.') {
                    return false;
                }
            }

            // Check que tous les points sont aux bons endroits et qu'il y en a le bon nombre
            if (Validate.countDot(s) == 3 && s.charAt(indexAVS1) == '.'
                    && s.charAt(indexAVS2) == '.' && s.charAt(indexAVS3) == '.') {
                return true;

            }
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
                if (local.charAt((local.length()-1)) == '.' || local.charAt(0) == '.')
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
     * Permet de verifier que la chaine de caracteres passee en parametre est un numéro de telegit phone
     *
     * @param s(String)
     *
     * @return boolean
     */
    public static boolean isPhoneNumber(String s) {
        if(Validate.isNotEmpty(s)) {
            if (s.length() > 13 || s.charAt(0) != '0' || s.charAt(1) != '0')
                return false;

            return true;
        }
        return false;
    }

    /**
     * Permet de verifier que la date passee en parametre respecte la syntaxe
     *
     * @param year(int)
     * @param month(int)
     * @param day(int)
     *
     * @return boolean
     */
    public static boolean isDate(int year, int month, int day) {
        if (year > 0 && year < 3000 && month > 0 && month < 13) {
            if (day < 0 && day > Validate.numberOfDay(month, year)) {

            }

        }
        return true;
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
     * Permet de connaitre le nombre de jour dans un mois
     *
     * @param year(int)
     * @param month(int)
     *
     * @return boolean
     */
    private static int numberOfDay(int year, int month){
        int nbDay;
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                nbDay = 31;
                break;
            case 2:
                if(Validate.isBisextille(year)){
                    nbDay = 29;
                }else{
                    nbDay = 28;
                }
                break;
            default :
                nbDay = 30;
                break;
        }
        return nbDay;
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
     * Permet de compter combien de . contient une chaine de caractere
     *
     * @param s(String)
     *
     * @return int(nombre de fois que le . est present)
     */
    private static int countDot(String s) {
        if(Validate.isNotEmpty(s)) {
            int ref = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '.')
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
