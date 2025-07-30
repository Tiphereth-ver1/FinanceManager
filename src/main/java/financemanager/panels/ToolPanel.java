package financemanager.panels;

import java.awt.FlowLayout;
import java.util.function.Supplier;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolPanel extends JPanel{
    private JComboBox<String> popupBox;
    private Runnable updateButtonRunnable; // store it for later
    private JButton updateButton;
    

    public ToolPanel(JFrame parentFrame, Runnable addButtonRunnable, Runnable deleteButtonRunnable,
    Runnable updateButtonRunnable, Runnable saveButtonRunnable, Runnable loadButtonRunnable) {
        this.updateButtonRunnable = updateButtonRunnable;
        String[] options = {"Date (Ascending)", "Date (Descending)", "Value (Ascending)", "Value (Descending)"};
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));      
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JLabel opts = new JLabel("Sorting options:");
        this.popupBox = new JComboBox<>(options);
        this.updateButton = new JButton("Update List");

        addButton.addActionListener(e -> {addButtonRunnable.run();});
        deleteButton.addActionListener(e -> {deleteButtonRunnable.run();});
        updateButton.addActionListener(e -> {if (updateButtonRunnable != null) {updateButtonRunnable.run();}});

        add(addButton);
        add(deleteButton);
        add(opts);
        add(popupBox);
        add(updateButton);
     
    }

    public void setUpdateButtonRunnable(Runnable updateRunnable) {
        this.updateButtonRunnable = updateRunnable;
        updateButton.addActionListener(e -> {if (updateButtonRunnable != null) {updateButtonRunnable.run();}});
    }

    public Supplier<String> getSortOptionSupplier() {
    return () -> (String) popupBox.getSelectedItem();
}

    
}
