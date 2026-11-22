package library.model;

import java.time.LocalDate;

import library.util.Require;

/**
 * Kütüphanedeki tüm materyallerin ortak üst sınıfı.
 * Ödünç süresi materyal türüne bağlı olduğu için her alt sınıf kendi süresini belirler.
 */
public abstract class Material implements Identifiable {

    private final String id;
    private final String title;

    protected Material(String id, String title) {
        this.id = Require.text(id, "Materyal id");
        this.title = Require.text(title, "Materyal adı");
    }

    @Override
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public abstract int getLoanPeriodDays();

    public abstract MaterialType getType();

    public LocalDate calculateDueDate(LocalDate borrowDate) {
        Require.nonNull(borrowDate, "Ödünç tarihi");
        return borrowDate.plusDays(getLoanPeriodDays());
    }
}
