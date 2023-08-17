package ba.unsa.etf.rpr;

import java.sql.*;

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        UserDAO userDAO = UserDAO.getInstance();

       userDAO.pretraga();

    }

}
