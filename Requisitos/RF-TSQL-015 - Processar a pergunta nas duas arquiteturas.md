# [RF-TSQL-015] - Processar a pergunta nas duas arquiteturas

## Requisito

> Quando **o usuário enviar uma pergunta válida para comparação**, o **sistema** deve **processar a mesma pergunta nas duas arquiteturas de Text-to-SQL, LLM sem contexto e LLM com contexto do banco de dados, e retornar separadamente a resposta de cada uma**, garantindo **que todos os resultados estejam associados à mesma pergunta, à mesma versão dos dados e à arquitetura que os produziu**.

## Por quê

O processamento obrigatório pelas duas arquiteturas permite ao usuário comparar diretamente as abordagens e avaliar diferenças de interpretação e acurácia. Sem a execução conjunta, seria necessário repetir manualmente a pergunta e controlar as condições de cada execução.

## Regras e limites

- **Entradas/dados**: pergunta válida do usuário, mesma versão do banco de dados, configurações do LLM para as duas arquiteturas e metadados do banco fornecidos somente à arquitetura com contexto.
- **Invariantes**: as duas arquiteturas devem receber a mesma pergunta e consultar a mesma fotografia dos dados; cada resultado deve conservar a identificação de sua arquitetura; resultados não podem ser combinados, substituídos ou atribuídos a outra arquitetura; toda consulta deve respeitar [[RF-TSQL-009 - Permitir somente consultas SELECT]]; perguntas vazias devem respeitar [[RF-TSQL-002 - Ignorar pergunta vazia]].
- **Exceções/fallback**: a falha de uma arquitetura não deve cancelar automaticamente a resposta já concluída pela outra; a interface deve distinguir resultado vazio, erro e processamento pendente.
- **Fora do escopo**: permitir que o usuário selecione somente uma arquitetura, escolher automaticamente a melhor resposta e consolidar os dois resultados em uma resposta única.

## Verificação e rastreabilidade

- **Método**: teste funcional, teste de integração e teste de contrato.
- **Evidência esperada**: teste demonstrando uma única pergunta encaminhada às duas arquiteturas e uma resposta contendo dois resultados identificados; testes adicionais com resultado vazio e falha isolada de uma arquitetura.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-003 - Fornecer contexto do banco de dados ao LLM]], [[RF-TSQL-004 - Gerar consulta com LLM sem contexto do banco de dados]], [[RF-TSQL-012 - Enviar dados para a interface gráfica]] e [[RF-TSQL-014 - Exibir o resultado obtido e o gabarito]].
- **Tarefa/teste**: A definir.
