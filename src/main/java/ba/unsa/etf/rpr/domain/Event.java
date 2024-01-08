package ba.unsa.etf.rpr.domain;

/**
 * This class is bean for schedule item
 * @author Emin Džanko
 */
public class Event {
    private int id, scheduleId;
    private String dayOfWeek, startTime, eventName, location;

    public Event(int id, int scheduleId, String eventName, String dayOfWeek, String startTime, String location) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.eventName = eventName;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.location = location;
    }

    public Event() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
