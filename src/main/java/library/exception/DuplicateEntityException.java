package library.exception;

/** Aynı id ile ikinci bir kayıt eklenmeye çalışıldığında fırlatılır. */
public class DuplicateEntityException extends LibraryException {

    public DuplicateEntityException(String message) {
        super(message);
    }
}
