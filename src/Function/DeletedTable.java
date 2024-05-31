package Function;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import db.QuanLyNhanSu;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

public class DeletedTable {
	
    private List<Deleted> deletedRows;
    
    
    public DeletedTable(List<Deleted> deletedRows) {
        this.deletedRows = deletedRows;
    }
    
    public void display() {
    	
        URL urlvne = DeletedTable.class.getResource("QL.jpg");
        Image img = Toolkit.getDefaultToolkit().createImage(urlvne);

        JFrame frame = new JFrame("Deleted Rows");
        frame.setIconImage(img);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Name", "Age", "Position", "Salary"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
       
        
        for (Deleted deletedRow : deletedRows) {   
            Object[] rowData = {deletedRow.getId(), deletedRow.getName(), deletedRow.getAge(),
                    deletedRow.getPosition(), deletedRow.getSalary()};
            model.addRow(rowData);
        }

        
        JTable table = new JTable(model); 
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btRestore = new JButton("Restore");
        btRestore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restoreSelectedRow(table, model);
            }
        });

        panel.add(scrollPane, BorderLayout.NORTH);
        panel.add(btRestore, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void restoreSelectedRow(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            Deleted deletedRow = deletedRows.remove(selectedRow);

        
            Object[] rowData = {deletedRow.getId(), deletedRow.getName(), deletedRow.getAge(),
                    deletedRow.getPosition(), deletedRow.getSalary()};
            DefaultTableModel mainTableModel = (DefaultTableModel) QuanLyNhanSu.tb.getModel();
            mainTableModel.addRow(rowData);

          
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to restore.");
        }
    }
}




