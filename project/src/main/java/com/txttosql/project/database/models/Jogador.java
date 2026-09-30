package com.txttosql.project.database.models;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jogador")
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;

    @Column( nullable = false, length = 100)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column (nullable = false, length = 50)
    private String posicao;

    @Column (nullable = false, length = 50)
    private String nacionalidade;

    @ManyToOne
    @JoinColumn(name = "time_id", nullable = false)
    private Time time;

    @OneToMany(mappedBy = "jogador")
    private Set<Gol> gols;
}
