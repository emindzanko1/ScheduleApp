package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.exceptions.ScheduleException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserSQLImplementation implements UserDao  {

    private static UserSQLImplementation instance = null;

    private Connection conn;
    private final PreparedStatement pretragaUpit, dodavanjeUpit, noviIdUpit, izmjenaUpit, brisanjeUpit, sviUpit, poImenuUpit;

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

        pretragaUpit = conn.prepareStatement("SELECT * FROM User WHERE User_ID=?");
        noviIdUpit = conn.prepareStatement("SELECT MAX(User_ID)+1 FROM User");
        dodavanjeUpit = conn.prepareStatement("INSERT INTO User VALUES(?,?,?,?,?)");
        izmjenaUpit = conn.prepareStatement("UPDATE User SET username=?, hashedpassword=?, salt=?, firstname=?, lastname=?, email=? WHERE User_ID=?");
        brisanjeUpit = conn.prepareStatement("DELETE FROM User WHERE User_ID=?");
        sviUpit = conn.prepareStatement("SELECT * FROM User");
        poImenuUpit = conn.prepareStatement("SELECT * FROM User WHERE username=?");
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
    public ArrayList<User> get(int id) throws ScheduleException{
        ArrayList<User> users = new ArrayList<>();
        try {
            pretragaUpit.setString(1, String.valueOf(id));
            ResultSet rs = pretragaUpit.executeQuery();
            while(rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting user by id.", e);
        }
        return users;
    }

    @Override
    public List<User> getAll() throws ScheduleException {
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet rs = sviUpit.executeQuery();
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
            ResultSet rs = noviIdUpit.executeQuery();
            if(rs.next())
                user.setId(rs.getInt(1));
            else
                user.setId(1);

            dodavanjeUpit.setInt(1, user.getId());
            dodavanjeUpit.setString(2, user.getUsername());
            dodavanjeUpit.setString(3, user.getPassword());
            dodavanjeUpit.setString(4, user.getFirstName());
            dodavanjeUpit.setString(5, user.getLastName());
            dodavanjeUpit.execute();

        } catch (SQLException e) {
            throw new ScheduleException("Failed creating a new user.", e);
        }
    }

    @Override
    public void update(User user) throws ScheduleException {
        try {
            izmjenaUpit.setInt(5, user.getId());
            izmjenaUpit.setString(1, user.getUsername());
            izmjenaUpit.setString(2, user.getPassword());
            izmjenaUpit.setString(3, user.getFirstName());
            izmjenaUpit.setString(4, user.getLastName());
            izmjenaUpit.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed updating a user.", e);
        }

    }

    @Override
    public void delete(User user) throws ScheduleException {
        try {
            brisanjeUpit.setInt(1, user.getId());
            brisanjeUpit.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed deleting a user.", e);
        }
    }

    @Override
    public User getByUsername(String username) throws ScheduleException {
        User user = new User();
        try {
            poImenuUpit.setString(1, username);
            ResultSet rs = poImenuUpit.executeQuery();
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
