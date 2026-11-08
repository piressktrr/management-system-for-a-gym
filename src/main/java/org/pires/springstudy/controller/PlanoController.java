package org.pires.springstudy.controller;

import org.pires.springstudy.dtos.response.alunoRegister.AlunoResponse;
import org.pires.springstudy.service.PlanoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/planos")
public class PlanoController {
    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }


    @GetMapping("/{planoId}/alunos")
    public ResponseEntity<List<AlunoResponse>> searchAlunosByTipoPlano(@PathVariable("planoId") Long planoId) {

        return new ResponseEntity<>(planoService.findAlunosByPlanoId(planoId), HttpStatus.OK);
    }
}
