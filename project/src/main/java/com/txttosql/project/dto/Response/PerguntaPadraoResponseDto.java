package com.txttosql.project.dto.Response;

import java.util.List;
import java.util.Map;

public record PerguntaPadraoResponseDto(
    String pergunta,
    String sqlReferencia,
    Map<String, List<Map<String, Object>>> gabarito,
    String sqlNoContext,
    List<Map<String, Object>> registrosNoContext,
    String sqlContext,
    List<Map<String, Object>> registrosContext,
    String statusNoContext,
    String statusContext
) {

}
