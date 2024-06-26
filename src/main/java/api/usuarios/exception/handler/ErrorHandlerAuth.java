package api.usuarios.exception.handler;

import api.usuarios.exception.*;
import api.usuarios.utils.IpUtils;
import api.usuarios.utils.RandomUtils;
import api.usuarios.utils.TimeUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ErrorHandlerAuth extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorDetails> errorValidatingToken(AuthException ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "externalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(RefreshException.class)
    public ResponseEntity<ErrorDetails> errorValidatingRefreshToken(RefreshException ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "externalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> errorUserIdNotFound(UserNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "internalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(UserCannotBeRegisteredException.class)
    public ResponseEntity<ErrorDetails> errorUserCanotBeRegistered(UserCannotBeRegisteredException ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "internalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(UsersCannotBeDeletedException.class)
    public ResponseEntity<ErrorDetails> errorDeleteAll(UsersCannotBeDeletedException ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "internalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(SomeException.class)
    public ResponseEntity<ErrorDetails> errorUserServer(SomeException ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "internalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(400));
    }
}