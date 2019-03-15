package services;

import dao.DAO;
import dao.ResultSelectDAO;
import dao.TransportationSelectDAO;
import dao.UsersLoginDAO;
import entities.*;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DbService {

    public boolean validateUser(String login, String password){
        DAO<User, String> dao = null;
        Connection connection = null;
        try {
             connection = DBConnect.getConnection();
            dao = new UsersLoginDAO(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dao == null)
            return false;
        User user = dao.select(login);
        return  (user.getLogin().equals(login) && user.getPassword().equals(password));
    }

    public boolean addUser(String login, String password, String email){
        DAO<User, String> dao = null;
        Connection connection = null;
        try {
            connection = DBConnect.getConnection();
            dao = new UsersLoginDAO(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dao == null)
            return false;
        return dao.insert(new User(0, login, password, email));
    }

    public List<Route> getRoutes(){
        TransportationSelectDAO tsDao = null;
        Connection connection = null;
        try {
            connection = DBConnect.getConnection();
            tsDao = new TransportationSelectDAO(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tsDao == null)
            return null;
        return new ArrayList<>(tsDao.select().getRoutes());
    }

    public List<Station> getRouteStations(int id){
        List<Route> list = getRoutes();
        for (Route route:list) {
            if(route.getId() == id)
                return route.getStations();
        }
        return null;
    }

    public Result getResult(){
        ResultSelectDAO dao = null;
        Connection connection = null;
        try {
            connection = DBConnect.getConnection();
            dao = new ResultSelectDAO(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dao == null)
            return null;
        return dao.select();
    }

    
}