package MyBCE.Entity;

public class Car {

	private String manufacter;
	private String model;
	private String plate;
	private float km;
	private Boolean free;

	private float dayPrice;



	public Car(String man, String model, String plate, float dayPrice) {
		manufacter = man;
		this.model = model;
		this.plate = plate;
		km = 0;
		this.dayPrice = dayPrice;
		free = true;
	}


	public void assignKM(float km){
		this.km = km;
	}

	public Boolean getFree() {
		return free;
	}

	public float getDayPrice() {
		return dayPrice;
	}

	public float getKm() {
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
}