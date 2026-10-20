package com.txttosql.project.dto;

import java.time.LocalDate;
import java.util.Set;

import com.txttosql.project.database.models.Gol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter
@ToString 
@Builder 
public class PartidaDto {
    
    private LocalDate data;
    private String timeCasaNome;
    private String timeVisitanteNome;
    private String campeonatoNome;
    private Set<Gol> gols;
}
