package org.pires.springstudy.service;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.pires.springstudy.dtos.RegisterRequest;
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
import org.pires.springstudy.infrastructure.repository.EnderecoRepository;
import org.pires.springstudy.infrastructure.repository.MatriculaRepository;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final EnderecoRepository enderecoRepository;

    public AlunoService(AlunoRepository alunoRepository, EnderecoRepository enderecoRepository) {
        this.alunoRepository = alunoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public AlunoResponse saveAluno(AlunoRequest alunoRequest) {

        var cpfSemCaracteres = alunoRequest.CPF().replaceAll("\\D", "");
        var telefoneSemCaracteres = alunoRequest.telefone().replaceAll("\\D", "");
        var cepSemCaracteres = alunoRequest.endereco().cep().replaceAll("\\D", "");
        var complementoNormalizado = alunoRequest.endereco().complemento().replace(" ", "");


        checkCpf(cpfSemCaracteres); // checa se ja tem cpf cadastrado
        checkTelefone(telefoneSemCaracteres); // checa se ja tem telefone cadastrado
        checkEmail(alunoRequest.email());  // checa se ja tem email cadastrado
        var en = searchByNumComplCep(alunoRequest, cepSemCaracteres, complementoNormalizado); // se houver endereço, retorna o mesmo endereço

        // se nao cria um novo pro aluno

        // checa se essa combinação ja ta cadastrada na tabela de endereço
        // o problema aqui é que caso a pessoa nao passe um complemento e ter um mesmo endereço mas com o complemento salvo
        // a logica pode interpretar como se fossem diferentes e deixar passar, tenho q arrumar isso

//        var endereco = new Endereco(
//                alunoRequest.endereco().rua(),
//                alunoRequest.endereco().numero(),
//                alunoRequest.endereco().complemento(),
//                alunoRequest.endereco().bairro(),
//                alunoRequest.endereco().municipio(),
//                alunoRequest.endereco().estado(),
//                alunoRequest.endereco().pais(),
//                cepSemCaracteres);


        var aluno = alunoRepository.save(new Aluno(en,
                cpfSemCaracteres,
                alunoRequest.nome(),
                alunoRequest.sobrenome(),
                alunoRequest.sexo(),
                alunoRequest.dataNascimento(),
                telefoneSemCaracteres,
                alunoRequest.email()));


        return new AlunoResponse(aluno.getNome(), aluno.getSobrenome(), aluno.getSexo());

    }

    public Endereco searchByNumComplCep(AlunoRequest alunoRequest, String cepSemCaracteres, String complementoNormalizado) {


            return enderecoRepository.findByNumeroAndComplementoAndCep(
                            alunoRequest.endereco().numero(),
                            complementoNormalizado, //  por enquanto ele ta aceitando ""
                            cepSemCaracteres)

                    .orElse(new Endereco(alunoRequest.endereco().rua(),
                            alunoRequest.endereco().numero(),
                            complementoNormalizado,
                            alunoRequest.endereco().bairro(),
                            alunoRequest.endereco().municipio(),
                            alunoRequest.endereco().estado(),
                            alunoRequest.endereco().pais(),
                            cepSemCaracteres));


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
