import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame implements ActionListener {
    JButton insert, delete, update, display;
    JButton[] actionButtons = new JButton[4];


    public MainFrame(String title) {
        super(title);
    }

    public static void createGUI() {
        MainFrame frame1 = new MainFrame("Employee Management System");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame1.setSize(500, 500);
        frame1.addComponentsToPane(frame1.getContentPane());
        // frame1.pack();
        frame1.setVisible(true);
    }

    public void addComponentsToPane(final Container pane) {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        setLayout(new BorderLayout(0,0));

        JLabel mainTitle = new JLabel("Welcome to Employee Management System");
        mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
        mainTitle.setFont(new Font("Comic Sans", Font.ITALIC, 18));
        add(mainTitle, BorderLayout.NORTH);

        //selection panel
        JPanel options = new JPanel();
        options.setLayout(layout);
        add(options, BorderLayout.CENTER);

        JLabel action = new JLabel("Select one of the action below: ");
        action.setFont(new Font("Comic Sans", Font.ITALIC, 18));
        gbc.gridx= 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        options.add(action, gbc);

        insert = new JButton("Insert new employee");
        insert.setFont(new Font("Comic Sans", Font.ITALIC, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        options.add(insert, gbc);

        delete = new JButton("Delete employee");
        delete.setFont(new Font("Comic Sans", Font.ITALIC, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        options.add(delete, gbc);

        update = new JButton("Update employee");
        update.setFont(new Font("Comic Sans", Font.ITALIC, 18));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        options.add(update, gbc);

        display = new JButton("Display records");
        display.setFont(new Font("Comic Sans", Font.ITALIC, 18));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        options.add(display, gbc);
        
        actionButtons[0] = insert;
        actionButtons[1] = delete;
        actionButtons[2] = update;
        actionButtons[3] = display;

        for(int i = 0; i<4; i++){
            actionButtons[i].setFocusable(false);
            actionButtons[i].addActionListener(this);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == actionButtons[0]){
            InsertFrame insertFrame = new InsertFrame("Insert New Employee");
        }
        else if(e.getSource() == actionButtons[1]){
            DisplayNDeleteFrame deleteFrame = new DisplayNDeleteFrame("Delete Existing Employee");
            
        }
        else if(e.getSource() == actionButtons[2]){
            DisplayNDeleteFrame upDeleteFrame = new DisplayNDeleteFrame("Update an employee");
        }
        else if(e.getSource() == actionButtons[3]){
            DisplayNDeleteFrame dispayDeleteFrame = new DisplayNDeleteFrame("Display Employees' Records");
        }
        
        }

    public static void main(String[] args) {
        createGUI();
        ConnectDB connect = new ConnectDB();
    }

}
