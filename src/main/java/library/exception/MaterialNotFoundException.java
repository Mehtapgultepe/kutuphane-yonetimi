package library.exception;

public class MaterialNotFoundException extends EntityNotFoundException {

    public MaterialNotFoundException(String materialId) {
        super("Materyal bulunamadı: " + materialId);
    }
}
