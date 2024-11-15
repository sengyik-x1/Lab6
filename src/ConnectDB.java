import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {

    static Connection conn = null;
    
    ConnectDB(){
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/employeedb", "", "");
            System.out.println("Success to make comnnection to db");
            if(conn != null){
                System.out.println("Connection successful");

            }
            else{
                System.out.println("Failed to make connection");
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static boolean isInsert(String empNo, String birth_date, String first_name, String last_name, String gender, String hire_date){

        //try{
        int emp_no = Integer.parseInt(empNo);
        String sql = "INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) VALUES (?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, emp_no);
            stmt.setString(2, birth_date);
            stmt.setString(3, first_name);
            stmt.setString(4, last_name);
            stmt.setString(5, gender);
            stmt.setString(6, hire_date);

            
        int rowAffected = stmt.executeUpdate();

        if(rowAffected > 0)
        {
            return true;
        }
        else{
            System.out.println("no row affected...");
            return false;
        }
        }
        catch(Exception e){
           e.printStackTrace();
           return false;
        }

    }

    public static boolean isDelete(String empNo){

        int emp_no = Integer.parseInt(empNo);
        int rowAffected;
        String sql = "DELETE FROM employees WHERE emp_no = ?";
        try{
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, emp_no);

        rowAffected = stmt.executeUpdate();
        if(rowAffected > 0)
        {
            return true;
        }
        else{
            System.out.println("no row affected...");
            return false;
        }


        }
        catch(Exception e){
            return false;
        }

    }

    public static boolean isUpdate(String target_empNo, String new_empNo, String new_birthDate, String new_name1, String new_name2, String new_gender, String new_hireDate){
        String sql = "UPDATE employees SET emp_no = ? , birth_date = ?, first_name = ?, last_name = ?, gender =?, hire_date = ? WHERE  emp_no = " + target_empNo + ";";
        int rowAffected = 0;
        int new_emp_no = Integer.parseInt(new_empNo);
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, new_emp_no);
            stmt.setString(2, new_birthDate);
            stmt.setString(3, new_name1);
            stmt.setString(4, new_name2);
            stmt.setString(5, new_gender);
            stmt.setString(6, new_hireDate);
            
            rowAffected = stmt.executeUpdate();

            if(rowAffected > 0){
                return true;
            }
            else{
                return false;
            }


        }
        catch(Exception e){

            e.printStackTrace();
            return false;
        }
    }

    public static List<String[]> display(){
        String sql = "SELECT * FROM employees ORDER BY emp_no";
        List<String[]> recordList = new ArrayList<>();

        try{
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            String[] row = new String[6];

            for(int i =0; i<6; i++){
                row[i] = rs.getString(i+1);
            }

            printRow(row);

            recordList.add(row);


        }
        return recordList;

        }
        catch(Exception e){

        return null;

        }
    }

    public static void printRow(String[] row) {
        for (String value : row) {
            System.out.print(value + " ");
        }
        System.out.println(); // Move to the next line after printing each row
    }
    
}
