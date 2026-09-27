# [RF-TSQL-012] - Enviar dados para a interface gráfica

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-012 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **um fluxo solicitado pela interface gráfica produzir dados, estados ou erros**, o **sistema** deve **enviar uma resposta estruturada e compatível com o contrato da interface, incluindo separadamente os resultados das três arquiteturas quando uma pergunta for processada**, garantindo **que a interface consiga identificar a solicitação, a origem de cada resultado e apresentá-los ao usuário**.

## Por quê

A interface gráfica depende das respostas do sistema para apresentar registros, estrutura do banco, estado de processamento e falhas. Um contrato de saída consistente reduz ambiguidades e evita que a interface interprete resultados ou erros de forma incorreta.

## Critérios de aceite

- [ ] **Sucesso** — Dada uma pergunta processada com sucesso pelas três arquiteturas, quando os resultados estiverem disponíveis, então o sistema deve enviar à interface uma resposta estruturada com o identificador da solicitação, o estado e um resultado identificado para cada arquitetura.
- [ ] **Fronteira** — Dado que uma arquitetura retorne um conjunto vazio, quando a resposta for enviada, então o sistema deve representar explicitamente esse resultado vazio, sem confundi-lo com os resultados das demais arquiteturas ou convertê-lo em erro.
- [ ] **Falha** — Dada uma falha em uma arquitetura ou no envio da resposta, quando o sistema responder à interface, então deve identificar a arquitetura afetada e fornecer um erro seguro, sem expor credenciais, prompts internos, comandos sensíveis ou detalhes técnicos desnecessários.

## Regras e limites

- **Entradas/dados**: registros consultados, metadados da estrutura, identificação de cada uma das três arquiteturas, estados das operações, identificador da solicitação e erros seguros.
- **Invariantes**: toda resposta deve seguir o contrato de saída; os três resultados devem permanecer separados e identificados; dados não autorizados e segredos não podem ser enviados; conjuntos vazios devem ser diferenciados de falhas; resultados devem respeitar [[RF-TSQL-006 - Visualizar os registros do banco de dados]].
- **Exceções/fallback**: se a interface estiver indisponível ou a resposta não puder ser entregue, o sistema deve registrar a falha de forma segura e não considerar a entrega concluída.
- **Fora do escopo**: definição do layout visual, renderização dos componentes e armazenamento permanente dos dados pela interface.

## Verificação e rastreabilidade

- **Método**: teste de contrato, teste funcional, teste de integração e teste de segurança.
- **Evidência esperada**: respostas de teste para resultado com dados, conjunto vazio, processamento em andamento, erro de validação e falha interna, todas compatíveis com o contrato de saída.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-005 - Visualizar a estrutura do banco de dados]], [[RF-TSQL-006 - Visualizar os registros do banco de dados]] e [[RF-TSQL-015 - Processar a pergunta nas três arquiteturas]].
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir o protocolo e o formato das respostas enviadas à interface gráfica — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Definir a estrutura padronizada de estados, dados e erros — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Definir se grandes resultados serão paginados, transmitidos progressivamente ou disponibilizados sob demanda — **Responsável**: Arquitetura — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.3 | 2026-09-27 | Inclusão do envio separado dos resultados das três arquiteturas | Codex |
| 0.2 | 2026-09-27 | Reclassificação de interface para requisito funcional e atualização do identificador | Codex |
| 0.1 | 2026-09-27 | Criação | Codex |
