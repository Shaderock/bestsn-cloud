package utm.pad.cloud.datawarehouse.exceptions;

public class LoginAlreadyExistsException extends Exception {
    public LoginAlreadyExistsException(String message) {
        super(message);
    }
}
