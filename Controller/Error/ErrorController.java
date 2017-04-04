package Controller.Error;

import View.Error.ErrorPanel;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Bureau on 27.03.2017.
 */
public class ErrorController {
    JFrame mainPanel = null;
    ErrorPanel error = null;

    public ErrorController(String e) {
        error = new ErrorPanel(e);
    }
}
