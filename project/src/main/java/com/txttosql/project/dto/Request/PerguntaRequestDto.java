package com.txttosql.project.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PerguntaRequestDto(
        @NotBlank(message = "Pergunta não pode ser vazia")
        @Pattern(regexp = "^(?!.*(?i:\\b(drop|delete|update|insert|alter|truncate|create)\\b)).+$", message = "Pergunta inválida") 
        String pergunta) {

}
