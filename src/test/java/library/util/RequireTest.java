package library.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import library.exception.InvalidInputException;

class RequireTest {

    @Test
    void nonNull_withValue_returnsValue() {
        assertEquals("x", Require.nonNull("x", "Alan"));
    }

    @Test
    void nonNull_withNull_throwsWithFieldNameInMessage() {
        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> Require.nonNull(null, "Tarih"));
        assertEquals("Tarih boş olamaz", ex.getMessage());
    }

    @Test
    void text_withText_returnsText() {
        assertEquals("abc", Require.text("abc", "Alan"));
    }

    @Test
    void text_withBlank_throwsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> Require.text("   ", "Alan"));
    }
}
