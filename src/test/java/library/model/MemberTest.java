package library.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import library.exception.InvalidInputException;
import library.exception.InvalidPaymentException;
import library.policy.StudentPolicy;

class MemberTest {

    private Member student;

    @BeforeEach
    void setUp() {
        student = new Member("U1", "Ayşe", new StudentPolicy());
    }

    @Test
    void newMember_hasZeroFineBalance() {
        assertEquals(new BigDecimal("0.00"), student.getFineBalance());
        assertFalse(student.hasUnpaidFine());
    }

    @Test
    void getType_comesFromPolicy() {
        assertEquals(MemberType.STUDENT, student.getType());
    }

    @Test
    void addFine_increasesBalance() {
        student.addFine(new BigDecimal("6.00"));
        assertEquals(new BigDecimal("6.00"), student.getFineBalance());
        assertTrue(student.hasUnpaidFine());
    }

    @Test
    void addFine_negativeAmount_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> student.addFine(new BigDecimal("-1")));
    }

    @Test
    void addFine_nullAmount_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> student.addFine(null));
    }

    @Test
    void payFine_partialPayment_reducesBalance() {
        student.addFine(new BigDecimal("10.00"));
        student.payFine(new BigDecimal("4.00"));
        assertEquals(new BigDecimal("6.00"), student.getFineBalance());
    }

    @Test
    void payFine_fullPayment_clearsUnpaidFine() {
        student.addFine(new BigDecimal("10.00"));
        student.payFine(new BigDecimal("10.00"));
        assertFalse(student.hasUnpaidFine());
    }

    @Test
    void payFine_zeroAmount_throwsInvalidPaymentException() {
        student.addFine(new BigDecimal("10.00"));
        assertThrows(InvalidPaymentException.class, () -> student.payFine(BigDecimal.ZERO));
    }

    @Test
    void payFine_negativeAmount_throwsInvalidPaymentException() {
        student.addFine(new BigDecimal("10.00"));
        assertThrows(InvalidPaymentException.class, () -> student.payFine(new BigDecimal("-5")));
    }

    @Test
    void payFine_nullAmount_throwsInvalidPaymentException() {
        assertThrows(InvalidPaymentException.class, () -> student.payFine(null));
    }

    @Test
    void payFine_moreThanBalance_throwsInvalidPaymentExceptionAndKeepsBalance() {
        student.addFine(new BigDecimal("3.00"));
        assertThrows(InvalidPaymentException.class, () -> student.payFine(new BigDecimal("5.00")));
        assertEquals(new BigDecimal("3.00"), student.getFineBalance());
    }

    @Test
    void canBorrowMore_belowLimit_returnsTrue() {
        assertTrue(student.canBorrowMore(StudentPolicy.BORROW_LIMIT - 1));
    }

    @Test
    void canBorrowMore_atLimit_returnsFalse() {
        assertFalse(student.canBorrowMore(StudentPolicy.BORROW_LIMIT));
    }

    @Test
    void constructor_nullPolicy_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> new Member("U1", "Ayşe", null));
    }

    @Test
    void constructor_blankName_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> new Member("U1", "", new StudentPolicy()));
    }
}
