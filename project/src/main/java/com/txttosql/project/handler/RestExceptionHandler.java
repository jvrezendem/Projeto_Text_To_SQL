package com.txttosql.project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.txttosql.project.exception.IaConnectionException;
import com.txttosql.project.exception.IlegalQuestionException;
import com.txttosql.project.exception.InvalidSqlException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IlegalQuestionException.class)
    private ResponseEntity<CustonErrorMessage> ilegalQuestionException(IlegalQuestionException exception) {
        CustonErrorMessage error = new CustonErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<CustonErrorMessage> validationExceptions(MethodArgumentNotValidException exception) {
        CustonErrorMessage error = new CustonErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidSqlException.class)
    private ResponseEntity<CustonErrorMessage> invalidSqlException(InvalidSqlException exception) {
        CustonErrorMessage error = new CustonErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IaConnectionException.class)
    private ResponseEntity<CustonErrorMessage> iaConnectionException(IaConnectionException exception) {
        CustonErrorMessage error = new CustonErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
