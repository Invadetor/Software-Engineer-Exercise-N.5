package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import  Entity.Parking;
import com.sun.jdi.VoidType;


public class DatabaseManager {

    private static DatabaseManager databaseManager = null;


    private Connection con;

    private final String url = "jdbc:h2:~/test";
    private final String user = "sa";
    private final String passwd= "";



    public static DatabaseManager getDatabaseManager() {
        if(databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public void openConnection() {
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection(url, user, passwd);
        } catch (ClassNotFoundException | SQLException r) {
            r.printStackTrace();
        }
    }


    public void closeConnection() throws SQLException {
        con.close();
    }

    public Connection getConnection() {
        return con;
    }

    public int executeUpdate(String query) throws SQLException{
        Statement st = con.createStatement();
        return st.executeUpdate(query);
    }


    public ResultSet executeQuery(String query) throws SQLException {
        Statement st = con.createStatement();
        return st.executeQuery(query);
    }


}