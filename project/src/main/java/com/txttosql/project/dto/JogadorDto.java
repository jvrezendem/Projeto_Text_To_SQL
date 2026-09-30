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
public class JogadorDto {
    
    private String nome;
    private String dataNascimento;
    private String posicao;
    private String nacionalidade;
    private String timeNome;
}
