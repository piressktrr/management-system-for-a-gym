package org.pires.springstudy.service;

import org.pires.springstudy.dtos.request.alunoRegister.ContratoRequest;
import org.pires.springstudy.infrastructure.entity.Contrato;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.pires.springstudy.infrastructure.repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;

    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public Contrato saveContrato(ContratoRequest contrato, Matricula matricula) {
        return contratoRepository.save(
                new Contrato(
                        matricula,
                        "Vou pedir pro claude me gerar uma descriçao ficticia para por aqui depois",
                        LocalDateTime.now(),
                        contrato.assinatura()
                ));
    }
}
