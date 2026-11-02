package org.pires.springstudy.infrastructure.exceptions;

public class ConflictByCreateAluno extends RuntimeException {
    public ConflictByCreateAluno(String message) {
        super(message);
    }
}
