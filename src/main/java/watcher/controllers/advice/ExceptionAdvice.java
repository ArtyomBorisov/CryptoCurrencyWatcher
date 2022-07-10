package watcher.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import watcher.service.exception.EntityNotFound;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<?> entityNotFoundHandler(EntityNotFound e) {
        return new ResponseEntity<>(new ResponseError("error", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
