package library.exception;

/** Ödünçte olmayan bir materyal iade edilmek istendiğinde fırlatılır. */
public class MaterialNotBorrowedException extends LibraryException {

    public MaterialNotBorrowedException(String message) {
        super(message);
    }
}
