package com.txttosql.project.service;

import java.util.List;
import java.util.Set;
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
    private final JogadorService jogadorService;
    private final PartidaService partidaService;

    public void deleteGolById(UUID id) {
        golRepository.deleteById(id);
    }

    public Gol getGolById(UUID id) {
        return golRepository.findById(id).orElse(null);
    }

    public List<GolDto> getAllGols() {
        List<Gol> listaGols = golRepository.findAll();
        List<GolDto> listaGolsDto = new java.util.ArrayList<>();

        for (Gol gol : listaGols) {
            GolDto golDto = new GolDto(
                    gol.getJogador().getNome(),
                    gol.getPartida().getId(),
                    gol.getPartida().getCampeonato().getNome(),
                    gol.getPartida().getTimeCasa().getNome(),
                    gol.getMinuto());
            listaGolsDto.add(golDto);
        }
        return listaGolsDto;
    }

    public void createGol(GolDto golDto) {
        golRepository.save(Gol.builder()
                .minuto(golDto.minuto())
                .jogador(jogadorService.findByNome(golDto.jogadorNome()))
                .partida(partidaService.getPartidaById(golDto.partidaId()))
                .build());
    }

    public Set<Gol> getGolsByPartidaId(UUID partidaId) {
        return golRepository.findByPartidaId(partidaId);
    }

}
