package Entity;

import Database.NoleggioDao;
import Exceptions.RentalNotFoundException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Rental {

	static NoleggioDao dao = new NoleggioDao();

	private final SimpleDateFormat  s = new SimpleDateFormat("dd/MM/yyyy");
	private String startingDate;
	private String dateEffettiva;
	private String finalDate;
	private String code;
	private Client client;

	private Car car;
	private double price;


	public Rental(String start, String end, Client cl, Car car, String code) {
		this.startingDate = start;
		this.finalDate = end;
		this.dateEffettiva = end;
		this.client = cl;
		this.car = car;
		this.code = UUID.randomUUID().toString();
	}


	public Rental(String code, String start, String end, Client cl, Car car, String actualEndDate) {
		this.startingDate = start;
		this.finalDate = end;
		this.client = cl;
		this.car = car;
		this.code = UUID.randomUUID().toString();
		this.dateEffettiva = actualEndDate;
	}

	public Rental(String id) throws SQLException, RentalNotFoundException {
		Optional<Rental> optionalRental = dao.get(id);

		if (optionalRental.isPresent()) {

			Rental r = optionalRental.get();
			this.dateEffettiva = r.dateEffettiva;
			this.car = r.car;
			this.client = r.client;
			this.finalDate = r.finalDate;
			this.startingDate = r.startingDate;
			this.code = r.code;
			this.price = r.price;

		} else {
			throw new RentalNotFoundException("Nessun noleggio trovato");
		}
	}

	public static Rental getLastRental(String name, String lastName, String plate) throws SQLException {
		Optional<Rental> or = dao.getLastRental(name, lastName, plate);

		if (or.isPresent()) {
			return or.get();
		} else {
			throw new RuntimeException("Nessun noleggio trovato per " + name + " " + lastName + " della macchina targata: " + plate);
		}

	}

	public static ArrayList<Rental> getAllRentals(String date) throws SQLException {
		return dao.getRentalOfDate(date);
	}

	public String toString(){
		return this.client.getName() + " " + this.client.getLastName() + " " + this.car.getPlate() + " " + this.startingDate + " " + this.finalDate + " " + this.dateEffettiva;
	}

	public void addRental(){
		dao.save(this);
	}

	public void closeRental(float km) throws SQLException {
		car.returnCar(km);

		setDateEffettiva(s.format(new Date()));

	}

	public double calculatePrice() throws ParseException {
		long differenza = 0;
		Date startDate = s.parse(startingDate);
		Date dataEffettiva = s.parse(dateEffettiva);
		Date fDate = s.parse(finalDate);
		differenza = dataEffettiva.getTime() - startDate.getTime();
		TimeUnit t = TimeUnit.DAYS;
		differenza = t.convert(differenza, TimeUnit.MILLISECONDS);
		double risultato = car.getDayPrice() * differenza;
		risultato += car.getKm();

		if (dataEffettiva.after(fDate)) {
			risultato += 100;
		}
		return risultato;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public String getCode() {
		return code;
	}

	public Car getCar() {
		return car;
	}

	public String getDateEffettiva() {
		return dateEffettiva;
	}

	public String getFinalDate() {
		return finalDate;
	}

	public Client getClient() {
		return client;
	}

	private void setDateEffettiva(String dateEffettiva) {
		dao.setActualReturnDate(this.code, dateEffettiva);
		this.dateEffettiva = dateEffettiva;
	}
}