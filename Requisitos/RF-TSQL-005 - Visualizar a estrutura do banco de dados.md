# [RF-TSQL-005] - Visualizar a estrutura do banco de dados

## Requisito

> Quando **o usuário solicitar a visualização da estrutura do banco de dados**, o **sistema** deve **apresentar os esquemas, tabelas, colunas, tipos de dados, chaves e relacionamentos que o usuário está autorizado a consultar**, garantindo **uma representação fiel e somente leitura da organização atual do banco**.

## Por quê

Conhecer a estrutura ajuda o usuário a compreender quais dados existem e como eles se relacionam. Sem essa visão, torna-se mais difícil formular perguntas adequadas, interpretar os resultados e verificar se as consultas utilizam as fontes corretas.

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
