package ledger.exception;

public class NotFoundIdException extends RuntimeException {
    private int id;

    public int getId() {
        return id;
    }

    public NotFoundIdException(String message,int id) {
        super(message);
        this.id = id;
    }
}
