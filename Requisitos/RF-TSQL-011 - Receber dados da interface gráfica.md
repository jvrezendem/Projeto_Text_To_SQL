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

# [RF-TSQL-011] - Receber dados da interface gráfica

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-011 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **a interface gráfica enviar dados ou uma ação do usuário**, o **sistema** deve **receber, interpretar e validar a mensagem antes de encaminhá-la ao fluxo correspondente**, garantindo **que somente dados bem-formados e permitidos sejam processados**.

## Por quê

A interface gráfica é o ponto de entrada das perguntas, escolhas e solicitações do usuário. Sem um contrato de recebimento e validação, mensagens incompletas ou malformadas podem causar resultados incorretos, falhas internas ou acesso indevido a operações do sistema.

## Critérios de aceite

- [ ] **Sucesso** — Dada uma mensagem válida enviada pela interface gráfica, quando o sistema recebê-la, então deve validar seus campos e encaminhar os dados ao fluxo solicitado.
- [ ] **Fronteira** — Dada uma mensagem com campos opcionais ausentes ou vazios, quando ela for recebida, então o sistema deve aplicar as regras do fluxo correspondente sem interpretar a ausência como um valor diferente do contrato.
- [ ] **Falha** — Dada uma mensagem malformada, com tipo desconhecido, campos obrigatórios ausentes ou valores inválidos, quando ela for recebida, então o sistema deve rejeitá-la, não iniciar o fluxo solicitado e retornar um erro seguro para a interface.

## Regras e limites

- **Entradas/dados**: perguntas do usuário, seleção da arquitetura, solicitações de visualização, identificadores e demais parâmetros definidos no contrato da interface.
- **Invariantes**: todos os dados recebidos da interface devem ser tratados como não confiáveis até sua validação; perguntas vazias devem respeitar [[RF-TSQL-002 - Ignorar pergunta vazia]]; a arquitetura deve respeitar [[RF-TSQL-010 - Escolher a arquitetura de geração da consulta]].
- **Exceções/fallback**: mensagens inválidas devem ser rejeitadas sem chamar o LLM nem acessar o PostgreSQL.
- **Fora do escopo**: definição visual dos componentes da interface, autenticação do usuário e armazenamento permanente do estado da tela.

## Verificação e rastreabilidade

- **Método**: teste de contrato, teste funcional e teste de segurança.
- **Evidência esperada**: testes com mensagens válidas, vazias, incompletas, malformadas, de tipo desconhecido e contendo valores inesperados.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-010 - Escolher a arquitetura de geração da consulta]] e [[RF-TSQL-005 - Visualizar a estrutura do banco de dados]].
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir o protocolo de comunicação entre a interface gráfica e o sistema — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Definir o formato, os tipos de mensagem e os campos obrigatórios do contrato de entrada — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Definir limites de tamanho, frequência e tempo para as mensagens recebidas — **Responsável**: Segurança — **Prazo**: A definir.

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
