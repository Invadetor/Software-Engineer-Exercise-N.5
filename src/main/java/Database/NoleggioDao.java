package Database;

import  Entity.Car;
import  Entity.Client;
import  Entity.Rental;
import  Exceptions.CarNotFoundException;
import  Exceptions.ClientNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NoleggioDao implements DAO<Rental, String> {
    @Override
    public Optional<Rental> get(String id) throws SQLException {
        String query = "Select * from Rentals where code  = '%s'";

        ResultSet rs = executeQuery(query);

        Rental r = null;

        if (rs.next()) {

            ClienteDao cdao = new ClienteDao();
            AutomobileDao aDao = new AutomobileDao();

            String codiceCliente = rs.getString("customer");
            String targaAuto = rs.getString("car");

            Optional<Car> optionalCar = aDao.get(targaAuto);
            Optional<Client> optionalCustomer = cdao.get(codiceCliente);

            if(optionalCar.isEmpty() ){
                throw new CarNotFoundException("Impossibile trovare la macchina con questa targa");
            }
            if(optionalCustomer.isEmpty()) {
                throw new ClientNotFoundException("Impossibile trovare il cliente con questo codice");
            }

            Client c = optionalCustomer.get();
            Car car = optionalCar.get();

            r = new Rental(rs.getString("code"),rs.getString("startDate"),rs.getString("endDate"),c, car, rs.getString("actualEndDate"));

        }

        return Optional.ofNullable(r);
    }

    public Optional<Rental> getLastRental(String customerName, String customerLastName, String carPlate) throws SQLException {

        String query = "Select * from Rentals as r join Customers as c where c.name = '%s' AND c.lastName = '%s' AND r.car = '%s' ";

        query = String.format(query, customerName, customerLastName, carPlate);

        ResultSet rs = executeQuery(query);

        Rental r = null;


        //TEST: Controllare che sia veramente l'ultimo aggiunto nella tabella
        if(rs.last()) {

            ClienteDao cdao = new ClienteDao();
            AutomobileDao aDao = new AutomobileDao();

            if(cdao.get(rs.getString("customer")).isEmpty() || aDao.get(rs.getString("car")).isEmpty()){
                throw new RuntimeException("Inconsistenza nei dati, impossibile trovare dati sul cliente o sul veicolo associato al noleggio");
            }

            Client c = cdao.get(rs.getString("customer")).get();
            Car car = aDao.get(rs.getString("car")).get();

            r = new Rental(rs.getString("code"),rs.getString("startDate"),rs.getString("endDate"),c, car, rs.getString("actualEndDate"));

        }

        return Optional.ofNullable(r);

    }

    public ArrayList<Rental> getRentalOfDate(String date) throws SQLException {
        String query = "select * from Rental where startingDate <= '%s' and endDate >= '%s' ";
        query = String.format(query, date, date);
        ResultSet rs = executeQuery(query);
        ArrayList<Rental> rentals = new ArrayList<>();
        while (rs.next()){
            ClienteDao cdao = new ClienteDao();
            AutomobileDao aDao = new AutomobileDao();

            if(cdao.get(rs.getString("customer")).isEmpty() || aDao.get(rs.getString("car")).isEmpty()){
                throw new RuntimeException("Inconsistenza nei dati, impossibile trovare dati sul cliente o sul veicolo associato al noleggio");
            }

            Client c = cdao.get(rs.getString("customer")).get();
            Car car = aDao.get(rs.getString("car")).get();

            Rental r = new Rental(rs.getString("code"),rs.getString("startDate"),rs.getString("endDate"),c, car, rs.getString("actualEndDate"));
            rentals.add(r);
        }
        return rentals;
    }


    public boolean setActualReturnDate(String code, String date){

        String query = "Update Rentals set actualEndDate = '%s' where code = '%s'";

        query = String.format(query, date, code);

        return executeUpdate(query);

    }


    @Override
    public ArrayList<Rental> getAll() throws SQLException, RuntimeException {
        String query = "Select * from Rentals";

        ResultSet rs = executeQuery(query);
        ArrayList<Rental> rents = new ArrayList<>();

        while (rs.next()) {
            ClienteDao cdao = new ClienteDao();
            AutomobileDao aDao = new AutomobileDao();

            if(cdao.get(rs.getString("customer")).isEmpty() || aDao.get(rs.getString("car")).isEmpty()){
                throw new RuntimeException("Inconsistenza nei dati, impossibile trovare dati sul cliente o sul veicolo associato al noleggio");
            }

            Client c = cdao.get(rs.getString("customer")).get();
            Car car = aDao.get(rs.getString("car")).get();

            Rental r = new Rental(rs.getString("code"),rs.getString("startDate"),rs.getString("endDate"),c, car, rs.getString("actualEndDate"));
            rents.add(r);
        }

        return rents;
    }

    @Override
    public boolean save(Rental dato) {

        String query = "insert into Rentals (startingDate, endDate, actualEndDate, code, client, car, price) values ('%s', '%s', '%s', '%s', '%s', '%s', %,.2f)";
        query = String.format(query, dato.getStartingDate(), dato.getFinalDate(), dato.getDateEffettiva(),dato.getCode(), dato.getClient().getCode(), dato.getCar().getPlate(), dato.getPrice());
        try {
            int result = databaseManager.executeUpdate(query);
            return true;
        } catch (SQLException errore) {
            errore.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean delete(String dato) {
        String query = "Delete row from Rentals where code = '%s'";
        query = String.format(query, dato);
        return executeUpdate(query);
    }

    @Override
    public boolean update(Optional<String> dato, HashMap<String, String> hm) {

        String query = "Update Rentals set ";

        for (Map.Entry<String, String> element :  hm.entrySet()) {
            String el = element.getKey() + " = " + element.getValue() + ",";
            query += el;
        }
        query = query.substring(0, query.length() - 1);

        if(dato.isPresent()){
            query += "where code = '%s'";
            query = String.format(query, dato.get());
        }

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

