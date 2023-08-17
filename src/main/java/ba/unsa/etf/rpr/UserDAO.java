package ba.unsa.etf.rpr;

import java.sql.*;

public class UserDAO {

    private static UserDAO instance = null;

    private Connection conn;
    private PreparedStatement ps;

    private UserDAO () throws SQLException {
        String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Projekat";
        String username = "freedb_edzanko1";
        String password = System.getenv("MYSQL_PASS");
        conn = DriverManager.getConnection(url, username, password);
        try {
            ps = conn.prepareStatement("SELECT * FROM User");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
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

    void pretraga() {
        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt(1);
                String username1 = rs.getString(2);
                System.out.println("Id 1. korisnika je " + id + ", naziv 1. korisnika je " + username1 + ".");
            }
            System.out.println("Connection successful!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }


}
