package utm.pad.cloud.datawarehouse.controllers.advices;

import utm.pad.cloud.datawarehouse.controllers.MessageController;
import utm.pad.cloud.datawarehouse.exceptions.MessageHistoryNotFoundException;
import utm.pad.cloud.datawarehouse.exceptions.MultiChatsException;
import utm.pad.cloud.datawarehouse.exceptions.UserNotFoundException;
import utm.pad.cloud.datawarehouse.models.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = MessageController.class)
public class MessageControllerAdvice {
    @ExceptionHandler({UserNotFoundException.class,
            MultiChatsException.class,
            MessageHistoryNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleMessageActionException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }
}
