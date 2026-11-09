package org.pires.springstudy.dtos.response.alunoRegister;

public record RegisterResponse(AlunoResponse aluno,
                               MatriculaResponse matricula,
                               ContratoResponse contrato,
                               PlanoResponse plano,
                               MatriculaPlanoResponse matriculaPlanoResponse,
                               CobrancaResponse cobranca) {
}
