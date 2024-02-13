package ru.effectivemobile.shorturl.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.effectivemobile.shorturl.exceptions.NotFoundException;
import ru.effectivemobile.shorturl.exceptions.dto.ExceptionMessage;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(new ExceptionMessage(false, "Cannot find this url"), HttpStatus.NOT_FOUND);
    }
}
