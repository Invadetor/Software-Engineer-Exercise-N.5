package Boundary;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientPage extends JFrame {

	private JTextField name;
	private JTextField lastName;
	private JTextField tel;
	private JTextField email;
	private JButton buttonSign;
	private JTextArea iscritto;
	private JButton buttonRentCar;
	private JButton buttonGiveCar;

	private FlowLayout fl;

	public ClientPage() {
		super("Pagina di registrazione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);

		fl = new FlowLayout(FlowLayout.LEADING);
		setLayout(fl);
		setLocationRelativeTo(null);

		name = new JTextField();
		name.setColumns(20);
		add(name);

		lastName = new JTextField();
		lastName.setColumns(20);
		add(lastName);

		tel = new JTextField();
		tel.setColumns(20);
		add(tel);

		email = new JTextField();
		email.setColumns(20);
		add(email);

		buttonSign = new JButton("Iscriviti");
		add(buttonSign);

		iscritto = new JTextArea();
		add(iscritto);
		buttonSign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = Controller.registerClient(name.getText(), lastName.getText(), tel.getText(), email.getText());
				iscritto.setText(message);
			}
		});

	}


	private void openRentingHandler() {
		// TODO - implement ClientPage.openRentingHandler
		throw new UnsupportedOperationException();
	}

}