package View.Staff.StaffAddPanel;

import View.DateLabelFormatter;
import View.GenericWindow;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Bureau on 16.04.2017.
 */
public class TestDate extends GenericWindow{
    private JDatePickerImpl jdpriStartDatePicker = null;
    public TestDate(){
        super("Test");

        JPanel jpDate = new JPanel();

        Properties pStartProperties = new Properties();
        pStartProperties.put("text.today", "Aujourd'hui");
        pStartProperties.put("text.month", "Mois");
        pStartProperties.put("text.year", "Année");

        SqlDateModel sdmModel1 = new SqlDateModel();

        JDatePanelImpl jdpliStartDatePanel = new JDatePanelImpl(sdmModel1, pStartProperties);
        jdpliStartDatePanel.setPreferredSize(new Dimension(200, 200));
        jdpriStartDatePicker = new JDatePickerImpl(jdpliStartDatePanel, new DateLabelFormatter());

        GridBagLayout gblDate = new GridBagLayout();
        jpDate.setLayout(gblDate);
        GridBagConstraints gbcDate = new GridBagConstraints();

        gbcDate.gridx = 0;
        gbcDate.gridy = 0;
        jpDate.add(jdpriStartDatePicker, gbcDate);

        // Permet d'obtenir un retour lors de la sélection d'une date dans le calendrier
        jdpriStartDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(jdpriStartDatePicker.getJDateInstantPanel().getModel().getDay());
                System.out.println(jdpriStartDatePicker.getJDateInstantPanel().getModel().getMonth());
                System.out.println(jdpriStartDatePicker.getJDateInstantPanel().getModel().getYear());
            }
        });

        jpMainPanel.add(jpDate);

        configFrame(getJfFrame(), this);
    }
}
