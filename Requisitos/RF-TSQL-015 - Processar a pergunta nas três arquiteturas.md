# [RF-TSQL-015] - Processar a pergunta nas três arquiteturas

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-015 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **o usuário enviar uma pergunta válida sobre os dados**, o **sistema** deve **processar a mesma pergunta nas três arquiteturas de Text-to-SQL e retornar separadamente a resposta de cada uma**, garantindo **que todos os resultados estejam associados à mesma pergunta, à mesma versão dos dados e à arquitetura que os produziu**.

## Por quê

O processamento obrigatório pelas três arquiteturas permite ao usuário comparar diretamente as abordagens e avaliar diferenças de interpretação e acurácia. Sem a execução conjunta, seria necessário repetir manualmente a pergunta e controlar as condições de cada execução.

## Critérios de aceite

- [ ] **Sucesso** — Dada uma pergunta válida, quando o usuário enviá-la, então o sistema deve encaminhá-la às três arquiteturas, aguardar o encerramento de cada processamento e retornar três resultados identificados pela arquitetura de origem.
- [ ] **Fronteira** — Dado que uma arquitetura retorne um conjunto vazio, quando o processamento terminar, então esse conjunto deve ser apresentado como o resultado válido daquela arquitetura, sem afetar ou substituir as respostas das demais.
- [ ] **Falha** — Dado que uma arquitetura falhe, fique indisponível ou exceda o tempo permitido, quando as execuções terminarem, então o sistema deve identificar o erro dessa arquitetura e ainda retornar os resultados concluídos pelas demais, sem inventar ou reutilizar uma resposta.

## Regras e limites

- **Entradas/dados**: pergunta válida do usuário, mesma versão do banco de dados e configurações necessárias para tradução direta com LLM, LLM com contexto do banco e geração sem LLM por templates de escrita e/ou expressões regulares.
- **Invariantes**: as três arquiteturas devem receber a mesma pergunta e consultar a mesma fotografia dos dados; cada resultado deve conservar a identificação de sua arquitetura; resultados não podem ser combinados, substituídos ou atribuídos a outra arquitetura; toda consulta deve respeitar [[RF-TSQL-009 - Permitir somente consultas SELECT]]; perguntas vazias devem respeitar [[RF-TSQL-002 - Ignorar pergunta vazia]].
- **Exceções/fallback**: a falha de uma arquitetura não deve cancelar automaticamente as respostas já concluídas pelas outras; a interface deve distinguir resultado vazio, erro e processamento pendente.
- **Fora do escopo**: permitir que o usuário selecione somente uma arquitetura, escolher automaticamente a melhor resposta e consolidar os três resultados em uma resposta única.

## Verificação e rastreabilidade

- **Método**: teste funcional, teste de integração e teste de contrato.
- **Evidência esperada**: teste demonstrando uma única pergunta encaminhada às três arquiteturas e uma resposta contendo três resultados identificados; testes adicionais com resultado vazio e falha isolada de uma arquitetura.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-003 - Fornecer contexto do banco de dados ao LLM]], [[RF-TSQL-004 - Gerar consulta com templates e regex]], [[RF-TSQL-012 - Enviar dados para a interface gráfica]] e [[RF-TSQL-014 - Exibir o resultado obtido e o gabarito]].
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir se as três arquiteturas serão executadas em paralelo ou sequencialmente — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Definir o tempo limite individual e total das execuções — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Definir se os resultados serão apresentados à medida que forem concluídos ou somente após o encerramento das três execuções — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir a ordem de apresentação das três arquiteturas na interface — **Responsável**: Produto — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.2 | 2026-09-27 | Definição da terceira arquitetura como templates e regex sem uso de LLM | Codex |
| 0.1 | 2026-09-27 | Criação | Codex |
