package library.exception;

/** Tarih mantıksız olduğunda (ör. iade tarihi ödünç tarihinden önce) fırlatılır. */
public class InvalidDateException extends LibraryException {

    public InvalidDateException(String message) {
        super(message);
    }
}
