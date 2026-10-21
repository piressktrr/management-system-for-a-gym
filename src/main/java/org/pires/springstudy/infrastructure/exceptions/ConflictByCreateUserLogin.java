package org.pires.springstudy.infrastructure.exceptions;

public class ConflictByCreateUserLogin extends RuntimeException {
    public ConflictByCreateUserLogin(String message) {
        super(message);
    }

    public ConflictByCreateUserLogin(String message, Throwable cause) {
        super(message, cause);
    }
}
