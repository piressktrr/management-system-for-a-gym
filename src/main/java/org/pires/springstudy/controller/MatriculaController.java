package org.pires.springstudy.controller;

import org.pires.springstudy.dtos.request.alunoRegister.MatriculaRequest;
import org.pires.springstudy.infrastructure.entity.enums.StatusMatricula;
import org.pires.springstudy.service.MatriculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping("/{matriculaId}/cancelamento")
    public ResponseEntity<?> cancelarMatriculaAluno(@PathVariable UUID matriculaId,
                                                    @RequestBody String motivoCancelamento) {


    }
}
