package library.policy;

import java.math.BigDecimal;

import library.model.MemberType;

public class FacultyPolicy implements MembershipPolicy {

    public static final int BORROW_LIMIT = 10;
    public static final BigDecimal DAILY_FINE = new BigDecimal("1.00");

    @Override
    public int getBorrowLimit() {
        return BORROW_LIMIT;
    }

    @Override
    public BigDecimal getDailyFine() {
        return DAILY_FINE;
    }

    @Override
    public MemberType getMemberType() {
        return MemberType.FACULTY;
    }
}
