package ba.unsa.etf.rpr;

import java.sql.*;

public class UserDAO {

    private static UserDAO instance = null;

    private Connection conn;
    private PreparedStatement pretragaUpit, dodavanjeUpit, noviIdUpit, izmjenaUpit;

    private UserDAO () throws SQLException {
        String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Projekat";
        String username = "freedb_edzanko1";
        String password = System.getenv("MYSQL_PASS");
        conn = DriverManager.getConnection(url, username, password);
        pretragaUpit = conn.prepareStatement("SELECT * FROM User WHERE username=?");
        noviIdUpit = conn.prepareStatement("SELECT MAX(User_ID)+1 FROM User");
        dodavanjeUpit = conn.prepareStatement("INSERT INTO User VALUES(?,?,?,?,?,?,?)");
        izmjenaUpit = conn.prepareStatement("UPDATE User SET username=?, hashedpassword=?, salt=?, firstname=?, lastname=?, email=? WHERE User_ID=?");
    }

    public static UserDAO getInstance() throws SQLException {
        if (instance == null) instance = new UserDAO();
        return instance;
    }

    public static void removeInstance() throws SQLException {
        if (instance == null) return;
        instance.conn.close();
        instance = null;
    }

    void pretraga(String pretraga) {
        try {
            pretragaUpit.setString(1,pretraga);
            ResultSet rs = pretragaUpit.executeQuery();
            while(rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String salt = rs.getString(4);
                String firstName = rs.getString(5);
                String lastName = rs.getString(6);
                String email = rs.getString(7);
                System.out.println("ID: " + id + " username: " + username + " password " + password + " salt: " + salt + " firstName: " + firstName + " lastName: " + lastName + " email: " + email);
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }


    public User dodaj(User user) {
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
        return user;
    }

    public void izmijeni(User user) {
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
}
