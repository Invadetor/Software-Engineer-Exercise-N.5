package MyBCE.Database;

import MyBCE.Entity.Parking;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class ParcheggioDao implements DAO<Parking> {

    private DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();


    @Override
    public Optional<Parking> get(int id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Parking> getAll() {
        return null;
    }

    @Override
    public boolean save(Parking dato) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("nome", dato.getNome());
        try {
            databaseManager.insertClient(hm);
            return true;
        } catch (SQLException errore) {
            errore.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Parking dato) {
        return false;
    }

    @Override
    public boolean update(Parking dato, String[] s) {
        return false;
    }
}
