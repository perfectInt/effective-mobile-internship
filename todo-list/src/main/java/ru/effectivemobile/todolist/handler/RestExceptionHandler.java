package ru.effectivemobile.todolist.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.effectivemobile.todolist.dto.ErrorMessage;
import ru.effectivemobile.todolist.dto.Response;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response<ErrorMessage>> handleNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new Response<>(false,
                new ErrorMessage(HttpStatus.NOT_FOUND.value(), "No such resource")),
                HttpStatus.NOT_FOUND);
    }
}
