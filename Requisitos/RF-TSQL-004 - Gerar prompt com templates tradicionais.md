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

# [RF-TSQL-004] - Gerar prompt com templates tradicionais

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-004 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **uma pergunta sobre os dados for recebida**, o **gerador de prompt** deve **selecionar um template tradicional predefinido, preenchê-lo com a pergunta e enviar o prompt resultante ao LLM**, garantindo **que o modelo receba instruções estruturadas para gerar uma consulta SQL do tipo `SELECT`**.

## Por quê

Templates predefinidos tornam a composição do prompt previsível e permitem controlar as instruções fornecidas ao LLM. Sem uma estrutura conhecida, perguntas equivalentes podem produzir prompts inconsistentes e consultas com qualidade variável.

## Critérios de aceite

- [ ] **Sucesso** — Dada uma pergunta compatível com um template predefinido, quando ela for processada, então o gerador deve selecionar o template adequado, preenchê-lo com a pergunta e enviar o prompt resultante ao LLM para geração de uma consulta `SELECT`.
- [ ] **Fronteira** — Dada uma pergunta que possa se enquadrar em mais de um template, quando ela for processada, então o gerador deve aplicar uma regra determinística de seleção e usar somente um template para produzir o prompt.
- [ ] **Falha** — Dada uma pergunta para a qual nenhum template aplicável possa ser determinado, quando ela for processada, então o sistema não deve executar uma consulta no banco de dados e deve informar que a solicitação não pôde ser interpretada.

## Regras e limites

- **Entradas/dados**: pergunta em linguagem natural e catálogo de templates predefinidos disponíveis para o gerador de prompt.
- **Invariantes**: o template aplicado deve orientar a geração exclusiva de consultas de leitura do tipo `SELECT`; a consulta produzida continua sujeita à validação definida em [[RF-TSQL-009 - Permitir somente consultas SELECT]].
- **Exceções/fallback**: se nenhum template puder ser selecionado com segurança, o fluxo deve ser interrompido antes da geração ou execução da consulta.
- **Fora do escopo**: criação automática de novos templates, alteração de dados e execução de comandos SQL diferentes de consultas permitidas.

## Verificação e rastreabilidade

- **Método**: teste funcional, inspeção do prompt e teste de integração.
- **Evidência esperada**: testes demonstrando a seleção do template, seu preenchimento com a pergunta e o envio do prompt resultante ao LLM.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]].
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir o catálogo inicial de templates tradicionais e as perguntas cobertas por cada um — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir a regra de seleção quando mais de um template for compatível — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Confirmar se haverá um template genérico de fallback para perguntas não classificadas — **Responsável**: Produto — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.1 | 2026-09-27 | Criação | Codex |

## Revisão rápida

- [x] Há somente uma obrigação principal.
- [x] Condição, comportamento e resultado são observáveis.
- [x] Critérios cobrem sucesso, limite e falha.
- [ ] Prioridade, origem, verificação e vínculo com a spec estão preenchidos.
