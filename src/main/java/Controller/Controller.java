package Controller;

import Entity.Rental;
import Database.NoleggioDao;
import Entity.Car;
import Entity.Client;
import Entity.Parking;
import Entity.Rental;
import Exceptions.CarNotFoundException;
import Exceptions.ClientNotFoundException;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;

public class Controller {

	Parking parking;

	public static ArrayList<Rental> showRentals(String date) throws SQLException {
		return Rental.getAllRentals(date);
	}

	public static String registerClient(String name, String lastName, String tel, String email) {
		Client c = new Client(name, lastName, tel, email);
		try {
			c.save();
		} catch (SQLException sqle) {
			return "Errore nella registrazione: " + sqle.getLocalizedMessage();
		}
		String message = "Benvenuto " + c.getName() + ". \nIl tuo codice è: " + c.getCode();
		String end = sendEmail("rere@example.com", c.getEmail(), message, "Il tuo codice");
		return end + "\nCliente aggiunto, il codice cliente è: " + c.getCode();
	}

	public static String sendEmail(String from, String to, String body, String subject) {
		try {
			Email.sendEmail(from, to, body, subject);
		} catch (MessagingException me) {
			return "Errore nell'invio dell'email: " + me.getLocalizedMessage();
		}
		return "Email mandata con successo";
	}

	public static String rentCar(String start, String end, String clientCode) {

		Client c;
		Car car = null;
		Rental r;

		try{
			c = new Client(clientCode);
			car = Car.getFreeCar(start, end);
			r = new Rental(start, end, c, car, c.getCode());
	        r.addRental();
		} catch (ClientNotFoundException | CarNotFoundException cnte) {
			return cnte.getMessage();
		} catch (SQLException sqle) {
			return sqle.getLocalizedMessage();
		}

		/*if(Parking.getParking().doesClientExistance(clientCode)) {
			if(Parking.getParking().isCarAviable(start)) {
				Client cl = Parking.getParking().findAndGetClient(clientCode);
				Car car = Parking.getParking().getLatestCar();
				Rental r = new Rental(start, end, cl, car);
				car.setFree(false);
				Parking.getParking().assignRental(r);
			} else {
				throw new RuntimeException("No car is available for the given date");
			}

		} else {
			throw new RuntimeException("This client doesn't exists");
		}*/

		return "Noleggio completato";
	}

	public static String giveCarBack(float km, String customerName, String customerLastName, String actualEndDate, String plate) {
		String message = "";
		double totalPrice = 0;
		try{
			Rental r = Parking.getParking().getLastRentalOfClient(customerName, customerLastName, plate);
			r.closeRental(km, actualEndDate);
			totalPrice = r.calculatePrice();
		} catch (SQLException sqle) {
			return message += "Errore nel Database: " + sqle.getLocalizedMessage();
		} catch (ParseException pe) {
			return message += "Errore nella lettura della data: " + pe.getLocalizedMessage();
		} catch (RuntimeException rte) {
			return message += rte.getLocalizedMessage();
		}

		return message = "Noleggio chiuso correttamente, con prezzo finale di " + String.valueOf(totalPrice);

	}

}