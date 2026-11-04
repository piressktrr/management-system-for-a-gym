package org.pires.springstudy.service;

import org.pires.springstudy.infrastructure.repository.ContratoRepository;
import org.springframework.stereotype.Service;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;

    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }
}
