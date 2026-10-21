package com.txttosql.project.dto;

import java.time.LocalDate;

public record JogadorDto(String nome, LocalDate dataNascimento, String posicao, String nacionalidade, String nomeTime) {
}
