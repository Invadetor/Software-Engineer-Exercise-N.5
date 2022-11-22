package MyBCE.Database;

import MyBCE.Entity.Car;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class AutomobileDao implements DAO<Car> {

    private DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();


    @Override
    public Optional<Car> get(int id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Car> getAll() {
        return null;
    }

    @Override
    public boolean save(Car dato) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("targa", dato.getPlate());
        hm.put("modello", dato.getModel());
        hm.put("casaAuto", dato.getManufacter());
        //hm.put("presenza", dato.getFree()); come faccio invece con gli interi
        //hm.put("km", dato.getKm());
        //hm.put("nomeParcheggio"); come faccio con le chiave esterne?
        try {
            databaseManager.insertClient(hm);
            return true;
        } catch (SQLException errore) {
            errore.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Car dato) {
        return false;
    }

    @Override
    public boolean update(Car dato, String[] s) {
        return false;
    }


}
