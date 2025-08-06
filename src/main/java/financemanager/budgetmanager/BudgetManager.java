package financemanager.budgetmanager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import financemanager.TransactionManager;


public class BudgetManager {
    private float monthlySpendings;
    private float monthlyEarnings;
    private float savingsGoal;
    private LocalDate startDate;
    private LocalDate finalDate;
    private TransactionManager transactions;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public BudgetManager(
        float monthlySpendings, 
        float monthlyEarnings, 
        String startDateString, 
        String finalDateString,
        TransactionManager transactions) {
        this.monthlySpendings = monthlySpendings;
        this.monthlyEarnings = monthlyEarnings;
        this.startDate = LocalDate.parse(startDateString, formatter);
        this.finalDate = LocalDate.parse(finalDateString, formatter);
        this.transactions = transactions;
    }

    public float calculateMonths() {
        return ChronoUnit.MONTHS.between(startDate, finalDate);
    }

    public float calculateTotalSavings() {
        return (monthlyEarnings - monthlySpendings)* calculateMonths();
    }

    public float calculateSurplusSavings() {
        return calculateTotalSavings() - transactions.netBalance();
    }

    public float calculateMonthlySavings() {
        return calculateSurplusSavings()/calculateMonths();
    }

    public float calculateMinMonthSavings() {
        return 0;
    }

    public void changeMonthlySpendings(float newMonthlySpendings){
        monthlySpendings = newMonthlySpendings;
    }

    public void changeMonthlyEarnings(float newMonthlyEarnings){
        monthlyEarnings = newMonthlyEarnings;
    }

    public void changeFinalDate(String newFinalDateString) {
        this.finalDate = LocalDate.parse(newFinalDateString, formatter);
    }
}