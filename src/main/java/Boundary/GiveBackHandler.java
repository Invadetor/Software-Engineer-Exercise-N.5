package Boundary;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GiveBackHandler extends JPanel {

    public static String n = "GiveBackHandler";

    private JTextField km;
    private JTextField name;
    private JTextField lastName;
    private JTextField plate;

    private JButton giveBack;
    private JLabel givenBack;

    private JButton tornaIndietro;
    private JPanel panel;
    private BoxLayout fl;

    public GiveBackHandler() {

        panel = new JPanel();

        setSize(800, 800);
        fl = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(fl);

        km = new JTextField();
        km.setColumns(30);
        panel.add(km);

        name = new JTextField();
        name.setColumns(30);
        panel.add(name);

        lastName = new JTextField();
        lastName.setColumns(30);
        panel.add(lastName);

        plate = new JTextField();
        plate.setColumns(30);
        panel.add(plate);



        giveBack = new JButton("Ritorna");
        panel.add(giveBack);

        giveBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                givenBack.setText(Controller.giveCarBack(Float.parseFloat(km.getText()), name.getText(), lastName.getText(), plate.getText()));
                panel.add(givenBack);
            }
        });

        tornaIndietro = new JButton("torna nella pagina principale");
        panel.add(tornaIndietro);

        tornaIndietro.addActionListener(new MainFrame.Switch(ManagerPage.name));


        add(panel);
    }
}
