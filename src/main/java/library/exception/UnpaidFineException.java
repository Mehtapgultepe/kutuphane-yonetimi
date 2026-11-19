package library.exception;

import java.math.BigDecimal;

/** Ödenmemiş cezası olan üye yeni materyal ödünç almak istediğinde fırlatılır. */
public class UnpaidFineException extends LibraryException {

    public UnpaidFineException(String memberId, BigDecimal balance) {
        super("Üyenin ödenmemiş cezası var (" + balance + " TL): " + memberId);
    }
}
