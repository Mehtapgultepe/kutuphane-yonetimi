package library.model;

public class Book extends Material {

    public static final int LOAN_PERIOD_DAYS = 21;

    public Book(String id, String title) {
        super(id, title);
    }

    @Override
    public int getLoanPeriodDays() {
        return LOAN_PERIOD_DAYS;
    }

    @Override
    public MaterialType getType() {
        return MaterialType.BOOK;
    }
}
