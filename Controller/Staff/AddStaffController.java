package Controller.Staff;

import View.Staff.StaffAddPanel.AddStaff;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Andre on 17.03.2017.
 * Controlleur de la fenêtre d'ajout de personnel
 */
public class AddStaffController {
    // String final pour le test de valeurs présentes
    private final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private final String NUM = "0123456789";
    private final String EXTRACHAR = "- '";
    private final int NPAMINI = 4;
    private final int NPAMAXI = 4;
    private final int PHONESIZE = 10;
    private final int SALARYMINSIZE = 4;
    private final int SALARYMAXSIZE = 6;
    private final int AVSSIZE = 13;
    private JFrame addPanel = null;
    private AddStaff add = null;
    // Booléen réutilise pour les test dans les Strings
    //private boolean OK = false;
    private boolean errorPanel = false;
    private boolean errorParsing = false;

    // tableau de String contenant les erreur
    private StringBuffer error = new StringBuffer();

    /**
     * Constructeur du controlleur de la fenêtre d'ajout de personnel
     */
    public AddStaffController() {
        addPanel = new JFrame("Ajout de Staff");
        addPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add = new AddStaff(this);
        addPanel.getContentPane().add(add, BorderLayout.CENTER);
        addPanel.setSize(500, 500);
        addPanel.setVisible(true);
    }

    /**
     * Méthode permettant de réafficher la fenêtre
     */
    public void revalidateView() {
        addPanel.setVisible(true);
    }

    /**
     * Méthode permettant de contrôler la saisie du nom
     *
     * @param lastName String contenant le nom à controller
     */
    public void checkLastNameInput(String lastName) {
        lastName = lastName.toLowerCase();
        if (!theStringIsOKForFirstAndLastName(lastName)) {
            add.setLastNameError("label Nom non conforme ");
            addError("1 - label Nom non conforme ", lastName);
            errorPanel = true;
        }
    }

    /**
     * Méthode permettant de contrôler la saisie du prénom
     *
     * @param firstName String contenant le prénom à contrôler
     */
    public void checkFirstNameInput(String firstName) {
        firstName = firstName.toLowerCase();
        if (!theStringIsOKForFirstAndLastName(firstName)) {
            add.setFirstNameError("label Prénom non conforme");
            addError("2 - label Prénom non conforme ", firstName);
            errorPanel = true;
        }
    }

    /**
     * Méthode permettant de contrôler la saisie de la date de naissance
     *
     * @param birthday String contenant la date de naissance à contrôler
     */
    public void checkBirthdayInput(String birthday) {

    }

    /**
     * Méthode permettant de contrôler la saisie du numéro AVS
     *
     * @param avs String contenant le numéro AVS à contrôler
     */
    public void checkAVSInput(String avs) {
        boolean errorParsing = false;
        try {
            Integer.parseInt(avs);
        } catch (NumberFormatException e) {
            addError("4 parse - label avs non conforme il faut entrer uniquement des chiffres", avs);
            add.setAVSError("label avs non conforme il faut entrer uniquement des chiffres");
            errorPanel = true;
            errorParsing = true;
        }
        if (!errorParsing && !numberOK(avs, AVSSIZE)) {
            addError("4 - label avs non conforme il ne comporte pas " + AVSSIZE + " chiffres", avs);
            add.setAVSError("label avs non conforme il ne comporte pas " + AVSSIZE + " chiffres");
            errorPanel = true;
        }
    }

    /**
     * Méthode permettant de contrôler la saisie de l'e-mail
     *
     * @param email String contenant l'e-mail à contrôler
     */
    public void checkEmailInput(String email) {
        email = email.toLowerCase();
        if (!errorParsing && !emailOK(email)) {
            addError("5 - label email non conforme", email);
            add.setEmailError("label email non conforme");
            errorPanel = true;
        }
    }

    /**
     * Méthode permettant de contrôler la saisie de l'adresse
     *
     * @param address String contenant l'adresse à contrôler
     */
    public void checkAdress(String address) {
        address = address.toLowerCase();

    }

    /**
     * Méthode permettant de contrôler la saisie de la ville
     *
     * @param city String contenant la ville à contrôler
     */
    public void checkCityInput(String city) {
        city.toLowerCase();
        if (!cityOK(city)) {
            addError("7 - Label ville non conforme, des caractères sont inappropié", city);
            add.setCityError("Label ville non conforme, des caractères sont inappropié");
            errorPanel = true;
        }
    }

    /**
     * Méthode permettant de contrôler la saisie du NPA
     *
     * @param npa String contenant le npa à contrôler
     */
    public void checkNPAInput(String npa) {
        errorParsing = false;
        try {
            Integer.parseInt(npa);
        } catch (NumberFormatException e) {
            addError("8 parse - Label npa non conforme, il faut taper uniquement des chiffres ", npa);
            add.setNPAError("Label npa non conforme, il faut taper uniquement des chiffres");
            errorPanel = true;
            errorParsing = true;
        }
        if (!errorParsing && !rangeNumberOK(npa, NPAMINI, NPAMAXI)) {
            addError("8 - label npa non conforme, il doit y avoir entre " + NPAMINI + " et " + NPAMAXI + " chiffres", npa);
            add.setNPAError("label npa non conforme, il doit y avoir entre " + NPAMINI + " et " + NPAMAXI + " chiffres");
            errorPanel = true;
        }
    }

    /**
     * Méthode permettant de contrôler la saisie du numéro de téléphone
     *
     * @param phone String contenant le téléphone à contrôler
     */
    public void checkPhoneInput(String phone) {
        errorParsing = false;
        try {
            Integer.parseInt(phone);
        } catch (NumberFormatException e) {
            addError("9 parse - Label téléphone non conforme, il faut entrer uniquement des chiffres", phone);
            add.setPhoneError("Label téléphone non conforme, il faut entrer uniquement des chiffres");
            errorPanel = true;
            errorParsing = true;
        }
        if (!errorParsing && !numberOK(phone, PHONESIZE)) {
            addError("9 - Label téléphone non conforme, le numéro de téléphone ne comporte pas " + PHONESIZE + " chiffres", phone);
            add.setPhoneError("Label téléphone non conforme, le numéro de téléphone ne comporte pas " + PHONESIZE + " chiffres");
            errorPanel = true;
        }
    }

    /**
     * Méthode permettant de contrôler la saisie du salaire
     *
     * @param salary String contenant le salaire à contrôler
     */
    public void checkSalaryInput(String salary) {
        errorParsing = false;
        try {
            Integer.parseInt(salary);
        } catch (NumberFormatException e) {
            addError("10 parse - Label salaire non conforme, il faut entrer uniquement des chiffres", salary);
            add.setSalaryError("Label salaire non conforme, il faut entrer uniquement des chiffres");
            errorPanel = true;
            errorParsing = true;
        }
        if (!errorParsing && !rangeNumberOK(salary, SALARYMINSIZE, SALARYMAXSIZE)) {
            addError("10 - Label salaire non conforme, il ne comporte pas entre " + SALARYMINSIZE + " et " + SALARYMAXSIZE + " chiffres", salary);
            add.setSalaryError("Label salaire non conforme, il faut entrer uniquement des chiffres");
            errorPanel = true;
        }
    }

    /**
     * Méthode permettant de contrôler les nom et prénom
     *
     * @param s String contenant un nom ou un prénom à tester
     * @return true si le paramètre est correct, false sinon
     */
    private boolean theStringIsOKForFirstAndLastName(String s) {
        for (int c = 0; c < s.length(); ++c) {
            if (!containsAlpha(s.charAt(c))) {
                if (!containsNum(s.charAt(c))) {
                    if (!containsExtraChar(s.charAt(c))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Méthode permettant de contrôler si la ville est correcte
     *
     * @param city String contenant la ville à tester
     * @return true si la ville paraît correct, false sinon
     */
    public boolean cityOK(String city) {
        for (int i = 0; i < city.length(); ++i) {
            if (!containsAlpha(city.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Méthode permettant de contrôler si le char contient une lettre
     *
     * @param c char à tester
     * @return true si le char est une lettre, false sinon
     */
    public boolean containsAlpha(char c) {
        //OK = false;
        for (int i = 0; i < ALPHA.length(); ++i) {
            if (c == ALPHA.charAt(i)) {
                //OK = true;
                return true;
            }
        }
        //return OK;
        return false;
    }

    /**
     * Méthode permettant de contrôler si le char contient un chiffre
     *
     * @param c char à tester
     * @return true si le char est une lettre, false sinon
     */
    public boolean containsNum(char c) {
        //OK = false;
        for (int i = 0; i < NUM.length(); ++i) {
            if (c == NUM.charAt(i)) {
                //OK = true;
                return true;
            }
        }
        //return OK;
        return false;
    }

    /**
     * Méthode permettant de contrôler si le char contient un caractère autre
     *
     * @param c char à tester
     * @return true si le caractère contient un des extra caractères
     */
    public boolean containsExtraChar(char c) {
        //OK = false;
        for (int i = 0; i < EXTRACHAR.length(); ++i) {
            if (c == EXTRACHAR.charAt(i)) {
                //OK = true;
                return true;
            }
        }
        //return OK;
        return false;
    }

    /**
     * Méthode permettant de tester si un chiffre à une certaine taille
     *
     * @param test String contenant le chiffre à tester
     * @param size taille que la String à tester doit avoir
     * @return true si la taille est respecté, false sinon
     */
    public boolean numberOK(String test, int size) {
        if (test.length() != size) {
            return true;
        }
        return false;
    }

    /**
     * Méthode permettant de tester si un chiffre est entre une certaine taille
     *
     * @param test String contenant le chiffre à tester
     * @param min  taille minimum aue le chiffre à tester doit avoir
     * @param max  taille maximum que le chiffre à tester doit avoir
     * @return true si la taille est comprise entre les bornes, false sinon
     */
    public boolean rangeNumberOK(String test, int min, int max) {
        if (test.length() <= min && test.length() >= max) {
            return true;
        }
        return false;
    }

    /**
     * Méthode permettant de contrôler si un e-mail est ok
     *
     * @param mail String contenant le mail à tester
     * @return true si le mail parait correct, false sinon
     */
    public boolean emailOK(String mail) {
        boolean at = false;
        boolean domain = false;
        int i = 0;
        StringBuffer buff = new StringBuffer();
        String adress;
        String host;
        /*
        while(at && mail.charAt(i) != '@'){
            buff.append(mail.charAt(i));
        }
        while(domain && mail.charAt(i) != '.'){

        }
        */
        for (i = 0; i < mail.length(); ++i) {
            if (!at && mail.charAt(i) == '@' && i > 0) {
                at = true;
            }
            if (at && !containsAlpha(mail.charAt(i))) {
                if (!containsNum(mail.charAt(i))) {
                    if (mail.charAt(i) == '.' || domain) {
                        domain = true;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Méthode permettant de construire la chaîne de caractère contenant les éventuelles erreurs
     *
     * @param label String contenant le label et une description du problème
     * @param input String contenant ce que l'utilisateur à tapé
     */
    private void addError(String label, String input) {
        error.append(label);
        error.append("\"" + input + "\"" + "\n");
    }

    /**
     * Méthode permettant de savoir si il y a eu une erreur lors du contrôle d'erreur
     *
     * @return true si des erreur sont présentes, false sinon.
     */
    public boolean getErrorPanel() {
        return errorPanel;
    }

    /**
     * Méthode permettant de réinitialisé les état d'erreur
     */
    public void resetError() {
        errorPanel = false;
        errorParsing = false;
    }

    /**
     * Méthode permettant d'obtenir les éventuelles erreurs
     *
     * @return String contenant les différentes erreurs
     */
    public String getError() {
        return error.toString();
    }

    /**
     * Méthode pour "vider" le buffer d'erreur
     */
    public void clearError() {
        error.setLength(0);
    }
}
