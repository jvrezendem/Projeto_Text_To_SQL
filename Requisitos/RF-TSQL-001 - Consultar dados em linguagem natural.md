# [RF-TSQL-001] - Consultar dados em linguagem natural

## Requisito

> Quando **o usuário enviar uma pergunta válida sobre os dados**, o **sistema** deve **processá-la pelas duas arquiteturas de Text-to-SQL, LLM sem contexto e LLM com contexto do banco de dados, obter consultas SQL do tipo `SELECT`, executá-las no banco de dados e apresentar os registros retornados**, garantindo **que cada resposta corresponda à pergunta realizada e permaneça identificada pela arquitetura de origem**.

## Por quê

Permitir que usuários consultem dados sem conhecer SQL reduz a barreira de acesso às informações. Sem esse recurso, a obtenção dos registros dependeria da escrita manual de consultas ou da intervenção de uma pessoa com conhecimento técnico.

## Regras e limites

- **Entradas/dados**: pergunta em linguagem natural fornecida pelo usuário, contexto e configurações necessárias para as arquiteturas de Text-to-SQL.
- **Invariantes**: cada arquitetura pode executar somente sua consulta previamente validada, preservando a identificação da origem dos resultados.
- **Exceções/fallback**: se alguma dependência falhar, o fluxo da arquitetura afetada deve ser interrompido e o usuário deve receber uma mensagem de erro, preservando os resultados concluídos pela outra arquitetura conforme [[RF-TSQL-015 - Processar a pergunta nas duas arquiteturas]].
- **Fora do escopo**: criação, alteração ou exclusão de registros no banco de dados.

## Verificação e rastreabilidade

- **Método**: teste funcional e teste de integração.
- **Evidência esperada**: registro do teste demonstrando a pergunta, a consulta `SELECT` validada e os registros apresentados.
- **Objetivo/spec**: [[RF-TSQL-015 - Processar a pergunta nas duas arquiteturas]].
- **Tarefa/teste**: A definir.
