package MyBCE.Database;

import MyBCE.Entity.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class ClienteDao implements DAO<Client> {

    private DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();


    @Override
    public Optional<Client> get(int id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Client> getAll() {
        return null;
    }

    @Override
    public boolean save(Client dato) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("name", dato.getName());
        hm.put("lastName", dato.getLastName());
        hm.put("code", dato.getCode());
        hm.put("tel", dato.getTel());
        try {
            databaseManager.insertClient(hm);
            return true;
        } catch (SQLException errore) {
            errore.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client dato, String[] s) {
        return false;
    }

    @Override
    public boolean delete(Client dato) {
        return false;
    }
}
