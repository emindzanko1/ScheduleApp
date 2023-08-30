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
        int userIdToFind = 10;
        List<User> users = null;
        try {
            users = userDao.get(userIdToFind);
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());

        User foundUser = users.get(0);
        assertEquals(userIdToFind, foundUser.getId());
    }
}
