package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.exceptions.ScheduleException;

/**
 * Dao interface for User domain bean
 * @author Emin Džanko
 */
public interface UserDao extends Dao<User> {
    User getByUsername(String username) throws ScheduleException;
}