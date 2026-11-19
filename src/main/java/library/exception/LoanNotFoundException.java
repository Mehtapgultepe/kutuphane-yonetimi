package library.exception;

public class LoanNotFoundException extends EntityNotFoundException {

    public LoanNotFoundException(String loanId) {
        super("Ödünç kaydı bulunamadı: " + loanId);
    }
}
