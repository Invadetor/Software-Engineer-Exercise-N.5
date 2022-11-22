package MyBCE.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import MyBCE.Entity.Parking;
import com.sun.jdi.VoidType;


public class DatabaseManager {

    private static DatabaseManager databaseManager = null;
    private Connection con;

    private Statement s;

    public static DatabaseManager getDatabaseManager() {
        if(databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public void openConnection() {
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (ClassNotFoundException | SQLException r) {
            System.out.println(r.getMessage());
        }
    }

    // insert into Client (codice, nome) values ("111", "marco");

    public int insertClient(HashMap<String, String> values) throws SQLException {
        String name = values.get("name");
        String lastName = values.get("lastName");
        String code = values.get("code");
        String tel = values.get("tel");
        String nomeParcheggio = values.get("nomeParcheggio");
        String query = "insert into Cliente (codice, nome, cognome, tel, parcheggio) values (%s, %s, %s, %s, %s)";
        query = String.format(query, name, lastName, code, tel, nomeParcheggio);
        Statement st = con.createStatement();
        return st.executeUpdate(query);
    }

    public int insertCar(HashMap<String, String> values) throws SQLException {
        String targa = values.get("targa");
        String modello = values.get("modello");
        String casaAuto = values.get("casaAuto");
        int presenza = Integer.parseInt(values.get("presenza"));
        int km = Integer.parseInt(values.get("km"));
        String nomeParcheggio = values.get("nomeParcheggio");
        String query = "insert into Cliente (targa, modello, casaAuto, presenza, km, nomeParcheggio) values (%s, %s, %s, %i, %i, %s)";
        query = String.format(query, targa, modello, casaAuto, presenza, km, nomeParcheggio);
        Statement st = con.createStatement();
        return st.executeUpdate(query);
    }

    public int insertRental(HashMap<String, String> values) throws SQLException {
        String dataNoleggio = values.get("dataNoleggio");
        String dataConsegnaPrevista = values.get("dataConsegnaPrevista");
        String dataConsegnaEffettiva = values.get("dataConsegnaEffettiva");
        String codice = values.get("codice");
        String codiceCliente = values.get("codiceCliente");
        String targaAuto = values.get("targaAuto");
        String query = "insert into Cliente (dataNoleggio, dataConsegnaPrevista, dataConsegnaEffettiva, codice, codiceCliente, targaAuto) values (%s, %s, %s, %s, %s, %s)";
        query = String.format(query, dataNoleggio, dataConsegnaPrevista, dataConsegnaEffettiva, codice, codiceCliente, targaAuto);
        Statement st = con.createStatement();
        return st.executeUpdate(query);
    }

    public int insertParking(HashMap<String, String> values) throws SQLException {
        String name = values.get("nome");
        String query = "insert into Cliente (nome) values (%s)";
        query = String.format(query, name);
        Statement st = con.createStatement();
        return st.executeUpdate(query);
    }




}