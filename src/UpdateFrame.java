import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UpdateFrame extends JFrame implements ActionListener{
     public JTextField new_empNoText, new_name1Text, new_name2Text, new_genderText, new_birthDateText, new_hireDateText;
     JButton update, cancel;
     String target_emp_no;

     UpdateFrame(String title, String target_emp_no){
        super(title);
        this.target_emp_no = target_emp_no;

        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);


        JLabel empNo = new JLabel("Enter new employee No. : ");
        new_empNoText = new JTextField();
        empNo.setBounds(20, 50, 150, 20);
        new_empNoText.setBounds(180, 50, 200, 20);
        add(empNo);
        add(new_empNoText);

        JLabel name1 = new JLabel("Enter New First Name: ");
        new_name1Text = new JTextField();
        name1.setBounds(20, 90, 150, 20);
        new_name1Text.setBounds(180, 90, 200, 20);
        add(name1);
        add(new_name1Text);

        JLabel name2 = new JLabel("Enter New Last Name: ");
        new_name2Text = new JTextField();
        name2.setBounds(20, 130, 150, 20);
        new_name2Text.setBounds(180, 130, 200, 20);
        add(name2);
        add(new_name2Text);


        JLabel gender = new JLabel("Enter New Gender [M/F]: ");
        new_genderText = new JTextField();
        gender.setBounds(20, 170, 150, 20);
        new_genderText.setBounds(180, 170, 200, 20);
        add(gender);
        add(new_genderText);

        JLabel birthDate = new JLabel("<html>Enter new birth date: <br>[YYYY-MM-DD] </html> ");
        new_birthDateText = new JTextField();
        birthDate.setBounds(20, 200, 150, 50);
        new_birthDateText.setBounds(180, 210, 200, 20);
        add(birthDate);
        add(new_birthDateText);

        JLabel hireDate = new JLabel("<html>Enter the hire date: <br>[YYYY-MM-DD] </html> ");
        new_hireDateText = new JTextField();
        hireDate.setBounds(20, 240, 200, 50);
        new_hireDateText.setBounds(180, 250, 200, 20);
        add(hireDate);
        add(new_hireDateText);

        update = new JButton("Update");
        update.addActionListener(this);
        cancel = new JButton("CLOSE");
        cancel.addActionListener(this);

        update.setBounds(20, 320, 100, 50);
        cancel.setBounds(260, 320, 100, 50);
        add(update);
        add(cancel);

        this.setVisible(true);
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Update")){
            //if textfield incomplete
            if(new_empNoText.getText().equals("") || new_name1Text.getText().equals("") || new_name2Text.getText().equals("") || new_genderText.getText().equals("") || new_birthDateText.getText().equals("") || new_hireDateText.getText().equals("")){
                Toolkit.getDefaultToolkit().beep();
                return;
            }

            String new_empNo = new_empNoText.getText();
            String new_first_name = new_name1Text.getText();
            String new_last_name = new_name2Text.getText();
            String new_gender = new_genderText.getText();
            String new_birth_date = new_birthDateText.getText();
            String new_hire_date = new_hireDateText.getText();

            if(ConnectDB.isUpdate(target_emp_no, new_empNo, new_birth_date, new_first_name, new_last_name, new_gender, new_hire_date)){
                System.out.println("Updated Successfully");
                dispose();
                //refresh table
                //DeleteFrame deleteFrame = new DeleteFrame("Updated Record");
            }
            else{
                System.out.println("Failed to Update");
            }


        }
        else{
            dispose();
        }
    }

    
}
