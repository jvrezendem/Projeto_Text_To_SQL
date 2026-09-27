# [RF-TSQL-016] - Exibir comparativo de acertos das três arquiteturas

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-016 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **existirem perguntas avaliadas com gabarito válido**, o **sistema** deve **apresentar ao usuário um comparativo das três arquiteturas com base no número de acertos de cada uma**, garantindo **que os valores sejam calculados sobre o mesmo conjunto de perguntas e possam ser associados às avaliações que os originaram**.

## Por quê

O comparativo permite identificar de forma objetiva qual arquitetura apresentou mais respostas corretas no conjunto avaliado. Sem a consolidação dos acertos, o usuário precisaria comparar manualmente cada resultado com seu gabarito e não teria uma visão clara do desempenho relativo das abordagens.

## Critérios de aceite

- [ ] **Sucesso** — Dado um conjunto de perguntas processadas pelas três arquiteturas e avaliadas contra gabaritos válidos, quando o usuário abrir o comparativo, então a interface deve mostrar cada arquitetura, seu número de acertos e o total de perguntas consideradas.
- [ ] **Fronteira** — Dado que duas ou três arquiteturas possuam o mesmo número de acertos, quando o comparativo for exibido, então o empate deve ser representado sem favorecer arbitrariamente uma das arquiteturas; dado que nenhuma pergunta tenha sido avaliada, a interface deve apresentar um estado sem dados e não calcular uma taxa inválida.
- [ ] **Falha** — Dado um resultado sem gabarito válido, com versão incompatível dos dados ou com avaliação inconclusiva, quando o comparativo for calculado, então o caso não deve ser contabilizado como acerto e a exclusão deve ser identificável para o usuário.

## Regras e limites

- **Entradas/dados**: identificador da pergunta, identificação da arquitetura, resultado obtido, gabarito, estado da comparação, versão do conjunto de dados e escopo do comparativo.
- **Invariantes**: cada combinação entre pergunta e arquitetura pode contribuir com no máximo um acerto; o número de acertos de uma arquitetura não pode superar o total de perguntas válidas consideradas; erro, ausência de resposta, processamento pendente e resultado divergente não contam como acerto; as três arquiteturas devem ser comparadas sobre o mesmo conjunto de perguntas e a mesma versão dos dados.
- **Exceções/fallback**: perguntas sem gabarito válido devem ser excluídas do cálculo e apresentadas como não avaliadas; se os dados consolidados não puderem ser carregados, o sistema deve informar que o comparativo está indisponível e não exibir valores estimados.
- **Fora do escopo**: combinar respostas das arquiteturas, alterar o gabarito, atribuir pontuação parcial e medir custo, tempo de resposta ou consumo de tokens.

## Verificação e rastreabilidade

- **Método**: teste funcional, teste de agregação, teste de interface e inspeção dos cálculos.
- **Evidência esperada**: conjunto de testes com acertos, erros, empates, perguntas não avaliadas e ausência de dados, demonstrando que os totais exibidos correspondem aos registros de avaliação.
- **Objetivo/spec**: [[RF-TSQL-014 - Exibir o resultado obtido e o gabarito]], [[RF-TSQL-015 - Processar a pergunta nas três arquiteturas]] e [[RNF-TSQL-001 - Garantir acurácia mínima das três arquiteturas]].
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir se o comparativo abrangerá a sessão atual, um período selecionado ou todo o histórico — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir se, além do número de acertos, serão exibidos total avaliado e percentual de acerto — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir o formato visual do comparativo, como tabela, cartões ou gráfico — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir se as arquiteturas serão ordenadas pelo número de acertos e como empates serão apresentados — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir se o usuário poderá abrir os casos que compõem cada total de acertos — **Responsável**: Produto — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.1 | 2026-09-27 | Criação | Codex |
