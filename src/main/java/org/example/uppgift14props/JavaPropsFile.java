package org.example.uppgift14props;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JavaPropsFile extends JFrame {
    private final JPanel panel = new JPanel();
    private final JButton button = new JButton("Klicka mig");
    private final JLabel label  = new JLabel("Hej Där!");

    public JavaPropsFile() {
        Properties props = new Properties();
        //loadPropFile(props);
        loadPropFileXML(props);

        panel.setLayout(new BorderLayout());

        // Label settings från props fil
        // Om ej "labelMessage" finns i props fil så får vi defaultValue istället
        String labelText = props.getProperty("labelMessage", "Hejsan");
        label.setText(labelText);

        // verkar behöva ha Strings
        String buttonText = props.getProperty("buttonText", "Klicka på mig !");
        String buttonColor = props.getProperty("buttonColor", "#EFF5F5");
        String fontName = props.getProperty("fontName", "Sans-serif");
        String fontStyle = props.getProperty("fontStyle", "PLAIN");
        String fontSize = props.getProperty("fontSize", "20");

        if(fontStyle.equals("BOLD")) {
            label.setFont(new Font(fontName, Font.BOLD, Integer.parseInt(fontSize)));
        } else if (fontStyle.equals("PLAIN")) {
            label.setFont(new Font(fontName, Font.PLAIN, Integer.parseInt(fontSize)));
        } else {
            label.setFont(new Font("Sans-serif", Font.BOLD, 12));
        }


        panel.add(label, BorderLayout.NORTH);

        button.setFocusable(false);
        button.setText(buttonText);
        button.setBackground(Color.decode(buttonColor));
        panel.add(button, BorderLayout.SOUTH);



        this.add(panel);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Java props file demo");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Läser väl in från filen och sparar i props objektet
    private void loadPropFile(Properties propsObject) {
        try {
            propsObject.load(new FileInputStream("src/main/java/org/example/uppgift14props/Styling.properties"));
        } catch (IOException e) {
            System.out.println("Kan inte läsa in props fil");
            e.printStackTrace();
        }
    }

    private void loadPropFileXML(Properties propsObject) {
        try {
            propsObject.loadFromXML(new FileInputStream("src/main/java/org/example/uppgift14props/Props.xml"));
        } catch (IOException e) {
            System.out.println("Kan inte läsa in props XML fil");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JavaPropsFile();
    }
}
