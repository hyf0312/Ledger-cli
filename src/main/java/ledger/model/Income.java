package ledger.model;

import java.time.LocalDate;

public class Income extends Transaction{
    public Income(double amount, LocalDate date, String category, String note){
        super(amount,date,category,note);
    }

    public String getTypeName(){
        return "收入";
    }
}
