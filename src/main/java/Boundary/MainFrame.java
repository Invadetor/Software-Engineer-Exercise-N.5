package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    static JPanel mainPanel;
    static CardLayout cl;

    public MainFrame() {
        super("Manager Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void run() {

        mainPanel = new JPanel();
        cl = new CardLayout();

        mainPanel.setLayout(cl);
        mainPanel.add(new ManagerPage(), ManagerPage.name);
        mainPanel.add(new RentingHandler(), RentingHandler.name);
        mainPanel.add(new GiveBackHandler(), GiveBackHandler.n);

        add(mainPanel);
        setVisible(true);
    }

     static class Switch implements ActionListener {
        String nome;

        public Switch(String nome) {
            this.nome = nome;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cl.show(mainPanel, nome);
        }
    }
}
