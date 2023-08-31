package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.UserDao;
import ba.unsa.etf.rpr.dao.UserSQLImplementation;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.*;

public class UserTest {

    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        try {
            userDao = UserSQLImplementation.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserByIdTest() {
        int id = 10;
        List<User> users = null;
        try {
            users = userDao.get(id);
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());

        User foundUser = users.get(0);
        assertEquals(id, foundUser.getId());
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = null;
        try {
            users = userDao.getAll();
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        assertEquals(17, users.size());
    }

    @Test
    public void saveUserTest() {
        List<User> users = null;
        try {
            users = userDao.getAll();
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        User user = new User(23, "edzanko11","54321","Emin","Dzanko");
        boolean foundUser = false;
        for(User user1 : users) {
            if(user1.getUsername().equals(user.getUsername()))
                foundUser = true;
        }
        if(!foundUser) {
            try {
                userDao.save(user);
            }
            catch (ScheduleException e) {
                e.printStackTrace();
            }
        }
        assertEquals(user.getUsername(), "edzanko11");
    }


    @Test
    public void getUserByUsernameTest() {
        int id = 12;
        List<User> users = null;
        try {
            users = userDao.get(id);
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        User foundUser = users.get(0);
        assertEquals(foundUser.getUsername(), "hilmo");

    }

    @Test
    public void getByUsernameTest() {
        int id = 12;
        List<User> users = null;
        try {
            users = userDao.get(id);
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        User foundUser = users.get(0);
        assertEquals(foundUser.getUsername(), "hilmo");

    }


}
