package library.exception;

/** Zorunlu bir parametre boş (null / boş metin) ya da geçersiz olduğunda fırlatılır. */
public class InvalidInputException extends LibraryException {

    public InvalidInputException(String message) {
        super(message);
    }
}
