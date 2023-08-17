package ba.unsa.etf.rpr;

import java.sql.*;

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Projekat";
        String username = "freedb_edzanko1";
        String password = System.getenv("MYSQL_PASS");

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM User ");
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
