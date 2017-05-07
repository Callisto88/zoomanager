package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.Adresse;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Intervenant;
import View.Staff.ModifyPanel.ModifyExternalPanel;
import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference;

import java.sql.SQLException;

/**
 * Created by André on 23.04.2017.
 * Class permettant d'instancier et de controller tout ce qui concerne la modification des intervenant
 */
public class ModifyExternalController {
    private ErrorController ecError = null;
    private ModifyExternalPanel mepExternal = null;
    private Intervenant external = null;

    DBInteraction querry = null;

    /**
     * Constructeur du controlleur de la fenêtre de modification des intervenant
     * @param external Interveant à modifier
     */
    public ModifyExternalController(Intervenant external){
        mepExternal = new ModifyExternalPanel(this, external);
        this.external = external;
    }

    /**
     * Méthode permettant de vérifier les champs de l'intervenant
     * @param firstName prénom de l'intervenant
     * @param lastName nom de l'intervenant
     * @param eMail email de l'intervenant
     * @param phone télephone de l'intervenant
     */
    public void checkModifyExternal(String firstName, String lastName, String eMail, String phone){
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

        boolean bPhone = Validate.isPhoneNumber(phone);
        if(!bPhone){
            mepExternal.setPhoneError("Champ télephone non conforme");
        }
        if(bLastName && bFirstName && bEmail && bPhone){
            external.setNom(lastName);
            external.setPrenom(firstName);
            external.setEmail(eMail);
            external.setTelephone(phone);
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
        try {
            querry.updateIntervenant(external);
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            ecError = new ErrorController(sqlException.toString());
        }

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
            ecError = new ErrorController(exceptionDB.toString());
        }
    }

    /**
     * Méthode permettant de récupérer l'adresse au complet avec son ID
     * @param idAddress id de l'adresse à trouver
     * @return Adresse complète pour récupérer tout les champs.
     */
    public Adresse getAddressByID(int idAddress){
        Adresse adresse = null;
        dbConnection();
        /*
        try{
        // TODO : Méthode non présente dans DBInteraction
            address = querry.getAddress(idAddress);
        } catch (ExceptionDataBase exceptionDB){
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            ecError = new ErrorController(sqlException.toString());
        }
        */
        return adresse;
    }
}
