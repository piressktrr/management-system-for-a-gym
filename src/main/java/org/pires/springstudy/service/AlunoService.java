package org.pires.springstudy.service;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.pires.springstudy.dtos.request.AlunoRequest;
import org.pires.springstudy.dtos.request.MatriculaRequest;
import org.pires.springstudy.dtos.response.AlunoResponse;
import org.pires.springstudy.dtos.response.MatriculaResponse;
import org.pires.springstudy.dtos.response.RegisterResponse;
import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.Endereco;
import org.pires.springstudy.infrastructure.entity.Matricula;
import org.pires.springstudy.infrastructure.exceptions.ConflictByCreateAluno;
import org.pires.springstudy.infrastructure.repository.AlunoRepository;
import org.pires.springstudy.infrastructure.repository.MatriculaRepository;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final MatriculaRepository matriculaRepository;

    public AlunoService(AlunoRepository alunoRepository, MatriculaRepository matriculaRepository) {
        this.alunoRepository = alunoRepository;
        this.matriculaRepository = matriculaRepository;
    }

    public RegisterResponse saveAluno(AlunoRequest alunoRequest, MatriculaRequest matriculaRequest) {
        var cpfSemCaracteres = alunoRequest.CPF().replaceAll("\\D", "");
        var telefoneSemCaracteres = alunoRequest.telefone().replaceAll("\\D", "");
        var cepSemCaracteres = alunoRequest.endereco().cep().replaceAll("\\D", "");

        checkCpf(cpfSemCaracteres);
        checkEmail(alunoRequest.email()); //  ver se isso ta certo ou ta overenginering com boilerplate la em baixo
        checkTelefone(telefoneSemCaracteres);

        var endereco = new Endereco(
                alunoRequest.endereco().rua(),
                alunoRequest.endereco().numero(),
                alunoRequest.endereco().complemento(),
                alunoRequest.endereco().bairro(),
                alunoRequest.endereco().municipio(),
                alunoRequest.endereco().estado(),
                alunoRequest.endereco().pais(),
                cepSemCaracteres);


        var aluno = alunoRepository.save(new Aluno(endereco,
                cpfSemCaracteres,
                alunoRequest.nome(),
                alunoRequest.sobrenome(),
                alunoRequest.sexo(),
                alunoRequest.dataNascimento(),
                telefoneSemCaracteres,
                alunoRequest.email()));

        var matricula = saveMatricula(matriculaRequest, aluno);

        return new RegisterResponse(new AlunoResponse(aluno.getNome(), aluno.getSobrenome(), aluno.getSexo()),
                matricula);

    }

    public List<AlunoResponse> searchByNumComplCep(@NumberFormat @NotNull Integer numero,
                                                   @NotNull String complemento,
                                                   @Pattern(regexp = "^\\d{5}-?\\d{3}$\n") @NotNull String cep){


        List<AlunoResponse> alunoResponses = new ArrayList<>();

        alunoRepository.findAlunoByEndereco_NumeroAndEndereco_ComplementoAndEndereco_Cep(
                numero, complemento, cep.replaceAll("\\D", "")).
                forEach(aluno ->
                { alunoResponses.add(new AlunoResponse(aluno.getNome(), aluno.getSobrenome(), aluno.getSexo())); });

        return alunoResponses;
    }



    private MatriculaResponse saveMatricula(MatriculaRequest matriculaRequest, Aluno aluno) {


        var matricula = matriculaRepository.save(new Matricula(matriculaRequest.descricao(),
                matriculaRequest.dataMatricula(),
                matriculaRequest.valorMatricula(),
                matriculaRequest.status(),
                matriculaRequest.motivoDesligamento(),
                matriculaRequest.dataDesligamento()));

        return new MatriculaResponse(matricula.getDataMatricula(), matricula.getValorMatricula(), matricula.getStatus());
    }

    public void checkCpf(String cpf) {
        if (alunoRepository.existsByCPF(cpf)) {
            throw new ConflictByCreateAluno("Erro na hora de salvar aluno, cheque as credenciais e tente novamente!");
            // aqui eu nao quero falar q o erro foi no CPF, para não dar informações a pessoas maliciosas
        }
    }
    // provavelmente esses dois de baixo sao boilerplate, ver dps se da pra elimina-los
    public void checkEmail(String email) {
        if (alunoRepository.existsByEmail(email)) {
            throw new ConflictByCreateAluno("Erro na hora de salvar aluno, cheque as credenciais e tente novamente!");

        }
    }

    public void checkTelefone(String telefone) {
        if (alunoRepository.existsByTelefone(telefone)) {
            throw new ConflictByCreateAluno("Erro na hora de salvar aluno, cheque as credenciais e tente novamente!");

        }
    }
}
