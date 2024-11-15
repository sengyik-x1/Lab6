// import javax.swing.JFrame;
// import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
// import javax.swing.JTextField;
// import javax.swing.border.Border;
// import javax.swing.event.AncestorListener;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class EmpManagementSys extends JFrame{

//     JTextArea display;
//     JTextField input;
    
//     EmpManagementSys(String name){
//         super(name);

//     }

//     public void addComponentsToPane(final Container pane){
//         //user input field
//         input = new JTextField();
//         input.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e){
//                     String command = input.getText();
                    

//             }
//         });

//         //display area
//         display = new JTextArea();
//         display.setEditable(false);
//         JScrollPane displayScrollPane = new JScrollPane(display);

//         //layout of the components
//         setLayout(new BorderLayout());
//         add(input, BorderLayout.NORTH);
//         add(displayScrollPane, BorderLayout.SOUTH);


//     }

//     public static void execute(String command){

//     }
//     /*public static void main(String[] args) {
        
//         createGUI();
        
//     }

//     /*public static void createGUI(){
//         EmpManagementSys frame1 = new EmpManagementSys("Employee Management System");
//         frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);

//         frame1.setSize(500,500);
//         frame1.addComponentsToPane(frame1.getContentPane());
//         //frame1.pack();
//         frame1.setVisible(true);
//     }*/

// }
