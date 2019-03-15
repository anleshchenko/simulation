package dao;

import entities.Route;
import entities.Station;
import entities.TransportationSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransportationSelectDAO {
    private final String selectRoutes = "SELECT route_id, breaktime FROM route;";
    private final String selectStations = "SELECT station_id, name FROM station JOIN route_station ON station.station_id = route_station.st_id WHERE rt_id = ?;";
    private final Connection connection;

    private TransportationSystem system;

    public TransportationSelectDAO(Connection connection) {
        this.connection = connection;
        system = new TransportationSystem();
    }

    public TransportationSystem select(){
        try {
            PreparedStatement routeStatement = connection.prepareStatement(selectRoutes);
            ResultSet rs = routeStatement.executeQuery();
            while(rs.next()){
                Route route = new Route();
                route.setId(rs.getInt("route_id"));
                route.setBreaktime(rs.getInt("breaktime"));
                system.addRoute(route);
            }

            for (Route route:system.getRoutes()) {
                PreparedStatement stationsStatement = connection.prepareStatement(selectStations);
                stationsStatement.setInt(1, route.getId());
                ResultSet stSet = stationsStatement.executeQuery();
                while(stSet.next()){
                    Station station = new Station();
                    station.setId(stSet.getInt("station_id"));
                    station.setName(stSet.getString("name"));
                    route.addStation(station);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return system;
    }
}
