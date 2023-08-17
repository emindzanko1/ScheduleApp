package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.Scanner;

public class App 
{
    private static UserDAO userDAO;
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {

        userDAO = UserDAO.getInstance();

        int opcija = 0;

        do {
            System.out.println("Unesite opciju:\n1 - pretraga\n2 - unos\n3 - izmjena\n4 - brisanje\n0 - kraj programa");
            Scanner ulaz = new Scanner(System.in);
            opcija = ulaz.nextInt();
            switch (opcija) {
                case 1:
                    pretraga();
                    break;
                case 2:
                    unos();
                    break;
                case 3:
                    izmjena();
                    break;
                case 4:
                    brisanje();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nepoznata opcija");
            }
        }
        while (opcija != 0);



        userDAO.pretraga();

    }

    private static void unos() {
    }

    private static void brisanje() {
    }

    private static void izmjena() {
    }

    private static void pretraga() {
    }

}
