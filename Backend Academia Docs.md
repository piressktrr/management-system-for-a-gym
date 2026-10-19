# Backend Academia Docs

*Projeto de estudo · Sistema de gestão de alunos · Revisão de 17/10/2026*


O projeto de estudo é um sistema de gestão dentro de uma academia, voltado ao registro e à manutenção de alunos. É proposto para se ter o registro desses clientes do sistema, que será usado por recepcionistas do local para registrar, atualizar, cancelar e consultar matrículas, planos, cobranças, pagamentos, contratos e informações dos alunos.

## Escopo

### Fase 1

A primeira fase do projeto foca em construir essa gestão de forma simples e que funcione localmente, sem integração com ferramentas externas e sem consumo de APIs para o gerenciamento de regras ou envio de informações.

**Inclui: registro de alunos, matrículas, contratos, planos, cobranças, pagamentos (simulados) e comprovantes (apenas registro), rotina diária de inadimplência, lista de contratos bloqueados, resumo de contagens, login da recepcionista e logs.**

### Fase 2

Foca em biometria, acesso e pessoa extra (que virá junto com os planos a partir do trimestral). Há uso de APIs e ferramentas externas, mas sem o uso decidido de e-mail ou WhatsApp para o envio de comprovantes, por exemplo.

**A biometria apenas decide se o aluno está liberado ou não, e será simulada (sem leitor real). O acesso registra cada tentativa de entrada e o motivo da resposta.**

### Fora do Escopo (nos dias atuais)

Integração com e-mail, WhatsApp, pagamento real e cobrança de dívida. O desuso dessas funcionalidades se deve à complexidade que viria junto com a implementação, e que não é o foco deste objetivo de estudo.

**Também ficam fora: gestão de funcionários, departamentos e RH (contratos de funcionários, VR, VT e cargas horárias); biometria com leitor real; e o contato da academia sobre processo de dívida (o sistema apenas lista os devedores).**

### Simplificações do projeto

**Estas simplificações valem apenas para o estudo e não alteram as regras do negócio real:**

- **Pix e cartão são simulados por chamadas à API (Postman); o dinheiro é confirmado pela recepcionista.**
- **O comprovante e o aviso de bloqueio são apenas registros, sem envio real por e-mail ou WhatsApp.**
- **Na Fase 1, a aplicação e o banco rodam localmente; o banco em servidor externo é requisito da versão final.**

## Atores

Recepcionista = Administrador = Usuário (todos se referem à mesma coisa).

**Sistema (rotina diária): ator automático que cancela matrículas por inadimplência e registra os avisos de bloqueio.**

## Rotina Diária

*Da recepcionista.* A recepcionista age na consulta, alteração, registro e cancelamento dos alunos no sistema. A ideia é integrar o sistema à recepção da academia para controlar o fluxo de alunos e de pessoas interessadas em treinar, facilitando o cadastro delas e permitindo uma entrada rápida na academia.

*Do sistema (automática, uma vez por dia).* **Grava o cancelamento das matrículas com mais de 50 dias de vencimento sem pagamento e registra o aviso dos contratos que bloquearam naquele dia. A rotina pode rodar mais de uma vez no mesmo dia sem duplicar cancelamentos nem avisos.**

## Glossário

### Matrícula

Realização inicial do registro do aluno na academia. Capta as informações do aluno e adiciona as informações da própria matrícula: valor da taxa, data de realização e status. **Guarda também a descrição de saúde do aluno.**

| Situação | Taxa de matrícula |
| --- | --- |
| Aluno novo | R$ 60 |
| Aluno que cancelou e volta à academia tempos depois (por conta própria ou por inadimplência) | R$ 30 (metade do valor inicial) |
| Aluno com contrato bloqueado que volta para pagar e voltar ao normal | R$ 0 |

**Cada retorno após um cancelamento gera uma nova matrícula; as anteriores ficam no histórico. O aluno só tem uma matrícula ativa por vez.**

### Contrato

Formalização da matrícula. Aqui o aluno assina os termos da academia, a LGPD e as boas práticas que devem ser respeitadas no ambiente em que vai usufruir dos serviços. **Um contrato assinado não é editado: quando a matrícula é refeita, um novo contrato é formalizado e o anterior fica no histórico. O contrato guarda o texto assinado (versão padrão da LGPD) e a data da assinatura.**

### Plano

É o plano que o aluno escolhe para definir como será a cobrança da sua mensalidade. É dividido em 3 tipos:

| Tipo | Valor | Cobrado a cada | Pessoa extra |
| --- | --- | --- | --- |
| Mensal | R$ 160 | 1 mês | Não |
| Trimestral | R$ 420 | 3 meses | Sim (funcionalidade futura) |
| Semestral | R$ 600 | 6 meses | Sim (funcionalidade futura) |

**Os planos formam um catálogo. O aluno pode passar por vários ao longo do tempo (troca de plano) e o histórico é preservado; cada matrícula tem um único plano vigente.**

### Cobrança

Valor que o aluno deve pagar, conforme o seu plano, para ter acesso à academia. Armazena a data de vencimento, o status e o tipo. **Existem dois tipos: taxa (única, gerada ao registrar a matrícula) e mensalidade (gerada a cada ciclo do plano: 1, 3 ou 6 meses). O status é pendente ou paga; “vencida” é calculada comparando a data de vencimento com a data de hoje. Só existe uma cobrança pendente por vez.**

### Pagamento

É o ato de pagar a cobrança que chega ao aluno, e pode ser feito de diferentes formas: pix, cartão de crédito, cartão de débito e dinheiro. **Uma cobrança pode ter várias tentativas de pagamento (aprovadas ou recusadas); só a aprovada quita a cobrança. Pix e cartão são automáticos (simulados na Fase 1) e o dinheiro é confirmado pela recepcionista, que fica registrada no pagamento.**

### Comprovante

É a comprovação do pagamento da cobrança, gerada após o ato. É um documento congelado que contém informações da academia, do dia da cobrança (data, hora e valor), do aluno, a próxima renovação e um texto de agradecimento. **Só é gerado após um pagamento aprovado, e as informações são copiadas no momento da emissão (inclusive o CNPJ da academia) e não mudam depois.**

### Dívida

Cobrança que venceu e não foi paga. **Existe no máximo uma dívida por aluno por vez, porque a próxima cobrança só nasce depois que a anterior é paga. No cancelamento por inadimplência a dívida permanece e precisa ser quitada para o aluno voltar.**

### Ativo

Um aluno ativo é aquele que contém matrícula ativa, contrato não bloqueado e nenhuma cobrança **vencida**.

### Bloqueado

Um aluno bloqueado é aquele que contém matrícula ativa e contrato bloqueado, **isto é, com uma cobrança vencida há mais de 20 dias. Não entra na academia, e o bloqueio é reversível: o aluno volta ao normal ao pagar a dívida.**

### Cancelado

Um aluno cancelado é aquele cuja matrícula, e com ela o contrato e o plano, foi cancelada. Ato desejado pelo próprio aluno ou decorrente de inadimplência. **É irreversível: para voltar, o aluno refaz a matrícula e assina um novo contrato.**

### Aluno

**Pessoa registrada no sistema, com seus dados pessoais, um endereço e o histórico de matrículas.**

### Endereço

**Local de moradia do aluno. Pode ser compartilhado por vários alunos; cada aluno pertence a um único endereço.**

### Âncora

**Data de início do plano vigente. Todos os vencimentos são calculados a partir dela (início + n ciclos), e não a partir do vencimento anterior.**

### Cancelar o plano

**Termo padrão para encerrar a participação do aluno: encerra a matrícula, o contrato e o plano. Trocar de plano não é cancelar.**

### Rematrícula

**Nova matrícula de um aluno que já foi cancelado, acompanhada de um novo contrato.**

### Pessoa extra

**Pessoa que compartilha o acesso do aluno a partir do plano trimestral (Fase 2). Pertence ao aluno, tem data de escolha e só pode ser trocada depois de 1 mês.**

### Aviso

**Registro, feito pelo sistema no dia em que o contrato bloqueia, de que o aluno deve ser avisado para pagar (ou para cancelar o plano, se não pretende continuar).**

## Regras de Negócio

### Alunos, matrículas e contratos

- RN01. Cada aluno só tem uma matrícula ativa, um contrato por matrícula, um plano ativo e só pertence a um endereço. **As matrículas, os contratos e os planos anteriores ficam guardados no histórico.**
- RN02. Um endereço contém vários alunos.
- RN03. Cada contrato tem a descrição dos cuidados necessários na academia e as responsabilidades que o aluno tem que tomar, além da LGPD. **O aluno assina a versão padrão da LGPD.**
- RN04. **Nenhum registro é apagado: matrículas, contratos, planos, cobranças e pagamentos são cancelados ou encerrados, e os dados continuam guardados (inclusive para o retorno do aluno).**

### Planos, cobranças e pagamentos

- RN05. Os planos são divididos em mensal, trimestral e semestral.
- RN06. O vencimento é feito com base no início do plano + n ciclos, para evitar erros em fevereiro. **Quando o dia não existe no mês, o vencimento cai no último dia (início em 31/01: vence 28/02 e depois 31/03).**
- RN07. O próximo vencimento é a primeira data da sequência da âncora que seja posterior ao vencimento anterior e à data do pagamento. **Exemplos (plano mensal, âncora no dia 16): pagou em dia (16/11), próximo em 16/12; pagou atrasado em 20/11, próximo em 16/12; pagou a dívida em 04/01, próximo em 16/01.**
- RN08. A próxima cobrança só nasce quando a anterior é paga. **Por isso só existe uma cobrança pendente, e no máximo uma dívida, por aluno.**
- RN09. **Ao registrar a matrícula é gerada uma cobrança do tipo taxa, paga na hora (pix, cartão ou dinheiro).**
- RN10. Deve ser possível fazer o pagamento da cobrança via **pix,** crédito, débito ou dinheiro.
- RN11. **Um pagamento recusado não quita a cobrança; o aluno pode tentar de novo, e todas as tentativas ficam registradas.**
- RN12. A recepcionista pode confirmar o pagamento no sistema (caso seja em dinheiro). **Pix e cartão são confirmados automaticamente.**
- RN13. O comprovante do pagamento da cobrança deverá ir para o WhatsApp e o e-mail do aluno (ver “Simplificações do projeto”). **Ele só é gerado após um pagamento aprovado.**
- RN14. **Não há multa nem juros por atraso.**

### Inadimplência e acesso

- RN15. Se o aluno não pagar a cobrança do plano, ele não entra na academia.
- RN16. O aluno não pode acessar a academia sem estar ativo (contrato ativo, matrícula ativa e sem cobranças **vencidas**).
- RN17. Ciclo de inadimplência: o aluno é barrado no dia seguinte ao vencimento; o contrato bloqueia após 20 dias do vencimento (reversível pagando); a matrícula cancela após 50 dias do vencimento (irreversível). **Bloqueado: hoje > vencimento + 20 dias. Cancelada: hoje > vencimento + 50 dias. Exemplo (vencimento em 16/11): barrado em 17/11, bloqueado em 07/12 e cancelado em 06/01.**
- RN18. A academia irá enviar avisos para os contratos bloqueados, para avisar os alunos de irem pagar e cancelar seus planos caso não forem continuar. **O sistema registra o aviso automaticamente no dia em que o contrato bloqueia.**
- RN19. Há uma lista mostrando os contratos bloqueados.

### Cancelamento, retornos e taxas

- RN20. Um aluno só pode cancelar o plano se estiver com o plano pago em dia; caso contrário, não. O cancelamento a pedido do aluno não deixa dívida. **O cancelamento por inadimplência mantém a dívida.**
- RN21. Cada matrícula terá uma taxa de R$ 60 (para alunos novos).
- RN22. Se o aluno cancelar o plano e quiser retornar à academia, pagará metade da taxa da matrícula (porque terá que refazê-la), seja o cancelamento por conta própria ou por inadimplência.
- RN23. Se o aluno teve o contrato bloqueado e retorna para pagar e voltar ao normal, ele não paga taxa da matrícula.
- RN24. **Para voltar após um cancelamento, o aluno assina um novo contrato, refaz a matrícula (uma matrícula nova) e, se o cancelamento foi por inadimplência, quita antes a dívida. O ciclo recomeça na data da nova assinatura.**

### Troca de plano

- RN25. Um aluno pode mudar seu plano se já estiver em um (por exemplo, do mensal para o trimestral e vice-versa). Só é permitido com a cobrança em dia.
- RN26. O novo ciclo começa na data da troca, sem desconto, sem crédito e sem nova taxa.

### Pessoa extra e outras regras

- RN27. Um aluno que fechar o plano a partir do trimestral tem direito a 1 pessoa para compartilhar o acesso à academia.
- RN28. A pessoa que compartilha o acesso pode ser trocada a cada 1 mês e, se o aluno deixar a cobrança do plano vencer, ela também não entra na academia (porque o aluno não está ativo). **A data de escolha da pessoa é guardada e a troca só é liberada 1 mês depois dela; a pessoa trocada deixa de ter acesso. Quando o aluno regulariza o pagamento, a pessoa extra volta a entrar automaticamente.**
- RN29. A pessoa extra só vale a partir do trimestral e fica inativa se o aluno abaixar o plano ou cancelar. A reativação da pessoa extra reinicia a regra do mês.
- RN30. A descrição de saúde serve para a recepcionista anotar exigências de saúde que o aluno tenha e, posteriormente, repassá-las a um professor local, para que fiquem atentos.

## Requisitos Funcionais

Esses requisitos serão gerenciados pelo Usuário (recepcionista) para o gerenciamento dos alunos na academia; nesse sentido, podem ser vistos, alterados, cancelados ou registrados.

- RF01. Pesquisa de alunos por nome, plano, contratos e cobranças.
- RF02. Registrar aluno (matrícula, contrato, plano, cobrança, endereço, taxa).
- RF03. Cancelar o plano do aluno (encerra matrícula, contrato e plano).
- RF04. Alterar matrícula **(dados do aluno e descrição de saúde; uma matrícula cancelada não pode ser alterada)**.
- RF05. Alterar plano (troca de plano).
- RF06. Formalizar um novo contrato (o anterior fica no histórico).
- RF07. Resumo com as contagens (consulta, sem tabela própria): quantidade de alunos, de contratos ativos e bloqueados e de cobranças pendentes.
- RF08. Pagamento pode ser confirmado pela recepcionista (por poder ser feito em dinheiro).
- RF09. Registrar o envio do comprovante ao aluno (e-mail e WhatsApp).
- RF10. Rematricular aluno.
- RF11. Registrar tentativas de pagamento recusadas, com nova tentativa.
- RF12. Consultar o histórico de pagamentos, de planos e de matrículas do aluno.
- RF13. Lista de contratos bloqueados com indicador de aviso registrado ou não.
- RF14. Login e logout da recepcionista.
- RF15. Consultar os logs.
- RF16. **Registrar pagamento por pix, cartão de crédito ou cartão de débito (simulado na Fase 1).**
- RF17. **Gerar o comprovante automaticamente após um pagamento aprovado.**
- RF18. **Rotina diária: cancelar as matrículas com mais de 50 dias de vencimento e registrar os avisos dos contratos que bloquearam.**
- RF19. **Consultar a dívida do aluno.**
- RF20. **(Fase 2) Escolher e trocar a pessoa extra, com data de escolha e trava de 1 mês.**
- RF21. **(Fase 2) Simular o acesso por biometria: informar o identificador e receber “liberado” ou “negado” com o motivo, registrando a tentativa.**

## Requisitos Não-Funcionais

- RNF01. Toda alteração que a recepcionista fizer será armazenada em logs com horário, dados alterados, quem alterou e valor antes e depois. Os logs só podem ser acrescentados, nunca editados, e incluem a confirmação de pagamento em dinheiro. Os logs são captados por um mecanismo externo à API (não são uma entidade do sistema).
- RNF02. A descrição de saúde é armazenada como dado criptografado, com leitura restrita e log de quem leu. **Os logs não podem registrar a descrição de saúde em texto aberto.**
- RNF03. Os dados são armazenados em um banco de dados em um servidor, fora dos computadores locais.
- RNF04. A aplicação será web, com login para usuários administradores (recepcionistas). **Toda operação exige usuário autenticado.**
- RNF05. A credencial da recepcionista será armazenada em hash. **A credencial nunca é registrada em logs.**
- RNF06. **A rotina diária pode rodar mais de uma vez no mesmo dia sem duplicar cancelamentos nem avisos.**
- RNF07. **Datas e prazos (vencimentos, 20 e 50 dias) são calculados por dia de calendário, no fuso de Brasília.**
- RNF08. **(Fase 2) O identificador de biometria é tratado como dado sensível; nenhuma impressão digital real é armazenada.**

## Integridade dos Dados (simplificado)

| Categoria | Campos |
| --- | --- |
| Únicos | CPF, e-mail, login e **a combinação CEP + número + complemento (juntos, e não cada um isolado)** |
| Não nulos | Todos os campos, exceto os listados em “podem ser nulos” |
| Podem ser nulos | Motivo (matrícula); **data de cancelamento (matrícula); data de fim do plano vigente; usuário no pagamento automático; plano vinculado na cobrança do tipo taxa** |
| Gerados automaticamente | Todos os IDs (UUID) |
| **Unicidade condicional** | **No máximo uma matrícula ativa por aluno; um plano vigente por matrícula; uma cobrança pendente por aluno; uma pessoa extra ativa por aluno (Fase 2); um comprovante por pagamento aprovado e nenhum para pagamento recusado** |

## Estados

**Seção inteira adicionada nesta revisão, conforme combinado.**

Estado é a situação em que algo está e que muda com o tempo. Cada estado é *calculado* (obtido a partir de outros dados, sem coluna própria, e reversível) ou *guardado* (um fato que aconteceu, gravado com data, que não se desfaz).

| Entidade | Estado | Entra quando | Sai quando | Tipo |
| --- | --- | --- | --- | --- |
| Cobrança | Pendente | nasce, sem pagamento aprovado | existe pagamento aprovado | calculado |
| Cobrança | Paga | existe pagamento aprovado | nunca | calculado |
| Cobrança | Vencida (por cima de pendente) | pendente e hoje > vencimento | pagamento aprovado | calculado |
| Pagamento | Aprovado | recepcionista confirma (dinheiro) ou retorno simulado de pix ou cartão com sucesso | nunca (é um fato) | guardado |
| Pagamento | Recusado | a tentativa falha | nunca | guardado |
| Plano do aluno (vínculo matrícula–plano) | Vigente | aluno escolhe o plano (data de início) | troca de plano ou cancelamento (data de fim) | guardado (data de fim) |
| Matrícula | Ativa | matrícula criada | cancelamento | calculado |
| Matrícula | Cancelada | aluno cancela (só com cobrança em dia) ou rotina diária (vencimento + 50 dias) | nunca; voltar = matrícula nova | guardado (data e motivo) |
| Contrato | Bloqueado | existe cobrança pendente vencida há mais de 20 dias (matrícula ativa) | a dívida é paga | calculado |
| Contrato | Cancelado | a matrícula foi cancelada | nunca | calculado (vem da matrícula) |
| Contrato | Ativo | nenhum dos dois acima | bloqueia ou cancela | calculado |
| Aluno | Ativo (“pode entrar”) | matrícula ativa, contrato não bloqueado e sem cobrança vencida | cobrança vence, ou contrato bloqueia ou cancela | calculado |
| Pessoa extra (Fase 2) | Ativa / Inativa | ativa ao escolher plano trimestral ou superior; inativa por downgrade, cancelamento ou troca | reativação (nova data de escolha) | guardado |
| Pessoa extra (Fase 2) | Pode entrar | ativa e o aluno está ativo | o aluno deixa de estar ativo | calculado |
| Pessoa extra (Fase 2) | Troca liberada | hoje ≥ data de escolha + 1 mês | nova troca | calculado |

O comprovante não tem estado: ele existe se, e somente se, o pagamento foi aprovado. O aluno não tem estado guardado: “ativo” é a resposta à pergunta “pode entrar?”. A única coisa que roda sozinha é a rotina diária (cancelamentos e avisos); todo o resto é calculado na hora.

Exemplo: aluno do plano mensal com vencimento em 16/11/2026 que não paga.

| Dia | O que acontece | Entra? | Contrato | Matrícula | Dívida |
| --- | --- | --- | --- | --- | --- |
| 16/11 | vence a cobrança | sim | ativo | ativa | 0 |
| 17/11 | cobrança vencida | não | ativo | ativa | 1 |
| 06/12 | último dia do prazo de 20 dias | não | ativo | ativa | 1 |
| 07/12 | contrato bloqueia; aviso registrado | não | bloqueado | ativa | 1 |
| 05/01 | último dia antes do cancelamento | não | bloqueado | ativa | 1 |
| 06/01 | cancelamento gravado (motivo: inadimplência) | não | cancelado | cancelada | 1 |

