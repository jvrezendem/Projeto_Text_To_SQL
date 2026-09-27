# Projeto Text To SQL

Projeto para consultar dados de um banco PostgreSQL por meio de perguntas em linguagem natural, transformadas em consultas SQL de leitura.

## Objetivo

Permitir que o usuário escreva uma pergunta sobre os dados e receba os registros correspondentes sem precisar escrever SQL manualmente. Antes da execução, toda consulta gerada deve ser validada para garantir que somente operações de leitura permitidas cheguem ao banco de dados.

## Arquiteturas de geração

O projeto prevê três arquiteturas executadas para cada pergunta do usuário:

1. Tradução direta da pergunta com LLM.
2. LLM com contexto autorizado da estrutura do banco de dados.
3. Geração de prompt com templates tradicionais predefinidos.

## Funcionalidades previstas

- Receber perguntas em linguagem natural pela interface gráfica.
- Processar cada pergunta nas três arquiteturas e manter os resultados separados.
- Compor o prompt com pergunta, contexto autorizado e restrições.
- Gerar consultas SQL do tipo `SELECT`.
- Bloquear comandos SQL não permitidos antes do acesso ao banco.
- Conectar-se a um banco PostgreSQL em modo somente leitura.
- Exibir a estrutura autorizada do banco de dados.
- Exibir os registros autorizados retornados pelas consultas.
- Exibir os três resultados obtidos e o gabarito da pergunta.
- Exibir um comparativo do número de acertos das três arquiteturas.
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
- [RF-TSQL-011 — Receber dados da interface gráfica](./Requisitos/RF-TSQL-011%20-%20Receber%20dados%20da%20interface%20gráfica.md)
- [RF-TSQL-012 — Enviar dados para a interface gráfica](./Requisitos/RF-TSQL-012%20-%20Enviar%20dados%20para%20a%20interface%20gráfica.md)
- [RF-TSQL-013 — Armazenar dados predefinidos do domínio de futebol](./Requisitos/RF-TSQL-013%20-%20Armazenar%20dados%20predefinidos%20do%20domínio%20de%20futebol.md)
- [RF-TSQL-014 — Exibir os resultados obtidos e o gabarito](./Requisitos/RF-TSQL-014%20-%20Exibir%20o%20resultado%20obtido%20e%20o%20gabarito.md)
- [RF-TSQL-015 — Processar a pergunta nas três arquiteturas](./Requisitos/RF-TSQL-015%20-%20Processar%20a%20pergunta%20nas%20três%20arquiteturas.md)
- [RF-TSQL-016 — Exibir comparativo de acertos das três arquiteturas](./Requisitos/RF-TSQL-016%20-%20Exibir%20comparativo%20de%20acertos%20das%20três%20arquiteturas.md)
- [RNF-TSQL-001 — Garantir acurácia mínima das três arquiteturas](./Requisitos/RNF-TSQL-001%20-%20Garantir%20acurácia%20mínima%20das%20três%20arquiteturas.md)

## Classificação

O projeto utiliza requisitos funcionais (`RF`) e não funcionais (`RNF`). Os requisitos funcionais descrevem os comportamentos observáveis do sistema; os não funcionais estabelecem qualidades mensuráveis, como a acurácia das arquiteturas.
