package org.pires.springstudy.service;

import org.pires.springstudy.infrastructure.entity.Plano;
import org.pires.springstudy.infrastructure.repository.PlanoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoService {
    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<Plano> getPlanos() {

        return planoRepository.findAll();
    }
}
