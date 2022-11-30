package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public interface DAO<T, R> {
    DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
    Optional<T> get(R id) throws SQLException;
    ArrayList<T> getAll() throws SQLException;
    boolean save(T dato) throws SQLException;
    boolean delete(R dato);
    boolean update(Optional<R> dato, HashMap<String, String> s);
    boolean executeUpdate(String query);
    ResultSet executeQuery(String query) throws SQLException;

}
