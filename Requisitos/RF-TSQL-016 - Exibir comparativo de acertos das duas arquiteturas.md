# [RF-TSQL-016] - Exibir comparativo de acertos das duas arquiteturas

## Requisito

> Quando **existirem perguntas avaliadas com gabarito válido**, o **sistema** deve **apresentar ao usuário um comparativo das arquiteturas LLM sem contexto e LLM com contexto do banco de dados com base no número de acertos de cada uma**, garantindo **que os valores sejam calculados sobre o mesmo conjunto de perguntas e possam ser associados às avaliações que os originaram**.

## Por quê

O comparativo permite identificar de forma objetiva qual arquitetura apresentou mais respostas corretas no conjunto avaliado. Sem a consolidação dos acertos, o usuário precisaria comparar manualmente cada resultado com seu gabarito e não teria uma visão clara do desempenho relativo das abordagens.

## Regras e limites

- **Entradas/dados**: identificador da pergunta, identificação da arquitetura, resultado obtido, gabarito, estado da comparação, versão do conjunto de dados e escopo do comparativo.
- **Invariantes**: cada combinação entre pergunta e arquitetura pode contribuir com no máximo um acerto; o número de acertos de uma arquitetura não pode superar o total de perguntas válidas consideradas; erro, ausência de resposta, processamento pendente e resultado divergente não contam como acerto; as duas arquiteturas devem ser comparadas sobre o mesmo conjunto de perguntas e a mesma versão dos dados.
- **Exceções/fallback**: perguntas sem gabarito válido devem ser excluídas do cálculo e apresentadas como não avaliadas; se os dados consolidados não puderem ser carregados, o sistema deve informar que o comparativo está indisponível e não exibir valores estimados.
- **Fora do escopo**: combinar respostas das arquiteturas, alterar o gabarito, atribuir pontuação parcial e medir custo, tempo de resposta ou consumo de tokens.

## Verificação e rastreabilidade

- **Método**: teste funcional, teste de agregação, teste de interface e inspeção dos cálculos.
- **Evidência esperada**: conjunto de testes com acertos, erros, empates, perguntas não avaliadas e ausência de dados, demonstrando que os totais exibidos correspondem aos registros de avaliação.
- **Objetivo/spec**: [[RF-TSQL-014 - Exibir o resultado obtido e o gabarito]], [[RF-TSQL-015 - Processar a pergunta nas duas arquiteturas]] e [[RNF-TSQL-001 - Garantir acurácia mínima das duas arquiteturas]].
- **Tarefa/teste**: A definir.
