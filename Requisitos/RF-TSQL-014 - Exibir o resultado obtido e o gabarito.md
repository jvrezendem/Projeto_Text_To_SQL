# [RF-TSQL-014] - Exibir os resultados obtidos e o gabarito

## Requisito

> Quando **uma pergunta for processada pelas duas arquiteturas e possuir um gabarito aprovado**, o **sistema** deve **apresentar ao usuário os dois resultados obtidos e o gabarito correspondente**, garantindo **que cada resultado esteja identificado pela arquitetura de origem e que os três conteúdos estejam associados à mesma pergunta e disponíveis para comparação**.

## Por quê

A apresentação conjunta permite que o usuário compare as duas arquiteturas e verifique quais delas interpretaram corretamente a pergunta e retornaram os dados esperados. Sem o gabarito visível, o usuário não consegue avaliar a acurácia dos resultados nem identificar divergências entre as arquiteturas.

## Regras e limites

- **Entradas/dados**: pergunta feita pelo usuário, resultados das duas arquiteturas, identificação da origem de cada resultado, identificador e versão do conjunto de dados, gabarito aprovado e estados de processamento.
- **Invariantes**: os dois resultados e o gabarito devem corresponder à mesma pergunta e à mesma versão do conjunto de dados; cada conteúdo deve ser rotulado de forma distinta; nenhum resultado produzido pode ser alterado para coincidir com o gabarito; a exibição deve respeitar os dados autorizados ao usuário.
- **Exceções/fallback**: se uma arquitetura falhar, a interface deve apresentar o erro no lugar do resultado correspondente e manter visíveis os demais resultados e, quando aplicável, o gabarito; se não houver gabarito aprovado, deve apresentar explicitamente sua indisponibilidade.
- **Fora do escopo**: alterar automaticamente a consulta para obter o gabarito, substituir o resultado obtido pelo resultado esperado e definir o conteúdo dos gabaritos.

## Verificação e rastreabilidade

- **Método**: teste de interface, teste funcional, teste de contrato e inspeção visual.
- **Evidência esperada**: testes demonstrando a exibição da pergunta, dos dois resultados identificados e do gabarito nos casos de acerto, divergência, resultado vazio, falha de uma arquitetura e gabarito indisponível.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-012 - Enviar dados para a interface gráfica]], [[RF-TSQL-015 - Processar a pergunta nas duas arquiteturas]] e [[RNF-TSQL-001 - Garantir acurácia mínima das duas arquiteturas]].
- **Tarefa/teste**: A definir.
