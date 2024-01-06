package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ScheduleException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * MySQL Implementation of DAO
 * @author Emin Džanko
 */
public class UserSQLImplementation implements UserDao  {

    private static UserSQLImplementation instance = null;

    private Connection conn;
    private final PreparedStatement searchQuery, addQuery, newIdQuery, changeQuery, deleteQuery, allQuery, getByNameQuery;

    private UserSQLImplementation() throws SQLException {
        Properties p = new Properties();
        try {
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = p.getProperty("db.url");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            conn = DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        searchQuery = conn.prepareStatement("SELECT * FROM User WHERE user_id=?");
        newIdQuery = conn.prepareStatement("SELECT MAX(user_id)+1 FROM User");
        addQuery = conn.prepareStatement("INSERT INTO User VALUES(?,?,?,?,?)");
        changeQuery = conn.prepareStatement("UPDATE User SET username=?, password=?, first_name=?, last_name=? WHERE user_id=?");
        deleteQuery = conn.prepareStatement("DELETE FROM User WHERE user_id=?");
        allQuery = conn.prepareStatement("SELECT * FROM User");
        getByNameQuery = conn.prepareStatement("SELECT * FROM User WHERE username=?");
    }

    public static UserSQLImplementation getInstance() throws SQLException {
        if (instance == null) instance = new UserSQLImplementation();
        return instance;
    }

    public static void removeInstance() throws SQLException {
        if (instance == null) return;
        instance.conn.close();
        instance = null;
    }

    @Override
    public User get(int id) throws ScheduleException{
        User user = new User();
        try {
            searchQuery.setString(1, String.valueOf(id));
            ResultSet rs = searchQuery.executeQuery();
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting user by id.", e);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws ScheduleException {
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet rs = allQuery.executeQuery();
            while(rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting all users.", e);
        }
        return users;
    }

    @Override
    public void save(User user) throws ScheduleException {
        try {
            ResultSet rs = newIdQuery.executeQuery();
            if(rs.next())
                user.setId(rs.getInt(1));
            else
                user.setId(1);

            addQuery.setInt(1, user.getId());
            addQuery.setString(2, user.getUsername());
            addQuery.setString(3, user.getPassword());
            addQuery.setString(4, user.getFirstName());
            addQuery.setString(5, user.getLastName());
            addQuery.execute();

        } catch (SQLException e) {
            throw new ScheduleException("Failed creating a new user.", e);
        }
    }

    @Override
    public void update(User user) throws ScheduleException {
        try {
            changeQuery.setInt(5, user.getId());
            changeQuery.setString(1, user.getUsername());
            changeQuery.setString(2, user.getPassword());
            changeQuery.setString(3, user.getFirstName());
            changeQuery.setString(4, user.getLastName());
            changeQuery.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed updating a user.", e);
        }

    }

    @Override
    public void delete(User user) throws ScheduleException {
        try {
            deleteQuery.setInt(1, user.getId());
            deleteQuery.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed deleting a user.", e);
        }
    }

    @Override
    public User getByUsername(String username) throws ScheduleException {
        User user = new User();
        try {
            getByNameQuery.setString(1, username);
            ResultSet rs = getByNameQuery.executeQuery();
            while(rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting a user by username.", e);
        }
        return user;
    }
}
