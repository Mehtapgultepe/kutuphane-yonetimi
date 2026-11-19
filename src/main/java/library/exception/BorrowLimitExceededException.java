package library.exception;

/** Üye, üye türüne göre belirlenen ödünç alma limitine ulaştığında fırlatılır. */
public class BorrowLimitExceededException extends LibraryException {

    public BorrowLimitExceededException(String memberId, int limit) {
        super("Üye ödünç alma limitine ulaştı (" + limit + "): " + memberId);
    }
}
