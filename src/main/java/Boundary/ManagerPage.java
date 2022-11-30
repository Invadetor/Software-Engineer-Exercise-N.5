package Boundary;

import Controller.Controller;
import Entity.Rental;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerPage extends JFrame{

	private JButton buttonShow;
	private JTextArea textArea;
	private JTextField data;
	private FlowLayout fl;

	public ManagerPage() {
		super("Manager Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);

		fl = new FlowLayout(FlowLayout.LEADING);
		setLayout(fl);
		setLocationRelativeTo(null);

		data = new JTextField();
		data.setColumns(20);
		add(data);

		buttonShow = new JButton("Mostra Noleggi");
		add(buttonShow);
		buttonShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showButton(data.getText());
			}

		});

		textArea = new JTextArea();
		textArea.setSize(400, 500);
		add(textArea);

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