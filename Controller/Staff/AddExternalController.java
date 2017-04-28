package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.*;
import View.Staff.StaffAddPanel.AddExternal;

import java.sql.SQLException;

/**
 * Created by André on 23.04.2017.
 * Class permettant d'instancier et de controller tout ce qui concerne l'ajout d'intervenant
 */
public class AddExternalController {
    private AddExternal aeExternal = null;
    private DBInteraction querry = null;
    private ErrorController ecError = null;

    /**
     * Constructeur du controlleur d'ajout d'intervenant
     */
    public AddExternalController(){
        aeExternal = new AddExternal(this);
        aeExternal.disableError();
    }

    /**
     * Méthode permettant d'établir la connection avec la DB
     */
    private void dbConnection(){
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            ecError = new ErrorController("Erreur dbCo " + exceptionDB.toString());
        }
    }

    /**
     * Méthode permettant de checker qu'un intervenant est OK avant de l'insérer
     * @param lastName Nom de l'intervenant
     * @param firstName Prénom de l'intervenant
     * @param compagny Numéro AVS de l'intervenant
     * @param email EMail de l'intervenant
     * @param address Adresse de l'intervenant
     * @param city Ville de l'intervenant ( inclus le nom de la ville et le NPA )
     * @param country Pays de l'intervenant
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

/************************* A checker les adresses ******************************/
        // Permet de checker le NPA
        boolean bNPA = Validate.isNumeric(npa);
        if(!bNPA){
            aeExternal.setNPAError("Le champ NPA ne contient pas que des chiffres");
        }
        // Permet de checker la ville
        boolean bCity = Validate.isAlphabetic(city);
        if(!bCity){
            aeExternal.setCityError("Le champ ville non conforme");
        }
        // Permet de checker le pays
        boolean bCountry = Validate.isAlphabetic(country);
        if(!bCountry){
            aeExternal.setCountryError("Le champ pays non conforme");
        }

        /*

        ! Plus nécessaire vu que le NPA est maintenant un attribut de type entier dans la classe Adresse

        // permet de convertir le npa en int
        int cp = 0;
        boolean bChange = true;
        if(bNPA) {
            try {
                cp = Integer.parseInt(city.getCp());
            } catch (Exception exception) {
                bChange = false;
                exception.printStackTrace();
                ecError = new ErrorController("Erreur Parsing NPA " + exception.toString());
            }
        }
        */

        // Permet d'insérer une adresse
        dbConnection();
        boolean bAddAddress = true;
        if(bNPA && bCity && bCountry) {
            try {

                Pays pays = new Pays();
                pays.setPays(country);

                Ville ville = new Ville();
                ville.setVille(city);
                ville.setPays(pays);

                Adresse adresse = new Adresse();
                adresse.setAdresse(address);
                adresse.setVille(ville);

                querry.insAddress(adresse, ville, pays);
            } catch (ExceptionDataBase exceptionDataBase) {
                exceptionDataBase.printStackTrace();
            } catch (SQLException sqlException) {
                bAddAddress = false;
                sqlException.printStackTrace();
                ecError = new ErrorController("Erreur insertion adresse " + sqlException.toString());
            }
        }

        // Permet de checker le numéro de télephone
        boolean bPhone = Validate.isPhoneNumber(phone);
        if(!bPhone){
            aeExternal.setPhoneError("Champ télephone non conforme");
        }

        // Si tout est ok, on lance l'insertion
        if (bLastName && bFirstName && bCompagny && bEmail && bNPA && bCity && bCountry && bAddAddress && bPhone) {
            dbConnection();
/***************** Problème pour récupérer l'id d'une adresse pour l'insertion ************************/
            Intervenant external = new Intervenant(compagny, lastName, firstName, 1,email, phone);
            insertExternal(external);
        }
    }

    /**
     * Méthode permettant d'interragir avec la DB pour insérer une personne
     * @param external personne à insérer dans la DB
     */
    public void insertExternal(Intervenant external){
/***************************** Problème méthode non présente dans la DB *************************************/
        /*
        try{
            querry.insertExternal(external);
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException exceptionsql){
            exceptionsql.printStackTrace();
            ecError = new ErrorController("Erreur insertion externe " + exceptionsql.toString());
        }
        */
        System.out.println("Intervenant externe inséré");

    }
}
