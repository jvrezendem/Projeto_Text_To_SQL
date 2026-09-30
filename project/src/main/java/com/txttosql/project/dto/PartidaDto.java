package com.txttosql.project.dto;

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
    
    private String data;
    private String timeCasaNome;
    private String timeVisitanteNome;
    private String campeonatoNome;
}
