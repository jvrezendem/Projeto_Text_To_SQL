# [RF-TSQL-007] - Compor o prompt com a pergunta e as restrições

## Requisito

> Quando **uma das duas arquiteturas processar uma pergunta válida**, o **gerador de prompt** deve **compor o prompt com a pergunta feita pelo usuário e as restrições de geração, acrescentando o contexto autorizado do banco somente na arquitetura contextualizada**, garantindo **que os elementos estejam claramente identificados e que o conteúdo da pergunta não possa substituir as informações controladas pelo sistema**.

## Por quê

As duas arquiteturas precisam receber a mesma intenção do usuário e as restrições que orientam a geração da consulta. A diferença entre os prompts é a inclusão da estrutura do banco somente na arquitetura com contexto. Separar a pergunta das informações controladas pelo sistema permite comparar as abordagens e evita que o conteúdo informado pelo usuário altere as regras obrigatórias.

## Regras e limites

- **Entradas/dados**: pergunta em linguagem natural, identificação da arquitetura, restrições de geração e, somente na arquitetura contextualizada, contexto autorizado do banco de dados.
- **Invariantes**: a pergunta não pode modificar as restrições nem o contexto controlado pelo sistema; as duas arquiteturas devem usar as mesmas restrições de leitura e respeitar [[RF-TSQL-009 - Permitir somente consultas SELECT]]; o contexto deve ser incluído somente na arquitetura descrita em [[RF-TSQL-003 - Fornecer contexto do banco de dados ao LLM]] e omitido na arquitetura descrita em [[RF-TSQL-004 - Gerar consulta com LLM sem contexto do banco de dados]]; perguntas vazias devem respeitar [[RF-TSQL-002 - Ignorar pergunta vazia]].
- **Exceções/fallback**: se as restrições obrigatórias não puderem ser carregadas, o prompt afetado não deve ser enviado ao LLM; falhas no carregamento do contexto devem afetar somente a arquitetura que depende dele.
- **Fora do escopo**: obtenção e atualização dos metadados do banco, documentadas em [[RF-TSQL-003 - Fornecer contexto do banco de dados ao LLM]], e treinamento ou ajuste fino do LLM.

## Verificação e rastreabilidade

- **Método**: teste funcional, inspeção do prompt e teste de segurança.
- **Evidência esperada**: um prompt sem contexto contendo pergunta e restrições e um prompt contextualizado contendo também a estrutura autorizada do banco; testes demonstrando a separação entre o texto do usuário e as informações controladas pelo sistema.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-003 - Fornecer contexto do banco de dados ao LLM]], [[RF-TSQL-009 - Permitir somente consultas SELECT]] e [[RF-TSQL-015 - Processar a pergunta nas duas arquiteturas]].
- **Tarefa/teste**: A definir.
