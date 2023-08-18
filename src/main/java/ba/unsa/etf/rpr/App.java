package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.Scanner;

public class App 
{
    private static UserSQLImplementation userDao;
    private static ScheduleSQLImplementation scheduleDao;
    private static Scanner ulaz;
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {

        userDao = UserSQLImplementation.getInstance();
        scheduleDao = ScheduleSQLImplementation.getInstance();

        ulaz = new Scanner(System.in);

        System.out.println("S kojom tabelom želite da radite?");



        int opcija = 0;

        do {
            System.out.println("Unesite opciju:\n1 - pretraga\n2 - unos\n3 - izmjena\n4 - brisanje\n5 - pretraga svih\n0 - kraj programa");
            opcija = ulaz.nextInt();
            if(ulaz.hasNextLine()) ulaz.nextLine();
            switch (opcija) {
                case 1:
                    pretragaKorisnika();
                    break;
                case 2:
                    unosKorisnika();
                    break;
                case 3:
                    izmjenaKorisnika();
                    break;
                case 4:
                    brisanjeKorisnika();
                    break;
                case 5:
                    pretragaSvihKorisnika();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nepoznata opcija");
            }
        }
        while (opcija != 0);

    }

    private static void unosKorisnika() {

        String username, password, salt, firstName, lastName, email;
        System.out.println("Unesite username: ");
        username = ulaz.nextLine();
        System.out.println("Unesite password: ");
        password = ulaz.nextLine();
        System.out.println("Unesite salt: ");
        salt = ulaz.nextLine();
        System.out.println("Unesite ime: ");
        firstName = ulaz.nextLine();
        System.out.println("Unesite prezime: ");
        lastName = ulaz.nextLine();
        System.out.println("Unesite email:");
        email = ulaz.nextLine();

        User user = new User(0, username, password, salt, firstName, lastName, email, 0);

        userDao.save(user);
    }

    private static void brisanjeKorisnika() {
        int id;
        String username, password, salt, firstName, lastName, email;
        System.out.println("Unesite ID usera kojeg brišete: ");
        id = ulaz.nextInt();
        User user = new User(id, "", "", "", "", "", "", id);
        userDao.delete(user);
    }

    private static void izmjenaKorisnika() {
        int id, rasporedId;
        String username, password, salt, firstName, lastName, email;
        System.out.println("Unesite ID usera kojeg mijenjate: ");
        id = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        System.out.println("Unesite username: ");
        username = ulaz.nextLine();
        System.out.println("Unesite password: ");
        password = ulaz.nextLine();
        System.out.println("Unesite salt: ");
        salt = ulaz.nextLine();
        System.out.println("Unesite ime: ");
        firstName = ulaz.nextLine();
        System.out.println("Unesite prezime: ");
        lastName = ulaz.nextLine();
        System.out.println("Unesite email:");
        email = ulaz.nextLine();
        System.out.println("Unesite id rasporeda:");
        rasporedId = ulaz.nextInt();
        User user = new User(id, username, password, salt, firstName, lastName, email, rasporedId);

        userDao.update(user);
    }

    private static void pretragaKorisnika() {
        System.out.println("Unesite id korisnika kojeg želite pretražiti: ");
        int id = ulaz.nextInt();

        for(User user : userDao.get(id))
            System.out.println("Ime i prezime korisnika je: " + user.getFirstName() + " " + user.getLastName() + ".");
    }

    private static void pretragaSvihKorisnika() {
        System.out.println("Pretražite ime i prezime svakog korisnika: ");

        for(User user : userDao.getAll())
            System.out.println("Ime i prezime korisnika je: " + user.getFirstName() + " " + user.getLastName() + ".");
    }

}
