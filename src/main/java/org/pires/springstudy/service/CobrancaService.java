package org.pires.springstudy.service;

import org.pires.springstudy.dtos.request.alunoRegister.CobrancaRequest;
import org.pires.springstudy.infrastructure.entity.Cobranca;
import org.pires.springstudy.infrastructure.entity.MatriculaPlano;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.pires.springstudy.infrastructure.repository.CobrancaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class CobrancaService {

    private final CobrancaRepository cobrancaRepository;

    public CobrancaService(CobrancaRepository cobrancaRepository) {
        this.cobrancaRepository = cobrancaRepository;
    }

    public Cobranca saveCobranca(CobrancaRequest cobranca, MatriculaPlano matriculaPlano) {

        return cobrancaRepository.save( // acho que ta certo agora, mas provavelmente vou ter que passar um local date aqui mesmo
                new Cobranca(matriculaPlano,
                        LocalDateTime.of(
                                LocalDateTime.now().getYear(),
                                LocalDateTime.now().getMonth().plus(1),
                                LocalDateTime.now().getDayOfMonth(),
                                LocalDateTime.now().getHour(),
                                LocalDateTime.now().getMinute()),
                        matriculaPlano.getPlano().getValorPlano(),
                        cobranca.tipoCobranca(),
                        StatusCobranca.PENDENTE)
        );
    }
}
