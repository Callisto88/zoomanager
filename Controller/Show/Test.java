package Controller.Show;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class Test {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel JLab = new JLabel("Date");
                (JLab).setPreferredSize(new Dimension(400,30));
                frame.add(JLab);
                TestPane x = new TestPane();
                frame.add(x);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                x.repaint(11,9,6,200,40);
                x.setToolTipText("mmm");
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {
            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");
            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
            setLayout(new GridBagLayout());
            add(datePicker);
        }

    }

    public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
}