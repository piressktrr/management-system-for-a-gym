package org.pires.springstudy.service;

import org.pires.springstudy.dtos.request.alunoRegister.CobrancaRequest;
import org.pires.springstudy.infrastructure.entity.Cobranca;
import org.pires.springstudy.infrastructure.entity.MatriculaPlano;
import org.pires.springstudy.infrastructure.repository.CobrancaRepository;
import org.springframework.stereotype.Service;

@Service
public class CobrancaService {

    private final CobrancaRepository cobrancaRepository;

    public CobrancaService(CobrancaRepository cobrancaRepository) {
        this.cobrancaRepository = cobrancaRepository;
    }

    public Cobranca saveCobranca(CobrancaRequest cobranca, MatriculaPlano matriculaPlano) {

        return cobrancaRepository.save(
                new Cobranca(matriculaPlano,
                        cobranca.dataDeVencimento(),
                        cobranca.valorAPagar(),
                        cobranca.tipoCobranca(),
                        cobranca.statusCobranca())
        );
    }
}
