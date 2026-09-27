# [RF-TSQL-007] - Compor o prompt com pergunta, contexto e restrições

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-007 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **uma arquitetura que utiliza LLM processar uma pergunta válida**, o **gerador de prompt** deve **compor o prompt com a pergunta feita pelo usuário, o contexto autorizado do banco de dados e as restrições aplicáveis à geração da consulta**, garantindo **que os três elementos estejam claramente identificados e que o conteúdo da pergunta não possa substituir o contexto ou as restrições do sistema**.

## Por quê

O LLM precisa receber a intenção do usuário, a organização dos dados disponíveis e os limites que devem orientar a geração da consulta. Separar claramente a pergunta, o contexto do banco e as restrições reduz ambiguidades, aumenta a compatibilidade da consulta com o esquema existente e evita que o conteúdo informado pelo usuário altere as regras obrigatórias do sistema.

## Critérios de aceite

- [ ] **Sucesso** — Dada uma pergunta válida e o contexto autorizado disponível, quando o prompt for composto, então ele deve conter a pergunta integral do usuário, o contexto necessário do banco de dados e as restrições aplicáveis, em seções ou campos claramente distinguíveis.
- [ ] **Fronteira** — Dada uma pergunta que contenha texto tentando ignorar, substituir ou contradizer o contexto ou as restrições, quando o prompt for composto, então esse texto deve permanecer identificado como conteúdo do usuário e não deve alterar as informações controladas pelo sistema.
- [ ] **Falha** — Dada a ausência da pergunta válida, do contexto obrigatório ou das restrições obrigatórias, quando o sistema tentar compor o prompt, então ele deve interromper o fluxo, não chamar o LLM e registrar ou apresentar o erro correspondente.

## Regras e limites

- **Entradas/dados**: pergunta em linguagem natural fornecida pelo usuário, contexto autorizado do banco de dados e conjunto de restrições definido pelo sistema para cada uma das três arquiteturas executadas.
- **Invariantes**: a pergunta não pode modificar o contexto nem as restrições; o contexto deve conter somente estruturas autorizadas e respeitar [[RF-TSQL-003 - Fornecer contexto do banco de dados ao LLM]]; as restrições devem exigir uma consulta de leitura e respeitar [[RF-TSQL-009 - Permitir somente consultas SELECT]]; perguntas vazias devem respeitar [[RF-TSQL-002 - Ignorar pergunta vazia]].
- **Exceções/fallback**: se o contexto ou as restrições obrigatórias não puderem ser carregados, o prompt não deve ser enviado ao LLM.
- **Fora do escopo**: obtenção e atualização dos metadados do banco, documentadas em [[RF-TSQL-003 - Fornecer contexto do banco de dados ao LLM]], e definição do catálogo de templates, documentada em [[RF-TSQL-004 - Gerar prompt com templates tradicionais]].

## Verificação e rastreabilidade

- **Método**: teste funcional, inspeção do prompt e teste de segurança.
- **Evidência esperada**: prompts de teste contendo a pergunta, o contexto autorizado do banco e as restrições em campos distintos, incluindo um caso em que a pergunta tenta substituir as informações controladas pelo sistema.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-003 - Fornecer contexto do banco de dados ao LLM]], [[RF-TSQL-009 - Permitir somente consultas SELECT]] e [[RF-TSQL-015 - Processar a pergunta nas três arquiteturas]].
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir a lista completa de restrições incluídas em todas as arquiteturas que utilizam LLM — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Definir o formato e a ordem das seções da pergunta, do contexto do banco e das restrições no prompt — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Definir quais metadados do banco são obrigatórios no contexto de cada arquitetura — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Definir como as versões das restrições serão identificadas e auditadas — **Responsável**: Segurança — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.3 | 2026-09-27 | Adequação da composição do prompt à execução obrigatória das três arquiteturas | Codex |
| 0.2 | 2026-09-27 | Inclusão do contexto autorizado do banco de dados na composição do prompt | Codex |
| 0.1 | 2026-09-27 | Criação | Codex |
