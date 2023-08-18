package ba.unsa.etf.rpr;

public class Schedule {
    private int id, userId;
    private String scheduleName;

    public Schedule(int id, int userId, String scheduleName) {
        this.id = id;
        this.userId = userId;
        this.scheduleName = scheduleName;
    }

    public Schedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }
}
