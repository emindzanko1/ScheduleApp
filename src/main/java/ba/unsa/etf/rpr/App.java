package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.Scanner;

public class App 
{
    private static UserSQLImplementation userDao;
    private static ScheduleSQLImplementation scheduleDao;
    private static ScheduleItemSQLImplementation scheduleItemDao;
    private static Scanner ulaz;
    public static void main( String[] args ) throws SQLException {

        userDao = UserSQLImplementation.getInstance();
        scheduleDao = ScheduleSQLImplementation.getInstance();
        scheduleItemDao = ScheduleItemSQLImplementation.getInstance();


        ulaz = new Scanner(System.in);

        int opcija = 0, brojTabele = 0;
        do {
            System.out.println("S kojom tabelom želite da radite: 1, 2 ili 3? Unesite 0 ako ne želite da radite.");
            brojTabele = ulaz.nextInt();

            if (brojTabele == 1) {
                do {
                    System.out.println("Unesite opciju:\n1 - pretraga\n2 - unos\n3 - izmjena\n4 - brisanje\n5 - pretraga svih\n6 - pretraga po imenu\n0 - kraj programa");
                    opcija = ulaz.nextInt();
                    if (ulaz.hasNextLine()) ulaz.nextLine();
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
                        case 6:
                            pretragaKorisnikaPoImenu();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Nepoznata opcija");
                    }
                }
                while (opcija != 0);
            } else if (brojTabele == 2) {
                do {
                    System.out.println("Unesite opciju:\n1 - pretraga\n2 - unos\n3 - izmjena\n4 - brisanje\n5 - pretraga svih\n6 - pretraga po imenu\n0 - kraj programa");
                    opcija = ulaz.nextInt();
                    if (ulaz.hasNextLine()) ulaz.nextLine();
                    switch (opcija) {
                        case 1:
                            pretragaRasporeda();
                            break;
                        case 2:
                            unosRasporeda();
                            break;
                        case 3:
                            izmjenaRasporeda();
                            break;
                        case 4:
                            brisanjeRasporeda();
                            break;
                        case 5:
                            pretragaSvihRasporeda();
                            break;
                        case 6:
                            pretragaRasporedaPoImenu();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Nepoznata opcija");
                    }
                }
                while (opcija != 0);
            } else if (brojTabele == 3) {
                do {
                    System.out.println("Unesite opciju:\n1 - pretraga\n2 - unos\n3 - izmjena\n4 - brisanje\n5 - pretraga svih\n6 - pretraga po imenu\n0 - kraj programa");
                    opcija = ulaz.nextInt();
                    if (ulaz.hasNextLine()) ulaz.nextLine();
                    switch (opcija) {
                        case 1:
                            pretragaSadržajaRasporeda();
                            break;
                        case 2:
                            unosSadržajaRasporeda();
                            break;
                        case 3:
                            izmjenaSadržajaRasporeda();
                            break;
                        case 4:
                            brisanjeSadržajaRasporeda();
                            break;
                        case 5:
                            pretragaSvihSadržajaRasporeda();
                            break;
                        case 6:
                            pretragaSadržajaRasporedaPoImenu();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Nepoznata opcija");
                    }
                }
                while (opcija != 0);
            }
            else {
                System.out.println("Vidim da ne želite raditi, doviđenja.");
            }
        } while(brojTabele != 0);
    }

    private static void pretragaSadržajaRasporedaPoImenu() {
    }

    private static void pretragaSvihSadržajaRasporeda() {
    }

    private static void brisanjeSadržajaRasporeda() {
    }

    private static void izmjenaSadržajaRasporeda() {
    }

    private static void unosSadržajaRasporeda() {
    }

    private static void pretragaSadržajaRasporeda() {
        System.out.println("Unesite id sadržaja kojeg želite pretražiti: ");
        int id = ulaz.nextInt();

        for(ScheduleItem scheduleItemDao : scheduleItemDao.get(id))
            System.out.println("Naziv sadržaja je: " + scheduleItemDao.getEventName() + ".");
    }

    private static User unosKorisnikaDuplication(int id) {
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
        return(new User(id, username, password, salt, firstName, lastName, email));
    }

    private static void unosKorisnika() {
        int id = 0;
        User user = unosKorisnikaDuplication(id);
        userDao.save(user);
    }

    private static void brisanjeKorisnika() {
        int id;
        System.out.println("Unesite ID usera kojeg brišete: ");
        id = ulaz.nextInt();
        User user = new User(id, "", "", "", "", "", "");
        userDao.delete(user);
    }

    private static void izmjenaKorisnika() {
        int id;
        System.out.println("Unesite ID usera kojeg mijenjate: ");
        id = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        User user = unosKorisnikaDuplication(id);
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

    private static void pretragaKorisnikaPoImenu() {
        System.out.println("Unesite username korisnika kojeg želite pretražiti:  ");
        String username = ulaz.nextLine();

        for(User user : userDao.getByUsername(username))
            System.out.println("Ime i prezime korisnika je: " + user.getFirstName() + " " + user.getLastName() + ".");
    }

    private static Schedule unosRasporedaDuplication(int id, int userId) {
        System.out.println("Unesite naziv rasporeda: ");
        String scheduleName = ulaz.nextLine();

        return new Schedule(id, userId, scheduleName);
    }

    private static void unosRasporeda() {
        int id = 0;
        System.out.println("Unesite ispravan id korisnika čiji raspored želite kreireati: ");
        int userId = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        Schedule schedule = unosRasporedaDuplication(id, userId);
        scheduleDao.save(schedule);
    }

    private static void brisanjeRasporeda() {
        int id, userId;
        System.out.println("Unesite ID rasporeda kojeg brišete: ");
        id = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        System.out.println("Unesite ID korisnika čiji raspored brišete: ");
        userId = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        Schedule schedule = new Schedule(id, userId, "");
        scheduleDao.delete(schedule);
    }

    private static void izmjenaRasporeda() {
        int id, userId;
        System.out.println("Unesite ID rasporeda kojeg mijenjate: ");
        id = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        System.out.println("Unesite ID korisnika čiji raspored mijenjate: ");
        userId = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        Schedule schedule = unosRasporedaDuplication(id,userId);
        scheduleDao.update(schedule);
    }

    private static void pretragaRasporeda() {
        System.out.println("Unesite id rasporeda kojeg želite pretražiti: ");
        int id = ulaz.nextInt();

        for(Schedule schedule : scheduleDao.get(id))
            System.out.println("Naziv rasporeda je: " + schedule.getScheduleName() + ".");
    }

    private static void pretragaSvihRasporeda() {
        System.out.println("Pretražite naziv svakog rasporeda: ");

        for(Schedule schedule : scheduleDao.getAll())
            System.out.println("Naziv svakog rasporeda: " + schedule.getScheduleName() + ".");
    }

    private static void pretragaRasporedaPoImenu() {
        System.out.println("Unesite naziv raspored koji želite pretražiti:  ");
        String scheduleName = ulaz.nextLine();

        for(Schedule schedule : scheduleDao.getByScheduleName(scheduleName))
            System.out.println("Naziv rasporeda je: " + schedule.getScheduleName() +  ".");
    }
}
