package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public static Connection getConnection() throws IOException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost/database?serverTimezone=Europe/Kiev";
        String username = "root";
        String password = "pass";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}