package Exception;

public class SingularMatrixException extends RuntimeException {

    public SingularMatrixException() {
        super("Matrix is singular or numerically non-invertible.");
    }

    public SingularMatrixException(String message) {
        super(message);
    }
}

