package ledger.exception;

public class NotFoundCategoryException extends RuntimeException {
    private String category;

    public String getCategory() {
        return category;
    }

    public NotFoundCategoryException(String message,String category) {
        super(message);
        this.category = category;
    }
}
