package library.policy;

import java.math.BigDecimal;

import library.model.MemberType;

/**
 * Strategy kalıbı: ödünç limiti ve günlük ceza üye türüne göre değişir.
 * Yeni bir üye türü eklemek için yalnızca yeni bir policy sınıfı yazmak yeterlidir.
 */
public interface MembershipPolicy {

    int getBorrowLimit();

    BigDecimal getDailyFine();

    MemberType getMemberType();
}
