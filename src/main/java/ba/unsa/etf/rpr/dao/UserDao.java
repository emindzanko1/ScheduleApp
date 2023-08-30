package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ScheduleException;

/**
 * Dao interface for User domain bean
 * @author Emin Džanko
 */
public interface UserDao extends Dao<User> {
    User getByUsername(String username) throws ScheduleException;
}