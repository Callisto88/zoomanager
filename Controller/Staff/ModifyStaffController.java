package Controller.Staff;

import Model.Personne;
import View.Staff.StaffModifyPanel.StaffModifyPanel;

/**
 * Created by Andre on 17.03.2017.
 * Controlleur de la fenêtre de modification d'employée
 */
public class ModifyStaffController {

    /**
     * Constructeur du controlleur
     */
    public ModifyStaffController(Personne personne) {
        StaffModifyPanel smp = new StaffModifyPanel(personne);
    }

    /**
     * Méthode permettant de réafficher la vue
     */
    public void revalidateView() {

    }
}
