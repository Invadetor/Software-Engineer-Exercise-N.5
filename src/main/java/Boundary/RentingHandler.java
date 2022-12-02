package Boundary;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RentingHandler extends JPanel {

	public static String name = "RentingHandler";
	private JTextField dateStart;
	private JLabel start;
	private JTextField dateEnd;
	private JLabel end;
	private JTextField code;
	private JLabel c;
	private JButton rent;
	private JLabel rented;
	private JButton tornaIndietro;
	private JPanel panel;

	private BoxLayout fl;

	public RentingHandler() {

		panel = new JPanel();

		setSize(800, 800);
		fl = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(fl);;

		start = new JLabel("Starting date");
		panel.add(start);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		dateStart = new JTextField();
		dateStart.setColumns(30);
		panel.add(dateStart);

		panel.add(Box.createRigidArea(new Dimension(0, 50)));

		end = new JLabel("End date");
		panel.add(end);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		dateEnd = new JTextField();
		dateEnd.setColumns(30);
		panel.add(dateEnd);

		panel.add(Box.createRigidArea(new Dimension(0, 50)));

		c = new JLabel("Code");
		panel.add(c);

		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		code = new JTextField();
		code.setColumns(30);
		panel.add(code);

		panel.add(Box.createRigidArea(new Dimension(0, 50)));

		rent = new JButton("Noleggia");
		panel.add(rent);

		rented = new JLabel();

		rent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rented.setText(Controller.rentCar(dateStart.getText(), dateEnd.getText(), code.getText()));
				panel.add(Box.createRigidArea(new Dimension(0, 20)));
				panel.add(rented);
			}
		});

		panel.add(Box.createRigidArea(new Dimension(0, 50)));

		tornaIndietro = new JButton("torna nella pagina principale");
		panel.add(tornaIndietro);

		tornaIndietro.addActionListener(new MainFrame.Switch(ManagerPage.name));


		add(panel);
	}


	//8ce4c7ef-7f71-4005-ad2a-eb82d876267a
}