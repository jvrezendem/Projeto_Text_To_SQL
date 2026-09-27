# [RF-TSQL-009] - Permitir somente consultas SELECT

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-009 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **o LLM gerar um comando SQL**, o **sistema** deve **validá-lo e permitir a execução somente se for uma consulta de leitura do tipo `SELECT`**, garantindo **que comandos não permitidos sejam bloqueados antes de chegar ao banco de dados e produzam uma mensagem de erro**.

## Por quê

Uma saída gerada por LLM não deve ser considerada segura sem validação. Executar comandos de escrita ou de definição de estrutura pode alterar, apagar ou expor indevidamente os dados do sistema.

## Critérios de aceite

- [ ] **Sucesso** — Dada uma consulta `SELECT` válida e sem operações proibidas, quando a validação for realizada, então o sistema deve autorizá-la para execução no banco de dados.
- [ ] **Fronteira** — Dada uma saída que contenha múltiplos comandos, comentários maliciosos ou uma operação proibida combinada com `SELECT`, quando a validação for realizada, então todo o conteúdo deve ser bloqueado antes de chegar ao banco de dados.
- [ ] **Falha** — Dada uma saída contendo `INSERT`, `UPDATE`, `DELETE`, `DROP`, `ALTER`, `CREATE`, `TRUNCATE` ou qualquer comando diferente de uma consulta permitida, quando a validação for realizada, então o sistema deve impedir a execução e emitir uma mensagem de erro.

## Regras e limites

- **Entradas/dados**: comando SQL produzido pelo LLM.
- **Invariantes**: nenhum comando reprovado pela validação pode ser enviado ao banco de dados; o acesso usado para consulta deve possuir somente permissões de leitura.
- **Exceções/fallback**: se o comando não puder ser classificado com segurança, ele deve ser tratado como não permitido e bloqueado.
- **Fora do escopo**: correção automática de comandos SQL reprovados.

## Verificação e rastreabilidade

- **Método**: testes automatizados de segurança e integração.
- **Evidência esperada**: testes comprovando que consultas permitidas são executadas e que comandos proibidos não produzem acesso ao banco de dados.
- **Objetivo/spec**: A definir.
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir quais construções de leitura além de `SELECT` simples serão aceitas, como CTEs iniciadas por `WITH` — **Responsável**: Arquitetura — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.2 | 2026-09-27 | Reclassificação de regra para requisito funcional e atualização do identificador | Codex |
| 0.1 | 2026-09-27 | Criação | Codex |
