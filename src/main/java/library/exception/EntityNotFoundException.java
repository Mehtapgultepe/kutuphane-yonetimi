package library.exception;

/** Aranan bir kayıt (materyal, üye, ödünç kaydı) bulunamadığında fırlatılır. */
public abstract class EntityNotFoundException extends LibraryException {

    protected EntityNotFoundException(String message) {
        super(message);
    }
}
