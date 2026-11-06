package org.pires.springstudy.dtos.request.alunoRegister;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record MatriculaPlanoRequest(@JsonFormat(pattern = "dd/MM/yyyy")
                                    LocalDate dataInicio) {
}
