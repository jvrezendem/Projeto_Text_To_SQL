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

# [RF-TSQL-005] - Visualizar a estrutura do banco de dados

> [!info] Como usar
> Mantenha uma obrigação por nota. Classifique-a somente como `RF` (requisito funcional) ou `RNF` (requisito não funcional). Se o requisito precisar de análise extensa, use [[Template - Requisito de Software]].

## Resumo

| Campo | Valor |
|---|---|
| ID | RF-TSQL-005 |
| Tipo | Funcional |
| Prioridade | Must |
| Status | Proposto |
| Responsável | A definir |
| Origem | História de usuário fornecida pelo solicitante |

## Requisito

> Quando **o usuário solicitar a visualização da estrutura do banco de dados**, o **sistema** deve **apresentar os esquemas, tabelas, colunas, tipos de dados, chaves e relacionamentos que o usuário está autorizado a consultar**, garantindo **uma representação fiel e somente leitura da organização atual do banco**.

## Por quê

Conhecer a estrutura ajuda o usuário a compreender quais dados existem e como eles se relacionam. Sem essa visão, torna-se mais difícil formular perguntas adequadas, interpretar os resultados e verificar se as consultas utilizam as fontes corretas.

## Critérios de aceite

- [ ] **Sucesso** — Dado um banco acessível com estruturas autorizadas, quando o usuário solicitar sua visualização, então o sistema deve apresentar os esquemas, tabelas, colunas, tipos de dados, chaves e relacionamentos disponíveis.
- [ ] **Fronteira** — Dado um banco sem tabelas ou sem estruturas visíveis para o usuário, quando a visualização for aberta, então o sistema deve apresentar um estado vazio sem indicar dados inexistentes.
- [ ] **Falha** — Dada a indisponibilidade do banco ou uma falha na leitura dos metadados, quando o usuário solicitar a estrutura, então o sistema deve informar que ela não pôde ser carregada e não deve apresentar informações inventadas como se fossem atuais.

## Regras e limites

- **Entradas/dados**: metadados autorizados do banco, incluindo esquemas, tabelas, colunas, tipos de dados, chaves e relacionamentos.
- **Invariantes**: a visualização não pode modificar a estrutura do banco; somente objetos autorizados para o usuário podem ser apresentados.
- **Exceções/fallback**: se parte dos metadados não puder ser carregada, o sistema deve identificar claramente que a visualização está incompleta.
- **Fora do escopo**: criação, alteração ou exclusão de esquemas, tabelas, colunas, chaves e relacionamentos.

## Verificação e rastreabilidade

- **Método**: teste funcional, inspeção e teste de integração.
- **Evidência esperada**: comparação entre os metadados autorizados do banco e os elementos apresentados ao usuário.
- **Objetivo/spec**: A definir.
- **Tarefa/teste**: A definir.

## Questões abertas

- [ ] Definir se a estrutura será apresentada como árvore, diagrama, lista ou combinação dessas opções — **Responsável**: Produto — **Prazo**: A definir.
- [ ] Definir quais metadados técnicos adicionais poderão ser exibidos, como índices, restrições e descrições — **Responsável**: Arquitetura — **Prazo**: A definir.
- [ ] Definir como as permissões limitarão os objetos visíveis para cada usuário — **Responsável**: Segurança — **Prazo**: A definir.

## Histórico

| Versão | Data | Alteração | Autor |
|---|---|---|---|
| 0.1 | 2026-09-27 | Criação | Codex |

## Revisão rápida

- [x] Há somente uma obrigação principal.
- [x] Condição, comportamento e resultado são observáveis.
- [x] Critérios cobrem sucesso, limite e falha.
- [ ] Prioridade, origem, verificação e vínculo com a spec estão preenchidos.
