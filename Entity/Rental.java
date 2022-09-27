package MyBCE.Entity;

import java.util.Date;

public class Rental {

	private String startingDate;
	private String finalDate;
	private String code;
	private Client client;
	private float price;



	public Rental (String start, String end, Client cl) {
		this.startingDate = start;
		this.finalDate = end;
		this.client = cl;
		code = cl.getCode() + cl.getLastName();
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

}