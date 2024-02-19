package ru.effectivemobile.authservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.effectivemobile.authservice.exception.ExpiredTokenException;
import ru.effectivemobile.authservice.exception.IncorrectCodeException;
import ru.effectivemobile.authservice.exception.ObjectAlreadyExistsException;
import ru.effectivemobile.authservice.exception.ObjectNotFoundException;
import ru.effectivemobile.authservice.exception.dto.ExceptionMessage;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ExceptionMessage> handleExpiredTokenException(ExpiredTokenException ex) {
        return new ResponseEntity<>(new ExceptionMessage(false, "Token has been expired!"),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IncorrectCodeException.class)
    public ResponseEntity<ExceptionMessage> handleIncorrectCodeException(IncorrectCodeException ex) {
        return new ResponseEntity<>(new ExceptionMessage(false, "Incorrect verification code!"),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<ExceptionMessage> handleObjectAlreadyExistsException(ObjectAlreadyExistsException ex) {
        return new ResponseEntity<>(new ExceptionMessage(false, "Object already exists!"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleObjectNotFoundException(ObjectNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionMessage(false, "Not found!"),
                HttpStatus.NOT_FOUND);
    }
}
