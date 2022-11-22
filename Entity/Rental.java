package MyBCE.Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Rental {

	private String startingDate;

	private String dateEffettiva;
	private String finalDate;
	private String code;
	private Client client;

	private Car car;
	private float price;



	public Rental (String start, String end, Client cl, Car car) {
		this.startingDate = start;
		this.finalDate = end;
		this.client = cl;
		this.car = car;
		code = cl.getCode() + cl.getLastName();
	}

	public float calculatePrice() throws ParseException {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		long differenza = 0;
		Date startDate = s.parse(startingDate);
		Date dataEffettiva = s.parse(dateEffettiva);
		Date fDate = s.parse(finalDate);
		differenza = dataEffettiva.getTime() - startDate.getTime();
		TimeUnit t = TimeUnit.DAYS;
		differenza = t.convert(differenza, TimeUnit.MILLISECONDS);
		float risultato = car.getDayPrice()*differenza;
		risultato += car.getKm();
		if(dataEffettiva.after(fDate)) {
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
	public float getPrice() {
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
}