package MyBCE.Database;

import java.util.ArrayList;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(int id);
    ArrayList<T> getAll();
    boolean save(T dato);
    boolean delete(T dato);
    boolean update(T dato, String[] s);

}
