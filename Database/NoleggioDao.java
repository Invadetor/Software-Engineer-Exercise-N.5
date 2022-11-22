package MyBCE.Database;

import MyBCE.Entity.Rental;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class NoleggioDao implements DAO<Rental> {

    private DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();


    @Override
    public Optional<Rental> get(int id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Rental> getAll() {
        return null;
    }

    @Override
    public boolean save(Rental dato) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("dataNoleggio", dato.getStartingDate());
        hm.put("dataConsegnaPrevista", dato.getFinalDate());
        hm.put("dataConsegnaEffettiva", dato.getDateEffettiva());
        hm.put("codice", dato.getCode());
        //hm.put("codiceCliente", ); stessa cosa dell'altro, come faccio a mettere le chiavi esterne?
        //hm.put("targaAuto", );
        try {
            databaseManager.insertClient(hm);
            return true;
        } catch (SQLException errore) {
            errore.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Rental dato) {
        return false;
    }

    @Override
    public boolean update(Rental dato, String[] s) {
        return false;
    }
}
