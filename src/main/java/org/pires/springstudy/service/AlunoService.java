package org.pires.springstudy.service;

import lombok.extern.slf4j.Slf4j;
import org.pires.springstudy.dtos.request.alunoRegister.AlunoRequest;
import org.pires.springstudy.dtos.response.alunoRegister.AlunoResponse;
import org.pires.springstudy.dtos.response.alunoSearch.AlunoResponseSearch;
import org.pires.springstudy.infrastructure.entity.Aluno;
import org.pires.springstudy.infrastructure.entity.Cobranca;
import org.pires.springstudy.infrastructure.entity.Endereco;
import org.pires.springstudy.infrastructure.entity.enums.StatusCobranca;
import org.pires.springstudy.infrastructure.exceptions.ConflictByCreateAluno;
import org.pires.springstudy.infrastructure.repository.AlunoRepository;
import org.pires.springstudy.infrastructure.repository.CobrancaRepository;
import org.pires.springstudy.infrastructure.repository.EnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final EnderecoRepository enderecoRepository;
    private final CobrancaRepository cobrancaRepository;

    public AlunoService(AlunoRepository alunoRepository, EnderecoRepository enderecoRepository, CobrancaRepository cobrancaRepository) {
        this.alunoRepository = alunoRepository;
        this.enderecoRepository = enderecoRepository;
        this.cobrancaRepository = cobrancaRepository;
    }

    public Aluno saveAluno(AlunoRequest alunoRequest) {

        var cpfSemCaracteres = alunoRequest.CPF().replaceAll("\\D", "");
        var telefoneSemCaracteres = alunoRequest.telefone().replaceAll("\\D", "");
        var cepSemCaracteres = alunoRequest.endereco().cep().replaceAll("\\D", "");
        var complementoNormalizado = alunoRequest.endereco().complemento().replace(" ", "");


        checkCpf(cpfSemCaracteres); // checa se ja tem cpf cadastrado
        checkTelefone(telefoneSemCaracteres); // checa se ja tem telefone cadastrado
        checkEmail(alunoRequest.email());  // checa se ja tem email cadastrado
        var endereco = searchByNumComplCep(alunoRequest, cepSemCaracteres, complementoNormalizado);
        // se houver endereço, retorna o mesmo endereço
        // se nao cria um novo pro aluno


        var aluno = alunoRepository.save(new Aluno(endereco,
                cpfSemCaracteres,
                alunoRequest.nome(),
                alunoRequest.sobrenome(),
                alunoRequest.sexo(),
                alunoRequest.dataNascimento(),
                telefoneSemCaracteres,
                alunoRequest.email()));


        return aluno;
//        return new AlunoResponse(aluno.getNome(), aluno.getSobrenome(), aluno.getSexo());

    }

    public List<AlunoResponseSearch> searchAlunoByName(@RequestParam String nome) {

        return alunoRepository.findAllByNome(nome)
                .stream()
                .map(aluno -> new AlunoResponseSearch(
                        aluno.getId(),
                        aluno.getNome(),
                        aluno.getSobrenome(),
                        aluno.getSexo()
                ))
                .toList();

    }

    public List<AlunoResponse> searchAlunoByStatusCobranca(StatusCobranca statusCobranca) {

        return cobrancaRepository.findAlunosByStatusCobranca(statusCobranca)
                .stream()
                .map(a -> new AlunoResponse(a.getNome(), a.getSobrenome(), a.getSexo()))
                .toList();
    }

    public List<AlunoResponse> searchAlunoByCobrancaVencida(){


        return cobrancaRepository.findAlunosByStatusCobrancaPendenteAndDataVencimentoBeforeActualDate(
                StatusCobranca.PENDENTE, LocalDate.now(ZoneId.of("America/Sao_Paulo"))) // aplica o fuso do horario de brasilia
                .stream()
                .map(a -> new AlunoResponse(a.getNome(), a.getSobrenome(), a.getSexo()))
                .toList();
    }

    private Endereco searchByNumComplCep(AlunoRequest alunoRequest, String cepSemCaracteres, String complementoNormalizado) {


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

    private void checkCpf(String cpf) {
        if (alunoRepository.existsByCPF(cpf)) {
            throw new ConflictByCreateAluno("Erro na hora de salvar aluno, cheque as credenciais e tente novamente!");
            // aqui eu nao quero falar q o erro foi no CPF, para não dar informações a pessoas maliciosas
        }
    }

    // provavelmente esses dois de baixo sao boilerplate, ver dps se da pra elimina-los
    private void checkEmail(String email) {
        if (alunoRepository.existsByEmail(email)) {
            throw new ConflictByCreateAluno("Erro na hora de salvar aluno, cheque as credenciais e tente novamente!");

        }
    }

    private void checkTelefone(String telefone) {
        if (alunoRepository.existsByTelefone(telefone)) {
            throw new ConflictByCreateAluno("Erro na hora de salvar aluno, cheque as credenciais e tente novamente!");

        }
    }
}
