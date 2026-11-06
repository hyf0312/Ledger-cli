package ledger.service;

import java.time.LocalDate;
import java.util.*;
import ledger.model.*;

public class Ledger {
    private final List<Transaction> records = new ArrayList<>();

    public void add(Transaction record){
        records.add(record);
    }

    public boolean remove(int id) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getId() == id) {
                records.remove(i);
                return true;
            }
        }
        return false;   // 没找到，由 Menu 提示"没有这个编号"
    }

    public void getAll(){
        double sumIncome,sumExpense;
        sumIncome = 0;
        sumExpense = 0;
        for (Transaction record : records) {
            if (record instanceof Income) {
                sumIncome += record.getAmount();
            } else if (record instanceof Expense) {
                sumExpense += record.getAmount();
            }
            System.out.println(record.toString());
        }
        System.out.println("总收入为:" + String.format("%.2f", sumIncome));
        System.out.println("总支出为:" + String.format("%.2f", sumExpense));
    }

    public List<Transaction> recordByCategory(String category){
        LinkedList<Transaction> record_category = new LinkedList<>();
        double sumIn,sumEx;
        sumEx = 0;
        sumIn = 0;
        for(Transaction record : records){
            if(record.getCategory().equals(category)){
                if(record instanceof Income){
                    sumIn += record.getAmount();
                }else if(record instanceof Expense){
                    sumEx += record.getAmount();
                }
                record_category.add(record);
            }
        }
        if(!record_category.isEmpty()){
            System.out.println(category + ":");
            System.out.println("收入:" + String.format("%.2f", sumIn) + "  支出:" + String.format("%.2f", sumEx));
        }
        return record_category;
    }

    public List<Transaction> recordByDate(LocalDate date){
        List<Transaction> record_date = new LinkedList<>();
        double sumIn,sumEx;
        sumEx = 0;
        sumIn = 0;
        for(Transaction record : records){
            if(record.getDate().equals(date)){
                if(record instanceof Income){
                    sumIn += record.getAmount();
                }else if(record instanceof Expense){
                    sumEx += record.getAmount();
                }
                record_date.add(record);
            }
        }
        if(!record_date.isEmpty()){
            System.out.println(date + ":");
            System.out.println("收入:" + String.format("%.2f", sumIn) + "  支出:" + String.format("%.2f", sumEx));
        }
        return record_date;
    }

    public Map<String,Double> summaryByCategory(){
        Map<String, Double> result_category = new LinkedHashMap<>();
        for (Transaction record : records) {
            String key = record.getCategory() + " " + record.getTypeName();
            double old = result_category.getOrDefault(key,0.0);
            result_category.put(key,old + record.getAmount());
        }
        return result_category;
    }

    public Map<String,Double> summaryByDate(){
        Map<String,Double> result_date = new LinkedHashMap<>();
        for(Transaction record : records){
            String key = record.getDate().toString() + ":" + record.getTypeName();
            double old = result_date.getOrDefault(key,0.0);
            result_date.put(key,old + record.getAmount());
        }
        return result_date;
    }

    public double getTotalIncome(){
        double sumIncome = 0;
        for(Transaction record : records){
            if(record instanceof Income){
                System.out.print(record.getTypeName() + ":");
                System.out.println(record.toString());
                sumIncome += record.getAmount();
            }
        }
        return sumIncome;
    }

    public double getTotalExpense(){
        double sumExpense = 0;
        for(Transaction record : records){
            if(record instanceof Expense){
                System.out.print(record.getTypeName() + ":");
                System.out.println(record.toString());
                sumExpense += record.getAmount();
            }
        }
        return sumExpense;
    }
}
