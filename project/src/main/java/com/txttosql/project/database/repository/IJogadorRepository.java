package com.txttosql.project.database.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.txttosql.project.database.models.Jogador;

public interface IJogadorRepository extends JpaRepository<Jogador, UUID>{

}
