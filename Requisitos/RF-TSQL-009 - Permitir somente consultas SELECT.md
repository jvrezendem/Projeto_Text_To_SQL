# [RF-TSQL-009] - Permitir somente consultas SELECT

## Requisito

> Quando **qualquer uma das duas arquiteturas gerar um comando SQL**, o **sistema** deve **validá-lo e permitir a execução somente se for uma consulta de leitura do tipo `SELECT`**, garantindo **que comandos não permitidos sejam bloqueados antes de chegar ao banco de dados e produzam uma mensagem de erro**.

## Por quê

Nenhuma saída SQL produzida pelo LLM, com ou sem contexto do banco de dados, deve ser considerada segura sem validação. Executar comandos de escrita ou de definição de estrutura pode alterar, apagar ou expor indevidamente os dados do sistema.

## Regras e limites

- **Entradas/dados**: comando SQL produzido por qualquer arquitetura de Text-to-SQL.
- **Invariantes**: nenhum comando reprovado pela validação pode ser enviado ao banco de dados; o acesso usado para consulta deve possuir somente permissões de leitura.
- **Exceções/fallback**: se o comando não puder ser classificado com segurança, ele deve ser tratado como não permitido e bloqueado.
- **Fora do escopo**: correção automática de comandos SQL reprovados.

## Verificação e rastreabilidade

- **Método**: testes automatizados de segurança e integração.
- **Evidência esperada**: testes comprovando que consultas permitidas são executadas e que comandos proibidos não produzem acesso ao banco de dados.
- **Objetivo/spec**: A definir.
- **Tarefa/teste**: A definir.
