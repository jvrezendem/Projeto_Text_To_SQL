# [RF-TSQL-012] - Enviar dados para a interface gráfica

## Requisito

> Quando **um fluxo solicitado pela interface gráfica produzir dados, estados ou erros**, o **sistema** deve **enviar uma resposta estruturada e compatível com o contrato da interface, incluindo separadamente os resultados das duas arquiteturas quando uma pergunta for processada**, garantindo **que a interface consiga identificar a solicitação, a origem de cada resultado e apresentá-los ao usuário**.

## Por quê

A interface gráfica depende das respostas do sistema para apresentar registros, estrutura do banco, estado de processamento e falhas. Um contrato de saída consistente reduz ambiguidades e evita que a interface interprete resultados ou erros de forma incorreta.

## Regras e limites

- **Entradas/dados**: registros consultados, metadados da estrutura, identificação de cada uma das duas arquiteturas, estados das operações, identificador da solicitação e erros seguros.
- **Invariantes**: toda resposta deve seguir o contrato de saída; os dois resultados devem permanecer separados e identificados; dados não autorizados e segredos não podem ser enviados; conjuntos vazios devem ser diferenciados de falhas; resultados devem respeitar [[RF-TSQL-006 - Visualizar os registros do banco de dados]].
- **Exceções/fallback**: se a interface estiver indisponível ou a resposta não puder ser entregue, o sistema deve registrar a falha de forma segura e não considerar a entrega concluída.
- **Fora do escopo**: definição do layout visual, renderização dos componentes e armazenamento permanente dos dados pela interface.

## Verificação e rastreabilidade

- **Método**: teste de contrato, teste funcional, teste de integração e teste de segurança.
- **Evidência esperada**: respostas de teste para resultado com dados, conjunto vazio, processamento em andamento, erro de validação e falha interna, todas compatíveis com o contrato de saída.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-005 - Visualizar a estrutura do banco de dados]], [[RF-TSQL-006 - Visualizar os registros do banco de dados]] e [[RF-TSQL-015 - Processar a pergunta nas duas arquiteturas]].
- **Tarefa/teste**: A definir.
