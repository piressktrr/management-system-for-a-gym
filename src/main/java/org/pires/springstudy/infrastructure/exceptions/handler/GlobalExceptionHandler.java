package org.pires.springstudy.infrastructure.exceptions.handler;


import org.pires.springstudy.infrastructure.exceptions.ConflictByCreateAluno;
import org.pires.springstudy.infrastructure.exceptions.ConflictBySaveLoginForUser;
import org.pires.springstudy.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// classe para gerenciar exceções globalmente
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictByCreateAluno.class)
    public ResponseEntity<String> handleConflictByCreateAluno(ConflictByCreateAluno e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ConflictBySaveLoginForUser.class)
    public ResponseEntity<String>  handleConflictByCreateUserLogin(ConflictBySaveLoginForUser e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
