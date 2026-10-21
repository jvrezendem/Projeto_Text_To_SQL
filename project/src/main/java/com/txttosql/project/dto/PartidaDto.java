package com.txttosql.project.dto;

import java.time.LocalDate;
import java.util.Set;

import com.txttosql.project.database.models.Gol;

public record PartidaDto(LocalDate data, String timeCasaNome, String timeVisitanteNome, String campeonatoNome,
        Set<Gol> gols) {
}
