package library.model;

public class Dvd extends Material {

    public static final int LOAN_PERIOD_DAYS = 7;

    public Dvd(String id, String title) {
        super(id, title);
    }

    @Override
    public int getLoanPeriodDays() {
        return LOAN_PERIOD_DAYS;
    }

    @Override
    public MaterialType getType() {
        return MaterialType.DVD;
    }
}
