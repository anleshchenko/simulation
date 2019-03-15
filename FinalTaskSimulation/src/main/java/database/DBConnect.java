package database;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnect {

    public static Connection getConnection() throws IOException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("classes/database.properties"))){
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
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