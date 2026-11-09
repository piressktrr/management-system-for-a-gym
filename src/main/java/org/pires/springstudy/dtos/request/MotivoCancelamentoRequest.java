package org.pires.springstudy.dtos.request;

import jakarta.validation.constraints.NotEmpty;

public record MotivoCancelamentoRequest(@NotEmpty String motivoCancelamento) {
}
