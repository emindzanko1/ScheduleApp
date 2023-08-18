package ba.unsa.etf.rpr;

import java.util.List;

public interface ScheduleDao extends Dao<Schedule>{
    List<Schedule> getByScheduleName(String scheduleName);
}
