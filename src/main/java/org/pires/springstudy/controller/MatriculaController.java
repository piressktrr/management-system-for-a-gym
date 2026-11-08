package org.pires.springstudy.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.pires.springstudy.dtos.request.MatriculaAlunoDadosRequest;
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



    @PostMapping("/{matriculaId}/cancelar")
    public ResponseEntity<Void> cancelarMatriculaAluno(@PathVariable UUID matriculaId,
                                                    @RequestBody @Valid @NotBlank String motivoCancelamento) {

        matriculaService.cancelarMatricula(matriculaId, motivoCancelamento);


        // porque o cancelamento foi concluido e não há nada a devolver
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/{matriculaId}/atualizar")
    public ResponseEntity<Void> alterarMatricula(
            @PathVariable UUID matriculaId,
            @RequestBody MatriculaAlunoDadosRequest matriculaAlunoDadosRequest) {

        matriculaService.alterarDadosMatricula(matriculaId, matriculaAlunoDadosRequest);

        return ResponseEntity.noContent().build();
    }
}
