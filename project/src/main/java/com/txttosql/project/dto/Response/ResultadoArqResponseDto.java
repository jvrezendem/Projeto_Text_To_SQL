package com.txttosql.project.dto.Response;

import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotNull;

public record ResultadoArqResponseDto(
                @NotNull String pergunta,
                @NotNull String sql,
                @NotNull List<Map<String, Object>> registros_encontrados) {
}
