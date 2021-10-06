import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Class3 {
    Class2 class2 = new Class2();
    private static final Logger logger = LoggerFactory.getLogger(Class3.class);
    public void method(){

       try {
           class2.method();
       }catch (Exception e){
           throw new CustomException(e);

       }
    }

}
