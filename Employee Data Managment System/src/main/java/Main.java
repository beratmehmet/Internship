import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static  void main(String[] args){

        String[][] objInfo = {{"Berat", "Topuz", "Engineer", "1998" },{"Ali", "Bakan", "Director", "1978"},
                {"Doğaç", "Öztürk", "Engineer", "1980"},{"Sude", "Kaçan","Program Manager", "1986"},
                {"Ayşe", "Demir", "Engineer", "1997"},{"Baran","Ölmez","Account Executive","1968"},
                {"Sıla","yılmaz","Engineer","1986"},{"Barış","Baykal","Assistant Director","1988"},
                {"İsa","Kaya","Program Coordinator","1979"},{"Biriçim","Demiröz","Engineer","1999"}};

        RelationalModel db = new RelationalModel("jdbc:mysql://localhost/empManagement","root","admin123");

        for(int i = 0; i<10; i++){
            db.insertEmployee(new Employee(i+1,objInfo[i][0],objInfo[i][1],objInfo[i][2],Integer.parseInt(objInfo[i][3])));
        }

        db.connectionClose();






    }

}
