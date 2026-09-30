package com.txttosql.project.database.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.txttosql.project.database.models.Partida;

public interface IPartidaRepository extends JpaRepository<Partida, UUID> {

}
