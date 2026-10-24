# [RF-TSQL-002] - Ignorar pergunta vazia

## Requisito

> Quando **o usuário tentar enviar uma pergunta vazia, nula ou composta somente por espaços**, o **sistema** deve **interromper o fluxo sem gerar prompts, chamar o LLM ou acessar o banco de dados**, garantindo **que nenhuma das duas arquiteturas processe uma entrada sem conteúdo válido**.

## Por quê

Entradas vazias não contêm intenção de consulta e consumiriam recursos sem produzir um resultado útil. Interromper o fluxo evita o acionamento desnecessário das duas arquiteturas e do banco de dados.

## Regras e limites

- **Entradas/dados**: conteúdo informado no campo de pergunta.
- **Invariantes**: geradores de prompt, LLM e banco de dados não podem ser acionados por nenhuma das duas arquiteturas para entradas sem conteúdo após a remoção de espaços.
- **Exceções/fallback**: entradas que contenham somente caracteres de espaçamento devem ser tratadas como vazias.
- **Fora do escopo**: validação semântica de perguntas preenchidas, mas sem relação com os dados disponíveis.

## Verificação e rastreabilidade

- **Método**: teste funcional automatizado.
- **Evidência esperada**: testes com valor vazio, nulo e somente espaços comprovando a ausência de chamadas às dependências.
- **Objetivo/spec**: A definir.
- **Tarefa/teste**: A definir.
