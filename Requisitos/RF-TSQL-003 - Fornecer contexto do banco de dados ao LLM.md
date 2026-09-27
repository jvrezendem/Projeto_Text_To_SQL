---
tipo: requisito
area: TextToSQL
status: proposto
prioridade: Must
versao: 0.1
data: 2026-09-27
responsavel: A definir
tags:
  - tipo/requisito
fonte: História de usuário fornecida pelo solicitante
objetivo_pai: "[[RF-TSQL-001 - Consultar dados em linguagem natural]]"
metodo_verificacao: teste
---

# [RF-TSQL-003] - Fornecer contexto do banco de dados ao LLM

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-003 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **uma pergunta sobre os dados for enviada para tradução em SQL**, o **gerador de prompt** deve **fornecer ao LLM o contexto autorizado de organização do banco de dados, incluindo as estruturas necessárias para interpretar a pergunta**, garantindo **que a consulta `SELECT` gerada utilize tabelas, colunas e relacionamentos existentes e permitidos**.

## Por quê

O LLM precisa conhecer a estrutura do banco para relacionar corretamente os termos usados pelo usuário com os elementos disponíveis no modelo de dados. Sem esse contexto, a consulta pode referenciar tabelas ou colunas inexistentes, produzir resultados incorretos ou tentar acessar estruturas que o usuário não pode consultar.

## Critérios de aceite

- [ ] **Sucesso** — Dada uma pergunta relacionada a estruturas conhecidas e autorizadas, quando o prompt for gerado, então ele deve fornecer ao LLM o contexto necessário de tabelas, colunas e relacionamentos para produzir uma consulta `SELECT` válida.
- [ ] **Fronteira** — Dado um banco com um esquema maior do que o contexto suportado pelo LLM, quando o prompt for gerado, então o sistema deve selecionar somente as estruturas autorizadas e relevantes para a pergunta, preservando as informações necessárias para formar a consulta.
- [ ] **Falha** — Dado que o contexto do banco esteja indisponível, inválido ou desatualizado a ponto de impedir uma geração segura, quando a pergunta for processada, então o sistema deve interromper a geração ou execução da consulta e informar que não foi possível concluir a solicitação.

## Regras e limites

- **Entradas/dados**: pergunta do usuário e metadados autorizados do banco, incluindo nomes e descrições de esquemas, tabelas, colunas, tipos e relacionamentos necessários.
- **Invariantes**: o LLM deve receber somente metadados de estruturas que possam ser consultadas pelo usuário; credenciais e valores sensíveis armazenados nas tabelas não devem ser incluídos no contexto.
- **Exceções/fallback**: se não houver contexto suficiente para interpretar a pergunta com segurança, o sistema deve interromper o fluxo sem executar uma consulta no banco de dados.
- **Fora do escopo**: treinamento ou ajuste fino do LLM, inclusão dos registros do banco no contexto e alteração da estrutura do banco de dados.

## Verificação e rastreabilidade

- **Método**: teste funcional, inspeção do prompt e teste de integração.
- **Evidência esperada**: registro de teste demonstrando que o prompt contém somente o contexto autorizado necessário e que a consulta gerada referencia estruturas existentes.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]].
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir como o contexto do banco será obtido, selecionado e atualizado — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Definir como as permissões do usuário limitarão as estruturas incluídas no contexto — **Responsável**: Segurança — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.1 | 2026-09-27 | Criação | Codex |

## Revisão rápida

- [x] Há somente uma obrigação principal.
- [x] Condição, comportamento e resultado são observáveis.
- [x] Critérios cobrem sucesso, limite e falha.
- [ ] Prioridade, origem, verificação e vínculo com a spec estão preenchidos.
