package library.exception;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(String memberId) {
        super("Üye bulunamadı: " + memberId);
    }
}
