package com.txttosql.project.service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.txttosql.project.dto.Request.PerguntaRequestDto;
import com.txttosql.project.dto.Response.RespostaResponseDto;
import com.txttosql.project.dto.Response.ResultadoArqResponseDto;
import com.txttosql.project.exception.IaConnectionException;
import com.txttosql.project.exception.IlegalQuestionException;

@Service
public class GroqAiService {

        @Value("${spring.ai.openai.api-key}")
        private String apiKey;
        @Value("${spring.ai.openai.base-url}")
        private String apiUrl;
        @Value("${spring.ai.openai.chat.model}")
        private String model;

        private final ChatClient chatClient;

        private final SqlExecutionService sqlExecutionService;
        private final DataBseContextService dataBseContextService;

        public GroqAiService(ChatClient.Builder builder, SqlExecutionService sqlExecutionService,
                        DataBseContextService dataBseContextService) {
                this.chatClient = builder.build();
                this.sqlExecutionService = sqlExecutionService;
                this.dataBseContextService = dataBseContextService;
        }

        // metodo de teste da ia
        public RespostaResponseDto fazerPergunta(PerguntaRequestDto perguntaDto) {
                return new RespostaResponseDto(perguntaDto.pergunta(), chatClient
                                .prompt()
                                .user(perguntaDto.pergunta())
                                .call()
                                .content());
        }

        public ResultadoArqResponseDto txtToSqlNoContext(PerguntaRequestDto pergunta) {

                String promptNoContext = """
                                Você é um experto em SQL.
                                Sua tarefa é retornar um SQL que responda à pergunta feita pelo usuário.
                                Regras obrigatórias:
                                - gere somente uma consulta SELECT;
                                - nunca gere INSERT, UPDATE, DELETE, DROP, ALTER, CREATE ou TRUNCATE;
                                - não utilize comentários;
                                - não utilize múltiplos comandos;
                                - retorne apenas o SQL;
                                - não utilize Markdown ou blocos de código.
                                """;

                String comandoSql = chatClient
                                .prompt(promptNoContext)
                                .user(pergunta.pergunta())
                                .call()
                                .content();

                try {
                        return new ResultadoArqResponseDto(pergunta.pergunta(), tratamentoSql(comandoSql),
                                        sqlExecutionService.executarSQL(tratamentoSql(comandoSql)));
                } catch (Exception e) {
                        throw new IaConnectionException();
                }
        }

        public ResultadoArqResponseDto txtToSqlContext(PerguntaRequestDto pergunta) {

                String contexto = dataBseContextService.migrationReader();

                String promptNoContext = """
                                Você é um experto em SQL.
                                Sua tarefa é retornar um SQL que responda à pergunta feita pelo usuário.
                                Regras obrigatórias:
                                - gere somente uma consulta SELECT;
                                - nunca gere INSERT, UPDATE, DELETE, DROP, ALTER, CREATE ou TRUNCATE;
                                - não utilize comentários;
                                - não utilize múltiplos comandos;
                                - retorne apenas o SQL;
                                - não utilize Markdown ou blocos de código.
                                - utilize o contexto abaixo para gerar o SQL.

                                contexto: %s
                                """.formatted(contexto);

                String comandoSql = chatClient
                                .prompt(promptNoContext)
                                .user(pergunta.pergunta())
                                .call()
                                .content();

                try {
                        return new ResultadoArqResponseDto(pergunta.pergunta(), tratamentoSql(comandoSql),
                                        sqlExecutionService.executarSQL(tratamentoSql(comandoSql)));
                } catch (Exception e) {
                        throw new IaConnectionException();
                }

        }

        private String tratamentoSql(String sqlNaotratado) {

                String sqlNoSpaces = sqlNaotratado.replace("```sql", "").replace("```", "").strip();

                List<String> comandosIlegais = List.of(
                                "delete",
                                "alter",
                                "drop",
                                "insert",
                                "update",
                                "truncate",
                                "create",
                                "comment",
                                "merge",
                                "grant",
                                "revoke",
                                "copy",
                                "call",
                                "execute");

                if (comandosIlegais.stream().anyMatch(sqlNoSpaces.toLowerCase()::contains)
                                || !sqlNoSpaces.toLowerCase().startsWith("select")) {
                        throw new IlegalQuestionException("Comando SQL ilegal identificado.");
                } else {
                        return sqlNoSpaces;
                }
        }
}
