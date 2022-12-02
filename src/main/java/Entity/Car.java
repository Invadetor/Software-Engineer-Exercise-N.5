package Entity;

import  Database.AutomobileDao;
import Exceptions.CarNotFoundException;

import java.sql.SQLException;
import java.util.Optional;

public class Car {


	static AutomobileDao dao = new AutomobileDao();
	private String manufacter;
	private String model;
	private String plate;
	private double km;
	private Boolean free;

	private double dayPrice;



	public Car(String man, String model, String plate, double dayPrice) {
		manufacter = man;
		this.model = model;
		this.plate = plate;
		km = 0;
		this.dayPrice = dayPrice;
		free = true;
	}

	public Car(String man, String model, String plate, double dayPrice, double km, boolean free){
		manufacter = man;
		this.model = model;
		this.plate = plate;
		this.km = km;
		this.dayPrice = dayPrice;
		this.free = free;

	}


	public void assignKM(double km){
		this.km = km;
	}

	public Boolean getFree() {
		return free;
	}

	public double getDayPrice() {
		return dayPrice;
	}

	public double getKm() {
		return km;
	}

	public void setFree(Boolean free) {
		this.free = free;
	}

	public String getManufacter() {
		return manufacter;
	}

	public String getModel() {
		return model;
	}

	public String getPlate() {
		return plate;
	}

	public static Car getFreeCar(String startingDate, String endDate) throws SQLException, CarNotFoundException {
		Optional<Car> oc = dao.getFreeCarFromDate(startingDate, endDate);
		Car car = null;
		if(oc.isPresent()) {
			car = oc.get();
		} else {
			throw new CarNotFoundException("Macchina non trovata");
		}
		return car;
	}

	public void returnCar(double km) throws SQLException {

		setFree(true);
		assignKM(km);

		dao.returnCar(plate, km);

	}
}