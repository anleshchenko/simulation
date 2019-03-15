package dao;

import entities.Result;
import entities.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSelectDAO {
    private final String SELECT = "SELECT * FROM RESULT;";

    private Connection connection;

    public ResultSelectDAO(Connection connection) {
        this.connection = connection;
    }

    public Result select(){
        Result result = new Result();
        PreparedStatement routeStatement = null;
        try {
            routeStatement = connection.prepareStatement(SELECT);
            ResultSet rs = routeStatement.executeQuery();
            if(rs.next()){
                result.setStart(rs.getString("start_time"));
                result.setEnd(rs.getString("end_time"));
                result.setBetween(rs.getString("time_between"));
                result.setEfficiency(rs.getDouble("efficiency"));
                result.setBuses(rs.getInt("buses"));
                result.setPassengers(rs.getInt("passengers"));
                result.setRoutes(rs.getInt("routes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
