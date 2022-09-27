package MyBCE.Controller;

import MyBCE.Entity.Car;
import MyBCE.Entity.Client;
import MyBCE.Entity.Parking;
import MyBCE.Entity.Rental;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;

public class Controller {



	public ArrayList<Rental> showParkingLot(String date) {
		ArrayList<Rental> completo = Parking.getParking().getRentalsOfTime(date);
		return completo;
	}

	public void registerClient(String name, String lastName, int tel) {
		Client c = new Client(name, lastName, tel);
		if(Parking.getParking().doesClientExistance(c.getCode())){
			throw new RuntimeException("This client already exists");
		}
		Parking.getParking().addClient(c);
	}

	public void rentCar(String start, String end, String clientCode) {
		if(Parking.getParking().doesClientExistance(clientCode)) {
			if(Parking.getParking().isCarAviable(start)) {
				Client cl = Parking.getParking().findAndGetClient(clientCode);
				Rental r = new Rental(start, end, cl);
				Car car = Parking.getParking().getLatestCar();
				Parking.getParking().assignRental(r, car, cl);
			} else {
				throw new RuntimeException("No cars is aviable for the given date");
			}

		} else {
			throw new RuntimeException("This client doesn't exists");
		}
	}

	public float giveCarBack(String date, float km, String clientCode) throws ParseException {
		Client cl = Parking.getParking().findAndGetClient(clientCode);
		float totalPrice = Parking.getParking().calculatePrice(date, km, cl);
		Rental r = cl.getRental();
		Car car = cl.getCar();
		Parking.getParking().removeRental(r, car, cl);
		return totalPrice;
	}

}