package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class XMLImporter {
    private final String insert = "INSERT INTO transportation (xml) VALUES (?);";
    private final String delete = "DELETE FROM transportation;";

    private Connection connection;
    private FileInputStream fileStream;

    public XMLImporter(Connection connection, String path) {
        this.connection = connection;
        try {
            fileStream = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void importToDB(){
        try{
            connection.setAutoCommit(false);
            PreparedStatement deleteStatement = connection.prepareStatement(delete);
            PreparedStatement insertStatement = connection.prepareStatement(insert);
            insertStatement.setBinaryStream(1, fileStream);
            deleteStatement.executeUpdate();
            insertStatement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
