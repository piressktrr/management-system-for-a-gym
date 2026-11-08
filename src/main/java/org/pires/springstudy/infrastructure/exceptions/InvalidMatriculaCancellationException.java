package org.pires.springstudy.infrastructure.exceptions;

public class InvalidMatriculaCancellationException extends RuntimeException {
    public InvalidMatriculaCancellationException(String message) {
        super(message);
    }
}
