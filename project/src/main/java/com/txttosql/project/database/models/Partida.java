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
@Table(name = "partida")
@AllArgsConstructor
@NoArgsConstructor
@Getter 
@Setter
public class Partida {

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;

    @Column (nullable = false, length = 100)
    private LocalDate data;

    @ManyToOne 
    @JoinColumn (name = "campeonato_id", nullable = false)
    private Campeonato campeonato;

    @ManyToOne 
    @JoinColumn(name = "time_casa_id", nullable = false)
    private Time timeCasa;

    @ManyToOne
    @JoinColumn(name = "time_visitante_id", nullable = false)
    private Time timeVisitante;

    @OneToMany(mappedBy = "partida")
    private Set<Gol> gols;
}
