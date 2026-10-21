package com.txttosql.project.dto.Response;

import jakarta.validation.constraints.NotNull;

public record RespostaResponseDto(@NotNull String pergunta, @NotNull String resposta) {

}
