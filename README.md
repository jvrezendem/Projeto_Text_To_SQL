# Projeto Text To SQL

Projeto para consultar dados de um banco PostgreSQL por meio de perguntas em linguagem natural, transformadas em consultas SQL de leitura.

## Objetivo

Permitir que o usuário escreva uma pergunta sobre os dados e receba os registros correspondentes sem precisar escrever SQL manualmente. Antes da execução, toda consulta gerada deve ser validada para garantir que somente operações de leitura permitidas cheguem ao banco de dados.

## Arquiteturas de geração

O projeto prevê três arquiteturas selecionáveis pelo usuário:

1. Tradução direta da pergunta com LLM.
2. LLM com contexto autorizado da estrutura do banco de dados.
3. Geração de prompt com templates tradicionais predefinidos.

## Funcionalidades previstas

- Receber perguntas em linguagem natural pela interface gráfica.
- Permitir a escolha da arquitetura de geração da consulta.
- Compor o prompt com pergunta, contexto autorizado e restrições.
- Gerar consultas SQL do tipo `SELECT`.
- Bloquear comandos SQL não permitidos antes do acesso ao banco.
- Conectar-se a um banco PostgreSQL em modo somente leitura.
- Exibir a estrutura autorizada do banco de dados.
- Exibir os registros autorizados retornados pelas consultas.
- Enviar resultados, estados e erros seguros para a interface gráfica.

## Requisitos

Os requisitos detalhados estão na pasta [`Requisitos`](./Requisitos):

- [RF-TSQL-001 — Consultar dados em linguagem natural](./Requisitos/RF-TSQL-001%20-%20Consultar%20dados%20em%20linguagem%20natural.md)
- [RF-TSQL-002 — Ignorar pergunta vazia](./Requisitos/RF-TSQL-002%20-%20Ignorar%20pergunta%20vazia.md)
- [RF-TSQL-003 — Fornecer contexto do banco de dados ao LLM](./Requisitos/RF-TSQL-003%20-%20Fornecer%20contexto%20do%20banco%20de%20dados%20ao%20LLM.md)
- [RF-TSQL-004 — Gerar prompt com templates tradicionais](./Requisitos/RF-TSQL-004%20-%20Gerar%20prompt%20com%20templates%20tradicionais.md)
- [RF-TSQL-005 — Visualizar a estrutura do banco de dados](./Requisitos/RF-TSQL-005%20-%20Visualizar%20a%20estrutura%20do%20banco%20de%20dados.md)
- [RF-TSQL-006 — Visualizar os registros do banco de dados](./Requisitos/RF-TSQL-006%20-%20Visualizar%20os%20registros%20do%20banco%20de%20dados.md)
- [RF-TSQL-007 — Compor o prompt com pergunta, contexto e restrições](./Requisitos/RF-TSQL-007%20-%20Compor%20o%20prompt%20com%20a%20pergunta%20e%20as%20restrições.md)
- [RF-TSQL-008 — Conectar ao banco de dados PostgreSQL](./Requisitos/RF-TSQL-008%20-%20Conectar%20ao%20banco%20de%20dados%20PostgreSQL.md)
- [RF-TSQL-009 — Permitir somente consultas SELECT](./Requisitos/RF-TSQL-009%20-%20Permitir%20somente%20consultas%20SELECT.md)
- [RF-TSQL-010 — Escolher a arquitetura de geração da consulta](./Requisitos/RF-TSQL-010%20-%20Escolher%20a%20arquitetura%20de%20geração%20da%20consulta.md)
- [RF-TSQL-011 — Receber dados da interface gráfica](./Requisitos/RF-TSQL-011%20-%20Receber%20dados%20da%20interface%20gráfica.md)
- [RF-TSQL-012 — Enviar dados para a interface gráfica](./Requisitos/RF-TSQL-012%20-%20Enviar%20dados%20para%20a%20interface%20gráfica.md)

## Classificação

Os requisitos atuais são funcionais (`RF`). Requisitos não funcionais (`RNF`) serão registrados separadamente quando houver uma obrigação principal de qualidade mensurável, como desempenho, disponibilidade, escalabilidade, confiabilidade ou usabilidade.
