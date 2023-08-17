package ba.unsa.etf.rpr;

import java.sql.*;

public class UserDAO {

    private Connection conn;
    private PreparedStatement ps;

    UserDAO () throws SQLException {
        String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Projekat";
        String username = "freedb_edzanko1";
        String password = System.getenv("MYSQL_PASS");
        this.conn = DriverManager.getConnection(url, username, password); // Initialize class-level conn
        ps = conn.prepareStatement("SELECT * FROM User ");
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
