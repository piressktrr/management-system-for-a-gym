package org.pires.springstudy.controller;

import org.pires.springstudy.dtos.PlanoResponseSearch;
import org.pires.springstudy.dtos.response.alunoRegister.AlunoResponse;
import org.pires.springstudy.dtos.response.alunoRegister.PlanoResponse;
import org.pires.springstudy.dtos.response.alunoSearch.AlunoResponseSearch;
import org.pires.springstudy.infrastructure.entity.Plano;
import org.pires.springstudy.service.MatriculaPlanoService;
import org.pires.springstudy.service.PlanoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/planos")
public class PlanoController {
    private final PlanoService planoService;
    private final MatriculaPlanoService matriculaPlanoService;

    public PlanoController(PlanoService planoService, MatriculaPlanoService matriculaPlanoService) {
        this.planoService = planoService;
        this.matriculaPlanoService = matriculaPlanoService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<PlanoResponseSearch>> getPlanos() {
        return new ResponseEntity<>(planoService.getPlanos(), HttpStatus.FOUND);

    }

    @GetMapping("/{planoId}/alunos")
    public ResponseEntity<List<AlunoResponseSearch>> searchAlunosByTipoPlano(@PathVariable("planoId") Long planoId) {

        return new ResponseEntity<>(matriculaPlanoService.findAlunosByPlanoId(planoId), HttpStatus.OK);
    }
}
