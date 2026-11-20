package library.exception;

/** Ceza ödemesi geçersiz olduğunda (sıfır, negatif veya borçtan fazla) fırlatılır. */
public class InvalidPaymentException extends LibraryException {

    public InvalidPaymentException(String message) {
        super(message);
    }
}
