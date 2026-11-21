package library.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ExceptionHierarchyTest {

    @Test
    void notFoundExceptions_extendEntityNotFoundException() {
        assertInstanceOf(EntityNotFoundException.class, new MaterialNotFoundException("B1"));
        assertInstanceOf(EntityNotFoundException.class, new MemberNotFoundException("U1"));
        assertInstanceOf(EntityNotFoundException.class, new LoanNotFoundException("L1"));
    }

    @Test
    void allCustomExceptions_extendLibraryException() {
        assertInstanceOf(LibraryException.class, new MaterialNotFoundException("B1"));
        assertInstanceOf(LibraryException.class, new DuplicateEntityException("x"));
        assertInstanceOf(LibraryException.class, new MaterialNotAvailableException("B1"));
        assertInstanceOf(LibraryException.class, new BorrowLimitExceededException("U1", 3));
        assertInstanceOf(LibraryException.class, new UnpaidFineException("U1", BigDecimal.ONE));
        assertInstanceOf(LibraryException.class, new MaterialNotBorrowedException("x"));
        assertInstanceOf(LibraryException.class, new InvalidPaymentException("x"));
        assertInstanceOf(LibraryException.class, new InvalidDateException("x"));
        assertInstanceOf(LibraryException.class, new InvalidInputException("x"));
    }

    @Test
    void libraryException_isUnchecked() {
        assertInstanceOf(RuntimeException.class, new InvalidInputException("x"));
    }

    @Test
    void borrowLimitExceededException_messageContainsLimitAndMember() {
        assertEquals("Üye ödünç alma limitine ulaştı (3): U1",
                new BorrowLimitExceededException("U1", 3).getMessage());
    }
}
