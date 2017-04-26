package Controller.Staff;

import Controller.Error.ErrorController;
import Controller.Validate.Validate;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Intervenant;
import View.Staff.ModifyPanel.ModifyExternalPanel;

import java.sql.SQLException;

/**
 * Created by André on 23.04.2017.
 * Class permettant d'instancier et de controller tout ce qui concerne la modification des intervenant
 */
public class ModifyExternalController {
    private ErrorController ecError = null;
    private ModifyExternalPanel mepExternal = null;
    private Intervenant external = null;

    /**
     * Constructeur du controlleur de la fenêtre de modification des intervenant
     * @param external Interveant à modifier
     */
    public ModifyExternalController(Intervenant external){
        mepExternal = new ModifyExternalPanel(this, external);
        this.external = external;
    }

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
        // Permet de joindre la BD
        DBInteraction querry = null;
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        }
/*************** Méthode non présente pour modifier un intervenant *****************/
/*
        // Permet d'insérer la personne modifié
        try {
            querry.updateIntervenant(external);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            ecError = new ErrorController(sqlException.toString());
        }
        */
    }
}
