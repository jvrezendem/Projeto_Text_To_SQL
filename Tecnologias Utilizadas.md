# Tecnologias Utilizadas

## Visão geral

O projeto **Text To SQL** será desenvolvido como uma API REST em Java. A aplicação receberá perguntas em linguagem natural, processará cada pergunta por meio das duas arquiteturas de Text-to-SQL definidas para o projeto e retornará os resultados para a interface gráfica.

## Tecnologias

| Tecnologia | Utilização no projeto |
|---|---|
| **Java** | Linguagem principal para o desenvolvimento da aplicação, das regras de negócio e das integrações. |
| **Spring Boot** | Criação, configuração e execução da aplicação, incluindo o gerenciamento das dependências e dos componentes. |
| **Spring Web** | Implementação dos endpoints HTTP da API REST e comunicação com a interface gráfica. |
| **Spring Data** | Acesso e persistência dos dados no banco de dados PostgreSQL por meio de repositórios. |
| **API Groq** | Acesso ao LLM utilizado pelas duas arquiteturas: geração sem contexto do banco de dados e geração com contexto do banco de dados. |

## Arquiteturas de Text-to-SQL

O escopo vigente contém duas arquiteturas, implementadas no serviço `GroqAiService`:

| Arquitetura | Implementação atual | Conteúdo enviado ao LLM |
|---|---|---|
| **LLM sem contexto do banco de dados** | `txtToSqlNoContext` | Pergunta do usuário e restrições de geração de SQL. |
| **LLM com contexto do banco de dados** | `txtToSqlContext` | Pergunta do usuário, restrições de geração e estrutura do banco obtida por `DataBseContextService`. |

As duas utilizam o mesmo `ChatClient` e a API Groq. Atualmente, o contexto da arquitetura contextualizada é lido da migration `db/migration/V1__criar_estrutura.sql`. O serviço `QuestoesPadraoService` executa as duas abordagens para a mesma pergunta e organiza os resultados para comparação.

## Arquitetura da API

A aplicação utilizará a arquitetura **REST** para disponibilizar seus recursos por meio de endpoints HTTP. Os dados de entrada e saída serão representados em JSON.

A API será responsável por:

- receber a pergunta enviada pela interface gráfica;
- validar a entrada recebida;
- encaminhar a pergunta para as duas arquiteturas de Text-to-SQL;
- integrar as duas arquiteturas com a API Groq;
- validar as consultas SQL geradas, permitindo somente operações de leitura;
- executar as consultas autorizadas no PostgreSQL;
- retornar separadamente os resultados das duas arquiteturas, o gabarito e os dados do comparativo de acertos;
- informar erros de validação ou de processamento de maneira padronizada.

## Organização de pastas — MVC

O código será organizado seguindo uma estrutura de pastas baseada em **MVC**, com separação entre a entrada da API, as regras de negócio e o acesso aos dados.

```text
src/main/java/com/txttosql/project/
├── controller/       # Endpoints REST e recebimento das requisições
├── service/          # Regras de negócio e implementação das duas arquiteturas
│   ├── GroqAiService.java          # Geração com LLM sem e com contexto do banco
│   ├── DataBseContextService.java # Leitura da estrutura fornecida como contexto
│   ├── QuestoesPadraoService.java # Comparação das duas arquiteturas com o gabarito
│   └── SqlExecutionService.java   # Execução das consultas SQL
├── database/
│   ├── models/       # Entidades do domínio
│   └── repository/   # Acesso ao banco de dados com Spring Data
├── dto/              # Objetos de entrada e saída da API
├── config/           # Configurações da aplicação e das integrações
├── handler/          # Conversão de exceções em respostas HTTP
└── exception/        # Tratamento e representação padronizada de erros
```

### Responsabilidades das camadas principais

- **Controller:** recebe as requisições da interface gráfica, valida o formato básico dos dados e devolve as respostas HTTP.
- **Service:** aplica as regras de negócio e coordena o processamento das perguntas pelas duas arquiteturas.
- **Model:** representa as entidades do domínio, como time, jogador, campeonato, partida e gol.
- **Repository:** realiza o acesso aos dados armazenados no PostgreSQL.
- **DTO:** define os contratos de entrada e saída da API sem expor diretamente as entidades do banco de dados.

## Diretrizes técnicas

- As credenciais da API Groq e do PostgreSQL não devem ser armazenadas diretamente no código-fonte.
- Toda consulta SQL deve ser validada antes da execução no banco de dados.
- Somente consultas de leitura autorizadas devem alcançar o PostgreSQL.
- As duas arquiteturas devem utilizar LLM por meio da API Groq; somente a arquitetura contextualizada deve receber a estrutura do banco no prompt.
- Cada uma das duas arquiteturas deve produzir um resultado identificado separadamente na resposta da API.
- Os erros retornados à interface gráfica devem possuir formato consistente e não devem expor informações sensíveis da aplicação.

## Relação com os requisitos

Este documento apoia principalmente os requisitos de processamento das perguntas, integração com a interface gráfica, conexão com o PostgreSQL, integração com LLM, validação das consultas SQL e comparação dos resultados das duas arquiteturas.
