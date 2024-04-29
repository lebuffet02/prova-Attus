package api.usuarios.exception.handler.auth;

import api.usuarios.exception.AuthException;
import api.usuarios.exception.ErrorDetails;
import api.usuarios.exception.RefreshException;
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
    public ResponseEntity<ErrorDetails> errorValidatingToken() {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), String.format("%s", "Error when validating token"), RandomUtils.generateCode());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(RefreshException.class)
    public ResponseEntity<ErrorDetails> errorValidatingRefreshToken() {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), String.format("%s", "Error Refresh token"), RandomUtils.generateCode());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(500));
    }
}