# Tecnologias Utilizadas

## Visão geral

O projeto **Text To SQL** será desenvolvido como uma API REST em Java. A aplicação receberá perguntas em linguagem natural, processará cada pergunta por meio das três arquiteturas de Text-to-SQL definidas para o projeto e retornará os resultados para a interface gráfica.

## Tecnologias

| Tecnologia | Utilização no projeto |
|---|---|
| **Java** | Linguagem principal para o desenvolvimento da aplicação, das regras de negócio e das integrações. |
| **Spring Boot** | Criação, configuração e execução da aplicação, incluindo o gerenciamento das dependências e dos componentes. |
| **Spring Web** | Implementação dos endpoints HTTP da API REST e comunicação com a interface gráfica. |
| **Spring Data** | Acesso e persistência dos dados no banco de dados PostgreSQL por meio de repositórios. |
| **API Groq** | Acesso aos modelos de linguagem utilizados pelas arquiteturas de tradução direta e de tradução com contexto do banco de dados. A arquitetura baseada em templates e expressões regulares não utilizará essa API. |

## Arquitetura da API

A aplicação utilizará a arquitetura **REST** para disponibilizar seus recursos por meio de endpoints HTTP. Os dados de entrada e saída serão representados em JSON.

A API será responsável por:

- receber a pergunta enviada pela interface gráfica;
- validar a entrada recebida;
- encaminhar a pergunta para as três arquiteturas de Text-to-SQL;
- integrar as arquiteturas que utilizam LLM com a API Groq;
- validar as consultas SQL geradas, permitindo somente operações de leitura;
- executar as consultas autorizadas no PostgreSQL;
- retornar separadamente os resultados das três arquiteturas, o gabarito e os dados do comparativo de acertos;
- informar erros de validação ou de processamento de maneira padronizada.

## Organização de pastas — MVC

O código será organizado seguindo uma estrutura de pastas baseada em **MVC**, com separação entre a entrada da API, as regras de negócio e o acesso aos dados.

```text
src/main/java/<pacote-base>/
├── controller/       # Endpoints REST e recebimento das requisições
├── service/          # Regras de negócio e coordenação dos casos de uso
├── model/            # Entidades e objetos do domínio
├── repository/       # Acesso ao banco de dados com Spring Data
├── dto/              # Objetos de entrada e saída da API
├── mapper/           # Conversão entre entidades e DTOs
├── client/           # Integração HTTP com a API Groq
├── texttosql/        # Implementações das três arquiteturas de Text-to-SQL
│   ├── direct/       # Tradução direta com LLM
│   ├── contextual/   # Tradução com LLM e contexto do banco de dados
│   └── template/     # Templates de escrita e expressões regulares, sem LLM
├── validation/       # Validação das perguntas e das consultas SQL
├── config/           # Configurações da aplicação e das integrações
└── exception/        # Tratamento e representação padronizada de erros
```

### Responsabilidades das camadas principais

- **Controller:** recebe as requisições da interface gráfica, valida o formato básico dos dados e devolve as respostas HTTP.
- **Service:** aplica as regras de negócio e coordena o processamento das perguntas pelas três arquiteturas.
- **Model:** representa as entidades do domínio, como time, jogador, campeonato, partida e gol.
- **Repository:** realiza o acesso aos dados armazenados no PostgreSQL.
- **DTO:** define os contratos de entrada e saída da API sem expor diretamente as entidades do banco de dados.

## Diretrizes técnicas

- As credenciais da API Groq e do PostgreSQL não devem ser armazenadas diretamente no código-fonte.
- Toda consulta SQL deve ser validada antes da execução no banco de dados.
- Somente consultas de leitura autorizadas devem alcançar o PostgreSQL.
- A arquitetura de templates deve gerar as consultas por meio de templates de escrita e/ou expressões regulares, sem utilizar LLM e sem recorrer à API Groq como alternativa.
- Cada uma das três arquiteturas deve produzir um resultado identificado separadamente na resposta da API.
- Os erros retornados à interface gráfica devem possuir formato consistente e não devem expor informações sensíveis da aplicação.

## Relação com os requisitos

Este documento apoia principalmente os requisitos de processamento das perguntas, integração com a interface gráfica, conexão com o PostgreSQL, integração com LLM, validação das consultas SQL e comparação dos resultados das três arquiteturas.
