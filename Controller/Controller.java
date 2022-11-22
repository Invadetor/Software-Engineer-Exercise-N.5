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

	public void registerClient(String name, String lastName, String tel) {
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
				Car car = Parking.getParking().getLatestCar();
				Rental r = new Rental(start, end, cl, car);
				car.setFree(false);
				Parking.getParking().assignRental(r);
			} else {
				throw new RuntimeException("No car is aviable for the given date");
			}

		} else {
			throw new RuntimeException("This client doesn't exists");
		}
	}

	public float giveCarBack(float km, String clientCode) throws ParseException {
		Client cl = Parking.getParking().findAndGetClient(clientCode);
		Rental r = Parking.getParking().getRentalOfClient(clientCode);
		r.getCar().assignKM(km);
		r.getCar().setFree(true);
		float totalPrice = r.calculatePrice();
		return totalPrice;
	}

}