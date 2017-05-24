package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.*;
import View.Staff.AddPanel.AddExternal;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by André on 23.04.2017.
 * Class permettant d'instancier et de controller tout ce qui concerne l'ajout d'intervenant
 */
public class AddExternalController {
    private AddExternal aeExternal = null;
    private DBInteraction querry = null;
    private StaffController scControlleur = null;


    /**
     * Constructeur du controlleur d'ajout d'intervenant
     * @param scControlleur controlleur géneral
     * @param countries permet d'avoir touts les pays présent dans la DB
     */
    public AddExternalController(StaffController scControlleur, ArrayList<Pays> countries){

        aeExternal = new AddExternal(this, countries);
        aeExternal.disableError();
        this.scControlleur = scControlleur;
    }

    /**
     * Méthode permettant d'établir la connection avec la DB
     */
    private void dbConnection(){
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            new ErrorController("Erreur dbCo " + exceptionDB.toString());
        }
    }

    /**
     * Méthode permettant de checker qu'un intervenant est OK avant de l'insérer
     * @param lastName Nom de l'intervenant
     * @param firstName Prénom de l'intervenant
     * @param compagny nom de l'entreprise de l'intervenant
     * @param email EMail de l'intervenant
     * @param address Adresse de l'entreprise de l'intervenant
     * @param npa npa de l'entreprise de l'intervenant
     * @param city Ville de l'entreprise de l'intervenant
     * @param country Pays de l'entreprise de l'intervenant
     * @param phone Numéro de télephone de l'intervenant
     */
    public void checkExternal(String lastName, String firstName, String compagny, String email, String address, String npa,
                              String city, String country, String phone) {

        // permet de checker le nom
        boolean bLastName = Validate.isAlphabetic(lastName);
        if(!bLastName){
            aeExternal.setLastNameError("Champ nom contenant des caractères innaproprié");
        }
        // permet de checker le prénom
        boolean bFirstName = Validate.isAlphabetic(firstName);
        if(!bFirstName){
            aeExternal.setFirstNameError("Champ prénom contenant des caractères innaproprié");
        }
        // Permet de checker la compagny
        boolean bCompagny = Validate.isAlphabetic(compagny);
        if(!bCompagny){
            aeExternal.setCompagnyError("Champ compagny non conforme");
        }
        // Permet de checker l'email
        boolean bEmail = Validate.isEmail(email);
        if(!bEmail){
            aeExternal.setEmailError("Champ email non conforme");
        }


        // Permet de checker le NPA
        boolean bNPA = true;
        boolean bCity = true;
        boolean bChange = true;
        boolean bCountry = true;
        boolean bAddAddress = true;
        // TODO : new adresse ou null??
        Adresse adresse = new Adresse();
        boolean bEmptyAddress = address.isEmpty();
        boolean bEmptyNPA = npa.isEmpty();
        boolean bEmptyCity = city.isEmpty();
        boolean bEmptyCountry = country.isEmpty();
        if(!bEmptyAddress && !bEmptyNPA && !bEmptyCity && !bEmptyCountry) {
            //boolean bAddress = Validate.isAlphabetic(address);
            // Permet de checker le npa
            bNPA = Validate.isNumeric(npa);
            if (!bNPA) {
                aeExternal.setNPAError("Le champ NPA ne contient pas que des chiffres");
            }
            // Permet de checker la ville
            bCity = Validate.isAlphabetic(city);
            if (!bCity) {
                aeExternal.setCityError("Le champ ville non conforme");
            }
            // Permet de checker le pays
            bCountry = Validate.isAlphabetic(country);
            if (!bCountry) {
                aeExternal.setCountryError("Le champ pays non conforme");
            }

            // Permet de convertir en int le npa

            int cp = 0;
            bChange = true;
            if (bNPA) {
                try {
                    cp = Integer.parseInt(npa);
                } catch (Exception exception) {
                    bChange = false;
                    exception.printStackTrace();
                    new ErrorController("Erreur conversion NPA " + exception.toString());
                }
            }

            // Permet d'insérer l'adresse dans la db
            dbConnection();
            bAddAddress = true;
            int cityID = 0;
            Pays pays = new Pays();
            pays.setPays(country);

            Ville ville = new Ville();
            ville.setVille(city);
            ville.setCp(cp);
            ville.setPays(pays);

            adresse.setAdresse(address);
            adresse.setVille(ville);

            try {
                querry.insAddress(adresse, ville, pays);
            } catch (ExceptionDataBase exceptionDataBase) {
                exceptionDataBase.printStackTrace();
                bAddAddress = false;
            } catch (SQLException sqlException) {
                bAddAddress = false;
                sqlException.printStackTrace();
                new ErrorController("Erreur insertion adresse " + sqlException.toString());
            }
        }
        else{
            if(bEmptyAddress){
                aeExternal.setAddressError("Champ manquant");
            }
            if(bEmptyNPA){
                aeExternal.setNPAError("Champ manquant");
            }
            if(bEmptyCity){
                aeExternal.setCityError("Champ manquant");
            }
            if(bEmptyCountry){
                aeExternal.setCountryError("Champ manquant");
            }
        }

        // Permet de checker le numéro de télephone
        boolean bPhone = Validate.isPhoneNumber(phone);
        if(!bPhone){
            aeExternal.setPhoneError("Champ télephone non conforme");
        }
        // Si tout est ok, on lance l'insertion
        if (bLastName && bFirstName && bCompagny && bEmail && bNPA && bChange && bCountry && bAddAddress && bPhone) {
            dbConnection();

            int statut = 1; // Pour dire qu'il est actif ou non
            Intervenant external = new Intervenant(compagny, lastName, firstName, adresse, email, phone, statut);
            insertExternal(external);

        }
    }

    /** TODO : tester le retour d'erreur
     * Méthode permettant d'interragir avec la DB pour insérer une personne
     * @param external personne à insérer dans la DB
     */
    private void insertExternal(Intervenant external){
        dbConnection();
        try{
            querry.insertIntervenant(external);
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            new ErrorController(exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            new ErrorController(exceptionsql.toString());
        }
        scControlleur.refreshExternalTab(external);
        int n = JOptionPane.showConfirmDialog(new JPanel(), "Voulez-vous ajouter d'autres intervenants ?",
                "Continuer des ajout?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        if(n == 1) {
            aeExternal.close();
        }
    }
}