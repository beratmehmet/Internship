import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelationalModel {

    private Connection conn;
    private Statement statement;

    RelationalModel(String connectionString, String username, String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(connectionString,username,password);
            statement = conn.createStatement();
            System.out.println("Connection Succes");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    void insertEmployee(Employee employee){

        String insertQuery= "INSERT INTO employee (id, name, surname, title, birthyear) VALUES"+
                "("+employee.getId()+", '"+employee.getName()+"', '"+ employee.getSurname()+"', '"+ employee.getTitle()+"', "+ employee.getBirthyear()+")";
        try {
            statement.executeUpdate(insertQuery);
            System.out.println("Employee Inserted.");
        } catch (SQLException throwables) {
            System.out.println("Insert Error!");
            throwables.printStackTrace();
        }
    }

    public List<Employee> readEmployees(OrderField order){
        List<Employee> employees = new ArrayList<>();
        String query= "SELECT * FROM employee ORDER BY ? ASC";


        try {
            PreparedStatement preStatement=conn.prepareStatement(query);
            preStatement.setString(1, order.getLabel());
            ResultSet rs = preStatement.executeQuery();
            while (rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String surname=rs.getString("surname");
                String title = rs.getString("title");
                int birthyear = rs.getInt("birthyear");

                employees.add(new Employee(id, name, surname, title, birthyear));
            }
        } catch (SQLException throwables) {
            System.out.println("Read Error!");
            throwables.printStackTrace();
        }
        return employees;
    }

    public  void connectionClose(){
        try {
            conn.close();
            System.out.println("Connection Closed");
        } catch (SQLException throwables) {
            System.out.println("Still connected!");
            throwables.printStackTrace();
        }
    }


}
