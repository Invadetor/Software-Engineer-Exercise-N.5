package MyBCE.Entity;

public class Client {

	private String name;
	private String lastName;
	private int tel;
	private String code;
	private Rental rental;
	private Car car;



	public Client(String name, String lastName, int tel) {
		this.name = name;
		this.lastName = lastName;
		this.tel = tel;
		code = tel + lastName;
	}

	public void assignRentalAndCar(Car c, Rental r) {
		this.rental = r;
		this.car = c;
	}

	public void removeRentalAndCar() {
		this.rental = null;
		this.car = null;
	}

	public Rental getRental() {
		return rental;
	}
	public Car getCar() {
		return car;
	}
	public String getCode() {
		return code;
	}
	public String getLastName() {
		return lastName;
	}

}