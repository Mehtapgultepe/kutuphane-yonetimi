package library.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import library.exception.InvalidDateException;
import library.util.Require;

/** Bir üyenin bir materyali ödünç aldığını gösteren kayıt. Tarihler hep parametreyle gelir. */
public class Loan implements Identifiable {

    private final String id;
    private final Member member;
    private final Material material;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;

    public Loan(String id, Member member, Material material, LocalDate borrowDate) {
        this.id = Require.text(id, "Ödünç id");
        this.member = Require.nonNull(member, "Üye");
        this.material = Require.nonNull(material, "Materyal");
        this.borrowDate = Require.nonNull(borrowDate, "Ödünç tarihi");
        this.dueDate = material.calculateDueDate(borrowDate);
    }

    @Override
    public String getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Material getMaterial() {
        return material;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    /** Verilen tarihe göre gecikme gün sayısı. */
    public long getOverdueDays(LocalDate asOf) {
        checkNotBeforeBorrowDate(asOf, "Hesaplama tarihi");
        long days = ChronoUnit.DAYS.between(dueDate, asOf);
        return Math.max(0, days);
    }

    public boolean isOverdue(LocalDate asOf) {
        return getOverdueDays(asOf) > 0;
    }

    public BigDecimal calculateFine(LocalDate asOf) {
        BigDecimal dailyFine = member.getPolicy().getDailyFine();
        return dailyFine.multiply(BigDecimal.valueOf(getOverdueDays(asOf)))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private void checkNotBeforeBorrowDate(LocalDate date, String fieldName) {
        Require.nonNull(date, fieldName);
        if (date.isBefore(borrowDate)) {
            throw new InvalidDateException(
                    fieldName + " (" + date + ") ödünç tarihinden (" + borrowDate + ") önce olamaz");
        }
    }
}
