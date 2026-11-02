package org.pires.springstudy.service;

import lombok.extern.slf4j.Slf4j;
import org.pires.springstudy.dtos.request.AlunoRequest;
import org.pires.springstudy.dtos.response.AlunoResponse;
import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.Endereco;
import org.pires.springstudy.infrastructure.exceptions.ConflictByCreateAluno;
import org.pires.springstudy.infrastructure.repository.AlunoRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponse saveAluno(AlunoRequest alunoRequest) {
        checkCpf(alunoRequest.CPF());
        checkEmail(alunoRequest.email()); //  ver se isso ta certo ou ta overenginering com boilerplate la em baixo
        checkTelefone(alunoRequest.telefone());

        var endereco = new Endereco(
                alunoRequest.endereco().rua(),
                alunoRequest.endereco().numero(),
                alunoRequest.endereco().complemento(),
                alunoRequest.endereco().bairro(),
                alunoRequest.endereco().municipio(),
                alunoRequest.endereco().estado(),
                alunoRequest.endereco().pais(),
                alunoRequest.endereco().cep());

        var aluno = alunoRepository.save(new Aluno(endereco, alunoRequest.CPF(), alunoRequest.nome(),
                alunoRequest.sobrenome(), alunoRequest.sexo(), alunoRequest.dataNascimento(), alunoRequest.telefone(),
                alunoRequest.email()));

        return new AlunoResponse(aluno.getNome(), aluno.getSobrenome(), aluno.getSexo());

    }

//    public AlunoResponse atualizarAluno(AlunoRequest alunoRequest) {}
//
//    public AlunoResponse desligarAluno(AlunoRequest alunoRequest) {}

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
