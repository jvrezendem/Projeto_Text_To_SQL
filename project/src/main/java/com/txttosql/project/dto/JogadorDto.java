package com.txttosql.project.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record JogadorDto(
        @NotBlank(message = "Nome não pode ser vazio") String nome,

        @NotNull(message = "Data de nascimento não pode ser vazio") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Data de nascimento deve estar no formato yyyy-MM-dd") LocalDate dataNascimento,

        @NotBlank(message = "Posição não pode ser vazia") String posicao,

        @NotBlank(message = "Nacionalidade não pode ser vazia") String nacionalidade,

        @NotBlank(message = "Nome do time não pode ser vazio") String nomeTime) {
}
