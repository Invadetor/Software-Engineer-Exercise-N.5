package Boundary;

import Controller.Controller;
import Entity.Rental;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerPage extends JPanel {

	public static String name = "ManagerPage";
	private JPanel panel;
	private JButton buttonShow;
	private JButton creaNoleggio;
	private JButton ritornaAuto;
	private JTextArea textArea;
	private JTextField data;
	private BoxLayout fl;


	public ManagerPage() {

		panel = new JPanel();

		setSize(800, 800);
		fl = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(fl);

		data = new JTextField();
		data.setColumns(20);
		panel.add(data);

		panel.add(Box.createRigidArea(new Dimension(0, 50)));

		buttonShow = new JButton("Mostra Noleggi");
		buttonShow.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(buttonShow);

		panel.add(Box.createRigidArea(new Dimension(0, 50)));

		buttonShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showButton(data.getText());
			}
		});

		textArea = new JTextArea();
		textArea.setSize(400, 500);
		panel.add(textArea);

		panel.add(Box.createRigidArea(new Dimension(0, 50)));

		ritornaAuto = new JButton("Ritorna l'auto");
		ritornaAuto.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(ritornaAuto);

		panel.add(Box.createRigidArea(new Dimension(0, 50)));
		ritornaAuto.addActionListener(new MainFrame.Switch(GiveBackHandler.n));

		creaNoleggio = new JButton("crea Noleggio");
		creaNoleggio.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(creaNoleggio);

		panel.add(Box.createRigidArea(new Dimension(0, 50)));
		creaNoleggio.addActionListener(new MainFrame.Switch(RentingHandler.name));


		add(panel);
	}

	private void showButton(String date) {
		try {
			ArrayList<Rental> rentals = Controller.showRentals(date);
			String s = "";
			for(Rental r : rentals) {
				s += r.toString() + "\n";
			}
			textArea.setText(s);
		} catch (SQLException error) {
			error.getLocalizedMessage();
		}
	}

}