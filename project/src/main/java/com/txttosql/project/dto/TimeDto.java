package com.txttosql.project.dto;

import java.time.LocalDate;

public record TimeDto(String nome, String cidade, String estado, String pais, LocalDate fundacao) {
}
