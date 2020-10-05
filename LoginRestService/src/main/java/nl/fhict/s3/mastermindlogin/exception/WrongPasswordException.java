package nl.fhict.s3.mastermindlogin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WrongPasswordException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String wrongPasswordException(WrongPasswordException ex) {
        return ex.getMessage();
    }
}
