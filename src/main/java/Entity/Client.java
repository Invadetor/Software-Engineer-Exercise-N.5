package Entity;

import Database.ClienteDao;
import Exceptions.ClientNotFoundException;

import java.util.UUID;
import java.sql.SQLException;
import java.util.Optional;

public class Client {

	private static final ClienteDao dao = new ClienteDao();
	private String name;
	private String lastName;
	private String tel;
	private String email;
	private String code;




	public Client (String name, String lastName, String tel, String code, String email) {
		this.name = name;
		this.lastName = lastName;
		this.tel = tel;
		this.email = email;
		this.code = code;
	}

	public Client (String name, String lastName, String tel, String email) {
		this.name = name;
		this.lastName = lastName;
		this.tel = tel;
		this.email = email;
		this.code = UUID.randomUUID().toString();
	}

	public Client (String code) throws SQLException, ClientNotFoundException {
		Optional<Client> oc = dao.get(code);

		if(oc.isPresent()) {
			Client c = oc.get();
			this.name = c.name;
			this.lastName = c.lastName;
			this.code = c.code;
			this.email = c.email;
			this.tel = c.tel;
		} else {
			throw new ClientNotFoundException("Nessun client con codice " + code + " trovato");
		}
	}

	public void save() throws SQLException {
		dao.save(this);
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

	public String getEmail() {
		return email;
	}
}