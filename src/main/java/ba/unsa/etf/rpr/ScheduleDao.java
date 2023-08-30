package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.exceptions.ScheduleException;

/**
 * Dao interface for Schedule domain bean
 * @author Emin Džanko
 */
public interface ScheduleDao extends Dao<Schedule>{
    Schedule getByScheduleName(String scheduleName) throws ScheduleException;
}
