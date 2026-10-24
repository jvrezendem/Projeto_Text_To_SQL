package com.txttosql.project.dto;

import java.util.List;
import java.util.Map;

public record PerguntaPadraoDto(
    String id,
    String texto,
    String sqlReferencia,
    Map<String, List<Map<String, Object>>> gabarito
) {

}
