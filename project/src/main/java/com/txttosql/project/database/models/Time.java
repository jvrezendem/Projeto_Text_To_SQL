package com.txttosql.project.database.models;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "time")
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
public class Time {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "pais", nullable = false, length = 100)
    private String pais;

    @Column(name = "data_fundacao", nullable = false)
    private LocalDateTime dataFundacao;   
    
    @OneToMany(mappedBy = "time")
    private Set<Jogador> jogadores;
}
