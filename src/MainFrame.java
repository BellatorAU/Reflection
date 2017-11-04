import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainFrame extends JFrame{
    private int width = 900, height = 495;
    Reader reader;
    String text;
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;
    ClassModel classModel;
    static Locale currentLocale;
    ResourceBundle resourceBundle;
    FieldTableModel model;

    public MainFrame() {
        super();
        this.setSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        Locale englishLocale = new Locale("en", "US");
        Locale russianLocale = new Locale("ru", "RU");
        currentLocale = englishLocale;

        resourceBundle = ResourceBundle.getBundle("text", currentLocale);

        this.setTitle(resourceBundle.getString("title"));


        JMenuBar menuBar = new JMenuBar();
        JMenu langBar = new JMenu(resourceBundle.getString("language"));
        JMenuItem engl = new JMenuItem(resourceBundle.getString("english"));
        JMenuItem russ = new JMenuItem(resourceBundle.getString("russian"));

        JPanel panelList = new JPanel(new FlowLayout());
        panelList.setPreferredSize(new Dimension(900, 400));

        String[] colNames = {resourceBundle.getString("fieldName"), resourceBundle.getString("fieldType"),
                resourceBundle.getString("fieldValue")};
        JTable fieldTable = new JTable();
        JScrollPane classScrollPane = new JScrollPane(fieldTable);
        classScrollPane.setPreferredSize(new Dimension(900, 400));
        fieldTable.setFillsViewportHeight(true);

        JPanel panelAdd = new JPanel(new FlowLayout());
        JTextField textField = new JTextField(70);

        JButton okButton = new JButton("Ok");
        okButton.setFocusable(false);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Field[] fields = Reflection.getFields(textField.getText());
                classModel = new ClassModel(textField.getText());
                classModel.setFields(fields);
                String[] colNames = {resourceBundle.getString("fieldName"), resourceBundle.getString("fieldType"),
                        resourceBundle.getString("fieldValue")};
                model = new FieldTableModel(colNames, classModel);
                model.fireTableDataChanged();
                fieldTable.setModel(model);
            }
        });

        engl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentLocale = englishLocale;
                Field[] fields = Reflection.getFields(textField.getText());
                classModel = new ClassModel(textField.getText());
                classModel.setFields(fields);
                resourceBundle = ResourceBundle.getBundle("text", currentLocale);
                langBar.setText(resourceBundle.getString("language"));
                engl.setText(resourceBundle.getString("english"));
                russ.setText(resourceBundle.getString("russian"));

                String[] colNames = {resourceBundle.getString("fieldName"), resourceBundle.getString("fieldType"),
                        resourceBundle.getString("fieldValue")};
                model = new FieldTableModel(colNames, classModel);
                model.fireTableDataChanged();
                fieldTable.setModel(model);
            }
        });

        russ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentLocale = russianLocale;
                Field[] fields = Reflection.getFields(textField.getText());
                classModel = new ClassModel(textField.getText());
                classModel.setFields(fields);
                resourceBundle = ResourceBundle.getBundle("text", currentLocale);
                langBar.setText(resourceBundle.getString("language"));
                engl.setText(resourceBundle.getString("english"));
                russ.setText(resourceBundle.getString("russian"));
                MainFrame.this.setTitle(resourceBundle.getString("title"));
                String[] colNames = {resourceBundle.getString("fieldName"), resourceBundle.getString("fieldType"),
                        resourceBundle.getString("fieldValue")};
                model = new FieldTableModel(colNames, classModel);
                model.fireTableDataChanged();
                fieldTable.setModel(model);
            }
        });

        langBar.add(engl);
        langBar.add(russ);
        menuBar.add(langBar);
        this.setJMenuBar(menuBar);

        panelList.add(classScrollPane);
        panelAdd.add(textField);
        panelAdd.add(okButton);
        this.add(panelAdd, BorderLayout.NORTH);
        this.add(panelList, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
