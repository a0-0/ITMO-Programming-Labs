package exception;

public class PersistentException extends RuntimeException{
    private final String dbErrorMessage;

    public PersistentException(String dbErrorMessage) {
        this.dbErrorMessage = dbErrorMessage;
    }

    public String getDbErrorMessage() {
        return dbErrorMessage;
    }
}
