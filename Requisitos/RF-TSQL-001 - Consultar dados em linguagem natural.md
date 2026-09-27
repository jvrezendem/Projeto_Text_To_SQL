# [RF-TSQL-001] - Consultar dados em linguagem natural

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-001 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **o usuário enviar uma pergunta válida sobre os dados**, o **sistema** deve **processá-la pelas arquiteturas de Text-to-SQL definidas, obter consultas SQL do tipo `SELECT`, executá-las no banco de dados e apresentar os registros retornados**, garantindo **que cada resposta corresponda à pergunta realizada e permaneça identificada pela arquitetura de origem**.

## Por quê

Permitir que usuários consultem dados sem conhecer SQL reduz a barreira de acesso às informações. Sem esse recurso, a obtenção dos registros dependeria da escrita manual de consultas ou da intervenção de uma pessoa com conhecimento técnico.

## Critérios de aceite

- [ ] **Sucesso** — Dada uma pergunta válida sobre os dados, quando o usuário enviá-la, então o sistema deve processá-la pelas arquiteturas definidas, obter consultas `SELECT`, validá-las, executá-las e apresentar os registros retornados por cada arquitetura.
- [ ] **Fronteira** — Dada uma pergunta válida cuja consulta não retorne registros, quando ela for processada, então o sistema deve apresentar um resultado vazio sem indicar falha na execução.
- [ ] **Falha** — Dada a indisponibilidade de uma arquitetura ou do banco de dados, quando uma pergunta for processada, então o sistema deve identificar a origem da falha, informar que a consulta correspondente não pôde ser concluída e não deve apresentar dados incorretos.

## Regras e limites

- **Entradas/dados**: pergunta em linguagem natural fornecida pelo usuário, contexto e configurações necessárias para as arquiteturas de Text-to-SQL.
- **Invariantes**: apenas uma consulta previamente validada pode ser executada no banco de dados.
- **Exceções/fallback**: se alguma dependência falhar, o fluxo deve ser interrompido e o usuário deve receber uma mensagem de erro.
- **Fora do escopo**: criação, alteração ou exclusão de registros no banco de dados.

## Verificação e rastreabilidade

- **Método**: teste funcional e teste de integração.
- **Evidência esperada**: registro do teste demonstrando a pergunta, a consulta `SELECT` validada e os registros apresentados.
- **Objetivo/spec**: [[RF-TSQL-015 - Processar a pergunta nas três arquiteturas]].
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir o formato de apresentação dos registros retornados — **Responsável**: Produto — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.2 | 2026-09-27 | Generalização do fluxo para as três arquiteturas de Text-to-SQL | Codex |
| 0.1 | 2026-09-27 | Criação | Codex |
