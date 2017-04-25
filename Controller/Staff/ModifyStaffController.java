package Controller.Staff;

import Controller.Error.ErrorController;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Personne;
import View.Staff.ModifyPanel.ModifyStaffPanel;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Andre on 17.03.2017.
 * Controlleur de la fenêtre de modification d'employée
 */
public class ModifyStaffController {
    private ErrorController ecError = null;

    /**
     * Constructeur du controlleur
     */
    public ModifyStaffController(Personne personne) {
        ModifyStaffPanel smp = new ModifyStaffPanel(this,personne);
    }

    /**
     * Méthode permettant de modifier la personne
     */
    public void checkModifyStaff(String sFirstName, String sLastName, String sSupervisor, String sEMail, String sAddress,
                                 String sNPA, String sCity, String sCountry, String sPhone) {

    }

    private void modifyStaff(Personne personne){
        // Permet de joindre la BD
        DBInteraction querry = null;
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        }

        // Permet d'insérer la personne modifié
        try {
            querry.updatePersonne(personne);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }
}
