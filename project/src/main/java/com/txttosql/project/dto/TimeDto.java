package com.txttosql.project.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record TimeDto(
        @NotBlank(message = "Nome do time não pode ser vazio") String nome,

        @NotBlank(message = "Cidade não pode ser vazia") String cidade,

        @NotBlank(message = "País não pode ser vazio") String pais,

        @NotNull(message = "Data de fundação não pode ser vazia") @Past(message = "Data de fundação deve ser uma data no passado") LocalDate fundacao) {
}
