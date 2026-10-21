package com.txttosql.project.dto;

import java.time.LocalDate;

public record PartidaDto(LocalDate data, String timeCasaNome, String timeVisitanteNome, String campeonatoNome,
                int golCasa, int golVisitante) {
}
