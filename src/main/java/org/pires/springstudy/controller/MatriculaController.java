package org.pires.springstudy.controller;

import org.pires.springstudy.dtos.request.alunoRegister.MatriculaRequest;
import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;
import org.pires.springstudy.service.MatriculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }


}
