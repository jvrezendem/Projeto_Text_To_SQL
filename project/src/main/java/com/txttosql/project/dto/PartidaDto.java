package com.txttosql.project.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PartidaDto(
        @NotNull(message = "Data não pode ser vazia") LocalDate data,

        @NotBlank(message = "Time da casa não pode ser vazio") String timeCasaNome,

        @NotBlank(message = "Time visitante não pode ser vazio") String timeVisitanteNome,

        @NotBlank(message = "Campeonato não pode ser vazio") String campeonatoNome,

        @NotNull(message = "Gols em casa não pode ser vazio") @PositiveOrZero(message = "Gols em casa deve ser maior ou igual a zero") int golCasa,

        @NotNull(message = "Gols visitantes não pode ser vazio") @PositiveOrZero(message = "Gols visitantes deve ser maior ou igual a zero") int golVisitante) {
}
