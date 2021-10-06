import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomException extends RuntimeException{

    public CustomException(Exception e) {
        super(e);
    }
}
