package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserSQLImplementation implements UserDao  {

    private static UserSQLImplementation instance = null;

    private final Connection conn;
    private final PreparedStatement pretragaUpit, dodavanjeUpit, noviIdUpit, izmjenaUpit, brisanjeUpit, sviUpit, poImenuUpit;

    private UserSQLImplementation() throws SQLException {
        String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Projekat";
        String username = "freedb_edzanko1";
        String password = System.getenv("MYSQL_PASS");
        conn = DriverManager.getConnection(url, username, password);
        pretragaUpit = conn.prepareStatement("SELECT * FROM User WHERE User_ID=?");
        noviIdUpit = conn.prepareStatement("SELECT MAX(User_ID)+1 FROM User");
        dodavanjeUpit = conn.prepareStatement("INSERT INTO User VALUES(?,?,?,?,?,?,?)");
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
    public ArrayList<User> get(int id) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            pretragaUpit.setString(1, String.valueOf(id));
            ResultSet rs = pretragaUpit.executeQuery();
            while(rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> getAll() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            ResultSet rs = sviUpit.executeQuery();
            while(rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return users;
    }

    @Override
    public void save(User user) {
        try {
            ResultSet rs = noviIdUpit.executeQuery();
            if(rs.next())
                user.setId(rs.getInt(1));
            else
                user.setId(1);

            dodavanjeUpit.setInt(1, user.getId());
            dodavanjeUpit.setString(2, user.getUsername());
            dodavanjeUpit.setString(3, user.getPassword());;
            dodavanjeUpit.setString(4, user.getSalt());
            dodavanjeUpit.setString(5, user.getFirstName());
            dodavanjeUpit.setString(6, user.getLastName());
            dodavanjeUpit.setString(7, user.getEmail());
            dodavanjeUpit.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            izmjenaUpit.setInt(7, user.getId());
            izmjenaUpit.setString(1, user.getUsername());
            izmjenaUpit.setString(2, user.getPassword());;
            izmjenaUpit.setString(3, user.getSalt());
            izmjenaUpit.setString(4, user.getFirstName());
            izmjenaUpit.setString(5, user.getLastName());
            izmjenaUpit.setString(6, user.getEmail());
            izmjenaUpit.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try {
            brisanjeUpit.setInt(1, user.getId());
            brisanjeUpit.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getByUsername(String username) {
        List<User> users = new ArrayList<User>();
        try {
            poImenuUpit.setString(1, username);
            ResultSet rs = poImenuUpit.executeQuery();
            while(rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return users;
    }
}
