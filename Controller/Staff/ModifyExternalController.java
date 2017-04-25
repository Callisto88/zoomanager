package Controller.Staff;

import Controller.Error.ErrorController;
import Model.DBInteraction;
import Model.ExceptionDataBase;
import Model.Intervenant;
import View.Staff.ModifyPanel.ModifyExternalPanel;

import java.sql.SQLException;

/**
 * Created by Bureau on 23.04.2017.
 */
public class ModifyExternalController {
    private ErrorController ecError = null;

    public ModifyExternalController(Intervenant external){
        ModifyExternalPanel mepExternal = new ModifyExternalPanel(external);
    }

    private void modifyExternal (Intervenant external){
        // Permet de joindre la BD
        DBInteraction querry = null;
        try {
            querry = new DBInteraction();
        } catch (ExceptionDataBase exceptionDB) {
            exceptionDB.printStackTrace();
            ecError = new ErrorController(exceptionDB.toString());
        }
/*
        // Permet d'insérer la personne modifié
        try {
            querry.updateIntervenant(external);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        */
    }
}
