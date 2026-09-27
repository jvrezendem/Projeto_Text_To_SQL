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
objetivo_pai:
metodo_verificacao: teste
---

# [RF-TSQL-002] - Ignorar pergunta vazia

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-002 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **o usuário tentar enviar uma pergunta vazia, nula ou composta somente por espaços**, o **sistema** deve **interromper o fluxo sem gerar prompt, chamar o LLM ou acessar o banco de dados**, garantindo **que nenhuma consulta seja processada sem conteúdo válido**.

## Por quê

Entradas vazias não contêm intenção de consulta e consumiriam recursos sem produzir um resultado útil. Interromper o fluxo evita chamadas desnecessárias ao LLM e ao banco de dados.

## Critérios de aceite

- [ ] **Sucesso** — Dada uma pergunta com conteúdo válido, quando o usuário enviá-la, então o sistema deve permitir o início do fluxo de consulta.
- [ ] **Fronteira** — Dada uma pergunta composta somente por espaços, tabulações ou quebras de linha, quando o usuário tentar enviá-la, então o sistema não deve iniciar nenhuma etapa do fluxo.
- [ ] **Falha** — Dada uma entrada vazia ou nula, quando o envio for solicitado, então o sistema não deve gerar prompt, chamar o LLM nem acessar o banco de dados.

## Regras e limites

- **Entradas/dados**: conteúdo informado no campo de pergunta.
- **Invariantes**: o gerador de prompt, o LLM e o banco de dados não podem ser acionados para entradas sem conteúdo após a remoção de espaços.
- **Exceções/fallback**: entradas que contenham somente caracteres de espaçamento devem ser tratadas como vazias.
- **Fora do escopo**: validação semântica de perguntas preenchidas, mas sem relação com os dados disponíveis.

## Verificação e rastreabilidade

- **Método**: teste funcional automatizado.
- **Evidência esperada**: testes com valor vazio, nulo e somente espaços comprovando a ausência de chamadas às dependências.
- **Objetivo/spec**: A definir.
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir se a interface deve permanecer silenciosa ou apresentar uma orientação ao usuário quando a pergunta estiver vazia — **Responsável**: Produto — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.1 | 2026-09-27 | Criação | Codex |

## Revisão rápida

- [x] Há somente uma obrigação principal.
- [x] Condição, comportamento e resultado são observáveis.
- [x] Critérios cobrem sucesso, limite e falha.
- [ ] Prioridade, origem, verificação e vínculo com a spec estão preenchidos.
