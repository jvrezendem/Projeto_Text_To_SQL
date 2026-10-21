package com.txttosql.project.dto.Response;

import java.util.Set;

import jakarta.validation.constraints.NotNull;

public record ResultadoArqResponseDto(
        @NotNull String pergunta,
        @NotNull String sql,
        @NotNull Set<String> registros_encontrados,
        @NotNull String resultado) {

}
