package financemanager.panels;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import financemanager.TransactionManager;

import java.awt.*;

public class TransactionTablePanel extends JPanel {
    private DefaultTableModel model;
    private JTable transactionTable;

        public TransactionTablePanel(TransactionManager transactions) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        String[] columnNames = { "","Date", "Type", "Value", "Description"};
        model = new DefaultTableModel(transactions.getMatrix(), columnNames) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            // First column is checkbox
            if (columnIndex == 0) return Boolean.class;
            // Let others default to Object
            return super.getColumnClass(columnIndex);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            // Only the checkbox is editable
            return column == 0;
        }


        };


        transactionTable = new JTable(model);
        transactionTable.getTableHeader().setReorderingAllowed(false);
        transactionTable.setFillsViewportHeight(true);
        JTableHeader header = transactionTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));


        JScrollPane sp = new JScrollPane(transactionTable);

        TableColumnModel column = transactionTable.getColumnModel();
        for (int i = 0; i < column.getColumnCount(); i++) {
            if (i == 0) {
                column.getColumn(i).setPreferredWidth(50);
            }
            else if (i == 4) {
                column.getColumn(i).setPreferredWidth(800);
            } 
            else {
                column.getColumn(i).setPreferredWidth(250);
                column.getColumn(i).setCellRenderer(centerRenderer);
            }
        }
            setLayout(new BorderLayout());
            add(sp, BorderLayout.CENTER);
        }

    public JTable getTransactionTable() {
        return transactionTable;
    }

    public DefaultTableModel getModel() {
        return model;
    }



}
