package org.pires.springstudy.dtos.request.alunoRegister;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MatriculaRequest (String descricaoSaude) {
}
