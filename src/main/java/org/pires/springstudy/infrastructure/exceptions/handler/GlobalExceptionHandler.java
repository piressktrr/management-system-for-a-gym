package org.pires.springstudy.infrastructure.exceptions.handler;


import org.pires.springstudy.infrastructure.exceptions.*;
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

    @ExceptionHandler(PlanoNotFound.class)
    public ResponseEntity<String>  handlePlanoNotFound(PlanoNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(AlunoHasACobrancaVencida.class)
    public ResponseEntity<String>  handleCobrancaVencida(AlunoHasACobrancaVencida e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }

    @ExceptionHandler(InvalidMatriculaCancellationException.class)
    public ResponseEntity<String>  handleInvalidMatriculaCancellationException(InvalidMatriculaCancellationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

    }

    @ExceptionHandler(AlunoNotFoundException.class)
    public ResponseEntity<String> handleAlunoNotFoundException(AlunoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalCredentialsException.class)
    public ResponseEntity<String> handleIllegalCredentialsExceptions(IllegalCredentialsException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
