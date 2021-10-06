import java.util.ArrayList;
import java.util.List;

public class Main2 {

    public static  void main(String[] args){

        RelationalModel db = new RelationalModel("jdbc:mysql://localhost/empManagement","root","admin123");
        List<Employee> employees = new ArrayList<>();

        employees=db.readEmployees(OrderField.SURNAME);

        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

        db.connectionClose();


    }

}
