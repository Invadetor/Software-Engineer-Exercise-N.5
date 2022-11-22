package MyBCE.Entity;

public class Client {

	private String name;
	private String lastName;
	private String tel;
	private String code;




	public Client(String name, String lastName, String tel) {
		this.name = name;
		this.lastName = lastName;
		this.tel = tel;
		code = tel + lastName;
	}

	public String getCode() {
		return code;
	}
	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return name;
	}

	public String getTel() {
		return tel;
	}
}