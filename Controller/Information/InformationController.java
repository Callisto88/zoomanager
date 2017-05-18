package Controller.Information;

import View.Error.ErrorPanel;
import View.Information.InformationPanel;

import javax.swing.*;

/**
 * Created by Miguel on 07/05/2017.
 */
public class InformationController {
    InformationPanel epInformation = null;

    public InformationController(String epInformation) {
        this.epInformation = new InformationPanel(epInformation);
    }
}
