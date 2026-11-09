package org.pires.springstudy.dtos.response.alunoRegister;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record MatriculaPlanoResponse(@JsonFormat(pattern = "dd/MM/yyyy")
                                     LocalDate dataInicio,
                                     @JsonFormat(pattern = "dd/MM/yyyy")
                                     LocalDate dataFim) {
}
