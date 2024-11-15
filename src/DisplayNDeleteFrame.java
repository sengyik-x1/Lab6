import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class DisplayNDeleteFrame extends JFrame implements ListSelectionListener {

    public JList<String[]> list;
    public DefaultListModel<String[]> listmodel;
    public List<String[]> record;
    public JTable table;

    private JButton delete, search, update;
    private JTextField textField;
    String[][] data;


    public DisplayNDeleteFrame(String title) {

        super(title);
        setLayout(new BorderLayout());
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        textField = new JTextField(50);

        JPanel upperPanel = new JPanel();

        record = new ArrayList<>();
        record = ConnectDB.display();

        // create table
        String[] columnName = { "emp_no", "birth_date", "first_name", "last_name", "gender", "hire_date" };
        data = new String[record.size()][];

        for (int i = 0; i < record.size(); i++) {
            data[i] = record.get(i);
        }

        table = new JTable(data, columnName);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(this);

        listmodel = new DefaultListModel<>();
        for (int i = 0; i < record.size(); i++) {
            listmodel.addElement(record.get(i));
        }

        list = new JList<>(listmodel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);

        JScrollPane listScrollPane = new JScrollPane(table);

        search = new JButton("Search");
        delete = new JButton("Delete");
        update = new JButton("Update");
        delete.addActionListener(new DeleteButtonListener());
        search.addActionListener(new SearchButtonListener());
        update.addActionListener(new UpdateButtonListener());

        upperPanel.add(textField);
        upperPanel.add(search);
        upperPanel.add(update);
        upperPanel.add(delete);
        

        add(listScrollPane, BorderLayout.CENTER);
        add(upperPanel, BorderLayout.PAGE_START);

        setVisible(true);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (table.getSelectedRow() == -1) {
                delete.setEnabled(false);
                search.setEnabled(false);
            } else if (table.getSelectedRow() >= 0) {
                delete.setEnabled(true);
                search.setEnabled(true);
                int selectedRowIndex = table.getSelectedRow();
                Object selectedValue = table.getValueAt(selectedRowIndex, 0); // Get value of first column
                textField.setText(selectedValue.toString());
            }
        }
    }

    class DeleteButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // get the empNo only from the textfield
            String empNo_text = textField.getText();
            int selectedRow = table.getSelectedRow();
            //Object selected_emp_no = table.getValueAt(selectedRow, 0);
            if (ConnectDB.isDelete(empNo_text)) {
                System.out.println("Deleted");
                // Remove the selected row from the data array
                String[][] newData = new String[data.length - 1][data[0].length];
                int destRow = 0;
                for (int srcRow = 0; srcRow < data.length; srcRow++) {
                    if (srcRow != selectedRow) {
                        System.arraycopy(data[srcRow], 0, newData[destRow], 0, data[srcRow].length);
                        destRow++;


                        // Reset selection
                        table.clearSelection();
                        textField.setText("");

                    } else if (textField.getText() == "") {
                        Toolkit.getDefaultToolkit().beep();
                        return;
                    } 
                }
                data = newData;
                // Refresh the table
                dispose();
            }
            else {
                System.out.println("Failed to delete");
            }

        }
    }

    class SearchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String empNo_text = textField.getText();

            for (int i = 0; i < table.getRowCount(); i++) {
                Object selected_emp_no = table.getValueAt(i, 0);

                if (selected_emp_no.equals(empNo_text)) {

                    table.setRowSelectionInterval(i, i); // Select the row
                    table.scrollRectToVisible(table.getCellRect(i, 0, true)); // Ensure it's visible
                    textField.setText(table.getValueAt(i, 0).toString());
                    ;
                } else {
                    Toolkit.getDefaultToolkit().beep();

                }
            }
        }

    }

    class UpdateButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                UpdateFrame updateFrame = new UpdateFrame("Update Employee", textField.getText());
            
               
        }

        
        
        
    }


}
