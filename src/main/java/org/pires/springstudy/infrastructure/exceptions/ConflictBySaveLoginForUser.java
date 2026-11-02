package org.pires.springstudy.infrastructure.exceptions;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


public class ConflictByCreateUserLogin extends ErrorResponseException {
    public ConflictByCreateUserLogin(HttpStatusCode status) {
        super(status);
    }



    public ConflictByCreateUserLogin(HttpStatusCode status, ProblemDetail body, @Nullable Throwable cause) {
        super(status, body, cause);
    }
}
