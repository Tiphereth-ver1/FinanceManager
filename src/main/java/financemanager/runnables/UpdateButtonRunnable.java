package financemanager.runnables;

import java.util.function.Supplier;

import financemanager.SortMode;
import financemanager.TransactionManager;
import financemanager.cardpanels.HomePanel;

public class UpdateButtonRunnable implements Runnable {
    private final Supplier<String> sortStringSupplier;
    private final HomePanel homePanel;
    private final TransactionManager transactions;

    public UpdateButtonRunnable(Supplier<String> sortStringSupplier, HomePanel homePanel, TransactionManager transactions) {
        this.sortStringSupplier = sortStringSupplier;
        this.homePanel = homePanel;
        this.transactions = transactions;
    }

    @Override
    public void run() {
        String sortString = sortStringSupplier.get();  // <- this line was breaking before
        if (sortString != null) {
            SortMode sorter = SortMode.fromString(sortString);
            transactions.sortTransactions(sorter);
        }
        homePanel.refreshTransactionTable();
        homePanel.refreshNetBalance();
    }
}
