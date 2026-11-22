package library.model;

public class Magazine extends Material {

    public static final int LOAN_PERIOD_DAYS = 14;

    public Magazine(String id, String title) {
        super(id, title);
    }

    @Override
    public int getLoanPeriodDays() {
        return LOAN_PERIOD_DAYS;
    }

    @Override
    public MaterialType getType() {
        return MaterialType.MAGAZINE;
    }
}
