package com.txttosql.project.dto.Response;

import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SqlExecutionResponseDto(
        @NotBlank String comandoSql,
        @NotNull List<Map<String, Object>> registros) {

}
