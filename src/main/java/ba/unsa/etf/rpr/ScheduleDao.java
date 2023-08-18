package ba.unsa.etf.rpr;

import java.util.List;

public interface ScheduleDao {
    List<Schedule> getByScheduleName(String scheduleName);
}
