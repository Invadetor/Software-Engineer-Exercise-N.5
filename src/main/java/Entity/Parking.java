package Entity;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parking {


	private ArrayList<Car> cars;
	private ArrayList<Client> clients;
	private ArrayList<Rental> rentals;
	private static Parking parking = null;


	public static Parking getParking() {
		if (parking == null) {
			parking = new Parking();
		}
		return parking;
	}

	public Boolean isCarAviable(String date) {
		for (Car c : cars) {
			if (c.getFree() == true) {
				return true;
			}
		}
		return false;
	}

	public Client findAndGetClient(String code) {
		for (Client c : clients) {
			if (c.getCode().equals(code)) {
				return c;
			}
		}
		return null;
	}

	public Rental getLastRentalOfClient(String name, String lastName, String plate) throws SQLException {
		return Rental.getLastRental(name, lastName, plate);
	}

	public void assignRental(Rental r) {
		rentals.add(r);
	}


	/* public float calculatePrice(String date, float km, Client cl) throws ParseException {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		Date n = s.parse(date);
		Date today = new Date();
		cl.getRental().setPrice(km);
		if(n.before(today)) {
			cl.getRental().setPrice(km*2);
			return cl.getRental().getPrice();
		}
		return -1;
	}*/

	public ArrayList<Rental> getRentalsOfTime(String date) {
		ArrayList<Rental> completo = new ArrayList<>();
		for (Rental r : rentals) {
			if (r.getStartingDate().equals(date)) {
				completo.add(r);
			}
		}
		return completo;
	}

	public Car getLatestCar() {
		for (Car c : cars) {
			if (c.getFree()) {
				return c;
			}
		}
		return null;
	}

	public String getNome() {
		return "Autonoleggio Rossi";
	}
}