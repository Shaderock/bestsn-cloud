package utm.pad.cloud.datawarehouse.controllers.advices;

import utm.pad.cloud.datawarehouse.controllers.FriendController;
import utm.pad.cloud.datawarehouse.exceptions.FriendAlreadyAddedException;
import utm.pad.cloud.datawarehouse.exceptions.FriendDoesNotExistException;
import utm.pad.cloud.datawarehouse.exceptions.UserNotFoundException;
import utm.pad.cloud.datawarehouse.models.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = FriendController.class)
public class FriendControllerAdvice {
    @ExceptionHandler({
            FriendAlreadyAddedException.class,
            UserNotFoundException.class,
            FriendDoesNotExistException.class})
    public ResponseEntity<ErrorResponse> handleFriendActionException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }
}
