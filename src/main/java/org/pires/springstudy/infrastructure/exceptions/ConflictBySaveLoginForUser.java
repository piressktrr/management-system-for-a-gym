package org.pires.springstudy.infrastructure.exceptions;


public class ConflictBySaveLoginForUser extends RuntimeException {
    public ConflictBySaveLoginForUser(String message) {
        super(message);
    }


}
