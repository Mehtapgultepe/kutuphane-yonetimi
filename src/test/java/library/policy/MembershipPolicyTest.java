package library.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import library.model.MemberType;

class MembershipPolicyTest {

    @Test
    void studentPolicy_limitIsThreeAndDailyFineIsTwoLira() {
        MembershipPolicy policy = new StudentPolicy();
        assertEquals(3, policy.getBorrowLimit());
        assertEquals(new BigDecimal("2.00"), policy.getDailyFine());
        assertEquals(MemberType.STUDENT, policy.getMemberType());
    }

    @Test
    void facultyPolicy_limitIsTenAndDailyFineIsOneLira() {
        MembershipPolicy policy = new FacultyPolicy();
        assertEquals(10, policy.getBorrowLimit());
        assertEquals(new BigDecimal("1.00"), policy.getDailyFine());
        assertEquals(MemberType.FACULTY, policy.getMemberType());
    }
}
