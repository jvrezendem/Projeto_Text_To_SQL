---
tipo: requisito
area: TextToSQL
status: proposto
prioridade: Must
versao: 0.2
data: 2026-09-27
responsavel: A definir
tags:
  - tipo/requisito
fonte: História de usuário fornecida pelo solicitante
objetivo_pai: "[[RF-TSQL-001 - Consultar dados em linguagem natural]]"
metodo_verificacao: teste
---

# [RF-TSQL-010] - Escolher a arquitetura de geração da consulta

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-010 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **o usuário preparar o envio de uma pergunta sobre os dados**, o **sistema** deve **permitir que ele escolha uma entre as três arquiteturas disponíveis para gerar a consulta SQL**, garantindo **que a pergunta seja processada exclusivamente pela arquitetura selecionada**.

## Por quê

A escolha permite que o usuário compare e utilize abordagens diferentes para transformar perguntas em consultas SQL. Sem um controle explícito, o usuário não saberá qual arquitetura processou a pergunta nem poderá avaliar os resultados produzidos por cada alternativa.

## Critérios de aceite

- [ ] **Sucesso** — Dada uma pergunta válida e uma das três arquiteturas selecionada, quando o usuário enviar a pergunta, então o sistema deve processá-la exclusivamente pela opção escolhida e identificar a arquitetura utilizada no resultado.
- [ ] **Fronteira** — Dado que o usuário altere a seleção antes do envio, quando a pergunta for enviada, então somente a arquitetura selecionada no momento do envio deve ser utilizada.
- [ ] **Falha** — Dada uma arquitetura selecionada que esteja indisponível, quando o usuário enviar a pergunta, então o sistema não deve substituí-la silenciosamente por outra opção nem executar uma consulta, devendo informar a indisponibilidade.

## Regras e limites

- **Entradas/dados**: pergunta do usuário e seleção de uma das arquiteturas: tradução direta com LLM, LLM com contexto da organização do banco ou geração de prompt com templates tradicionais.
- **Invariantes**: exatamente uma arquitetura deve processar cada pergunta; a consulta produzida deve respeitar [[RF-TSQL-009 - Permitir somente consultas SELECT]]; entradas vazias devem respeitar [[RF-TSQL-002 - Ignorar pergunta vazia]].
- **Exceções/fallback**: se a opção escolhida estiver indisponível, o sistema deve interromper o fluxo e solicitar que o usuário tente novamente ou escolha outra arquitetura.
- **Fora do escopo**: executar as três arquiteturas simultaneamente, combinar resultados automaticamente ou escolher a arquitetura com base na pergunta sem participação do usuário.

## Verificação e rastreabilidade

- **Método**: teste de interface, teste funcional e teste de integração.
- **Evidência esperada**: testes demonstrando que cada opção pode ser selecionada e que somente a arquitetura escolhida recebe e processa a pergunta.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-003 - Fornecer contexto do banco de dados ao LLM]] e [[RF-TSQL-004 - Gerar prompt com templates tradicionais]].
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir os nomes e as descrições exibidos para as três arquiteturas — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir se haverá uma arquitetura selecionada por padrão — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir se a escolha será preservada entre perguntas ou reiniciada após cada envio — **Responsável**: Produto — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.2 | 2026-09-27 | Reclassificação de interface para requisito funcional e atualização do identificador | Codex |
| 0.1 | 2026-09-27 | Criação | Codex |

## Revisão rápida

- [x] Há somente uma obrigação principal.
- [x] Condição, comportamento e resultado são observáveis.
- [x] Critérios cobrem sucesso, limite e falha.
- [ ] Prioridade, origem, verificação e vínculo com a spec estão preenchidos.
