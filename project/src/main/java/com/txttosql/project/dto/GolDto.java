package com.txttosql.project.dto;

import java.util.UUID;

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
public class GolDto {
    
    private String jogadorNome;
    private UUID partidaId;
    private String campeonatoNome;
    private String timeNome;
    private int minuto;
}
