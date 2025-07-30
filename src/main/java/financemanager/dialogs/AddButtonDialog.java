package financemanager.dialogs;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import financemanager.Transaction;
import financemanager.TransactionManager;

public class AddButtonDialog extends JDialog{
    
    public AddButtonDialog(JFrame parent, TransactionManager transactions, Runnable onTransactionAdded) {
        super(parent, "Add Transaction", true); // modal dialog
        setLocationRelativeTo(parent); // center on parent
        setSize(600,250);
        

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        Font titleFont = new Font("Arial", Font.BOLD, 18);
        JLabel title = new JLabel("Add Transaction",SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(titleFont); 

        JPanel formPanel = new JPanel(new GridLayout(5,2));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] selections = {"DEPOSIT", "EXPENDITURE"};

        SpinnerDateModel dateModel = new SpinnerDateModel();


        JLabel dateLabel = new JLabel("Date");
        JLabel typeLabel = new JLabel("Type");
        JLabel valueLabel = new JLabel("Value");
        JLabel descriptionLabel = new JLabel("Description");

        JSpinner dateSpinner = new JSpinner(dateModel);
        JComboBox<String> typeBox = new JComboBox<>(selections);
        JTextField valueField = new JTextField();
        JTextField descriptionField = new JTextField();

        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));

        formPanel.add(dateLabel); formPanel.add(dateSpinner);
        formPanel.add(typeLabel); formPanel.add(typeBox);
        formPanel.add(valueLabel); formPanel.add(valueField);
        formPanel.add(descriptionLabel); formPanel.add(descriptionField);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20, 10));
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        addButton.addActionListener(e -> {
            Date date = (Date) dateSpinner.getValue();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = sdf.format(date);  // If you still want to store as String
            String type = (String) typeBox.getSelectedItem();
            if (valueField.getText().trim() != null) {
                Float valueText = Float.parseFloat(valueField.getText().trim());
                String description = descriptionField.getText().trim();
                Transaction t = new Transaction(dateString, type, valueText, description);
                transactions.addTransaction(t);
                onTransactionAdded.run();

                dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });

        
        bottomPanel.add(addButton);
        bottomPanel.add(cancelButton);

        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        masterPanel.add(topPanel);
        masterPanel.add(formPanel);
        masterPanel.add(bottomPanel);

        add(masterPanel);

    }
}