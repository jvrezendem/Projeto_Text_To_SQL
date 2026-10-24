package com.txttosql.project.dto;

import jakarta.validation.constraints.NotBlank;

public record CampeonatoDto(
        @NotBlank(message = "Nome do campeonato não pode ser vazio") String nome,
        @NotBlank(message = "Temporada não pode ser vazia") String temporada,
        @NotBlank(message = "País não pode ser vazio") String pais) {
}
