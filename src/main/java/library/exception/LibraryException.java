package library.exception;

/**
 * Kütüphane sistemindeki tüm hataların temel sınıfı.
 * Unchecked (RuntimeException) seçildi: hatalar çoğunlukla programlama/kural
 * ihlalidir ve çağıran her katmanı throws ile kirletmek istemiyoruz.
 */
public abstract class LibraryException extends RuntimeException {

    protected LibraryException(String message) {
        super(message);
    }
}
