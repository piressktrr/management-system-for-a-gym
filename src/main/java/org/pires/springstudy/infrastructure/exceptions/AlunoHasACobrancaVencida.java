package org.pires.springstudy.infrastructure.exceptions;

public class AlunoHasACobrancaVencida extends RuntimeException {
    public AlunoHasACobrancaVencida(String message) {
        super(message);
    }
}
