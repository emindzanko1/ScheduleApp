package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.exceptions.ScheduleException;

import java.util.List;

public interface UserDao extends Dao<User> {
    User getByUsername(String username) throws ScheduleException;
}