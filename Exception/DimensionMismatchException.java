package Exception;

public class DimensionMismatchException extends RuntimeException {

    public DimensionMismatchException() {
        super("Dimension mismatch.");
    }

    public DimensionMismatchException(String message) {
        super(message);
    }
}
