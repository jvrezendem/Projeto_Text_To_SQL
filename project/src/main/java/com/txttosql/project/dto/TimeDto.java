package com.txttosql.project.dto;

import java.time.LocalDate;

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
public class TimeDto {
    
    private String nome;
    private String cidade;
    private String estado;
    private String pais;
    private LocalDate fundacao;
}
