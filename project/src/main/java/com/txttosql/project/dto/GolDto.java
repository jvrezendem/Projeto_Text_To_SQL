package com.txttosql.project.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GolDto(
        @NotBlank(message = "Jogador nome não pode ser vazio") String jogadorNome,

        @NotNull(message = "Partida ID não pode ser vazio") UUID partidaId,

        @NotBlank(message = "Campeonato nome não pode ser vazio") String campeonatoNome,

        @NotBlank(message = "Time nome não pode ser vazio") String timeNome,

        @NotNull(message = "Minuto não pode ser vazio") int minuto) {
}
