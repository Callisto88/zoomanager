package View.Staff.StaffAddPanel;

import Controller.Error.ErrorController;
import Controller.Staff.AddStaffController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andre on 17.03.2017.
 * Fenêtre principale pour l'ajout de personnel
 */
public class AddStaff extends JPanel {
    AddStaffInputError inputError = null;
    ErrorController error = null;
    private AddStaffController controller;
    private AddStaffInput input = null;
    private String lastName;
    private String firstName;
    private String avs;
    private String email;
    private String address;
    private String city;
    private String npa;
    private String phone;
    private String salary;

    /**
     * Constructeur de la fenêtre principale d'ajout de personnel.
     *
     * @param apc controlleur de la fenêtre pour permettre de lui remonter les information utiles.
     */
    public AddStaff(AddStaffController apc) {
        controller = apc;

        setLayout(new BorderLayout());
        AddStaffLabel label = new AddStaffLabel();
        this.add(label, BorderLayout.WEST);

        input = new AddStaffInput();
        this.add(input, BorderLayout.CENTER);

        JButton add = new JButton("Test");
        this.add(add, BorderLayout.SOUTH);

        inputError = new AddStaffInputError();
        this.add(inputError, BorderLayout.EAST);

        // Permet de controller et mettre à jour à chaque fois que l'on va appuyer sur le bouton ajouter
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputError.disableError();
                controller.resetError();
                System.out.println("ajout");
                lastName = input.getInputLastName();
                firstName = input.getInputFirstName();
                avs = input.getInputAVS();
                email = input.getEMailInput();
                address = input.getInputAddress();
                city = input.getInputCity();
                npa = input.getInputNPA();
                phone = input.getInputPhone();
                salary = input.getInputSalary();
                controller.checkFirstNameInput(firstName);
                controller.checkLastNameInput(lastName);
                controller.checkAVSInput(avs);
                controller.checkEmailInput(email);
                controller.checkAdress(address);
                controller.checkCityInput(city);
                controller.checkNPAInput(npa);
                controller.checkPhoneInput(phone);
                controller.checkSalaryInput(salary);
                /*if(controller.getErrorPanel()){
                    if(error == null) {
                        error = new ErrorController(controller.getError());
                        controller.clearError();
                    }
                    else{
                        error = new ErrorController(controller.getError());
                        controller.clearError();
                    }
                }*/
            }
        });
    }

    public void setFirstNameError(String error) {
        inputError.setFirstNameError(error);
    }

    public void setLastNameError(String error) {
        inputError.setLastNameError(error);
    }

    public void setBirthdayError(String error) {
        inputError.setBirthdayError(error);
    }

    public void setAVSError(String error) {
        inputError.setAVSError(error);
    }

    public void setEmailError(String error) {
        inputError.setEMailError(error);
    }

    public void setAddressError(String error) {
        inputError.setAddressError(error);
    }

    public void setCityError(String error) {
        inputError.setCityError(error);
    }

    public void setNPAError(String error) {
        inputError.setNPAError(error);
    }

    public void setPhoneError(String error) {
        inputError.setPhoneError(error);
    }

    public void setSalaryError(String error) {
        inputError.setSalaryError(error);
    }
}
