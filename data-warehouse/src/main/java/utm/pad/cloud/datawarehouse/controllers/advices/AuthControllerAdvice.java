package utm.pad.cloud.datawarehouse.controllers.advices;

import utm.pad.cloud.datawarehouse.controllers.AuthController;
import utm.pad.cloud.datawarehouse.exceptions.LoginAlreadyExistsException;
import utm.pad.cloud.datawarehouse.models.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = AuthController.class)
public class AuthControllerAdvice {
    @ExceptionHandler(LoginAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleRegistrationException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }
}
