# [RF-TSQL-004] - Gerar consulta com LLM sem contexto do banco de dados

## Requisito

> Quando **uma pergunta válida sobre os dados for recebida pela arquitetura sem contexto do banco de dados**, o **sistema** deve **enviar ao LLM a pergunta e as restrições de geração para obter uma consulta SQL do tipo `SELECT`**, garantindo **que o prompt não inclua a estrutura do banco de dados, seus registros, gabaritos ou consultas SQL de referência**.

## Por quê

A geração sem contexto do banco de dados permite avaliar a capacidade do LLM de traduzir a pergunta usando somente o texto do usuário e as restrições do sistema. Comparar essa abordagem com a geração contextualizada permite observar o efeito de fornecer tabelas, colunas e relacionamentos ao modelo.

## Regras e limites

- **Entradas/dados**: pergunta em linguagem natural, restrições de geração e configurações do LLM acessado pela API Groq.
- **Invariantes**: a geração deve utilizar LLM; o sistema não deve acrescentar ao prompt metadados, registros, gabaritos ou SQL de referência; a saída deve continuar sujeita à validação definida em [[RF-TSQL-009 - Permitir somente consultas SELECT]]; os resultados devem permanecer identificados como sem contexto do banco de dados.
- **Exceções/fallback**: falhas de comunicação com o LLM devem ser identificadas como falhas da integração; SQL inválido deve ser identificado como falha da consulta; o sistema não deve inserir contexto do banco nem substituir a resposta pela produzida pela outra arquitetura.
- **Fora do escopo**: fornecer a estrutura do banco ao LLM, treinar ou ajustar o modelo e executar comandos de alteração de dados ou de estrutura.

## Verificação e rastreabilidade

- **Método**: teste funcional, inspeção do prompt, teste de integração com o LLM e teste de execução da consulta.
- **Evidência esperada**: registros da chamada ao LLM contendo a pergunta e as restrições, sem contexto do banco, e testes com SQL válido, SQL inválido e resultado vazio.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-007 - Compor o prompt com a pergunta e as restrições]] e [[RF-TSQL-015 - Processar a pergunta nas duas arquiteturas]].
- **Tarefa/teste**: A definir.
