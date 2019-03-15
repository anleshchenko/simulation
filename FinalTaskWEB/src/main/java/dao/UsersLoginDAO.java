package dao;

import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersLoginDAO implements DAO<User, String> {

    private final Connection connection;

    public UsersLoginDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(User user) {
        boolean isSuccessful = false;
        try(PreparedStatement statement = connection.prepareStatement(UserQuery.INSERT.query)){
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            isSuccessful = statement.executeUpdate() > 0;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    @Override
    public User select(String login) {
        User user = new User();
        try(PreparedStatement statement = connection.prepareStatement(UserQuery.SELECT.query)){
            statement.setString(1, login);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                user.setUserId(set.getInt("user_id"));
                user.setLogin(set.getString("login"));
                user.setPassword(set.getString("password"));
                user.setEmail(set.getString("email"));
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean isSuccessful = false;
        try(PreparedStatement statement = connection.prepareStatement(UserQuery.UPDATE.query)){
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getUserId());
            isSuccessful = statement.executeUpdate() > 0;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    @Override
    public boolean delete(String login) {
        boolean isSuccessful = false;
        try(PreparedStatement statement = connection.prepareStatement(UserQuery.DELETE.query)){
            statement.setString(1, login);
            isSuccessful = statement.executeUpdate() > 0;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }
}

enum UserQuery{
    SELECT("SELECT user_id, login, password, email FROM user WHERE login = ?;"),
    INSERT("INSERT INTO user (login, password, email) VALUES (?, ?, ?);"),
    UPDATE("UPDATE user SET login = ?, password = ?, email = ? WHERE user_id = ?"),
    DELETE("DELETE FROM user WHERE login = ?");

    String query;

    UserQuery(String query) {
        this.query = query;
    }
}