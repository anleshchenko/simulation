package database;

import simulation.Statistics;
import simulation.Timer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResultImporter {
    private final String insert = "INSERT INTO result (start_time, end_time, time_between, efficiency, routes, buses, passengers) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String delete = "DELETE FROM result";

    private Connection connection;
    private Statistics statistics;

    public ResultImporter(Connection connection) {
        this.connection = connection;
        statistics = Statistics.getInstance();
    }

    public void importToDB(){
        try{
            connection.setAutoCommit(false);
            PreparedStatement deleteStatement = connection.prepareStatement(delete);
            PreparedStatement insertStatement = connection.prepareStatement(insert);
            insertStatement.setString(1, Timer.msToDate(statistics.getStartTime()));
            insertStatement.setString(2, Timer.msToDate(statistics.getEndTime()));
            insertStatement.setString(3, (statistics.getDays() + " day(s)"));
            insertStatement.setDouble(4, statistics.countAveEfficiency());
            insertStatement.setInt(5, statistics.getRoutesNumber());
            insertStatement.setInt(6, statistics.getBusesNumber());
            insertStatement.setInt(7, statistics.getPassengersNumber());
            deleteStatement.executeUpdate();
            insertStatement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
