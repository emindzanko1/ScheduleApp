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
}
