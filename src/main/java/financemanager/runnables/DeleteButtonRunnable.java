package financemanager.runnables;

import javax.swing.table.DefaultTableModel;

import financemanager.GUI;
import financemanager.TransactionManager;

public class DeleteButtonRunnable implements Runnable{
    private final DefaultTableModel model;
    private final TransactionManager transactions;
    private final GUI guiRef;

    public DeleteButtonRunnable(DefaultTableModel model, TransactionManager transactions, GUI guiRef) {
        this.model = model;
        this.transactions = transactions;
        this.guiRef = guiRef;
    }

    @Override
    public void run() {
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                Boolean checked = (Boolean) model.getValueAt(i, 0);
                if (checked != null && checked) {
                    transactions.removeTransaction(i); // also remove from internal list
                    model.removeRow(i);
                }
            }
            guiRef.refreshTransactionTable(); // optional depending on how you sync
            guiRef.refreshNetBalance();    };
    }
    


