package ledger.model;

import ledger.exception.InvalidAmountException;

import java.time.LocalDate;

public abstract class Transaction {
    private static int nextId = 1;
    private final int id;
    private final double amount;
    private final LocalDate date;
    private final String category;
    private final String note;

    public Transaction(double amount,LocalDate date,String category,String note){
        if(Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0){
            throw new InvalidAmountException("金额须大于0");
        }
        this.amount = amount;
        this.date = date;
        this.category = (category == null||category.isBlank())?"未分类":category;
        this.note = (note == null||note.isBlank())?"无":note;
        this.id = nextId++;
    }

    public abstract String getTypeName();

    public double getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getNote() {
        return note;
    }

    public static void setNextId(int nextId) {
        Transaction.nextId = nextId;
    }

    @Override
    public String toString() {
        return String.format("#%d,%s,%s,%s,%.2f,%s",getId(),getDate(),getTypeName(),getCategory(),getAmount(),getNote());
    }
}
