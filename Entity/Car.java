package MyBCE.Entity;

public class Car {

	private String manufacter;
	private String model;
	private String plate;
	private float km;
	private Boolean free;
	private Rental rental;
	private Client client;



	public Car(String man, String model, String plate) {
		manufacter = man;
		this.model = model;
		this.plate = plate;
		km = 0;
		free = true;
	}

	public void assignRentalAndClient(Client c, Rental r) {
		this.rental = r;
		this.client = c;
		free = false;
	}

	public void removeRentalAndClient() {
		this.rental = null;
		this.client = null;
		free = true;
	}

	public Boolean getFree() {
		return free;
	}

}