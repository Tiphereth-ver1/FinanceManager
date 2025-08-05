package financemanager.runnables;

import financemanager.cardpanels.HomePanel;

public class AddButtonRunnable implements Runnable{

    private final HomePanel homePanel;

    public AddButtonRunnable(HomePanel homePanel) {
        this.homePanel = homePanel;
    }

    @Override
    public void run() {
            homePanel.showAddTransactionDialog();
    }
}
