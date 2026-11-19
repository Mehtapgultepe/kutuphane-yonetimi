package library.exception;

/** Materyal başka bir üyede ödünçteyken tekrar ödünç alınmak istendiğinde fırlatılır. */
public class MaterialNotAvailableException extends LibraryException {

    public MaterialNotAvailableException(String materialId) {
        super("Materyal şu anda başka bir üyede ödünçte: " + materialId);
    }
}
