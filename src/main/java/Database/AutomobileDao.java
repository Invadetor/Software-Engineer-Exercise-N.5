package Database;

import  Entity.Car;
import  Exceptions.CarNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class AutomobileDao implements DAO<Car, String> {

    @Override
    public Optional<Car> get(String id) throws SQLException {

        String query = "Select * from Cars where plate = '%s'";

        query = String.format(query,id);

        ResultSet rs = executeQuery(query);
        Car c = null;

        if (rs.next()) {

            int free = rs.getInt("free");

            boolean isFree = free == 1;

            c = new Car( rs.getString("manufacturer"), rs.getString("model"), rs.getString("plate"), rs.getDouble("pricePerDay"), rs.getDouble("km"), isFree);
        }

        return Optional.ofNullable(c);
    }

    @Override
    public ArrayList<Car> getAll() throws SQLException {

        String query = "Select * from Cars";

        ResultSet rs = executeQuery(query);

        ArrayList<Car> cars = new ArrayList<>();

        while (rs.next()) {
            Car c = new Car(rs.getString("manufacturer"), rs.getString("model"), rs.getString("plate"),
                    rs.getDouble("pricePerDay"), rs.getDouble("km"), rs.getInt("free") == 1 );
            cars.add(c);
        }

        return cars;
    }

    public Optional<Car> getFreeCarFromDate(String startingDate, String endDate) throws SQLException, CarNotFoundException {
        String query = "Select * from Cars where plate not in (Select car from Rentals where (startingDate >= '%s' AND startingDate <= '&s') OR (endDate >= '&s' AND endDate <= '&s') OR (startingDate <= '&s' AND endDate>= '&s'))";
        query = String.format(query, startingDate, endDate, startingDate, endDate, startingDate, endDate);
        ResultSet rs = executeQuery(query);
        Car c = null;
        if (rs.next()) {
            c = new Car(rs.getString("manufacturer"), rs.getString("model"), rs.getString("plate"),
                    rs.getDouble("pricePerDay"), rs.getDouble("km"), rs.getInt("free") == 1 );
        }
        return Optional.ofNullable(c);
    }

    @Override
    public boolean save(Car dato) {

        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.ITALIAN);
        DecimalFormat df = new DecimalFormat();
        df.setDecimalFormatSymbols(dfs);
        String sKm = df.format(dato.getKm());
        String sPrice = df.format(dato.getDayPrice());

        String query = "insert into Cars (plate, model, manufacturer, free, km, pricePerDay) values ('%s', '%s', '%s', %i, %s, %s)";
        query = String.format(query, dato.getPlate(), dato.getModel(), dato.getManufacter(), dato.getFree()? 1: 0, sKm,sPrice);
        return executeUpdate(query);

    }

    public boolean returnCar(String plate, double km) throws SQLException {

        Optional<Car> optionalCar = get(plate);

        if(optionalCar.isEmpty()) {
            throw new RuntimeException("Errore, nessuna auto con la targa indicata");
        }

        Car c = optionalCar.get();

        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.ITALIAN);
        DecimalFormat df = new DecimalFormat();
        df.setDecimalFormatSymbols(dfs);
        String sKm = df.format(km);

        String query = "update Cars set free = 1, km = %s where plate = '%s'";

        query = String.format(query, c.getKm()+sKm ,plate);
        return executeUpdate(query);
    }

    public boolean setOccupata(String plate) {

        String query = "update Cars set free = 0 where plate = '%s'";
        query = String.format(query,plate);
        return executeUpdate(query);

    }

    @Override
    public boolean delete(String dato) {
        String query = "Delete row from Cars where plate = '%s'";
        query = String.format(query, dato);
        return executeUpdate(query);
    }

    @Override
    public boolean update(Optional<String> plate, HashMap<String, String> hm) {

        String query = "Update Cars set ";

        for (HashMap.Entry<String, String> element :  hm.entrySet()) {
            String el = element.getKey() + " = " + element.getValue() + ",";
            query += el;
        }

        //Toglie l'ultimo carattere
        query = query.substring(0, query.length() - 1);

        if(plate.isPresent()){
            query += "where plate = '%s'";
            query = String.format(query, plate.get());
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
