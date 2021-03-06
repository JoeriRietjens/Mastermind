package nl.fhict.s3.mastermindlogin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IncorrectLoginException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @ExceptionHandler(IncorrectLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String incorrectLoginException(IncorrectLoginException ex) {
        return ex.getMessage();
    }
}
