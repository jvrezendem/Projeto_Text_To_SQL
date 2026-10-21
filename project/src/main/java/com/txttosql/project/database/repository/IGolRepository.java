package com.txttosql.project.database.repository;

import java.util.UUID;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import com.txttosql.project.database.models.Gol;

public interface IGolRepository extends JpaRepository<Gol, UUID> {
    Set<Gol> findByPartidaId(UUID partidaId);
}
