package Database;

import Entity.Car;
import Entity.Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ClienteDao implements DAO<Client, String> {

    @Override
    public Optional<Client> get(String id) throws SQLException {
        String query = "Select * from Customers where code = '%s'";
        query = String.format(query,id);

        ResultSet rs = executeQuery(query);
        Client c = null;
        if (rs.next()) {
            c = new Client(rs.getString("name"), rs.getString("lastName"), rs.getString("tel"), rs.getString("code"), rs.getString("email"));
        }
        return Optional.ofNullable(c);
    }

    @Override
    public ArrayList<Client> getAll() throws SQLException {

        String query = "Select * from Customers";

        ResultSet rs = executeQuery(query);
        ArrayList<Client> customers = new ArrayList<>();

        while (rs.next()) {
            Client  c = new Client(rs.getString("name"), rs.getString("lastName"), rs.getString("tel"), rs.getString("code"), rs.getString("email"));
            customers.add(c);
        }

        return customers;
    }

    @Override
    public boolean save(Client dato) throws SQLException {

        String query = "insert into Customers (code, name, lastName, tel, email) values ('%s', '%s', '%s', '%s', '%s')";
        query = String.format(query, dato.getCode(), dato.getName(), dato.getLastName(), dato.getTel(), dato.getEmail());

        databaseManager.executeUpdate(query);
        return true;
    }

    @Override
    public boolean update(Optional<String> code, HashMap<String, String> hm) {
        String query = "Update Customers set ";

        for (Map.Entry<String, String> element :  hm.entrySet()) {
            String el = element.getKey() + " = " + element.getValue() + ",";
            query += el;
        }
        query = query.substring(0, query.length() - 1);

        if(code.isPresent()){
            query += "where code = '%s'";
            query = String.format(query, code.get());
        }

        return executeUpdate(query);
    }

    @Override
    public boolean delete(String dato) {
        String query = "Delete row from Customers where code = '%s'";

        query = String.format(query, dato);
        return executeUpdate(query);
    }

    @Override
    public boolean executeUpdate(String query) {
        try {
            int result = databaseManager.executeUpdate(query);
            return true;
        } catch (SQLException errore) {
            errore.printStackTrace();
            return false;
        }
    }

    @Override
    public ResultSet executeQuery(String query) throws SQLException {
        return databaseManager.executeQuery(query);
    }



}

