# [RF-TSQL-013] - Armazenar dados predefinidos do domínio de futebol

## Requisito

> Quando **o banco de dados do projeto for preparado ou os dados predefinidos forem carregados**, o **sistema** deve **armazenar os dados do domínio de futebol nas entidades Time, Jogador, Campeonato, Partida e Gol, utilizando as chaves primárias e estrangeiras especificadas**, garantindo **a integridade referencial e a recuperação consistente das informações pelo TextToSQL**.

## Por quê

O TextToSQL precisa de um modelo de dados conhecido e consistente para gerar consultas e responder às perguntas do usuário. Uma estrutura relacional explícita permite representar times, jogadores, campeonatos, partidas e gols, além de fornecer ao LLM o contexto necessário para relacionar corretamente essas informações.

## Regras e limites

- **Entradas/dados**:
  - **Time**: `id_time` (PK), `nome`, `cidade`, `pais`, `ano_fundacao`.
  - **Jogador**: `id_jogador` (PK), `nome`, `data_nascimento`, `posicao`, `nacionalidade`, `id_time` (FK → Time).
  - **Campeonato**: `id_campeonato` (PK), `nome`, `temporada`, `pais`.
  - **Partida**: `id_partida` (PK), `data`, `id_campeonato` (FK → Campeonato), `id_time_casa` (FK → Time), `id_time_visitante` (FK → Time), `gols_casa`, `gols_visitante`.
  - **Gol**: `id_gol` (PK), `id_partida` (FK → Partida), `id_jogador` (FK → Jogador), `minuto`.
- **Invariantes**: toda chave primária deve identificar um único registro e não pode ser nula; `Jogador.id_time` deve referenciar um Time existente; `Partida.id_campeonato` deve referenciar um Campeonato existente; `Partida.id_time_casa` e `Partida.id_time_visitante` devem referenciar Times existentes; `Gol.id_partida` deve referenciar uma Partida existente; `Gol.id_jogador` deve referenciar um Jogador existente.
- **Exceções/fallback**: uma falha de persistência ou de integridade deve interromper a unidade de carga afetada e preservar o último estado consistente do banco.
- **Fora do escopo**: alteração da estrutura pelo usuário, armazenamento de outros domínios e definição de dados que não pertençam às cinco entidades especificadas.

## Verificação e rastreabilidade

- **Método**: inspeção do esquema, teste de migração, teste de integração e teste de integridade referencial.
- **Evidência esperada**: esquema PostgreSQL contendo as cinco entidades e suas relações, carga de dados válida concluída, consultas de recuperação bem-sucedidas e testes que rejeitem chaves duplicadas ou referências inexistentes.
- **Objetivo/spec**: [[RF-TSQL-003 - Fornecer contexto do banco de dados ao LLM]], [[RF-TSQL-005 - Visualizar a estrutura do banco de dados]], [[RF-TSQL-006 - Visualizar os registros do banco de dados]] e [[RF-TSQL-008 - Conectar ao banco de dados PostgreSQL]].
- **Tarefa/teste**: A definir.
