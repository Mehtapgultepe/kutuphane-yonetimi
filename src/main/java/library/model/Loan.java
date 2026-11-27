package library.model;

import java.time.LocalDate;

import library.util.Require;

/** Bir üyenin bir materyali ödünç aldığını gösteren kayıt. */
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
}
