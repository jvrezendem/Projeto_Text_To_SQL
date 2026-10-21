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
    private final TimeService timeService;
    private final CampeonatoService campeonatoService;

    public void createPartida(PartidaDto partidaDto) {
        partidaRepository.save(Partida.builder()
                .data(partidaDto.data())
                .golCasa(partidaDto.golCasa())
                .golVisitante(partidaDto.golVisitante())
                .timeCasa(timeService.findByNome(partidaDto.timeCasaNome()))
                .timeVisitante(timeService.findByNome(partidaDto.timeVisitanteNome()))
                .campeonato(campeonatoService.findByNome(partidaDto.campeonatoNome()))
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
