package View.Show;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import View.DateLabelFormatter;
import View.GenericWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

/**
 * Created by doriane kaffo on 10/05/2017.
 */
public class DateModel extends  JPanel{
    Dimension dLabel;
    JDatePickerImpl jdpriStartDatePicker = null;
    GridBagConstraints gbcConstraint = null;
    JLabel jlBirthdayLabel;

    public DateModel(String c) {
        dLabel = new Dimension(200, 50);
        jlBirthdayLabel = new JLabel(c);
        gbcConstraint = new GridBagConstraints();
        init();
        this.setSize(400,80);

    }
    private  void init(){
        gbcConstraint.gridx = 0;
        gbcConstraint.gridy = 0;
        gbcConstraint.insets = new Insets(0,0,0,5);
        gbcConstraint.anchor = GridBagConstraints.WEST;

//      positionner le titre
        GridBagConstraints gbcDateLabel = new GridBagConstraints();
        gbcDateLabel.gridx = 0;
        gbcDateLabel.gridy = 0;
        jlBirthdayLabel.setPreferredSize(new Dimension(130, 50));

        this.add(jlBirthdayLabel, gbcDateLabel);


        Properties pStartProperties = new Properties();
        pStartProperties.put("text.today", "Aujourd'hui");
        pStartProperties.put("text.month", "Mois");
        pStartProperties.put("text.year", "Année");
        SqlDateModel sdmModel1 = new SqlDateModel();

        JDatePanelImpl jdpliStartDatePanel = new JDatePanelImpl(sdmModel1, pStartProperties);
        jdpliStartDatePanel.setPreferredSize(new Dimension(220, 200));
        jdpriStartDatePicker = new JDatePickerImpl(jdpliStartDatePanel, new DateLabelFormatter());

        gbcDateLabel.gridx = 1;
        gbcDateLabel.gridy = 0;
        jdpriStartDatePicker.setPreferredSize(new Dimension(220, 30));
        jdpriStartDatePicker.setToolTipText("YYYYY-MM-DD");
        this.add(jdpriStartDatePicker, gbcDateLabel);
        // Permet d'obtenir un retour lors de la sélection d'une date dans le calendrier
        JDatePickerImpl finalJdpriStartDatePicker = jdpriStartDatePicker;
        jdpriStartDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(finalJdpriStartDatePicker.getJDateInstantPanel().getModel().getDay());
                System.out.println(finalJdpriStartDatePicker.getJDateInstantPanel().getModel().getMonth());
                System.out.println(finalJdpriStartDatePicker.getJDateInstantPanel().getModel().getYear());
            }
        });
    }
}
