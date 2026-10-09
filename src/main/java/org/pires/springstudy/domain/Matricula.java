package org.pires.springstudy.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Matricula {
    private UUID ID_Matricula;
    private UUID ID_Aluno;
    private String Plano_Matricula;
    private BigDecimal Valor_Matricula;
    private LocalDateTime Data_Renovacao;
    private Boolean Limitacao_Saude;
    private String Descricao_Limitacao;
}
