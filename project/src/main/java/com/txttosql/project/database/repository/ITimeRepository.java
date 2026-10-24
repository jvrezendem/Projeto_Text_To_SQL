package com.txttosql.project.database.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.txttosql.project.database.models.Time;

public interface ITimeRepository extends JpaRepository<Time, UUID> {

    Time findByNome(String nome);
}
