# [RNF-TSQL-001] - Garantir acurácia mínima das três arquiteturas

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RNF-TSQL-001 |
| Tipo | Não funcional — Acurácia |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | Conjunto mínimo de perguntas fornecido pelo solicitante |

## Requisito

> Quando **as três arquiteturas de Text-to-SQL forem avaliadas sobre a mesma versão do banco de dados**, o **sistema** deve **responder corretamente, em cada arquitetura, a todas as perguntas do conjunto mínimo obrigatório**, garantindo **100% de acerto nas 15 execuções resultantes da combinação entre três arquiteturas e cinco perguntas**.

## Por quê

As três arquiteturas precisam demonstrar que conseguem interpretar perguntas com filtros, junções, contagens, agrupamentos, ordenação, limitação de resultados e regras dependentes do papel de um time em uma partida. Um conjunto comum de referência permite comparar as abordagens e detectar regressões na geração das consultas SQL ou na apresentação dos resultados.

## Critérios de aceite

- [ ] **Sucesso** — Dada a mesma versão do conjunto de dados e as cinco perguntas obrigatórias, quando cada pergunta for executada em cada uma das três arquiteturas, então as 15 respostas devem corresponder aos resultados das consultas SQL de referência aprovadas.
- [ ] **Fronteira** — Dado um empate em uma pergunta de ordenação ou liderança, quando o resultado for avaliado, então a arquitetura deve seguir a regra de desempate definida para o caso de teste e produzir uma resposta determinística compatível com a referência.
- [ ] **Falha** — Dada qualquer execução que produza SQL inválido, erro, resultado divergente, quantidade incorreta de registros ou interpretação semântica diferente da consulta de referência, quando a matriz for avaliada, então o requisito deve ser considerado não atendido para a arquitetura correspondente.

## Regras e limites

- **Entradas/dados**: banco PostgreSQL com a estrutura definida em [[RF-TSQL-013 - Armazenar dados predefinidos do domínio de futebol]], versão identificada do conjunto de dados, execução das arquiteturas de LLM direto, LLM com contexto e templates/regex sem LLM, e as seguintes perguntas obrigatórias:
  1. **Quais são os jogadores do time Cruzeiro Esporte Clube?** — deve relacionar Jogador e Time e retornar somente os jogadores vinculados ao time cujo nome corresponde a “Cruzeiro Esporte Clube”.
  2. **Quais são os 3 jogadores com mais gols?** — deve relacionar Jogador e Gol, contar os gols por jogador, ordenar pela maior quantidade e retornar três jogadores conforme a regra de desempate aprovada.
  3. **Quais são os clubes com mais jogadores argentinos?** — deve relacionar Time e Jogador, considerar a nacionalidade argentina segundo a normalização aprovada, contar os jogadores por clube e ordenar os clubes pela maior quantidade.
  4. **Quantas partidas o time Cruzeiro Esporte Clube ganhou no Campeonato Brasileiro?** — deve relacionar Time, Partida e Campeonato, considerar partidas em casa e como visitante, identificar as vitórias pela comparação do placar e contar somente as partidas pertencentes ao campeonato solicitado.
  5. **Qual o time com mais vitórias?** — deve considerar as vitórias de cada time como mandante e visitante, somar os resultados e retornar o líder conforme a regra de desempate aprovada.
- **Invariantes**: as três arquiteturas devem ser avaliadas com a mesma fotografia do banco e as mesmas respostas de referência; nenhuma execução pode alterar os dados; toda consulta gerada deve respeitar [[RF-TSQL-009 - Permitir somente consultas SELECT]]; a avaliação deve identificar qual arquitetura produziu cada resposta.
- **Exceções/fallback**: se o banco, o conjunto de referência ou uma arquitetura estiver indisponível, a execução deve ser marcada como não realizada e não pode ser contabilizada como acerto.
- **Fora do escopo**: medir desempenho, custo, consumo de tokens ou tempo de resposta; aceitar respostas aproximadamente corretas; substituir as cinco perguntas obrigatórias por outras sem revisão deste requisito.

## Verificação e rastreabilidade

- **Método**: teste automatizado de aceitação, comparação com consultas SQL de referência e inspeção das respostas.
- **Evidência esperada**: matriz com 15 casos identificando pergunta, arquitetura, SQL gerado, resposta esperada, resposta obtida e estado aprovado ou reprovado; taxa de acerto igual a 15/15.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-003 - Fornecer contexto do banco de dados ao LLM]], [[RF-TSQL-004 - Gerar consulta com templates e regex]], [[RF-TSQL-013 - Armazenar dados predefinidos do domínio de futebol]] e [[RF-TSQL-015 - Processar a pergunta nas três arquiteturas]].
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir a versão do conjunto de dados e os resultados esperados para as cinco perguntas — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Aprovar as consultas SQL de referência usadas para calcular as respostas corretas — **Responsável**: Dados — **Prazo**: A definir.
- [ ] Definir a regra de desempate para os três maiores goleadores e para o time com mais vitórias — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir a normalização de nacionalidade usada para identificar jogadores argentinos — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Confirmar se “Campeonato Brasileiro” abrange todas as temporadas armazenadas ou uma temporada específica — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Confirmar se “clubes” deve ser interpretado como a entidade Time em todas as respostas — **Responsável**: Produto — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.3 | 2026-09-27 | Definição explícita da arquitetura de templates e regex sem LLM | Codex |
| 0.2 | 2026-09-27 | Adequação da avaliação à execução obrigatória das três arquiteturas | Codex |
| 0.1 | 2026-09-27 | Criação | Codex |
