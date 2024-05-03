package api.usuarios.exception.handler;

import api.usuarios.exception.*;
import api.usuarios.utils.IpUtils;
import api.usuarios.utils.RandomUtils;
import api.usuarios.utils.TimeUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
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

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<ErrorDetails> errorValidatingAwsEmailSes(EmailException ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "externalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> errorUserIdNotFound(UserNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "internalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(UserCanotBeRegisteredException.class)
    public ResponseEntity<ErrorDetails> errorUserCanotBeRegistered(UserCanotBeRegisteredException ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "internalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(UnauthorizedExcepion.class)
    public ResponseEntity<ErrorDetails> errorUserUnauthorized(UnauthorizedExcepion ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "internalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(401));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorDetails> errorUserForbidden(ForbiddenException ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "internalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(403));
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ErrorDetails> errorUserServerError(ServerException ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "internalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<ErrorDetails> errorUserServerErrord(MailException ex) {
        ErrorDetails errorDetails = new ErrorDetails(TimeUtils.getZoneTimeWithClock(), ex.getMessage(), "internalError", RandomUtils.generateCode(), IpUtils.getAddress());
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(500));
    }
}