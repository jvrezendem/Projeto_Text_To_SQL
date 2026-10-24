# [RF-TSQL-008] - Conectar ao banco de dados PostgreSQL

## Requisito

> Quando **o sistema precisar acessar a estrutura ou os registros utilizados pelo TextToSQL**, o **componente de acesso a dados** deve **estabelecer uma conexão autenticada com um banco de dados PostgreSQL configurado**, garantindo **que somente operações autorizadas de leitura sejam realizadas e que as credenciais não sejam expostas**.

## Por quê

A conexão com PostgreSQL é necessária para consultar os metadados do esquema e executar as consultas `SELECT` geradas pelo sistema. Sem uma conexão válida e protegida, o TextToSQL não pode apresentar a estrutura do banco nem retornar os registros solicitados.

## Regras e limites

- **Entradas/dados**: endereço do servidor, porta, nome do banco, usuário, credencial, parâmetros de segurança da conexão e limites de tempo.
- **Invariantes**: as credenciais não podem aparecer em logs, mensagens de erro ou prompts enviados ao LLM; o usuário do banco deve possuir somente as permissões necessárias de leitura; as consultas devem respeitar [[RF-TSQL-009 - Permitir somente consultas SELECT]].
- **Exceções/fallback**: se não for possível estabelecer uma conexão segura e autenticada, nenhuma consulta ou leitura de metadados deve ser realizada.
- **Fora do escopo**: suporte a outros sistemas gerenciadores de banco de dados, criação do banco PostgreSQL, administração do servidor e concessão automática de permissões.

## Verificação e rastreabilidade

- **Método**: teste de integração, teste de segurança e inspeção da configuração.
- **Evidência esperada**: testes de conexão bem-sucedida, credencial inválida, servidor indisponível, tempo limite e confirmação de que segredos não aparecem em logs ou mensagens.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-005 - Visualizar a estrutura do banco de dados]] e [[RF-TSQL-006 - Visualizar os registros do banco de dados]].
- **Tarefa/teste**: A definir.
