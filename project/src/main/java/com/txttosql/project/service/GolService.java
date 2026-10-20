package com.txttosql.project.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.txttosql.project.database.models.Gol;
import com.txttosql.project.database.repository.IGolRepository;
import com.txttosql.project.dto.GolDto;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class GolService {

    private final IGolRepository golRepository;

    public void deleteGolById(UUID id) {
        golRepository.deleteById(id);
    }

    public Gol getGolById(UUID id) {
        return golRepository.findById(id).orElse(null);
    }

    public List<Gol> getAllGols() {
        return golRepository.findAll();
    }

    public void createGol(GolDto golDto) {
        golRepository.save(Gol.builder()
                .minuto(golDto.getMinuto())
                .jogador(jogadorService.findByNome(golDto.getNomeJogador()))
                .partida(partidaService.findById(golDto.getIdPartida()))
                .build());
    }


}
