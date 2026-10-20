package com.txttosql.project.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.txttosql.project.database.models.Partida;
import com.txttosql.project.database.repository.IPartidaRepository;
import com.txttosql.project.dto.PartidaDto;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PartidaService {

    private final IPartidaRepository partidaRepository;

    public void createPartida(PartidaDto partidaDto) {
        partidaRepository.save(Partida.builder()
                .data(partidaDto.getData())
                .gols(partidaDto.getGols())
                .timeCasa(timeService.findByNome(partidaDto.getTimeCasaNome()))
                .timeVisitante(timeService.findByNome(partidaDto.getTimeVisitanteNome()))
                .campeonato(campeonatoService.findByNome(partidaDto.getCampeonatoNome()))
                .build());
    }

    public void deletePartidaById(UUID id) {
        partidaRepository.deleteById(id);
    }

    public Partida getPartidaById(UUID id) {
        return partidaRepository.findById(id).orElse(null);
    }

    public List<Partida> getAllPartidas() {
        return partidaRepository.findAll();
    }


}
