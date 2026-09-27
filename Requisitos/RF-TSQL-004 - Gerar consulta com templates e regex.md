# [RF-TSQL-004] - Gerar consulta com templates e regex

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

> Quando **uma pergunta sobre os dados for recebida pela arquitetura baseada em regras**, o **sistema** deve **identificar o padrão da pergunta com templates de escrita e/ou expressões regulares, extrair os parâmetros necessários e gerar diretamente uma consulta SQL do tipo `SELECT`**, garantindo **que nenhum LLM seja chamado nesse fluxo**.

## Por quê

Templates de escrita e expressões regulares oferecem uma alternativa determinística às arquiteturas baseadas em LLM. Essa abordagem permite mapear perguntas conhecidas para consultas controladas, reduzir variações na geração do SQL e avaliar separadamente o desempenho de uma solução sem inteligência generativa.

## Critérios de aceite

- [ ] **Sucesso** — Dada uma pergunta compatível com um template de escrita ou expressão regular cadastrada, quando ela for processada, então o sistema deve reconhecer o padrão, extrair os parâmetros, gerar diretamente uma consulta `SELECT` válida e não realizar nenhuma chamada a LLM.
- [ ] **Fronteira** — Dada uma pergunta compatível com mais de um padrão, quando ela for processada, então o sistema deve aplicar uma prioridade determinística e utilizar somente o template ou regra selecionada; valores extraídos devem ser tratados de forma segura, inclusive quando contiverem espaços ou caracteres especiais.
- [ ] **Falha** — Dada uma pergunta para a qual nenhum template ou expressão regular aplicável possa ser determinado, quando ela for processada, então o sistema não deve chamar um LLM, gerar uma consulta arbitrária ou acessar o banco de dados, devendo informar que a solicitação não pôde ser interpretada por essa arquitetura.

## Regras e limites

- **Entradas/dados**: pergunta em linguagem natural, catálogo de templates de escrita SQL, expressões regulares, regras de prioridade, mapeamentos de parâmetros e metadados necessários para preencher a consulta.
- **Invariantes**: essa arquitetura não pode chamar LLMs; a consulta deve ser gerada somente a partir de templates e/ou regex previamente cadastrados; valores extraídos da pergunta não podem ser concatenados de forma insegura ao SQL; a saída deve ser exclusivamente de leitura e continuar sujeita à validação definida em [[RF-TSQL-009 - Permitir somente consultas SELECT]].
- **Exceções/fallback**: se nenhum padrão puder ser selecionado com segurança ou um parâmetro obrigatório não puder ser extraído, o fluxo dessa arquitetura deve ser interrompido antes do acesso ao banco de dados.
- **Fora do escopo**: usar LLM como fallback, criar automaticamente novos templates ou regex, alterar dados e executar comandos SQL diferentes de consultas permitidas.

## Verificação e rastreabilidade

- **Método**: teste funcional, teste de regras e expressões regulares, inspeção da consulta gerada, teste de integração e verificação de ausência de chamadas ao LLM.
- **Evidência esperada**: testes demonstrando o reconhecimento do padrão, a extração de parâmetros, a geração direta de `SELECT`, a rejeição de perguntas não reconhecidas e zero chamadas a serviços de LLM.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]].
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir o catálogo inicial de templates de escrita e expressões regulares e as perguntas cobertas por cada um — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir a sintaxe dos templates, os parâmetros permitidos e a forma segura de vinculá-los à consulta SQL — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Definir a prioridade quando mais de um template ou regex for compatível — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Confirmar se haverá uma regra genérica sem LLM para perguntas não classificadas — **Responsável**: Produto — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.2 | 2026-09-27 | Remoção do uso de LLM e adoção de templates de escrita e expressões regulares | Codex |
| 0.1 | 2026-09-27 | Criação | Codex |
