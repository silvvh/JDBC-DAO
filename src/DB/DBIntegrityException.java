package DB;

public class DBIntegrityException extends RuntimeException {
    public DBIntegrityException (String message) {
        super(message);
    }
}
