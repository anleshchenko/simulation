package database;

import model.BusStation;
import model.Route;
import model.TransportationSystem;
import xml.StationsPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class RoutesImporter {
    private final String routeDelete = "DELETE FROM route;";
    private final String stationDelete = "DELETE FROM station;";
    private final String transDelete = "DELETE FROM route_station;";
    private final String routeInsert = "INSERT INTO route (route_id, circle, breaktime) VALUES (?, ?, ?);";
    private final String stationInsert = "INSERT INTO station (station_id, name) VALUES (?, ?);";
    private final String transInsert = "INSERT INTO route_station (rt_id, st_id) VALUES (?, ?);";

    private TransportationSystem system;
    private Connection connection;
    private StationsPool pool;

    public RoutesImporter(Connection connection, TransportationSystem system, StationsPool pool) {
        this.connection = connection;
        this.system = system;
        this.pool = pool;
    }

    public void importToDB(){
        delete();
       importStations();
       importRoutes();
       connectTables();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void delete(){
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(transDelete);
            deleteStatement.executeUpdate();
            deleteStatement = connection.prepareStatement(routeDelete);
            deleteStatement.executeUpdate();
            deleteStatement = connection.prepareStatement(stationDelete);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void importStations(){
        Set<BusStation> stations = pool.getStations();
        for (BusStation station: stations) {
            try {
                PreparedStatement statement = connection.prepareStatement(stationInsert);
                statement.setInt(1, station.getId());
                statement.setString(2, station.getName());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void importRoutes(){
        Set<Route> routes = system.getRoutes();
        for (Route route: routes){
            try {
                PreparedStatement statement = connection.prepareStatement(routeInsert);
                statement.setInt(1, route.getId());
                statement.setBoolean(2, route.isCircle());
                statement.setInt(3, route.getBreakTime());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void connectTables(){
        Set<Route> routes = system.getRoutes();
        for (Route route: routes){
            List<BusStation> stations = route.getBusStations();
            for (BusStation station: stations){
                try {
                    PreparedStatement statement = connection.prepareStatement(transInsert);
                    statement.setInt(1, route.getId());
                    statement.setInt(2, station.getId());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
