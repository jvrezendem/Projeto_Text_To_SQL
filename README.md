# Projeto Text To SQL

Projeto para consultar dados de um banco PostgreSQL por meio de perguntas em linguagem natural, transformadas em consultas SQL de leitura.

## Objetivo

Permitir que o usuário escreva uma pergunta sobre os dados e receba os registros correspondentes sem precisar escrever SQL manualmente. Antes da execução, toda consulta gerada deve ser validada para garantir que somente operações de leitura permitidas cheguem ao banco de dados.

## Tecnologias utilizadas

| Tecnologia | Versão/configuração | Utilização |
|---|---|---|
| Java | 21 | Linguagem e plataforma da aplicação. |
| Spring Boot | 4.1.1 | Inicialização, configuração e execução do servidor. |
| Spring Web MVC | Incluído pelo Spring Boot | Endpoints HTTP/REST e entrega dos arquivos da interface. |
| Spring Data JPA | Incluído pelo Spring Boot | Persistência e acesso ao PostgreSQL por meio de JPA. |
| PostgreSQL | Versão definida pelo ambiente | Banco relacional da aplicação. |
| Flyway | Incluído pelo Spring Boot | Criação da estrutura e carga de dados pelas migrations em `src/main/resources/db/migration`. |
| Spring AI | 2.0.1 | Integração com o modelo de chat por uma API compatível com OpenAI. |
| Groq API | Modelo `openai/gpt-oss-120b` | Geração das consultas SQL nas arquiteturas com e sem contexto. |
| Springdoc OpenAPI | 3.1.0 | Documentação interativa dos endpoints. |
| Lombok | Gerenciado pelo Spring Boot | Redução de código repetitivo em classes Java. |
| HTML, CSS e JavaScript | JavaScript sem framework | Interface gráfica, servida pelo próprio Spring Boot. |
| Maven | Não fixada no projeto | Gerenciamento de dependências, testes e build. |

## Como executar

### Pré-requisitos

- JDK 21 instalado e disponível no `PATH`.
- Maven instalado e disponível no `PATH`.
- Uma instância PostgreSQL acessível.
- Uma chave de API da Groq.

### Configuração

1. Crie um banco PostgreSQL. Por exemplo, no `psql`:

	```sql
	CREATE DATABASE text_to_sql;
	```

2. Na pasta `project`, crie ou ajuste o arquivo `.env` com as configurações abaixo. Altere usuário, senha, host, porta ou nome do banco conforme o seu ambiente:

	```properties
	GROQ_API_KEY=sua_chave_groq
	BD_URL=jdbc:postgresql://localhost:5432/text_to_sql
	BD_USER=seu_usuario_postgres
	BD_PASSWORD=sua_senha_postgres
	PORT=8081
	```

	`PORT` é opcional e, se omitido, a aplicação usa a porta `8081`. O `.env` está listado no `.gitignore`; não versione chaves ou senhas.

### Inicialização

No terminal, execute:

```bash
cd project
mvn spring-boot:run
```

Na primeira inicialização, o Flyway aplica as migrations e carrega os dados de referência do domínio de futebol. A interface fica disponível em [http://localhost:8081](http://localhost:8081) (ou na porta configurada em `PORT`). A documentação interativa da API fica em `http://localhost:8081/swagger-ui/index.html`.

Para executar os testes e gerar o pacote:

```bash
mvn test
mvn clean package
```

O JAR gerado pode ser iniciado, a partir da pasta `project`, com:

```bash
java -jar target/project-0.0.1-SNAPSHOT.jar
```

## Arquiteturas de geração

O projeto utiliza duas arquiteturas de Text-to-SQL, comparadas sobre a mesma pergunta:

1. **LLM sem contexto do banco de dados:** envia a pergunta e as restrições de geração à API Groq, sem incluir a estrutura do banco no prompt.
2. **LLM com contexto do banco de dados:** envia a pergunta e as mesmas restrições à API Groq, incluindo a estrutura do banco obtida pela aplicação.

As duas abordagens estão implementadas em `GroqAiService`, nos métodos `txtToSqlNoContext` e `txtToSqlContext`. O fluxo de perguntas padrão executa ambas e mantém seus resultados separados.

## Funcionalidades previstas

- Receber perguntas em linguagem natural pela interface gráfica.
- Processar cada pergunta nas duas arquiteturas e manter os resultados separados.
- Compor o prompt com a pergunta e as restrições em ambas as arquiteturas, incluindo o contexto autorizado somente na arquitetura com contexto do banco de dados.
- Gerar consultas SQL do tipo `SELECT`.
- Bloquear comandos SQL não permitidos antes do acesso ao banco.
- Conectar-se a um banco PostgreSQL em modo somente leitura.
- Exibir a estrutura autorizada do banco de dados.
- Exibir os registros autorizados retornados pelas consultas.
- Exibir os dois resultados obtidos e o gabarito da pergunta.
- Exibir um comparativo do número de acertos das duas arquiteturas.
- Enviar resultados, estados e erros seguros para a interface gráfica.

## Requisitos

Os requisitos detalhados estão na pasta [`Requisitos`](./Requisitos):

- [RF-TSQL-001 — Consultar dados em linguagem natural](./Requisitos/RF-TSQL-001%20-%20Consultar%20dados%20em%20linguagem%20natural.md)
- [RF-TSQL-002 — Ignorar pergunta vazia](./Requisitos/RF-TSQL-002%20-%20Ignorar%20pergunta%20vazia.md)
- [RF-TSQL-003 — Fornecer contexto do banco de dados ao LLM](./Requisitos/RF-TSQL-003%20-%20Fornecer%20contexto%20do%20banco%20de%20dados%20ao%20LLM.md)
- [RF-TSQL-004 — Gerar consulta com LLM sem contexto do banco de dados](./Requisitos/RF-TSQL-004%20-%20Gerar%20consulta%20com%20LLM%20sem%20contexto%20do%20banco%20de%20dados.md)
- [RF-TSQL-005 — Visualizar a estrutura do banco de dados](./Requisitos/RF-TSQL-005%20-%20Visualizar%20a%20estrutura%20do%20banco%20de%20dados.md)
- [RF-TSQL-006 — Visualizar os registros do banco de dados](./Requisitos/RF-TSQL-006%20-%20Visualizar%20os%20registros%20do%20banco%20de%20dados.md)
- [RF-TSQL-007 — Compor o prompt com a pergunta e as restrições](./Requisitos/RF-TSQL-007%20-%20Compor%20o%20prompt%20com%20a%20pergunta%20e%20as%20restrições.md)
- [RF-TSQL-008 — Conectar ao banco de dados PostgreSQL](./Requisitos/RF-TSQL-008%20-%20Conectar%20ao%20banco%20de%20dados%20PostgreSQL.md)
- [RF-TSQL-009 — Permitir somente consultas SELECT](./Requisitos/RF-TSQL-009%20-%20Permitir%20somente%20consultas%20SELECT.md)
- [RF-TSQL-011 — Receber dados da interface gráfica](./Requisitos/RF-TSQL-011%20-%20Receber%20dados%20da%20interface%20gráfica.md)
- [RF-TSQL-012 — Enviar dados para a interface gráfica](./Requisitos/RF-TSQL-012%20-%20Enviar%20dados%20para%20a%20interface%20gráfica.md)
- [RF-TSQL-013 — Armazenar dados predefinidos do domínio de futebol](./Requisitos/RF-TSQL-013%20-%20Armazenar%20dados%20predefinidos%20do%20domínio%20de%20futebol.md)
- [RF-TSQL-014 — Exibir os resultados obtidos e o gabarito](./Requisitos/RF-TSQL-014%20-%20Exibir%20o%20resultado%20obtido%20e%20o%20gabarito.md)
- [RF-TSQL-015 — Processar a pergunta nas duas arquiteturas](./Requisitos/RF-TSQL-015%20-%20Processar%20a%20pergunta%20nas%20duas%20arquiteturas.md)
- [RF-TSQL-016 — Exibir comparativo de acertos das duas arquiteturas](./Requisitos/RF-TSQL-016%20-%20Exibir%20comparativo%20de%20acertos%20das%20duas%20arquiteturas.md)
- [RNF-TSQL-001 — Garantir acurácia mínima das duas arquiteturas](./Requisitos/RNF-TSQL-001%20-%20Garantir%20acurácia%20mínima%20das%20duas%20arquiteturas.md)

## Classificação

O projeto utiliza requisitos funcionais (`RF`) e não funcionais (`RNF`). Os requisitos funcionais descrevem os comportamentos observáveis do sistema; os não funcionais estabelecem qualidades mensuráveis, como a acurácia das arquiteturas. 
