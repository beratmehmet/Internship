import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainClass {
    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);
    public static void main(String[] args) {
        Class3 class3 = new Class3();
        try{
            class3.method();
        }catch (CustomException e){
            logger.error("Exception:",e);
        }

    }
}
