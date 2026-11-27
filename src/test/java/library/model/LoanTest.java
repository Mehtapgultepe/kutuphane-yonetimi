package library.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import library.exception.InvalidDateException;
import library.exception.InvalidInputException;
import library.exception.MaterialNotBorrowedException;
import library.policy.FacultyPolicy;
import library.policy.StudentPolicy;

class LoanTest {

    private static final LocalDate BORROW_DATE = LocalDate.of(2026, 11, 1);

    private Member student;
    private Material dvd;
    private Loan loan;

    @BeforeEach
    void setUp() {
        student = new Member("U1", "Ayşe", new StudentPolicy());
        dvd = new Dvd("D1", "Matrix");
        loan = new Loan("L1", student, dvd, BORROW_DATE); // son gün: 8 Kasım
    }

    @Test
    void constructor_setsDueDateFromMaterialType() {
        assertEquals(LocalDate.of(2026, 11, 8), loan.getDueDate());
    }

    @Test
    void newLoan_isActiveAndHasNoReturnDate() {
        assertTrue(loan.isActive());
        assertTrue(loan.getReturnDate().isEmpty());
    }

    @Test
    void constructor_nullMember_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> new Loan("L1", null, dvd, BORROW_DATE));
    }

    @Test
    void constructor_nullBorrowDate_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> new Loan("L1", student, dvd, null));
    }

    @Test
    void getOverdueDays_onDueDate_isZero() {
        assertEquals(0, loan.getOverdueDays(LocalDate.of(2026, 11, 8)));
    }

    @Test
    void getOverdueDays_threeDaysAfterDueDate_isThree() {
        assertEquals(3, loan.getOverdueDays(LocalDate.of(2026, 11, 11)));
    }

    @Test
    void getOverdueDays_dateBeforeBorrowDate_throwsInvalidDateException() {
        assertThrows(InvalidDateException.class, () -> loan.getOverdueDays(LocalDate.of(2026, 10, 31)));
    }

    @Test
    void isOverdue_beforeDueDate_returnsFalse() {
        assertFalse(loan.isOverdue(LocalDate.of(2026, 11, 5)));
    }

    @Test
    void isOverdue_afterDueDate_returnsTrue() {
        assertTrue(loan.isOverdue(LocalDate.of(2026, 11, 9)));
    }

    @Test
    void calculateFine_notOverdue_isZero() {
        assertEquals(new BigDecimal("0.00"), loan.calculateFine(LocalDate.of(2026, 11, 8)));
    }

    @Test
    void calculateFine_studentThreeDaysLate_isSixLira() {
        assertEquals(new BigDecimal("6.00"), loan.calculateFine(LocalDate.of(2026, 11, 11)));
    }

    @Test
    void calculateFine_facultyThreeDaysLate_isThreeLira() {
        Member faculty = new Member("U2", "Hoca", new FacultyPolicy());
        Loan facultyLoan = new Loan("L2", faculty, dvd, BORROW_DATE);
        assertEquals(new BigDecimal("3.00"), facultyLoan.calculateFine(LocalDate.of(2026, 11, 11)));
    }

    @Test
    void calculateFine_afterReturn_usesReturnDateNotLaterDate() {
        loan.close(LocalDate.of(2026, 11, 10)); // 2 gün gecikme
        assertEquals(new BigDecimal("4.00"), loan.calculateFine(LocalDate.of(2026, 12, 1)));
    }

    @Test
    void calculateFine_nullDate_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> loan.calculateFine(null));
    }

    @Test
    void close_setsReturnDateAndDeactivates() {
        LocalDate returnDate = LocalDate.of(2026, 11, 5);
        loan.close(returnDate);
        assertFalse(loan.isActive());
        assertEquals(returnDate, loan.getReturnDate().orElseThrow());
    }

    @Test
    void close_dateBeforeBorrowDate_throwsInvalidDateException() {
        assertThrows(InvalidDateException.class, () -> loan.close(LocalDate.of(2026, 10, 1)));
        assertTrue(loan.isActive());
    }

    @Test
    void close_alreadyClosed_throwsMaterialNotBorrowedException() {
        loan.close(LocalDate.of(2026, 11, 5));
        assertThrows(MaterialNotBorrowedException.class, () -> loan.close(LocalDate.of(2026, 11, 6)));
    }
}
