package View.Show;

import Controller.Show.*;
import Controller.Show.Test;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import View.DateLabelFormatter;
import View.GenericWindow;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;


public class DateModel extends  JPanel{
    JDatePickerImpl datePicker = null;

    public DateModel(String c) {
        init();
    }
    public DateModel() {
        init();
    }
    private  void init(){
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        setLayout(new GridBagLayout());
        add(datePicker);
        datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(datePicker.getJDateInstantPanel().getModel().getDay());
                System.out.println(datePicker.getJDateInstantPanel().getModel().getMonth());
                System.out.println(datePicker.getJDateInstantPanel().getModel().getYear());
            }
        });
    }
}
