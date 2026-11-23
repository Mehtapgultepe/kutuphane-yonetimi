package library.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import library.exception.InvalidInputException;

class MaterialTest {

    private static final LocalDate BORROW_DATE = LocalDate.of(2026, 11, 1);

    @Test
    void book_loanPeriodIs21Days() {
        assertEquals(21, new Book("B1", "Suç ve Ceza").getLoanPeriodDays());
    }

    @Test
    void dvd_loanPeriodIs7Days() {
        assertEquals(7, new Dvd("D1", "Matrix").getLoanPeriodDays());
    }

    @Test
    void magazine_loanPeriodIs14Days() {
        assertEquals(14, new Magazine("M1", "Bilim Teknik").getLoanPeriodDays());
    }

    @Test
    void eachSubclass_reportsItsOwnType() {
        assertEquals(MaterialType.BOOK, new Book("B1", "Kitap").getType());
        assertEquals(MaterialType.DVD, new Dvd("D1", "Film").getType());
        assertEquals(MaterialType.MAGAZINE, new Magazine("M1", "Dergi").getType());
    }

    @Test
    void calculateDueDate_addsLoanPeriodToBorrowDate() {
        Material dvd = new Dvd("D1", "Matrix");
        assertEquals(LocalDate.of(2026, 11, 8), dvd.calculateDueDate(BORROW_DATE));
    }

    @Test
    void calculateDueDate_nullDate_throwsInvalidInputException() {
        Material book = new Book("B1", "Kitap");
        assertThrows(InvalidInputException.class, () -> book.calculateDueDate(null));
    }

    @Test
    void constructor_blankId_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> new Book("  ", "Kitap"));
    }

    @Test
    void constructor_nullTitle_throwsInvalidInputException() {
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> new Magazine("M1", null));
        assertEquals("Materyal adı boş olamaz", ex.getMessage());
    }
}
