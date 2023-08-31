package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.ScheduleItemSQLImplementation;
import ba.unsa.etf.rpr.dao.ScheduleSQLImplementation;
import ba.unsa.etf.rpr.dao.UserSQLImplementation;
import ba.unsa.etf.rpr.domain.Schedule;
import ba.unsa.etf.rpr.domain.ScheduleItem;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ScheduleException;

import java.sql.*;
import java.util.Scanner;

/**
 *  CLI (Command Line Interface) implementation
 * @author Emin Džanko
 */
public class App
{
    private static UserSQLImplementation userDao;
    private static ScheduleSQLImplementation scheduleDao;
    private static ScheduleItemSQLImplementation scheduleItemDao;
    private static Scanner ulaz;
    public static void main( String[] args ) throws SQLException, ScheduleException {

        userDao = UserSQLImplementation.getInstance();
        scheduleDao = ScheduleSQLImplementation.getInstance();
        scheduleItemDao = ScheduleItemSQLImplementation.getInstance();


        ulaz = new Scanner(System.in);

        int opcija, brojTabele ;
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
                            pretragaSadrzajaRasporeda();
                            break;
                        case 2:
                            unosSadrzajaRasporeda();
                            break;
                        case 3:
                            izmjenaSadrzajaRasporeda();
                            break;
                        case 4:
                            brisanjeSadrzajaRasporeda();
                            break;
                        case 5:
                            pretragaSvihSadrzajaRasporeda();
                            break;
                        case 6:
                            pretragaSadrzajaRasporedaPoImenu();
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

    private static void pretragaSadrzajaRasporedaPoImenu() throws ScheduleException {
        System.out.println("Unesite naziv događaja:  ");
        String eventName = ulaz.nextLine();

        for(ScheduleItem scheduleItem : scheduleItemDao.getByEventName(eventName))
            System.out.println("Naziv događaja je: " + scheduleItem.getEventName());
    }

    private static void pretragaSvihSadrzajaRasporeda() throws ScheduleException {
        System.out.println("Pretražite naziv sadržaja rasporeda: ");

        for(ScheduleItem scheduleItem : scheduleItemDao.getAll())
            System.out.println("Naziv sadržaja rasporeda je: " + scheduleItem.getEventName() + ".");
    }

    private static void brisanjeSadrzajaRasporeda() throws ScheduleException {
        int id, scheduleId;
        System.out.println("Unesite ID sadržaja rasporeda kojeg brišete: ");
        id = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        System.out.println("Unesite ID rasporeda čiji sadržaj brišete: ");
        scheduleId = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        ScheduleItem scheduleItem = new ScheduleItem(id, scheduleId, "", "", "", "", "");
        scheduleItemDao.delete(scheduleItem);
    }

    private static void izmjenaSadrzajaRasporeda() throws ScheduleException {
        int id, scheduleId;
        System.out.println("Unesite ID sadržaja rasporeda koje mijenjate mijenjate: ");
        id = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        System.out.println("Unesite ID rasporeda čiji sadržaj mijenjate: ");
        scheduleId = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        ScheduleItem scheduleItem = unosSadrzajaRasporedaDuplication(id,scheduleId);
        scheduleItemDao.update(scheduleItem);
    }

    private static void pretragaSadrzajaRasporeda() throws ScheduleException {
        System.out.println("Unesite id sadržaja kojeg želite pretražiti: ");
        int id = ulaz.nextInt();

        for(ScheduleItem scheduleItemDao : scheduleItemDao.get(id))
            System.out.println("Naziv sadržaja je: " + scheduleItemDao.getEventName() + ".");
    }

    private static User unosKorisnikaDuplication(int id) {
        String username, password, firstName, lastName;
        System.out.println("Unesite username: ");
        username = ulaz.nextLine();
        System.out.println("Unesite password: ");
        password = ulaz.nextLine();
        System.out.println("Unesite ime: ");
        firstName = ulaz.nextLine();
        System.out.println("Unesite prezime: ");
        lastName = ulaz.nextLine();
        return(new User(id, username, password, firstName, lastName));
    }

    private static void unosSadrzajaRasporeda() throws ScheduleException {
        int id = 0;
        System.out.println("Unesite ispravan id rasporeda čije stavke želite kreireati: ");
        int scheduleId = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        ScheduleItem scheduleItem = unosSadrzajaRasporedaDuplication(id, scheduleId);
        scheduleItemDao.save(scheduleItem);
    }

    private static ScheduleItem unosSadrzajaRasporedaDuplication(int id, int scheduleId) {
        String dayOfTheWeek, startTime, endTime, eventName, location;
        System.out.println("Unesite dan u sedmici: ");
        dayOfTheWeek = ulaz.nextLine();
        System.out.println("Unesite početno vrijeme: ");
        startTime = ulaz.nextLine();
        System.out.println("Unesite krajnje vrijeme: ");
        endTime = ulaz.nextLine();
        System.out.println("Unesite naziv događaja: ");
        eventName = ulaz.nextLine();
        System.out.println("Unesite lokaciju: ");
        location = ulaz.nextLine();
        return(new ScheduleItem(id, scheduleId, dayOfTheWeek, startTime, endTime, eventName, location));
    }

    private static void unosKorisnika() throws ScheduleException {
        int id = 0;
        User user = unosKorisnikaDuplication(id);
        userDao.save(user);
    }

    private static void brisanjeKorisnika() throws ScheduleException {
        int id;
        System.out.println("Unesite ID usera kojeg brišete: ");
        id = ulaz.nextInt();
        User user = new User(id, "", "", "", "");
        userDao.delete(user);
    }

    private static void izmjenaKorisnika() throws ScheduleException {
        int id;
        System.out.println("Unesite ID usera kojeg mijenjate: ");
        id = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        User user = unosKorisnikaDuplication(id);
        userDao.update(user);
    }

    private static void pretragaKorisnika() throws ScheduleException {
        System.out.println("Unesite id korisnika kojeg želite pretražiti: ");
        int id = ulaz.nextInt();

        for(User user : userDao.get(id))
            System.out.println("Ime i prezime korisnika je: " + user.getFirstName() + " " + user.getLastName() + ".");
    }

    private static void pretragaSvihKorisnika() throws ScheduleException {
        System.out.println("Pretražite ime i prezime svakog korisnika: ");

        for(User user : userDao.getAll())
            System.out.println("Ime i prezime korisnika je: " + user.getFirstName() + " " + user.getLastName() + ".");
    }

    private static void pretragaKorisnikaPoImenu() throws ScheduleException {
        System.out.println("Unesite username korisnika kojeg želite pretražiti:  ");
        String username = ulaz.nextLine();

        User user = userDao.getByUsername(username);
            System.out.println("Ime i prezime korisnika je: " + user.getFirstName() + " " + user.getLastName() + ".");
    }

    private static Schedule unosRasporedaDuplication(int id, int userId) {
        System.out.println("Unesite naziv rasporeda: ");
        String scheduleName = ulaz.nextLine();

        return new Schedule(id, userId, scheduleName);
    }

    private static void unosRasporeda() throws ScheduleException {
        int id = 0;
        System.out.println("Unesite ispravan id korisnika čiji raspored želite kreireati: ");
        int userId = ulaz.nextInt();
        if(ulaz.hasNextLine()) ulaz.nextLine();
        Schedule schedule = unosRasporedaDuplication(id, userId);
        scheduleDao.save(schedule);
    }

    private static void brisanjeRasporeda() throws ScheduleException {
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

    private static void izmjenaRasporeda() throws ScheduleException {
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

    private static void pretragaRasporeda() throws ScheduleException {
        System.out.println("Unesite id rasporeda kojeg želite pretražiti: ");
        int id = ulaz.nextInt();

        for(Schedule schedule : scheduleDao.get(id))
            System.out.println("Naziv rasporeda je: " + schedule.getScheduleName() + ".");
    }

    private static void pretragaSvihRasporeda() throws ScheduleException {
        System.out.println("Pretražite naziv svakog rasporeda: ");

        for(Schedule schedule : scheduleDao.getAll())
            System.out.println("Naziv svakog rasporeda: " + schedule.getScheduleName() + ".");
    }

    private static void pretragaRasporedaPoImenu() throws ScheduleException {
        System.out.println("Unesite naziv raspored koji želite pretražiti:  ");
        String scheduleName = ulaz.nextLine();

        Schedule schedule = scheduleDao.getByScheduleName(scheduleName);
            System.out.println("Naziv rasporeda je: " + schedule.getScheduleName() +  ".");
    }
}