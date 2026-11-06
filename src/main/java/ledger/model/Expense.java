package ledger.model;

import java.time.LocalDate;

public class Expense extends Transaction{
    public Expense(double amount, LocalDate date, String category, String note){
        super(amount,date,category,note);
    }

    public String getTypeName(){
        return "支出";
    }
}
