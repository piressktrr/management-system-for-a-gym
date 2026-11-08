package org.pires.springstudy.infrastructure.exceptions;

public class AlunoNotFoundException extends RuntimeException {
    public AlunoNotFoundException(String message) {
        super(message);
    }
}
