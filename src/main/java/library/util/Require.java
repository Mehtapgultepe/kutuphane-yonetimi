package library.util;

import library.exception.InvalidInputException;

/** Parametre doğrulamaları için küçük yardımcı sınıf. */
public final class Require {

    private Require() {
    }

    public static <T> T nonNull(T value, String fieldName) {
        if (value == null) {
            throw new InvalidInputException(fieldName + " boş olamaz");
        }
        return value;
    }

    public static String text(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new InvalidInputException(fieldName + " boş olamaz");
        }
        return value;
    }
}
