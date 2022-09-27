package MyBCE.Entity;

import java.lang.reflect.Array;
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
		if(parking == null) {
			parking = new Parking();
		}
		return parking;
	}

	public void addClient(Client c) {
		clients.add(c);
	}

	public void addCar(Car c) {
		if(cars.indexOf(c) > 50) {
			throw new RuntimeException("Not enought spaces to add more Cars");
		} else {
			cars.add(c);
		}
	}

	public Boolean isCarAviable(String date) {
		for(Car c : cars) {
			if(c.getFree() == true) {
				return true;
			}
		}
		return false;
	}

	public Boolean doesClientExistance(String code) {
		for(Client c : clients) {
			if(c.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public Client findAndGetClient(String code) {
		for(Client c : clients) {
			if(c.getCode().equals(code)) {
				return c;
			}
		}
		return null;
	}

	public void assignRental(Rental r, Car ca, Client cl) {
		cl.assignRentalAndCar(ca, r);
		ca.assignRentalAndClient(cl, r);
		clients.add(cl);
		rentals.add(r);
	}


	public void removeRental(Rental r, Car ca, Client cl) {
		cl.removeRentalAndCar();
		ca.removeRentalAndClient();
		rentals.remove(r);
		clients.remove(cl);
	}

	public float calculatePrice(String date, float km, Client cl) throws ParseException {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		Date n = s.parse(date);
		Date today = new Date();
		cl.getRental().setPrice(km);
		if(n.before(today)) {
			cl.getRental().setPrice(km*2);
			return cl.getRental().getPrice();
		}
		return -1;
	}

	public ArrayList<Rental> getRentalsOfTime(String date) {
		ArrayList<Rental> completo = new ArrayList<>();
		for(Client c : clients) {
			if(c.getRental().getStartingDate().equals(date)) {
				completo.add(c.getRental());
			}
		}
		return completo;
	}

	public Car getLatestCar() {
		for(Car c : cars) {
			if(c.getFree()) {
				return c;
			}
		}
		return null;
	}

}