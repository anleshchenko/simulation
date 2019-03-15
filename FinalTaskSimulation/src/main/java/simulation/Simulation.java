package simulation;

import database.DBConnect;
import database.ResultImporter;
import database.RoutesImporter;
import database.XMLImporter;
import model.*;
import xml.StationsPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

public class Simulation {
    private final long MINUTE = 60000;
    private final long DAY = 86400000;

    private TransportationSystem system;
    private Timer timer;
    private long initTime, endTime;
    private int timeBetween;
    private Statistics stat;

    private int passengersEveryMinute;

    public Simulation(TransportationSystem system, int days, int timeBetween, int passengersEveryMinute) {
        this.system = system;
        Date date = new Date();
        this.initTime = date.getTime() - date.getTime()%MINUTE;
        this.endTime = initTime + days*DAY;
        stat = Statistics.getInstance();
        stat.setRoutesNumber(system.getRoutes().size());
        stat.setStartTime(initTime);
        stat.setEndTime(endTime);
        stat.setDays(days);
        timer = new Timer(initTime, endTime);
        this.timeBetween = timeBetween;
        this.passengersEveryMinute = passengersEveryMinute;
        addInstancesToTimer();
    }

    private void addInstancesToTimer(){
        Set<Route> routes = system.getRoutes();
        for (Route route: routes) {
            // Generating passengers
            PassengersOnRoute passengers = new PassengersOnRoute(route, passengersEveryMinute);
            timer.addInstance(passengers);
            // Generating buses
            int busNumber = route.getBusesNeeded(timeBetween);
            for (int i = 0; i < busNumber; i++) {
                BusOnRoute bus = new BusOnRoute(new Bus(BusModel.MAN, route), initTime + timeBetween*MINUTE*i);
                timer.addInstance(bus);
                stat.setBusesNumber(stat.getBusesNumber()+1);

            }
        }
    }

    public void start(){
        timer.startTimer();
    }

    public void exportResult(){
        Connection connection = null;
        try {
            connection = DBConnect.getConnection();
        } catch (IOException | SQLException e ) {
            e.printStackTrace();
        }
        if (connection != null){
            ResultImporter importer = new ResultImporter(connection);
            importer.importToDB();
        }
    }

    public void exportXML(String path){
        Connection connection = null;
        try {
            connection = DBConnect.getConnection();
        } catch (IOException | SQLException e ) {
            e.printStackTrace();
        }
        if (connection != null){
            XMLImporter importer = new XMLImporter(connection, path);
            importer.importToDB();
        }
    }

    public void exportRoutes(StationsPool pool){
        Connection connection = null;
        try {
            connection = DBConnect.getConnection();
        } catch (IOException | SQLException e ) {
            e.printStackTrace();
        }
        if (connection != null){
            RoutesImporter importer = new RoutesImporter(connection, system, pool);
            importer.importToDB();
        }
    }
}
