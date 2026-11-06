package ledger.exception;

import java.time.LocalDate;

public class NotFoundDateException extends RuntimeException {
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public NotFoundDateException(String message,LocalDate date) {
        super(message);
        this.date = date;
    }
}
