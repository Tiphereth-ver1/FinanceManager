package financemanager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private TransactionType transactionType;
    private float value;
    private LocalDate  date;
    private String description;

    public Transaction(String dateString, String transactionString, float value, String description) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.transactionType = TransactionType.fromString(transactionString);
        this.value = value;
        this.date = LocalDate.parse(dateString, formatter);
        this.description = description;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public float getValue() {
        return value;
    }

    public LocalDate getDate(){
        return date;
    }

    public String getDateString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public String getDescription(){
        return description;
    }

    public float getBalanceEffect() {
        return transactionType.applyTo(value);
    }

    public Object[] getTransactionAsArray() {
        return new Object[] {false,date.toString(), transactionType.toString(),getBalanceEffect(),description}
        ;
    }

    public void printTransactionDetails() {
        System.out.println(getDateString() + " | " +
            getTransactionType() + " | $" +
            getValue() + " | " +
            getDescription());
    }

}
