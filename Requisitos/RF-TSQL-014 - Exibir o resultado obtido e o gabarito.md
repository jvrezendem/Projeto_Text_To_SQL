# [RF-TSQL-014] - Exibir os resultados obtidos e o gabarito

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-014 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **uma pergunta for processada pelas três arquiteturas e possuir um gabarito aprovado**, o **sistema** deve **apresentar ao usuário os três resultados obtidos e o gabarito correspondente**, garantindo **que cada resultado esteja identificado pela arquitetura de origem e que os quatro conteúdos estejam associados à mesma pergunta e disponíveis para comparação**.

## Por quê

A apresentação conjunta permite que o usuário compare as três arquiteturas e verifique quais delas interpretaram corretamente a pergunta e retornaram os dados esperados. Sem o gabarito visível, o usuário não consegue avaliar a acurácia dos resultados nem identificar divergências entre as arquiteturas.

## Critérios de aceite

- [ ] **Sucesso** — Dada uma pergunta processada pelas três arquiteturas com gabarito aprovado, quando a resposta for apresentada, então a interface deve exibir a pergunta, os três resultados identificados por arquitetura e o gabarito em áreas claramente distinguíveis.
- [ ] **Fronteira** — Dado que um ou mais resultados sejam vazios ou diferentes do gabarito, quando a resposta for apresentada, então o sistema deve exibir os três resultados e o gabarito integralmente, sem ocultar, substituir ou corrigir silenciosamente qualquer resposta.
- [ ] **Falha** — Dado que uma arquitetura falhe ou que o gabarito esteja ausente, indisponível ou associado a outra versão do conjunto de dados, quando a resposta for apresentada, então o sistema deve identificar a falha ou a indisponibilidade no espaço correspondente e não deve fabricar um resultado ou gabarito.

## Regras e limites

- **Entradas/dados**: pergunta feita pelo usuário, resultados das três arquiteturas, identificação da origem de cada resultado, identificador e versão do conjunto de dados, gabarito aprovado e estados de processamento.
- **Invariantes**: os três resultados e o gabarito devem corresponder à mesma pergunta e à mesma versão do conjunto de dados; cada conteúdo deve ser rotulado de forma distinta; nenhum resultado produzido pode ser alterado para coincidir com o gabarito; a exibição deve respeitar os dados autorizados ao usuário.
- **Exceções/fallback**: se uma arquitetura falhar, a interface deve apresentar o erro no lugar do resultado correspondente e manter visíveis os demais resultados e, quando aplicável, o gabarito; se não houver gabarito aprovado, deve apresentar explicitamente sua indisponibilidade.
- **Fora do escopo**: alterar automaticamente a consulta para obter o gabarito, substituir o resultado obtido pelo resultado esperado e definir o conteúdo dos gabaritos.

## Verificação e rastreabilidade

- **Método**: teste de interface, teste funcional, teste de contrato e inspeção visual.
- **Evidência esperada**: testes demonstrando a exibição da pergunta, dos três resultados identificados e do gabarito nos casos de acerto, divergência, resultado vazio, falha de uma arquitetura e gabarito indisponível.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-012 - Enviar dados para a interface gráfica]], [[RF-TSQL-015 - Processar a pergunta nas três arquiteturas]] e [[RNF-TSQL-001 - Garantir acurácia mínima das três arquiteturas]].
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir o formato visual da comparação entre resultado obtido e gabarito — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir se a interface destacará automaticamente diferenças entre registros e valores — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir como os gabaritos serão armazenados, versionados e vinculados às perguntas e ao conjunto de dados — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Confirmar se a exibição do gabarito será restrita às perguntas de avaliação ou obrigatória para qualquer pergunta enviada pelo usuário — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir se o SQL gerado e o SQL de referência também serão apresentados — **Responsável**: Produto — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.2 | 2026-09-27 | Substituição do resultado único pela comparação dos resultados das três arquiteturas | Codex |
| 0.1 | 2026-09-27 | Criação | Codex |
