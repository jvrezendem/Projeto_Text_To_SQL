package com.txttosql.project.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.txttosql.project.database.models.Campeonato;
import com.txttosql.project.database.repository.ICampeonatoRepository;
import com.txttosql.project.dto.CampeonatoDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampeonatoService {

    private final ICampeonatoRepository campeonatoRepository;

    public void createCampeonato(CampeonatoDto campeonatoDto) {
        campeonatoRepository.save(Campeonato.builder()
                .nome(campeonatoDto.nome())
                .temporada(campeonatoDto.temporada())
                .pais(campeonatoDto.pais())
                .build());
    }

    public void deleteCampeonatoById(UUID id) {
        campeonatoRepository.deleteById(id);
    }

    public Campeonato getCampeonatoById(UUID id) {
        return campeonatoRepository.findById(id).orElse(null);
    }

    public List<Campeonato> getAllCampeonatos() {
        return campeonatoRepository.findAll();
    }

    public Campeonato findByNome(String nome) {
        return campeonatoRepository.findByNome(nome);
    }

}
