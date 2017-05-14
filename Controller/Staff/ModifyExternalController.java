package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.*;
import View.Staff.ModifyPanel.ModifyExternalPanel;
import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by André on 23.04.2017.
 * Class permettant d'instancier et de controller tout ce qui concerne la modification des intervenant
 */
public class ModifyExternalController {
    private ModifyExternalPanel mepExternal = null;
    private Intervenant external = null;

    private DBInteraction querry = null;

    /**
     * Constructeur du controlleur de la fenêtre de modification des intervenant
     * @param external Interveant à modifier
     */
    public ModifyExternalController(Intervenant external){
        mepExternal = new ModifyExternalPanel(this, external, getCountries());
        this.external = external;
    }

    /**
     * Méthode permettant de vérifier les champs de l'intervenant
     * @param firstName prénom de l'intervenant
     * @param lastName nom de l'intervenant
     * @param eMail email de l'intervenant
     * @param address adresse de l'entreprise
     * @param NPA npa de l'entreprise
     * @param city ville de l'entreprise
     * @param country pays de l'entreprise
     * @param phone télephone de l'intervenant
     */
    public void checkModifyExternal(String firstName, String lastName, String eMail, String address, String NPA,
                                    String city, String country, String phone){
        boolean bLastName = Validate.isAlphabetic(lastName);
        if(!bLastName){
            mepExternal.setLastNameError("Champ nom contenant des caractères innaproprié");
        }
        boolean bFirstName = Validate.isAlphabetic(firstName);
        if(!bFirstName){
            mepExternal.setFirstNameError("Champ prénom contenant des caractères innaproprié");
        }
        boolean bEmail = Validate.isEmail(eMail);
        if(!bEmail){
            mepExternal.setEmailError("Champ email non conforme");
        }
        boolean bNPA = Validate.isNumeric(NPA);
        if(!bNPA){
            mepExternal.setNPAError("Le champ NPA ne contient pas que des chiffres");
        }
        boolean bCity = Validate.isAlphabetic(city);
        if(!bCity){
            mepExternal.setCityError("Le champ ville non conforme");
        }
        int cp = 0;
        boolean bChange = true;
        try{
            cp = Integer.parseInt(NPA);
        } catch(Exception exception){
            bChange = false;
            exception.printStackTrace();
            new ErrorController(exception.toString());
        }
        dbConnection();
        boolean bAddAddress = true;
        Adresse adresse = new Adresse();
        try{
            Pays pays = new Pays();
            pays.setPays(country);

            Ville ville = new Ville();
            ville.setCp(cp);
            ville.setVille(city);
            ville.setPays(pays);

            adresse.setAdresse(address);
            adresse.setVille(ville);

            querry.insAddress(adresse, ville, pays);
        } catch(SQLException sqlException){
            bAddAddress = false;
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
        } catch (ExceptionDataBase exceptionDataBase) {
            exceptionDataBase.printStackTrace();
            new ErrorController(exceptionDataBase.toString());
        }
        boolean bPhone = Validate.isPhoneNumber(phone);
        if(!bPhone){
            mepExternal.setPhoneError("Champ télephone non conforme");
        }
        if(bLastName && bFirstName && bEmail && bAddAddress && bPhone){
            external.setNom(lastName);
            external.setPrenom(firstName);
            external.setEmail(eMail);
            external.setTelephone(phone);
            external.setAdresse(adresse);
            modifyExternal(external);
        }
    }

    /**
     * Méthode permettant de mettre à jour un intervenant dans la db
     * @param external intervenant externe à mettre à jour
     */
    private void modifyExternal (Intervenant external){
        dbConnection();
        // Permet d'insérer la personne modifié
        boolean error = false;
        try {
            querry.updateIntervenant(external);
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            new ErrorController(exceptionDB.toString());
            error = true;
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
            error = true;
        }
        if(!error){
            mepExternal.close();
        }

    }

    private ArrayList<Pays> getCountries(){
        dbConnection();
        ArrayList<Pays> alp = null;
        try {
            alp = querry.selCountries();
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            new ErrorController(exceptionDB.toString());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            new ErrorController(sqlException.toString());
        }
        return alp;
    }

    /**
     * Méthode pour permettre la connection à la DB
     */
    private void dbConnection(){
        // Permet de joindre la BD
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            new ErrorController(exceptionDB.toString());
        }
    }

}
