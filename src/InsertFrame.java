import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InsertFrame extends JFrame implements ActionListener {
    JTextField empNoText, name1Text, name2Text, genderText, birthDateText, hireDateText;
    JButton save, cancel;


    InsertFrame(String title){

        super(title);

        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);


        JLabel empNo = new JLabel("Enter employee no: ");
        empNoText = new JTextField();
        empNo.setBounds(20, 50, 150, 20);
        empNoText.setBounds(180, 50, 200, 20);
        add(empNo);
        add(empNoText);

        JLabel name1 = new JLabel("Enter First Name: ");
        name1Text = new JTextField();
        name1.setBounds(20, 90, 150, 20);
        name1Text.setBounds(180, 90, 200, 20);
        add(name1);
        add(name1Text);

        JLabel name2 = new JLabel("Enter Last Name: ");
        name2Text = new JTextField();
        name2.setBounds(20, 130, 150, 20);
        name2Text.setBounds(180, 130, 200, 20);
        add(name2);
        add(name2Text);


        JLabel gender = new JLabel("Enter Gender [M/F]: ");
        genderText = new JTextField();
        gender.setBounds(20, 170, 150, 20);
        genderText.setBounds(180, 170, 200, 20);
        add(gender);
        add(genderText);

        JLabel birthDate = new JLabel("<html>Enter the birth date: <br>[YYYY-MM-DD] </html> ");
        birthDateText = new JTextField();
        birthDate.setBounds(20, 200, 150, 50);
        birthDateText.setBounds(180, 210, 200, 20);
        add(birthDate);
        add(birthDateText);

        JLabel hireDate = new JLabel("<html>Enter the hire date: <br>[YYYY-MM-DD] </html> ");
        hireDateText = new JTextField();
        hireDate.setBounds(20, 240, 200, 50);
        hireDateText.setBounds(180, 250, 200, 20);
        add(hireDate);
        add(hireDateText);

        save = new JButton("SAVE");
        save.addActionListener(this);
        cancel = new JButton("CLOSE");
        cancel.addActionListener(this);

        save.setBounds(20, 320, 100, 50);
        cancel.setBounds(260, 320, 100, 50);
        add(save);
        add(cancel);

        this.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        if(command.equals("SAVE")){
            //if textfield incomplete
            if(empNoText.getText().equals("") || name1Text.getText().equals("") || name2Text.getText().equals("") || genderText.getText().equals("") || birthDateText.getText().equals("") || hireDateText.getText().equals("")){
                Toolkit.getDefaultToolkit().beep();
                return;
            }

            String empNo = empNoText.getText();
            String first_name = name1Text.getText();
            String last_name = name2Text.getText();
            String gender = genderText.getText();
            String birth_date = birthDateText.getText();
            String hire_date = hireDateText.getText();

            ConnectDB.isInsert(empNo, birth_date, first_name, last_name, gender, hire_date);
            dispose();

           
        }
        else if (command.equals("CLOSE")){
            dispose();
        }
       
    }
}
