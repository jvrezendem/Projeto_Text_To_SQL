package com.txttosql.project.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.txttosql.project.dto.Request.PerguntaRequestDto;
import com.txttosql.project.dto.Response.RespostaResponseDto;

@Service
public class GroqAiService {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;
    @Value("${spring.ai.openai.base-url}")
    private String apiUrl;
    @Value("${spring.ai.openai.chat.model}")
    private String model;

    private final ChatClient chatClient;

    public GroqAiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public RespostaResponseDto fazerPergunta(PerguntaRequestDto perguntaDto) {
        return new RespostaResponseDto(perguntaDto.pergunta(), chatClient
                .prompt()
                .user(perguntaDto.pergunta())
                .call()
                .content());
    }
}
