package ba.unsa.etf.rpr.exceptions;

public class ScheduleException extends Exception {

    public ScheduleException(String message, Exception cause) {
        super(message, cause);
    }

    public ScheduleException(String message) {
        super(message);
    }
}
