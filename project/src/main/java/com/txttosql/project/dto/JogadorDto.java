package com.txttosql.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import com.txttosql.project.database.models.Time;


@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter
@ToString 
@Builder 
public class JogadorDto {
    
    private String nome;
    private LocalDate dataNascimento;
    private String posicao;
    private String nacionalidade;
    private String nomeTime;
}
