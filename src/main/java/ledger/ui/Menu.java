package ledger.ui;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;

import ledger.exception.*;
import ledger.model.*;
import ledger.service.Ledger;

public class Menu {
    public static final Scanner in = new Scanner(System.in);
    public static void show(){
        Ledger ledger = new Ledger();
        while(true){
            System.out.println("  ======菜单======");
            System.out.println("1. 添加收入");
            System.out.println("2. 添加支出");
            System.out.println("3. 查看所有记录");
            System.out.println("4. 删除记录");
            System.out.println("5. 查看某类的收支统计");
            System.out.println("6. 查看某天的收支统计");
            System.out.println("7. 查看收入总览");
            System.out.println("8. 查看支出总览");
            System.out.println("9. 分类统计");
            System.out.println("10. 按日期统计");
            System.out.println("0. 退出");
            System.out.print("请选择:");
            try{
                int op;
                try{
                    op = in.nextInt();
                }catch(InputMismatchException e){
                    in.nextLine();
                    throw new InputMismatchException("您输入了非数字");
                }
                if(op == 0){
                    break;
                }
                in.nextLine();
                switch(op) {
                    case 1:
                        System.out.print("请输入金额:");
                        double in_amount;
                        try{
                            in_amount = in.nextDouble();
                        }catch(InputMismatchException e){
                            in.nextLine();
                            throw new InputMismatchException("您输入了非数字");
                        }
                        in.nextLine();
                        System.out.print("请输入日期(格式为2026-11-05,直接回车为今天):");
                        String in_input = in.nextLine();
                        LocalDate in_date;
                        if (in_input.isEmpty()) {
                            in_date = LocalDate.now();
                        } else {
                            try {
                                in_date = LocalDate.parse(in_input);
                            } catch (DateTimeParseException e) {
                                throw new DateTimeParseException("输入的日期格式有误", in_input, 0);
                            }
                        }
                        System.out.print("请输入收入种类:");
                        String in_category = in.nextLine();
                        System.out.print("请输入日志:");
                        String in_note = in.nextLine();
                        Income in_record = new Income(in_amount, in_date, in_category, in_note);
                        ledger.add(in_record);
                        System.out.println(" ---------------------");
                        System.out.println();
                        break;
                    case 2:
                        System.out.print("请输入金额:");
                        double ex_amount;
                        try{
                            ex_amount = in.nextDouble();
                        }catch(InputMismatchException e){
                            in.nextLine();
                            throw new InputMismatchException("您输入了非数字");
                        }
                        in.nextLine();
                        System.out.print("请输入日期(格式为2026-11-05,直接回车为今天):");
                        String ex_input = in.nextLine();
                        LocalDate ex_date;
                        if (ex_input.isEmpty()) {
                            ex_date = LocalDate.now();
                        } else {
                            try {
                                ex_date = LocalDate.parse(ex_input);
                            } catch (DateTimeParseException e) {
                                throw new DateTimeParseException("输入的日期格式有误", ex_input, 0);
                            }
                        }
                        System.out.print("请输入支出种类:");
                        String ex_category = in.nextLine();
                        System.out.print("请输入日志:");
                        String ex_note = in.nextLine();
                        Expense ex_record = new Expense(ex_amount, ex_date, ex_category, ex_note);
                        ledger.add(ex_record);
                        System.out.println(" ---------------------");
                        System.out.println();
                        break;
                    case 3:
                        ledger.getAll();
                        System.out.println(" ---------------------");
                        System.out.println();
                        break;
                    case 4:
                        System.out.print("请输入你要删除的记录id:");
                        int id;
                        try{
                            id = in.nextInt();
                        }catch(InputMismatchException e){
                            in.nextLine();
                            throw new InputMismatchException("您输入了非数字");
                        }
                        in.nextLine();
                        boolean result = ledger.remove(id);
                        if (result) {
                            System.out.println("删除成功");
                        } else {
                            throw new NotFoundIdException("没有找到id", id);
                        }
                        System.out.println(" ---------------------");
                        System.out.println();
                        break;
                    case 5:
                        System.out.print("请输入你要查询的收支种类:");
                        String category = in.nextLine();
                        List<Transaction> record_category = ledger.recordByCategory(category);
                        if (record_category.isEmpty()) {
                            throw new NotFoundCategoryException("没有收支记录", category);
                        }
                        for (Transaction temp : record_category) {
                            System.out.println(temp.getCategory() + ":" + temp.toString());
                        }
                        System.out.println(" ---------------------");
                        System.out.println();
                        break;
                    case 6:
                        System.out.print("请输入要查询的日期(格式为2026-11-05,直接回车为今天):");
                        String input = in.nextLine();
                        LocalDate date;
                        if (input.isEmpty()) {
                            date = LocalDate.now();
                        } else {
                            try {
                                date = LocalDate.parse(input);
                            } catch (DateTimeParseException e) {
                                throw new DateTimeParseException("输入的日期格式有误", input, 0);
                            }
                        }
                        List<Transaction> record_date = ledger.recordByDate(date);
                        if (record_date.isEmpty()) {
                            throw new NotFoundDateException(("没有收支记录"), date);
                        }
                        for (Transaction temp : record_date) {
                            System.out.println(temp.getDate() + ":" + temp.toString());
                        }
                        System.out.println(" ---------------------");
                        System.out.println();
                        break;
                    case 7:
                        double sumIncome = ledger.getTotalIncome();
                        System.out.println("总收入为:" + String.format("%.2f", sumIncome));
                        System.out.println(" ---------------------");
                        System.out.println();
                        break;
                    case 8:
                        double sumExpense = ledger.getTotalExpense();
                        System.out.println("总支出为:" + String.format("%.2f", sumExpense));
                        System.out.println(" ---------------------");
                        System.out.println();
                        break;
                    case 9 :
                        Map<String,Double> result_category = ledger.summaryByCategory();
                        for(Map.Entry<String,Double> temp : result_category.entrySet()){
                            System.out.println(temp.getKey() + ":" + String.format("%.2f", temp.getValue()));
                        }
                        System.out.println(" ---------------------");
                        System.out.println();
                        break;
                    case 10 :
                        Map<String,Double> result_date = ledger.summaryByDate();
                        for(Map.Entry<String,Double> temp : result_date.entrySet()){
                            System.out.println(temp.getKey() + ":" + String.format("%.2f", temp.getValue()));
                        }
                        System.out.println(" ---------------------");
                        System.out.println();
                        break;
                    default:
                        throw new InvalidInputException("输入无效符号");
                }
            }catch(InvalidAmountException | InvalidInputException | InputMismatchException e){
                System.out.println(e.getMessage());
            }catch(DateTimeParseException e){
                System.out.println(e.getMessage() + ":" + e.getParsedString());
            }catch(NotFoundCategoryException e){
                System.out.println(e.getCategory() + " " + e.getMessage());
            }catch(NotFoundDateException e) {
                System.out.println(e.getDate() + " " + e.getMessage());
            }catch(NotFoundIdException e){
                System.out.println(e.getMessage() + ":" + e.getId());
            }
        }
    }
}