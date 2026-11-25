package library.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import library.exception.InvalidInputException;
import library.exception.InvalidPaymentException;
import library.policy.MembershipPolicy;
import library.util.Require;

/** Kütüphane üyesi. Limit ve ceza kuralları dışarıdan verilen policy'den gelir. */
public class Member implements Identifiable {

    private final String id;
    private final String name;
    private final MembershipPolicy policy;
    private BigDecimal fineBalance = money(BigDecimal.ZERO);

    public Member(String id, String name, MembershipPolicy policy) {
        this.id = Require.text(id, "Üye id");
        this.name = Require.text(name, "Üye adı");
        this.policy = Require.nonNull(policy, "Üyelik politikası");
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MembershipPolicy getPolicy() {
        return policy;
    }

    public MemberType getType() {
        return policy.getMemberType();
    }

    public BigDecimal getFineBalance() {
        return fineBalance;
    }

    public boolean hasUnpaidFine() {
        return fineBalance.signum() > 0;
    }

    public boolean canBorrowMore(int activeLoanCount) {
        return activeLoanCount < policy.getBorrowLimit();
    }

    public void addFine(BigDecimal amount) {
        Require.nonNull(amount, "Ceza tutarı");
        if (amount.signum() < 0) {
            throw new InvalidInputException("Ceza tutarı negatif olamaz: " + amount);
        }
        fineBalance = money(fineBalance.add(amount));
    }

    public void payFine(BigDecimal amount) {
        if (amount == null) {
            throw new InvalidPaymentException("Ödeme tutarı boş olamaz");
        }
        if (amount.signum() <= 0) {
            throw new InvalidPaymentException("Ödeme tutarı sıfırdan büyük olmalıdır: " + amount);
        }
        if (amount.compareTo(fineBalance) > 0) {
            throw new InvalidPaymentException(
                    "Ödeme tutarı (" + amount + " TL) ceza borcundan (" + fineBalance + " TL) fazla olamaz");
        }
        fineBalance = money(fineBalance.subtract(amount));
    }

    private static BigDecimal money(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
