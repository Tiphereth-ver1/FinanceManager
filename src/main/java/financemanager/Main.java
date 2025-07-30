package financemanager;
public class Main {
    public static void main(String[] args) {
        // Transaction t1 = new Transaction("10/12/2024", "DEPOSIT", 400, "Weekly pay");
        // Transaction t2 = new Transaction("18/12/2023", "EXPENDITURE", 175, "Keyboard");
        // Transaction t3 = new Transaction("20/10/2024", "DEPOSIT", 1000,  "Birthday money");
        // Transaction t4 = new Transaction("30/11/2024","EXPENDITURE", 200000, "this little shit can't stop buying beyblades");
        // Transaction t5 = new Transaction("13/02/2025", "DEPOSIT", 666666, "I sold my soul to Satan to pay off the debt");


        TransactionManager transactions = new TransactionManager();

        // transactions.addTransaction(t1);
        // transactions.addTransaction(t2);
        // transactions.addTransaction(t3);
        // transactions.addTransaction(t4);
        // transactions.addTransaction(t5);

        transactions.readFromCSV("transactions.csv");
        transactions.sortTransactions(SortMode.DATE_DESC);
        transactions.printAllTransactions();
    }
}
