# [RF-TSQL-003] - Fornecer contexto do banco de dados ao LLM

## Requisito

> Quando **uma pergunta sobre os dados for enviada para tradução em SQL pela arquitetura com contexto do banco de dados**, o **gerador de prompt** deve **fornecer ao LLM o contexto autorizado de organização do banco de dados, incluindo as estruturas necessárias para interpretar a pergunta**, garantindo **que a consulta `SELECT` gerada utilize tabelas, colunas e relacionamentos existentes e permitidos**.

## Por quê

O LLM precisa conhecer a estrutura do banco para relacionar corretamente os termos usados pelo usuário com os elementos disponíveis no modelo de dados. Sem esse contexto, a consulta pode referenciar tabelas ou colunas inexistentes, produzir resultados incorretos ou tentar acessar estruturas que o usuário não pode consultar.

## Regras e limites

- **Entradas/dados**: pergunta do usuário e metadados autorizados do banco, incluindo nomes e descrições de esquemas, tabelas, colunas, tipos e relacionamentos necessários.
- **Invariantes**: o contexto deve ser incluído somente na arquitetura com contexto do banco de dados; o LLM deve receber somente metadados de estruturas que possam ser consultadas pelo usuário; credenciais e valores sensíveis armazenados nas tabelas não devem ser incluídos no contexto; a arquitetura sem contexto deve seguir [[RF-TSQL-004 - Gerar consulta com LLM sem contexto do banco de dados]].
- **Exceções/fallback**: se não houver contexto suficiente para interpretar a pergunta com segurança, o sistema deve interromper o fluxo contextualizado sem executar sua consulta no banco de dados, preservando o resultado da arquitetura sem contexto.
- **Fora do escopo**: treinamento ou ajuste fino do LLM, inclusão dos registros do banco no contexto e alteração da estrutura do banco de dados.

## Verificação e rastreabilidade

- **Método**: teste funcional, inspeção do prompt e teste de integração.
- **Evidência esperada**: registro de teste demonstrando que o prompt contém somente o contexto autorizado necessário e que a consulta gerada referencia estruturas existentes.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]].
- **Tarefa/teste**: A definir.
