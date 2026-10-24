# [RNF-TSQL-001] - Garantir acurácia mínima das duas arquiteturas

## Requisito

> Quando **as duas arquiteturas de Text-to-SQL forem avaliadas sobre a mesma versão do banco de dados**, o **sistema** deve **responder corretamente, em cada arquitetura, a todas as perguntas do conjunto mínimo obrigatório**, garantindo **100% de acerto nas 10 execuções resultantes da combinação entre duas arquiteturas e cinco perguntas**.

## Por quê

As duas arquiteturas precisam demonstrar que conseguem interpretar perguntas com filtros, junções, contagens, agrupamentos, ordenação, limitação de resultados e regras dependentes do papel de um time em uma partida. Um conjunto comum de referência permite comparar as abordagens e detectar regressões na geração das consultas SQL ou na apresentação dos resultados.

## Regras e limites

- **Entradas/dados**: banco PostgreSQL com a estrutura definida em [[RF-TSQL-013 - Armazenar dados predefinidos do domínio de futebol]], versão identificada do conjunto de dados, execução das arquiteturas de LLM sem contexto do banco de dados e LLM com contexto do banco de dados, e as seguintes perguntas obrigatórias:
  1. **Quais são os jogadores do time Cruzeiro Esporte Clube?** — deve relacionar Jogador e Time e retornar somente os jogadores vinculados ao time cujo nome corresponde a “Cruzeiro Esporte Clube”.
  2. **Quais são os 3 jogadores com mais gols?** — deve relacionar Jogador e Gol, contar os gols por jogador, ordenar pela maior quantidade e retornar três jogadores conforme a regra de desempate aprovada.
  3. **Quais são os clubes com mais jogadores argentinos?** — deve relacionar Time e Jogador, considerar a nacionalidade argentina segundo a normalização aprovada, contar os jogadores por clube e ordenar os clubes pela maior quantidade.
  4. **Quantas partidas o time Cruzeiro Esporte Clube ganhou no Campeonato Brasileiro?** — deve relacionar Time, Partida e Campeonato, considerar partidas em casa e como visitante, identificar as vitórias pela comparação do placar e contar somente as partidas pertencentes ao campeonato solicitado.
  5. **Qual o time com mais vitórias?** — deve considerar as vitórias de cada time como mandante e visitante, somar os resultados e retornar o líder conforme a regra de desempate aprovada.
- **Invariantes**: as duas arquiteturas devem ser avaliadas com a mesma fotografia do banco e as mesmas respostas de referência; nenhuma execução pode alterar os dados; toda consulta gerada deve respeitar [[RF-TSQL-009 - Permitir somente consultas SELECT]]; a avaliação deve identificar qual arquitetura produziu cada resposta.
- **Exceções/fallback**: se o banco, o conjunto de referência ou uma arquitetura estiver indisponível, a execução deve ser marcada como não realizada e não pode ser contabilizada como acerto.
- **Fora do escopo**: medir desempenho, custo, consumo de tokens ou tempo de resposta; aceitar respostas aproximadamente corretas; substituir as cinco perguntas obrigatórias por outras sem revisão deste requisito.

## Verificação e rastreabilidade

- **Método**: teste automatizado de aceitação, comparação com consultas SQL de referência e inspeção das respostas.
- **Evidência esperada**: matriz com 10 casos identificando pergunta, arquitetura, SQL gerado, resposta esperada, resposta obtida e estado aprovado ou reprovado; taxa de acerto igual a 10/10.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-003 - Fornecer contexto do banco de dados ao LLM]], [[RF-TSQL-004 - Gerar consulta com LLM sem contexto do banco de dados]], [[RF-TSQL-013 - Armazenar dados predefinidos do domínio de futebol]] e [[RF-TSQL-015 - Processar a pergunta nas duas arquiteturas]].
- **Tarefa/teste**: A definir.
