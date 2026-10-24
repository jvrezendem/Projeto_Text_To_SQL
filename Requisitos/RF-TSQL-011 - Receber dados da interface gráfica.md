# [RF-TSQL-011] - Receber dados da interface gráfica

## Requisito

> Quando **a interface gráfica enviar dados ou uma ação do usuário**, o **sistema** deve **receber, interpretar e validar a mensagem antes de encaminhá-la ao fluxo correspondente**, garantindo **que somente dados bem-formados e permitidos sejam processados**.

## Por quê

A interface gráfica é o ponto de entrada das perguntas, escolhas e solicitações do usuário. Sem um contrato de recebimento e validação, mensagens incompletas ou malformadas podem causar resultados incorretos, falhas internas ou acesso indevido a operações do sistema.

## Regras e limites

- **Entradas/dados**: perguntas do usuário, solicitações de visualização, identificadores e demais parâmetros definidos no contrato da interface.
- **Invariantes**: todos os dados recebidos da interface devem ser tratados como não confiáveis até sua validação; perguntas vazias devem respeitar [[RF-TSQL-002 - Ignorar pergunta vazia]]; toda pergunta válida deve ser encaminhada ao fluxo definido em [[RF-TSQL-015 - Processar a pergunta nas duas arquiteturas]].
- **Exceções/fallback**: mensagens inválidas devem ser rejeitadas antes de chamar o LLM em qualquer uma das duas arquiteturas ou acessar o PostgreSQL.
- **Fora do escopo**: definição visual dos componentes da interface, autenticação do usuário e armazenamento permanente do estado da tela.

## Verificação e rastreabilidade

- **Método**: teste de contrato, teste funcional e teste de segurança.
- **Evidência esperada**: testes com mensagens válidas, vazias, incompletas, malformadas, de tipo desconhecido e contendo valores inesperados.
- **Objetivo/spec**: [[RF-TSQL-001 - Consultar dados em linguagem natural]], [[RF-TSQL-015 - Processar a pergunta nas duas arquiteturas]] e [[RF-TSQL-005 - Visualizar a estrutura do banco de dados]].
- **Tarefa/teste**: A definir.
