package financemanager.runnables;

import financemanager.GUI;

public class AddButtonRunnable implements Runnable{

    private final GUI guiRef;

    public AddButtonRunnable(GUI guiRef) {
        this.guiRef = guiRef;
    }

    @Override
    public void run() {
            guiRef.showAddTransactionDialog();
    }
}
