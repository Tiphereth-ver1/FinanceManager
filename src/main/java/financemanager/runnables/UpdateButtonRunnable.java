package financemanager.runnables;

import java.util.function.Supplier;

import financemanager.GUI;
import financemanager.SortMode;
import financemanager.TransactionManager;

public class UpdateButtonRunnable implements Runnable {
    private final Supplier<String> sortStringSupplier;
    private final GUI guiRef;
    private final TransactionManager transactions;

    public UpdateButtonRunnable(Supplier<String> sortStringSupplier, GUI guiRef, TransactionManager transactions) {
        this.sortStringSupplier = sortStringSupplier;
        this.guiRef = guiRef;
        this.transactions = transactions;
    }

    @Override
    public void run() {
        String sortString = sortStringSupplier.get();  // <- this line was breaking before
        if (sortString != null) {
            SortMode sorter = SortMode.fromString(sortString);
            transactions.sortTransactions(sorter);
        }
        guiRef.refreshTransactionTable();
        guiRef.refreshNetBalance();
    }
}
